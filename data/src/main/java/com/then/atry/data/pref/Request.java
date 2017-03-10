package com.then.atry.data.pref;


import net.yslibrary.simplepreferences.annotation.Key;
import net.yslibrary.simplepreferences.annotation.Preferences;

/**
 *
 * @类名: Request
 * @描述:  请求的配置文件
 * @作者 then
 * @创建日期 2017/3/8 8:26
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
