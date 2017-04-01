package com.then.atry.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.kymjs.themvp.ViewListenerManager;
import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.R;
import com.then.atry.activity.BaseActivity;
import com.then.atry.activity.login.LoginActivity;
import com.then.atry.databinding.ActivityMainBinding;
import com.then.atry.fragment.messagehub.MessageHubFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainDelegate, ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private Fragment messageHubFrag;

    private Fragment contactsFrag;

    private Fragment scheduleFrag;

    private ArrayList<Fragment> fragmentArrayList;

    private Fragment mCurrentFrgment;

    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewListenerManager.getInstance().reigester(this);
        initFragment();
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //可以同时对多个控件设置同一个点击事件,后面id参数可以传多个
        viewDelegate.setOnClickListener(this);

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void actionViewModel(View view, BaseViewModel baseViewModel, int actionType) {

    }


    @Override
    protected Class getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initFragment() {
        fragmentArrayList = new ArrayList<>(3);
        messageHubFrag = new MessageHubFragment();
        contactsFrag = new Fragment();
        scheduleFrag = new Fragment();
        fragmentArrayList.add(messageHubFrag);
        fragmentArrayList.add(contactsFrag);
        fragmentArrayList.add(scheduleFrag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //抽屉
        if (item.getGroupId() == R.id.drawer_nav) {
            changeDrawerNav(item);
        }
        //底部导航
        else if (item.getGroupId() == R.id.bottom_nav) {
            changeBottomNav(item);
        }
        return true;
    }


    private void changeDrawerNav(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
    }


    private void changeBottomNav(MenuItem item) {
        int position = 0;

        int id = item.getItemId();
        //底部
        if (id == R.id.bottom_nav_message) {
            position = 0;
        } else if (id == R.id.bottom_nav_music) {
            position = 1;
        } else if (id == R.id.bottom_nav_schedules) {
            position = 2;
        }

        ft = getSupportFragmentManager().beginTransaction();

        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentArrayList.get(position).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = fragmentArrayList.get(position);
        }
        mCurrentFrgment = fragment;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(binding.appBarMain.contentMain.content.getId(), fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewListenerManager.getInstance().unreigester(this);
    }

}
