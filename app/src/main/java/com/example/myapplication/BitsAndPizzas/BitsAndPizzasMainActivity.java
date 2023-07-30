package com.example.myapplication.BitsAndPizzas;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class BitsAndPizzasMainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wrapper.setOrientation(LinearLayout.VERTICAL);

        // ViewPager
        ViewPager2 viewPager = new ViewPager2(this);
        ViewPager.LayoutParams viewPagerLP = new ViewPager.LayoutParams();
        viewPagerLP.width = ViewGroup.LayoutParams.MATCH_PARENT;
        viewPagerLP.height = ViewGroup.LayoutParams.MATCH_PARENT;
        viewPager.setLayoutParams(viewPagerLP);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewPager.setId(View.generateViewId());
        }

        viewPager.setAdapter(homePagerAdapter);
        wrapper.addView(viewPager);

        setContentView(wrapper);
    }
}
