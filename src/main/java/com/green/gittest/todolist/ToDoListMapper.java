package com.green.gittest.todolist;

import com.green.gittest.todolist.model.PostDoToListReq;
import com.green.gittest.todolist.model.PostTodoListPicDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToDoListMapper {


    int postToDoList(PostDoToListReq p);
    int postToDoListPics(PostTodoListPicDto p);

//    List<FeedGetRes> getFeed(FeedGetReq p);
//    List<String> getFeedPicsByFeedId(long feedId);
//    List<FeedCommentGetRes> getFeedCommentTopBy4ByFeedId(long feedId);

}
