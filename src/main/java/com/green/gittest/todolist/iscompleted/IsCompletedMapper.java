package com.green.gittest.todolist.iscompleted;

import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListRes;
import com.green.gittest.todolist.iscompleted.model.ToggleIsCompletedReq;
import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListReq;
import com.green.gittest.todolist.iscompleted.model.SelIsCompletedRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IsCompletedMapper {
    SelIsCompletedRes SelIsCompleted(ToggleIsCompletedReq p);
    int patchDelIsCompleted(ToggleIsCompletedReq p);
    int patchInsIsCompleted(ToggleIsCompletedReq p);
    List<GetIsCompletedListRes> getIsCompletedList(GetIsCompletedListReq p);
}
