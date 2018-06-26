package com.eljebo.serviceprovider.new_bean;

/**
 * Created by samosys on 26/6/18.
 */

public class CountryDialogListDataBean {

    private String id;
    private String sortname;
    private String name;
    private String phonecode;
    private String status;

    public CountryDialogListDataBean(String id, String sortname, String name, String phonecode, String status) {
        this.id = id;
        this.sortname = sortname;
        this.name = name;
        this.phonecode = phonecode;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
