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
import com.then.atry.activity.main.MainActivity;
import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.business.GetFirstOrg;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.data.viewmodel.MessageHubViewModel;
import com.then.atry.data.viewmodel.common.ListViewModel;
import com.then.atry.databinding.MessageHubFragmentBinding;
import com.then.atry.domain.interactor.BusinessUseCase;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.ehome.req.cpf.SysListReq;
import com.then.atry.domain.interactor.ehome.req.oauth.LoginReq;
import com.then.atry.domain.interactor.subscriber.DefaultSubscriber;
import com.then.atry.domain.model.ehome.Icon;
import com.then.atry.domain.model.ehome.Oauth;
import com.then.atry.domain.model.ehome.Sys;
import com.then.atry.domain.model.sample.User;
import com.then.atry.fragment.BaseFragment;

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

    @Inject
    @Named("oauthCase")
    UseCase oauthCase;

    @Inject
    @Named("cpfCase")
    UseCase cpfCase;

    @Inject
    BusinessUseCase businessUseCase;

    @Inject
    EhomeReqHelper ehomeReqHelper;

    @Inject
    IPreferencesPrefs preferencesPrefs;


    private ObservableList items;

    public MessageHubFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewListenerManager.getInstance().reigester(this);
      /*  PluginManagerHelper.installPlugin("/sdcard/chat.apk");
        Collection<PluginDescriptor> pluginDescriptors = PluginManagerHelper.getPlugins();
        Log.d("MessageHubFragment", "pluginDescriptors.size():" + pluginDescriptors.size());*/
        ((MainActivity) getActivity()).getUserComponent().inject(this);
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
        useCase.execute(new GetUserListSubscriber());

        LoginReq loginReq = new LoginReq();
        loginReq.setLoginName("18650725014");
        loginReq.setPwd("12345678");


//        oauthCase.execute(loginReq, new LoginSubscriber(), new OauthInfoSaveAction(preferencesPrefs));

        SysListReq sysListReq = new SysListReq();

//        cpfCase.execute(sysListReq, new SysListSubscriber());

        businessUseCase.execute(new GetFirstOrg(), new OrgListSubscriber());

        binding.setListViewModel(listViewModel);

        return binding;
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


    private static class GetUserListSubscriber extends DefaultSubscriber<List<User>> {
        @Override
        public void onNext(List<User> users) {
            Log.d("GetUserListSubscriber", "users.size():" + users.size());
        }
    }

    private static class LoginSubscriber extends DefaultSubscriber<Oauth> {

        @Override
        public void onNext(Oauth oauth) {
            Log.d("LoginSubscriber", "oauth.getAccessToken():" + oauth.getAccessToken());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

    }

    private static class SysListSubscriber extends DefaultSubscriber<List<Sys>> {
        @Override
        public void onNext(List<Sys> syses) {
            Log.d("SysListSubscriber", "syses.size()" + syses.size());
        }
    }

    private static class OrgListSubscriber extends DefaultSubscriber<Icon> {
        @Override
        public void onNext(Icon icon) {
            Log.d("OrgListSubscriber", "iconName:" + icon.getIconName());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }
    }


}
