package com.then.atry.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.activity.BaseActivity;

/**
 * Created by then on 2017/3/9.
 */

public class LoginActivity extends BaseActivity {

    private  static  String INTENT_KEY_NAME="INTENT_KEY_NAME";


    public  static  void  start(Context context,String name){
        Intent intent=new Intent(context, LoginActivity.class);
        intent.putExtra(INTENT_KEY_NAME,"kdjf");
        context.startActivity(intent);
    }

    @Override
    public void actionViewModel(View view, BaseViewModel baseViewModel, int actionType) {

    }

    @Override
    protected Class getDelegateClass() {
        return null;
    }
}
