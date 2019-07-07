package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.PoemsPerCategory;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;
import com.shahar91.poems.ui.base.list.BaseViewHolder;

import butterknife.BindView;

public class PoemsPerCategoryListViewHolder extends BaseViewHolder<PoemsPerCategory, BaseInteractionListener> {
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.writerTv)
    TextView writerTv;

    public PoemsPerCategoryListViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(int position, PoemsPerCategory item, BaseInteractionListener baseInteractionListener) {
        PoemsPerCategoryListInteractionListener listener = (PoemsPerCategoryListInteractionListener) baseInteractionListener;

        titleTv.setText(item.getTitle());
        writerTv.setText(item.getWriter());

        itemView.setOnClickListener(view -> listener.onPoemClicked(item.getId()));
    }
}
