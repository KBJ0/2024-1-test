package com.green.gittest.todolist;

import com.green.gittest.todolist.model.GetToDoListRes;
import com.green.gittest.todolist.model.PostToDoListReq;
import com.green.gittest.todolist.model.PostTodoListPicDto;
import com.green.gittest.todolist.model.UpdateToDoListReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToDoListMapper {

    int postToDoList(PostToDoListReq p);
    int postToDoListPics(PostTodoListPicDto p);

    List<GetToDoListRes> getToDoListByUserIdForRead(int userId);
    List<GetToDoListRes> getFavoriteToDoListByUserIdForRead(int userId);
    List<String> getContentImageByListId(long listId);

    int updateToDoList(UpdateToDoListReq p);
    UpdateToDoListReq getToDoListByUserIdForUpdate(long userId, long listId);

    int deleteToDoList(Integer listId);
    int deleteToDoListPics(Integer listId);




//    List<FeedGetRes> getFeed(FeedGetReq p);
//    List<String> getFeedPicsByFeedId(long feedId);
//    List<FeedCommentGetRes> getFeedCommentTopBy4ByFeedId(long feedId);

}
