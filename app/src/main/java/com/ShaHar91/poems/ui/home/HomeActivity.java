package com.shahar91.poems.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.New_Poem;
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity;
import com.shahar91.poems.ui.home.categories.CategoryFragment;

import io.realm.Realm;

public class HomeActivity extends BaseGoogleMobileActivity<HomeViewModel, HomeComponent> {
    private static final String TAG_CATEGORIES = "TagCategories";

    CategoryFragment categoryFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected HomeComponent createComponent() {
        return DaggerHomeComponent.builder()
                .applicationComponent(getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String json = Realm.getDefaultInstance().where(New_Poem.class).findAll().asJSON();

        component.inject(this);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);

        showCategoryFragment(savedInstanceState);
    }

    private void showCategoryFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            categoryFragment = CategoryFragment.newInstance(false);
            replaceFragment(R.id.flHomeContainer, categoryFragment, TAG_CATEGORIES, false);
        } else {
            categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_CATEGORIES);
        }
    }
}