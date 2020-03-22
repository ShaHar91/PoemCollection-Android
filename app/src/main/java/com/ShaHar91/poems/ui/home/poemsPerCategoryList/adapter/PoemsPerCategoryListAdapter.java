package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.ui.base.list.BaseAdapter;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;
import com.shahar91.poems.ui.base.list.BaseViewHolder;

import butterknife.BindView;

public class PoemsPerCategoryListAdapter extends BaseAdapter<Poem, BaseInteractionListener, BaseViewHolder<Poem, BaseInteractionListener>> {
    private final PoemsPerCategoryListInteractionListener listener;

    public PoemsPerCategoryListAdapter(Context context, PoemsPerCategoryListInteractionListener listener) {
        super(context);

        this.listener = listener;
    }

    @NonNull
    @Override
    public PoemsPerCategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoemsPerCategoryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_poem_per_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Poem, BaseInteractionListener> holder, int position) {
        holder.bind(position, getItem(position), listener);
    }

    static class PoemsPerCategoryListViewHolder extends BaseViewHolder<Poem, BaseInteractionListener> {
        @BindView(R.id.titleTv)
        TextView titleTv;
        @BindView(R.id.writerTv)
        TextView writerTv;

        PoemsPerCategoryListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(int position, Poem item, BaseInteractionListener baseInteractionListener) {
            PoemsPerCategoryListInteractionListener listener = (PoemsPerCategoryListInteractionListener) baseInteractionListener;

            titleTv.setText(item.getTitle());
            writerTv.setText(item.getWriter());

            itemView.setOnClickListener(view -> listener.onPoemClicked(item.getId()));
        }
    }
}
