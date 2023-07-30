package com.example.myapplication.BitsAndPizzas;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class HomePagerAdapter extends FragmentStateAdapter {
    private static final int SIZE = 4;

    public HomePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new PastaFragment();
    }

    @Override
    public int getItemCount() {
        return SIZE;
    }
}
