package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.ui.base.list.BaseAdapter;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;
import com.shahar91.poems.ui.base.list.BaseViewHolder;

public class PoemsPerCategoryListAdapter extends BaseAdapter<Poem, BaseInteractionListener, BaseViewHolder<Poem, BaseInteractionListener>> {
    private AdapterDelegatesManager<Poem> delegatesManager;

    public PoemsPerCategoryListAdapter(Context context, PoemsPerCategoryListInteractionListener listener) {
        super(context);

        //Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new PoemsPerCategoryListDelegate(context, listener));
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (BaseViewHolder) delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Poem, BaseInteractionListener> holder, int position) {
        delegatesManager.onBindViewHolder(getItem(position), position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(getItem(position), position);
    }
}
