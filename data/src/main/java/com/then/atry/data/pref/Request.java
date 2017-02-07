package com.then.atry.data.pref;


import net.yslibrary.simplepreferences.annotation.Key;
import net.yslibrary.simplepreferences.annotation.Preferences;

/**
 * Created by then on 2017/2/7.
 */
@Preferences
public class Request {

    @Key
    protected String cid;

    @Key
    protected String ak;

    @Key
    protected String at;

}
