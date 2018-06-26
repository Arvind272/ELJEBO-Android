package com.eljebo.common.utils;

/**
 * Created by TOXSL\vinay.goyal on 19/6/18.
 */

public class AdapterListener {

    private static AdapterListener onClick = new AdapterListener();
    private CountryAdapterInterface adapterInterface;

    public static AdapterListener getInstance() {
        return onClick;
    }

    public void setAdapterListener(CountryAdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public void onEndList() {
        if (adapterInterface != null) {
            adapterInterface.hitApi();
        }
    }

    public interface CountryAdapterInterface {

        void hitApi();
    }
}
