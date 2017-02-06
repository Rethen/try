package com.then.atry.data;

import android.text.TextUtils;

import com.then.atry.data.preferences.IPreferencesPrefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fjhongda.sdk.ContentCrypt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by then on 2016/12/26.
 */

@Singleton
public class EhomeReqHelper {


    IPreferencesPrefs preferences;

    @Inject
    public EhomeReqHelper(IPreferencesPrefs preferences) {
        this.preferences = preferences;
    }

    private static Pattern p = Pattern.compile("\t|\r|\n");

    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public String createHeader(String sn, Object obj) {
        String ct = gson.toJson(obj);
        String header = processHeaderInfo(ct, sn, false);
        return header;
    }

    public RequestBody createRequestBody(Object obj) {

        String content = contentEncryption(gson.toJson(obj), false);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), content.getBytes());
        return requestBody;
    }


    public String processHeaderInfo(String ct, String sn, boolean isSec) {
        String header = "";
        String sec = isSec == true ? "T" : "F";

        if (TextUtils.isEmpty(preferences.getCid())) {
            preferences.setCid("FB5DC0F5D2624A398493276A1651CAF1");
        }

        String cid = preferences.getCid();

        String ak = preferences.getAk();

        String at = preferences.getAt();


        try {
            List<String> paramsList = new ArrayList<>();
            paramsList.add(cid);
            paramsList.add(ak);
            if (sn != null) {
                paramsList.add(sn);
            }
            paramsList.add(ct);
            paramsList.add(sec);

            String sign = null;
            if (!TextUtils.isEmpty(at)) {
                sign = createSign(paramsList, at);
                sign = sign.toUpperCase();
            }

            StringBuffer reqParam = new StringBuffer();
            reqParam.append(cid).append(";");

            if (!TextUtils.isEmpty(ak)) {
                reqParam.append(ak).append(";");
            }

            if (!TextUtils.isEmpty(at)) {
                reqParam.append(sign).append(";");
                reqParam.append(sec).append(";");
            }

            if (sn != null) {
                reqParam.append(sn).append(";");
            }
            header = reqParam.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return header;
    }


    public String createSign(List<String> paramList, String accessToken) {
        try {

            Collections.sort(paramList);

            StringBuilder sb = new StringBuilder();

            for (String item : paramList) {
                sb.append(item);
            }

            String paramsStr = accessToken + replace_ENTER_NEWLINE_TAB_Symbol(sb.toString()) + accessToken;

            return hmacSha1(paramsStr, accessToken);
            // return HmacMD5.hmacMd5( paramsStr.getBytes( "UTF-8" ),
            // accessToken.getBytes( "UTF-8" ) );
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }


    public String hmacSha1(String base, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secret;
        try {
            secret = new SecretKeySpec(key.getBytes("UTF-8"), "HmacMD5");
            // Mac mac = Mac.getInstance( type );
            Mac mac = Mac.getInstance(secret.getAlgorithm());
            mac.init(secret);

            byte[] digest = mac.doFinal(base.getBytes("UTF-8"));

            return bytesToHexString(digest);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    private String replace_ENTER_NEWLINE_TAB_Symbol(String content) {

        if (content == null) {
            return null;
        } else {
            Matcher m = p.matcher(content);

            return m.replaceAll("");
        }
    }

    public String contentEncryption(String ct, boolean isSec) {
        String content = "";
        String at = preferences.getAt();
        try {
            if (isSec) {
                content += ContentCrypt.encode(ct, at);
            } else {
                content += URLEncoder.encode(ct, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String contentDecryption(String ct, boolean isSec) {
        String content = "";
        String at = preferences.getAt();
        try {
            if (isSec) {
                if (at == null) {
                    at = ct.toString();
                }
                content = ContentCrypt.decode(ct.toString(), at);
            } else {
                content = new String(URLDecoder.decode(ct.toString(), "UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

}
