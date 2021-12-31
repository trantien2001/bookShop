package com.example.shopbansach.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//import com.example.shopbansach.fragment.LoaiSachFragment;
import com.example.shopbansach.fragment.SachFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SachFragment();
            case 1:
                return new SachFragment();
            default:
                return new SachFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
