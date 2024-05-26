package com.green.gittest.todolist;

import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.todolist.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToDoListService {
    private final ToDoListMapper mapper;
    private final CustomFileUtils customFileUtils;

    // 기능 : (listId 폴더만들기 + DB에 정보 저장 + 사진파일 저장)
    @Transactional
    public PostToDoListRes postToDoList(List<MultipartFile> pics, PostToDoListReq p) {
        mapper.postToDoList(p); // 글 내용 먼저 작성

        String path = String.format("todolist/%d", p.getListId());
        customFileUtils.makeFolders(path);
        if (pics == null || pics.isEmpty()) {
            return PostToDoListRes.builder().listId(p.getListId()).build();
        }// 사진이 없을 경우 = 폴더만 만들어줌(업뎃용) + 만들고 리턴

        PostTodoListPicDto picDto = PostTodoListPicDto.builder().listId(p.getListId()).build();
        for (MultipartFile pic : pics) {
            String saveFileName = customFileUtils.makeRandomFileName(pic);
            picDto.getFileNames().add(saveFileName);
            String target = String.format("%s/%s", path, saveFileName);
            try {
                customFileUtils.transferTo(pic, target); // 사진 이름 지정 후 파일에 저장
            } catch (IOException e) {
                log.error("파일 전송 중 오류 발생", e);
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
            }
        }
        mapper.postToDoListPics(picDto);
        // 사진 정보 DB 및 폴더에 저장.

        return PostToDoListRes.builder().listId(p.getListId()).contentImages(picDto.getFileNames()).build();
    }

    public List<GetToDoListRes> getToDoList(int userId) {
        List<GetToDoListRes> list = mapper.getToDoListByUserIdForRead(userId);
        if(list == null || list.isEmpty()) {throw new UserNotFoundException();} //없는 유저일 경우 반환
        for(GetToDoListRes res : list){
            List<String> contentImages = mapper.getContentImageByListId(res.getListId());
            res.setContentImages(contentImages);
        }
        return list;
    }

    public int updateToDoList(UpdateToDoListReq p) {
        UpdateToDoListReq result = mapper.getToDoListByUserIdForUpdate(p.getUserId(), p.getListId());
        if(result == null) {throw new UserNotFoundException();}
        BeanUtils.copyProperties(p, result); // result에 p의 정보를 담아줌.
        // null인 부분을 예외 처리하려면 노가다 하거나
        // 그게 싫으면 아래와 같은 class를 만들어서 사용하면됨.

        return mapper.updateToDoList(result);
    }

    public int deleteToDoList(Integer listId) {
        String path = String.format("todolist/%d", listId);
        String absolutePath = customFileUtils.getUploadPath() + File.separator + path; // 절대 경로 만듬.
        customFileUtils.deleteFolder(absolutePath); // 해당 TDL 파일 삭제
        mapper.deleteToDoListPics(listId); // DB todolist_image 정보 삭제
        return mapper.deleteToDoList(listId); // DB todolist 정보 삭제 및 결과 전달.
    }



}

/*
public static void copyNonNullProperties(Object source, Object target) throws IllegalAccessException {
    List<Field> fields = FieldUtils.getAllFieldsList(source.getClass());
    for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(source);
        if (value != null) {
            Field targetField = FieldUtils.getField(target.getClass(), field.getName(), true);
            if (targetField != null) {
                targetField.set(target, value);
            }
        }
    }
}
*/
