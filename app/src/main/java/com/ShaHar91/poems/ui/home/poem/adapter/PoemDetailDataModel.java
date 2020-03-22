package com.shahar91.poems.ui.home.poem.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.ui.base.list.BaseEpoxyHolder;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.list_item_poem_detail)
public abstract class PoemDetailDataModel extends EpoxyModelWithHolder<PoemDetailDataModel.Holder> {
    @EpoxyAttribute
    Poem poem;

    @Override
    public void bind(@NonNull Holder holder) {
        holder.poemTitleTv.setText(poem.getTitle());
        holder.poemTv.setText(poem.getPoem());
        holder.writerTv.setText(poem.getWriter());
    }

    static class Holder extends BaseEpoxyHolder {
        @BindView(R.id.tvPoemTitle)
        TextView poemTitleTv;
        @BindView(R.id.tvPoem)
        TextView poemTv;
        @BindView(R.id.tvWriterPoemDetail)
        TextView writerTv;
    }
}
