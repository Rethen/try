package com.then.atry.activity.main;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.kymjs.themvp.view.AppDelegate;
import com.then.atry.R;
import com.then.atry.databinding.ActivityMainBinding;

/**
 * Created by then on 2016/12/6.
 */

public class MainDelegate extends AppDelegate<ActivityMainBinding> {


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();


        Toolbar toolbar = get(R.id.toolbar);
        getActivity().setSupportActionBar(toolbar);

        DrawerLayout drawer = get(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = get(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) getActivity());

        BottomNavigationView bottomNavigationView = get(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) getActivity());

    }




}
