package com.then.atry.fragment.messagehub;

import com.kymjs.themvp.view.AppDelegate;
import com.then.atry.R;
import com.then.atry.databinding.MessageHubFragmentBinding;

/**
 * Created by then on 2016/12/6.
 */

public class MessageHubDelegate extends AppDelegate<MessageHubFragmentBinding> {

    @Override
    public int getRootLayoutId() {
        return R.layout.message_hub_fragment;
    }

    @Override
    public void initWidget() {
        super.initWidget();
//       viewDataBinding.list.setVisibility(View.GONE);
    }


}
