package com.shahar91.poems.ui.home.poemsPerCategoryList.redux;

import com.shahar91.poems.data.models.Poem;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

import java.util.ArrayList;
import java.util.List;

@AutoReducer
public abstract class PoemsPerCategoryListReducer implements Reducer<PoemsPerCategoryListState> {
    @AutoReducer.InitialState
    PoemsPerCategoryListState initialState() {
        return PoemsPerCategoryListState.builder().setPoemsPerCategoryList(new ArrayList<>()).build();
    }

    @AutoReducer.Action(value = PoemsPerCategoryListActions.RESET, from = PoemsPerCategoryListActions.class)
    PoemsPerCategoryListState reset(PoemsPerCategoryListState state) {
        return initialState();
    }

    @AutoReducer.Action(value = PoemsPerCategoryListActions.SET_POEM_LIST, from = PoemsPerCategoryListActions.class)
    PoemsPerCategoryListState setPoemsPerCategoryList(PoemsPerCategoryListState state, List<Poem> poemsPerCategoryList) {
        return state.toBuilder().setPoemsPerCategoryList(poemsPerCategoryList).build();
    }

    public static PoemsPerCategoryListReducer create() {
        return new PoemsPerCategoryListReducerImpl();
    }
}
