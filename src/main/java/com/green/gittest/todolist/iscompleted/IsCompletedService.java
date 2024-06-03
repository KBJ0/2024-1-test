package com.green.gittest.todolist.iscompleted;

import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListReq;
import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListRes;
import com.green.gittest.todolist.iscompleted.model.SelIsCompletedRes;
import com.green.gittest.todolist.iscompleted.model.ToggleIsCompletedReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IsCompletedService {
    private final IsCompletedMapper mapper;

    public int toggleIsCompleted(ToggleIsCompletedReq p){
        SelIsCompletedRes result = mapper.SelIsCompleted(p);

        if(result.getIsCompleted() == 1) {
            mapper.patchDelIsCompleted(p);
            return 0;
        } else {
            mapper.patchInsIsCompleted(p);
            return 1;
        }
    }

    public List<GetIsCompletedListRes> getIsCompletedList(GetIsCompletedListReq p){
        return mapper.getIsCompletedList(p);
    }
}
