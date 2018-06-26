package com.eljebo.common.socialLogin;

import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONObject;

/**
 * Created by neeraj.narwal on 9/8/16.
 */
public class SocialLogin {

    private SocialLoginListener listener;
    private static final SocialLogin instance = new SocialLogin();


    public void setListener(SocialLoginListener listener) {
        this.listener = listener;
    }

    public static SocialLogin getInstance() {
        return instance;
    }

    public void facebookLoginDone(JSONObject object, GraphResponse response) {
        if (listener != null)
            listener.onFacebookLoginDone(object, response);
    }

    public void gPlusLoginDone(GoogleSignInAccount currentPerson) {
        if (listener != null)
            listener.onGPlusLoginDone(currentPerson);
    }

    public interface SocialLoginListener {
        void onFacebookLoginDone(JSONObject object, GraphResponse response);

        void onGPlusLoginDone(GoogleSignInAccount currentPerson);
    }
}
