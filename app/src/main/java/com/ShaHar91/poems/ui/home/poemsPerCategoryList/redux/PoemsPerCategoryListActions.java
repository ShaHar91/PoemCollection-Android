package com.shahar91.poems.ui.home.poemsPerCategoryList.redux;

import com.shahar91.poems.data.models.PoemsPerCategory;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

import java.util.List;

@ActionCreator
public interface PoemsPerCategoryListActions {
    String RESET = "PoemsPerCategoryList_RESET";
    String SET_POEM_LIST = "PoemsPerCategoryList_SET_POEM_LIST";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SET_POEM_LIST)
    Action setPoemsPerCategoryList(List<PoemsPerCategory> poemsPerCategoryList);
}
