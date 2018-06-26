
package android.databinding;
import com.eljebo.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 17;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.eljebo.R.layout.fragment_service_provider_location:
                    return com.eljebo.databinding.FragmentServiceProviderLocationBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_service_provider:
                    return com.eljebo.databinding.AdapterServiceProviderBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_customer_check_in_timer:
                    return com.eljebo.databinding.FragmentCustomerCheckInTimerBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_terms:
                    return com.eljebo.databinding.FragmentTermsBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_country_dialog:
                    return com.eljebo.databinding.AdapterCountryDialogBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_otp:
                    return com.eljebo.databinding.FragmentOtpBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_service_providers:
                    return com.eljebo.databinding.FragmentServiceProvidersBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_service:
                    return com.eljebo.databinding.AdapterServiceBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_pay:
                    return com.eljebo.databinding.FragmentPayBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_services:
                    return com.eljebo.databinding.AdapterServicesBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_customer_profile:
                    return com.eljebo.databinding.FragmentCustomerProfileBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_customer_check_in:
                    return com.eljebo.databinding.FragmentCustomerCheckInBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_notification_layout:
                    return com.eljebo.databinding.AdapterNotificationLayoutBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_forgot_password:
                    return com.eljebo.databinding.FragmentForgotPasswordBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.services_layout:
                    return com.eljebo.databinding.ServicesLayoutBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_profile:
                    return com.eljebo.databinding.FragmentProfileBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_login:
                    return com.eljebo.databinding.FragmentLoginBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_add_card:
                    return com.eljebo.databinding.FragmentAddCardBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_settings:
                    return com.eljebo.databinding.FragmentSettingsBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_services:
                    return com.eljebo.databinding.FragmentServicesBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_check_in:
                    return com.eljebo.databinding.FragmentCheckInBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_list:
                    return com.eljebo.databinding.FragmentListBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_home:
                    return com.eljebo.databinding.FragmentHomeBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_child_service:
                    return com.eljebo.databinding.AdapterChildServiceBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.layout_info_window:
                    return com.eljebo.databinding.LayoutInfoWindowBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.security_questions_layout:
                    return com.eljebo.databinding.SecurityQuestionsLayoutBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_customer_signup:
                    return com.eljebo.databinding.FragmentCustomerSignupBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_change_password:
                    return com.eljebo.databinding.FragmentChangePasswordBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_service_provider_detail:
                    return com.eljebo.databinding.FragmentServiceProviderDetailBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.dialog_country:
                    return com.eljebo.databinding.DialogCountryBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_group_service:
                    return com.eljebo.databinding.AdapterGroupServiceBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_personal_info:
                    return com.eljebo.databinding.FragmentPersonalInfoBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_customer_home:
                    return com.eljebo.databinding.FragmentCustomerHomeBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_service_provider_signup:
                    return com.eljebo.databinding.FragmentServiceProviderSignupBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_payment:
                    return com.eljebo.databinding.FragmentPaymentBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.service_detail_layout:
                    return com.eljebo.databinding.ServiceDetailLayoutBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_service_user_detail:
                    return com.eljebo.databinding.FragmentServiceUserDetailBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.adapter_custom_camera_images:
                    return com.eljebo.databinding.AdapterCustomCameraImagesBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_feedback:
                    return com.eljebo.databinding.FragmentFeedbackBinding.bind(view, bindingComponent);
                case com.eljebo.R.layout.fragment_select_role:
                    return com.eljebo.databinding.FragmentSelectRoleBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1806289664: {
                if(tag.equals("layout/fragment_service_provider_location_0")) {
                    return com.eljebo.R.layout.fragment_service_provider_location;
                }
                break;
            }
            case 1199730209: {
                if(tag.equals("layout/adapter_service_provider_0")) {
                    return com.eljebo.R.layout.adapter_service_provider;
                }
                break;
            }
            case 798063178: {
                if(tag.equals("layout/fragment_customer_check_in_timer_0")) {
                    return com.eljebo.R.layout.fragment_customer_check_in_timer;
                }
                break;
            }
            case 1542621390: {
                if(tag.equals("layout/fragment_terms_0")) {
                    return com.eljebo.R.layout.fragment_terms;
                }
                break;
            }
            case -130094153: {
                if(tag.equals("layout/adapter_country_dialog_0")) {
                    return com.eljebo.R.layout.adapter_country_dialog;
                }
                break;
            }
            case -445025550: {
                if(tag.equals("layout/fragment_otp_0")) {
                    return com.eljebo.R.layout.fragment_otp;
                }
                break;
            }
            case 1716455839: {
                if(tag.equals("layout/fragment_service_providers_0")) {
                    return com.eljebo.R.layout.fragment_service_providers;
                }
                break;
            }
            case 778881169: {
                if(tag.equals("layout/adapter_service_0")) {
                    return com.eljebo.R.layout.adapter_service;
                }
                break;
            }
            case -444659409: {
                if(tag.equals("layout/fragment_pay_0")) {
                    return com.eljebo.R.layout.fragment_pay;
                }
                break;
            }
            case -1624466812: {
                if(tag.equals("layout/adapter_services_0")) {
                    return com.eljebo.R.layout.adapter_services;
                }
                break;
            }
            case -775880477: {
                if(tag.equals("layout/fragment_customer_profile_0")) {
                    return com.eljebo.R.layout.fragment_customer_profile;
                }
                break;
            }
            case -1993464892: {
                if(tag.equals("layout/fragment_customer_check_in_0")) {
                    return com.eljebo.R.layout.fragment_customer_check_in;
                }
                break;
            }
            case -130257894: {
                if(tag.equals("layout/adapter_notification_layout_0")) {
                    return com.eljebo.R.layout.adapter_notification_layout;
                }
                break;
            }
            case -1976631298: {
                if(tag.equals("layout/fragment_forgot_password_0")) {
                    return com.eljebo.R.layout.fragment_forgot_password;
                }
                break;
            }
            case -103721097: {
                if(tag.equals("layout/services_layout_0")) {
                    return com.eljebo.R.layout.services_layout;
                }
                break;
            }
            case 1940278000: {
                if(tag.equals("layout/fragment_profile_0")) {
                    return com.eljebo.R.layout.fragment_profile;
                }
                break;
            }
            case -986431952: {
                if(tag.equals("layout/fragment_login_0")) {
                    return com.eljebo.R.layout.fragment_login;
                }
                break;
            }
            case -1588805879: {
                if(tag.equals("layout/fragment_add_card_0")) {
                    return com.eljebo.R.layout.fragment_add_card;
                }
                break;
            }
            case 1117800958: {
                if(tag.equals("layout/fragment_settings_0")) {
                    return com.eljebo.R.layout.fragment_settings;
                }
                break;
            }
            case -603030663: {
                if(tag.equals("layout/fragment_services_0")) {
                    return com.eljebo.R.layout.fragment_services;
                }
                break;
            }
            case 603069271: {
                if(tag.equals("layout/fragment_check_in_0")) {
                    return com.eljebo.R.layout.fragment_check_in;
                }
                break;
            }
            case -1006825287: {
                if(tag.equals("layout/fragment_list_0")) {
                    return com.eljebo.R.layout.fragment_list;
                }
                break;
            }
            case -1115993926: {
                if(tag.equals("layout/fragment_home_0")) {
                    return com.eljebo.R.layout.fragment_home;
                }
                break;
            }
            case 512395502: {
                if(tag.equals("layout/adapter_child_service_0")) {
                    return com.eljebo.R.layout.adapter_child_service;
                }
                break;
            }
            case 456027522: {
                if(tag.equals("layout/layout_info_window_0")) {
                    return com.eljebo.R.layout.layout_info_window;
                }
                break;
            }
            case -1795336953: {
                if(tag.equals("layout/security_questions_layout_0")) {
                    return com.eljebo.R.layout.security_questions_layout;
                }
                break;
            }
            case -1897035808: {
                if(tag.equals("layout/fragment_customer_signup_0")) {
                    return com.eljebo.R.layout.fragment_customer_signup;
                }
                break;
            }
            case -1154357935: {
                if(tag.equals("layout/fragment_change_password_0")) {
                    return com.eljebo.R.layout.fragment_change_password;
                }
                break;
            }
            case 1398078236: {
                if(tag.equals("layout/fragment_service_provider_detail_0")) {
                    return com.eljebo.R.layout.fragment_service_provider_detail;
                }
                break;
            }
            case -345107499: {
                if(tag.equals("layout/dialog_country_0")) {
                    return com.eljebo.R.layout.dialog_country;
                }
                break;
            }
            case 799763089: {
                if(tag.equals("layout/adapter_group_service_0")) {
                    return com.eljebo.R.layout.adapter_group_service;
                }
                break;
            }
            case 1181093652: {
                if(tag.equals("layout/fragment_personal_info_0")) {
                    return com.eljebo.R.layout.fragment_personal_info;
                }
                break;
            }
            case -1799450713: {
                if(tag.equals("layout/fragment_customer_home_0")) {
                    return com.eljebo.R.layout.fragment_customer_home;
                }
                break;
            }
            case 665665059: {
                if(tag.equals("layout/fragment_service_provider_signup_0")) {
                    return com.eljebo.R.layout.fragment_service_provider_signup;
                }
                break;
            }
            case -1435807731: {
                if(tag.equals("layout/fragment_payment_0")) {
                    return com.eljebo.R.layout.fragment_payment;
                }
                break;
            }
            case 1541321498: {
                if(tag.equals("layout/service_detail_layout_0")) {
                    return com.eljebo.R.layout.service_detail_layout;
                }
                break;
            }
            case 1420054658: {
                if(tag.equals("layout/fragment_service_user_detail_0")) {
                    return com.eljebo.R.layout.fragment_service_user_detail;
                }
                break;
            }
            case -453988950: {
                if(tag.equals("layout/adapter_custom_camera_images_0")) {
                    return com.eljebo.R.layout.adapter_custom_camera_images;
                }
                break;
            }
            case 1772431584: {
                if(tag.equals("layout/fragment_feedback_0")) {
                    return com.eljebo.R.layout.fragment_feedback;
                }
                break;
            }
            case -1319936992: {
                if(tag.equals("layout/fragment_select_role_0")) {
                    return com.eljebo.R.layout.fragment_select_role;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}