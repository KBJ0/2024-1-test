package com.green.gittest.todolist;

import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.todolist.model.PostDoToListReq;
import com.green.gittest.todolist.model.PostToDoListRes;
import com.green.gittest.todolist.model.PostTodoListPicDto;
import com.green.gittest.user.model.SignUpPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToDoListService {
    private final ToDoListMapper mapper;
    private final CustomFileUtils customFileUtils;

    // 기능 : (listId 폴더만들기 + DB에 정보 저장 + 사진파일 저장)
    public PostToDoListRes postToDoList(List<MultipartFile> pics, PostDoToListReq p) {
        mapper.postToDoList(p); // 글 내용 먼저 작성

        String path = String.format("todolist/%d", p.getListId());
        customFileUtils.makeFolders(path);
        if(pics == null || pics.isEmpty()){
            return PostToDoListRes.builder().listId(p.getListId()).build();
        }// 사진이 없을 경우 = 폴더만 만들어줌(업뎃용) + 만들고 리턴

        PostTodoListPicDto picDto = PostTodoListPicDto.builder().listId(p.getListId()).build();
        for(MultipartFile pic : pics) {
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


}
