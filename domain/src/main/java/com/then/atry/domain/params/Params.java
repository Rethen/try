package com.then.atry.domain.params;

import com.google.gson.reflect.TypeToken;

/**
 * Created by then on 2017/2/8.
 */

public interface Params<T> {

    int  OAUTH=0;

    int CPF=1;

    int WF=2;

    TypeToken<T> takeTypeToken();

    String takeSn();

    int takeService();


}
