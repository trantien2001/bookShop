package com.example.shopbansach.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.shopbansach.R;
import com.example.shopbansach.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivityAdmin extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private ViewPagerAdapter mainViewPagerAdapter;

    LinearLayout ln_home,ln_tk,ln_search;
    private Toolbar toolbarMain;
    private DrawerLayout drawerLayout;
    private LinearLayout lnTimKiem,lnTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        AnhXa();
        TLayout();
        ActionBar();
        OnClickMenu();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void TLayout() {
        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Kệ sách ");
                        break;
                    case 1:
                        tab.setText("Sách");
                        break;
                }
            }
        }).attach();
    }

    private void OnClickMenu() {
        ln_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TimKiemSachAd.class);
                startActivity(intent);
            }
        });
        ln_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivityAdmin.class);
                startActivity(intent);
            }
        });
        ln_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TaiKhoanADActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        ln_home = findViewById(R.id.ln_home_ad);
        ln_tk = findViewById(R.id.ln_tk_ad);
        ln_search = findViewById(R.id.ln_search_ad);
        mTabLayout = findViewById(R.id.tabLayoutMain);
        mViewPager2 = findViewById(R.id.viewPagerMain);
        mainViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager2.setAdapter(mainViewPagerAdapter);
        toolbarMain = findViewById(R.id.toolBarMain);
        drawerLayout = findViewById(R.id.drawerLayoutMain);
        lnTimKiem = findViewById(R.id.ln_search);
        lnTaiKhoan = findViewById(R.id.ln_tk);
    }
}