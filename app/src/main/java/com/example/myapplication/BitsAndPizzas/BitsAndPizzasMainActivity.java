package com.example.myapplication.BitsAndPizzas;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

public class BitsAndPizzasMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wrapper.setOrientation(LinearLayout.VERTICAL);

        // ViewPager
        ViewPager viewPager = new ViewPager(this);
        ViewPager.LayoutParams viewPagerLP = new ViewPager.LayoutParams();
        viewPagerLP.width = ViewGroup.LayoutParams.MATCH_PARENT;
        viewPagerLP.height = ViewGroup.LayoutParams.MATCH_PARENT;
        viewPager.setLayoutParams(viewPagerLP);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewPager.setId(View.generateViewId());
        }
        homePagerAdapter.addFragment(new HomeFragment(), "Home");
        homePagerAdapter.addFragment(new PizzasFragment(), "Pizzas");
        homePagerAdapter.addFragment(new PastaFragment(), "Pasta");
        viewPager.setAdapter(homePagerAdapter);
        wrapper.addView(viewPager);

        viewPager.setCurrentItem(2);
        setContentView(wrapper);
    }
}
