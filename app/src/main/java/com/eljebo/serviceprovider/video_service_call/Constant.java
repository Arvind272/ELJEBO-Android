package com.eljebo.serviceprovider.video_service_call;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by samosys on 5/9/17.
 */

public class Constant {


    public static Boolean front                     = true;

    public static final String secret_key           = "NZw6GtBo78oguGvunzCyaw934JCuzvtbsrvT17pg";
    public static final String api_key              = "C6E9AE641D98EB4F9F4433F33A9EDE7F56315CF7458101D14DF424885B08350F";

    public static String BaseURL                    = "http://103.15.67.77/celebidt/celebidt/webservice/main/";
    public static String NodeBaseURL                = "http://103.15.67.77:8000/";

    // With BaseURL
    public static String LoginMethod                = "login";
    public static String RegistrationMethod         = "register";
    public static String EmailVarification          = "verify_email";
    public static String MY_PREFS_NAME              = "MyPref";
    public static String SAVE_PROMOVIDEO            = "savePromoVdo";
    public static String Celeb_PROMOVIDEO_HISTORY   = "celbs_promoVdoHistory";
    public static String Promo_VideoLIST            = "getPromoVdo_list";
    public static String AddPaymentInfo             = "add_paymentInfo";
    public static String GetUserData                = "getUserData";
    public static String GetFanData                 = "getFanData";
    public static String GetWinnerDetail            = "winner_details";
    public static String Update_profile             = "update_profile";
    public static String SaveVideoChat              = "saveVideoChatDetails";
    public static String getFanSavedVideoChat       = "getFanSavedVideoChat";
    public static String updateRecording_saveStatus = "updateVideoRecording_saveStatus";
    public static String updateCelebrityWallet      = "updateCelebrityWallet";
    public static String celbs_winnerDetail         = "celbs_winnerDetail";
    public static String fAq                        = "fAq";
    public static String withDrawProcess            = "withDrawProcess";
    public static String saveBankInfo               = "saveBankInfo";
    public static String sendLoveCrad               = "sendLoveCrad";
    public static String getFanSavedVideoChatList   = "getFanSavedVideoChatList";
    public static String setVideo_AvailabilityStatus= "setVideo_AvailabilityStatus";
    public static String celebrity_homePage         = "celebrity_homePage";
    public static String withDrawRequest_List       = "withDrawRequest_List";
    public static String walletTransactionInfo      = "walletTransactionInfo";
    public static String getBankDetail              = "getBankDetail";




    // With NodeBaseURL
    public static String GetBiddings                = "bidding_page";
    public static String AddBid                     = "add_bid";
    public static String GetUserChat                = "getUserChat";
    public static String ReturnWinner               = "returnWinner";
    public static String SharePromoVideo            = "updatePromoVdo_share";
    public static String GetTwilioToken             = "getTwilioToken";


    public static void alertDialogShow(Context context, String message)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
