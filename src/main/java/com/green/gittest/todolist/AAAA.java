//package com.green.gittest.todolist;
//
//package com.green.greengram.feed;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class AAAA {
//    private final FeedMapper mapper;
//    private final CustomFileUtils customFileUtils;
//
//    @Transactional
//    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) { //feed pk값 리턴
//        int result = mapper.postFeed(p);
//
//        FeedPicPostDto picDto = FeedPicPostDto.builder().feedId(p.getFeedId()).build();
//        //사진 담을 공간.
//
//            String path = String.format("feed/%d", p.getFeedId());
//            customFileUtils.makeFolders(path);
//            // 폴더 만들어 준다. 피드 아이디에 대한
//
//            for(MultipartFile pic : pics) {
//                String saveFileName = customFileUtils.makeRandomFileName(pic);
//                picDto.getFileNames().add(saveFileName);
//                String target = String.format("%s/%s", path, saveFileName);
//                customFileUtils.transferTo(pic, target);
//            }
//            int affectedRowsPics = mapper.postFeedPics(picDto);
//
//        return FeedPostRes.builder()
//                .feedId(p.getFeedId())
//                .pics(picDto.getFileNames())
//                .build();
//    }
//
//    public List<FeedGetRes> getFeed(FeedGetReq p) {
//        List<FeedGetRes> list = mapper.getFeed(p);
//        for(FeedGetRes res : list) {
//            //피드 하나당 포함된
//            //사진 리스트
//            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
//            res.setPics(pics);
//
//            //댓글 리스트
//            List<FeedCommentGetRes> comments = mapper.getFeedCommentTopBy4ByFeedId(res.getFeedId());
//            if(comments.size() == GlobalConst.COMMENT_SIZE_PER_FEED) {
//                res.setIsMoreComment(1);
//                comments.remove(comments.size() - 1);
//            }
//            res.setComments(comments);
//        }
//        return list;
//    }
//}