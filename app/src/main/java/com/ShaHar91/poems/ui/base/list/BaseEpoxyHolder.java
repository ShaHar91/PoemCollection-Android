package com.shahar91.poems.ui.base.list;

import android.view.View;

import androidx.annotation.CallSuper;

import com.airbnb.epoxy.EpoxyHolder;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;

public abstract class BaseEpoxyHolder extends EpoxyHolder {
    @CallSuper
    @Override
    protected void bindView(@NotNull View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
