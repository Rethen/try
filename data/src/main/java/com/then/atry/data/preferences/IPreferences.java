package com.then.atry.data.preferences;


import net.yslibrary.simplepreferences.annotation.Key;
import net.yslibrary.simplepreferences.annotation.Preferences;

/**
 * Created by then on 2016/12/26.
 */

@Preferences
public class IPreferences {


    @Key
    protected String ak;

    @Key
    protected String at;

    @Key
    protected String cid;


}
