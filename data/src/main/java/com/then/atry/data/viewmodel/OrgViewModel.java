package com.then.atry.data.viewmodel;

import com.kymjs.themvp.viewmodel.BaseViewModel;

/**
 * Created by then on 2017/1/16.
 */

public class OrgViewModel extends BaseViewModel {


    private String sysId;

    private String orgName;

    private String orgShort;

    private String orgId;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgShort() {
        return orgShort;
    }

    public void setOrgShort(String orgShort) {
        this.orgShort = orgShort;
    }
}
