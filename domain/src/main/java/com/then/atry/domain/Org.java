package com.then.atry.domain;

/**
 * Created by then on 2016/4/28.
 */
public class Org {

    private String orgId;// ": "O5VHR88ZBEOPVIIHMGECAOUR23",

    private String orgName;// ": "福建省鸿达电子技术开发有限公司",

    private String orgShort;// ": "福建鸿达"

    /**
     * @return the orgId
     */
    public String getOrgId()
    {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    /**
     * @return the orgName
     */
    public String getOrgName()
    {
        return orgName;
    }

    /**
     * @param orgName
     *            the orgName to set
     */
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    /**
     * @return the orgShort
     */
    public String getOrgShort()
    {
        return orgShort;
    }

    /**
     * @param orgShort
     *            the orgShort to set
     */
    public void setOrgShort(String orgShort)
    {
        this.orgShort = orgShort;
    }


    @Override
    public String toString() {
        return orgShort;
    }

}
