package com.then.atry.fragment.messagehub;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kymjs.themvp.ViewListenerManager;
import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.BR;
import com.then.atry.R;
import com.then.atry.data.action.login.LoginSavePrefsConsumer;
import com.then.atry.databinding.MessageHubFragmentBinding;
import com.then.atry.domain.IconSort;
import com.then.atry.domain.Oauth;
import com.then.atry.domain.Sys;
import com.then.atry.domain.User;
import com.then.atry.domain.interactor.DefaultObserver;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysList;
import com.then.atry.domain.interactor.atom.oauth.AccountLogin;
import com.then.atry.fragment.BaseFragment;
import com.then.atry.internal.di.components.DaggerMessageHubFragmentComponent;
import com.then.atry.internal.di.components.MessageHubFragmentComponent;
import com.then.atry.internal.di.modules.ActivityModule;
import com.then.atry.internal.di.modules.UserModule;
import com.then.atry.viewmodel.ListViewModel;
import com.then.atry.viewmodel.MessageHubViewModel;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by then on 2016/12/6.
 */

public class MessageHubFragment extends BaseFragment<MessageHubDelegate, MessageHubFragmentBinding> {


    @Inject
    @Named("userList")
    UseCase useCase;


//    @Inject
//    @Named("sysInfo")
//    UseCase sysUseCase;


    @Inject
    @Named("sysList")
    UseCase sysListUseCase;

    @Inject
    @Named("login")
    UseCase loginUseCase;

    @Inject
    @Named("iconList")
    UseCase iconListUseCase;

    @Inject
    LoginSavePrefsConsumer loginSaveConsumer;

    private ObservableList items;

    private MessageHubFragmentComponent messageHubFragmentComponent;


    public MessageHubFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewListenerManager.getInstance().reigester(this);
      /*  PluginManagerHelper.installPlugin("/sdcard/chat.apk");
        Collection<PluginDescriptor> pluginDescriptors = PluginManagerHelper.getPlugins();
        Log.d("MessageHubFragment", "pluginDescriptors.size():" + pluginDescriptors.size());*/
        messageHubFragmentComponent = DaggerMessageHubFragmentComponent.builder().userModule(new UserModule()).activityModule(new ActivityModule(getActivity())).applicationComponent(getAppComponment()).build();
        messageHubFragmentComponent.inject(this);


    }


    @Override
    protected Class getDelegateClass() {
        return MessageHubDelegate.class;
    }

    @Override
    protected MessageHubFragmentBinding createBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = super.createBinding(inflater, container, savedInstanceState);
        items = new ObservableArrayList();
        for (int i = 0; i < 100; i++) {
            MessageHubViewModel messageHubViewModel = new MessageHubViewModel();
            messageHubViewModel.setTitle("ldkjgldkfjg" + i);
            items.add(messageHubViewModel);
        }

        ListViewModel listViewModel = new ListViewModel(items, R.layout.message_hub_item, BR.item);

//        useCase.execute(new GetUserListSubscriber(), null);
//
//        sysListUseCase.execute(new GetUserListSubscriber(), GetSysInfo.Params.forSys("kldgkl"));

        final OauthObserver oauthObserver = new OauthObserver();

        final GetSysListObserver getSysListObserver = new GetSysListObserver();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loginUseCase.execute(oauthObserver, AccountLogin.LoginParams.create("18650725014", "12345678"), bindUntilEvent(FragmentEvent.PAUSE));
                sysListUseCase.execute(getSysListObserver, new GetSysList.SysListParams(), bindUntilEvent(FragmentEvent.PAUSE));
            }
        }).start();


        binding.setListViewModel(listViewModel);

        return binding;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("MessageHubFragment", "onPause");
    }

    @Override
    public void actionViewModel(View view, BaseViewModel baseViewModel, int actionType) {
        switch (view.getId()) {
            case R.id.item_message_hub_message_content_container:
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.then.chat");
                startActivityForResult(launchIntent, 3);
                break;

            case R.id.item_message_hub_title:
                MessageHubViewModel messageHubViewModel = (MessageHubViewModel) baseViewModel;
                Log.d("MessageHubFragment", messageHubViewModel.getTitle());
                messageHubViewModel.setTitle("河河");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewListenerManager.getInstance().unreigester(this);
    }


    private class GetIconListObserver extends DefaultObserver<List<IconSort>> {
        @Override
        public void onNext(List<IconSort> iconSorts) {
            for (IconSort iconSort : iconSorts) {
                Log.d("GetIconListObserver", "image:"+iconSort.getImage());
            }
        }

        @Override
        public void onError(Throwable exception) {
           exception.printStackTrace();
        }
    }

    private class GetUserListSubscriber extends DefaultObserver<List<User>> {
        @Override
        public void onNext(List<User> users) {
            Log.d("GetUserListSubscriber", "users.size():" + users.size());
        }
    }

    private class GetSysListObserver extends DefaultObserver<List<Sys>> {
        @Override
        public void onNext(List<Sys> syses) {
            final GetIconListObserver getIconListObserver = new GetIconListObserver();
            iconListUseCase.execute(getIconListObserver, syses.get(0).getSysId());
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
        }
    }

    private class OauthObserver extends DefaultObserver<Oauth> {
        @Override
        public void onNext(Oauth result) {
            Log.d("OauthObserver", "accessToken:" + result.getAccessToken());
            MessageHubViewModel messageHubViewModel = (MessageHubViewModel) items.get(0);
            messageHubViewModel.setTitle(result.getAccessToken());
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
        }
    }


}
