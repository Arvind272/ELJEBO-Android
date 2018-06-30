package com.eljebo.customer.cus_new_bean;

/**
 * Created by samosys on 29/6/18.
 */

public class ServiceProviderListBean {

    private String user_id;
    private String name;
    private String profile_pic;

    public ServiceProviderListBean(String user_id, String name, String profile_pic) {
        this.user_id = user_id;
        this.name = name;
        this.profile_pic = profile_pic;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
