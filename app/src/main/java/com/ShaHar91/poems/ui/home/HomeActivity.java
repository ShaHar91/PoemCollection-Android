package com.shahar91.poems.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.R;
import com.shahar91.poems.ui.add.AddPoemActivity;
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity;
import com.shahar91.poems.ui.entry.EntryActivity;
import com.shahar91.poems.ui.home.categories.CategoryFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseGoogleMobileActivity<HomeViewModel, HomeComponent> {
    private static final String TAG_CATEGORIES = "TagCategories";

    @OnClick(R.id.fabAddPoem)
    void fabAddPoemClicked() {
        //TODO: check if user is logged in, if is logged in ==> startAddPoem()
//        if (isLoggedIn) {
//            startAddPoem();
//        } else {
        // start the EntryActivity to make sure the user gets logged in
        startActivityForResult(EntryActivity.startWithIntent(this), 100);
//        }
    }

    /**
     * This may only be called when the user successfully logged in or already was logged in
     */
    private void startAddPoem() {
        startActivityForResult(AddPoemActivity.startWithIntent(this), 101);
    }

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
        ButterKnife.bind(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                startAddPoem();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}