package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCustomerSignupBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(25);
        sIncludes.setIncludes(1, 
            new String[] {"security_questions_layout"},
            new int[] {2},
            new int[] {R.layout.security_questions_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.firstNameET, 3);
        sViewsWithIds.put(R.id.lastNameET, 4);
        sViewsWithIds.put(R.id.emailET, 5);
        sViewsWithIds.put(R.id.confirmEmailET, 6);
        sViewsWithIds.put(R.id.userNameET, 7);
        sViewsWithIds.put(R.id.passwordET, 8);
        sViewsWithIds.put(R.id.confirmPasswordET, 9);
        sViewsWithIds.put(R.id.maleRB, 10);
        sViewsWithIds.put(R.id.otherRB, 11);
        sViewsWithIds.put(R.id.femaleRB, 12);
        sViewsWithIds.put(R.id.countryET, 13);
        sViewsWithIds.put(R.id.addressET, 14);
        sViewsWithIds.put(R.id.addressTwoET, 15);
        sViewsWithIds.put(R.id.stateET, 16);
        sViewsWithIds.put(R.id.cityET, 17);
        sViewsWithIds.put(R.id.zipCodeET, 18);
        sViewsWithIds.put(R.id.phoneNumberET, 19);
        sViewsWithIds.put(R.id.gedCB, 20);
        sViewsWithIds.put(R.id.highSchoolCB, 21);
        sViewsWithIds.put(R.id.collegeCB, 22);
        sViewsWithIds.put(R.id.certificatesET, 23);
        sViewsWithIds.put(R.id.nextBT, 24);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomEditText addressET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText addressTwoET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText certificatesET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText cityET;
    @NonNull
    public final android.widget.CheckBox collegeCB;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText confirmEmailET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText confirmPasswordET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText countryET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText emailET;
    @NonNull
    public final android.widget.RadioButton femaleRB;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText firstNameET;
    @NonNull
    public final android.widget.CheckBox gedCB;
    @NonNull
    public final android.widget.CheckBox highSchoolCB;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText lastNameET;
    @NonNull
    public final android.widget.RadioButton maleRB;
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final com.eljebo.common.utils.CustomButton nextBT;
    @NonNull
    public final android.widget.RadioButton otherRB;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText passwordET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText phoneNumberET;
    @Nullable
    public final com.eljebo.databinding.SecurityQuestionsLayoutBinding questionsLL;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText stateET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText userNameET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText zipCodeET;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentCustomerSignupBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds);
        this.addressET = (com.eljebo.common.utils.CustomEditText) bindings[14];
        this.addressTwoET = (com.eljebo.common.utils.CustomEditText) bindings[15];
        this.certificatesET = (com.eljebo.common.utils.CustomEditText) bindings[23];
        this.cityET = (com.eljebo.common.utils.CustomEditText) bindings[17];
        this.collegeCB = (android.widget.CheckBox) bindings[22];
        this.confirmEmailET = (com.eljebo.common.utils.CustomEditText) bindings[6];
        this.confirmPasswordET = (com.eljebo.common.utils.CustomEditText) bindings[9];
        this.countryET = (com.eljebo.common.utils.CustomEditText) bindings[13];
        this.emailET = (com.eljebo.common.utils.CustomEditText) bindings[5];
        this.femaleRB = (android.widget.RadioButton) bindings[12];
        this.firstNameET = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.gedCB = (android.widget.CheckBox) bindings[20];
        this.highSchoolCB = (android.widget.CheckBox) bindings[21];
        this.lastNameET = (com.eljebo.common.utils.CustomEditText) bindings[4];
        this.maleRB = (android.widget.RadioButton) bindings[10];
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.nextBT = (com.eljebo.common.utils.CustomButton) bindings[24];
        this.otherRB = (android.widget.RadioButton) bindings[11];
        this.passwordET = (com.eljebo.common.utils.CustomEditText) bindings[8];
        this.phoneNumberET = (com.eljebo.common.utils.CustomEditText) bindings[19];
        this.questionsLL = (com.eljebo.databinding.SecurityQuestionsLayoutBinding) bindings[2];
        setContainedBinding(this.questionsLL);
        this.stateET = (com.eljebo.common.utils.CustomEditText) bindings[16];
        this.userNameET = (com.eljebo.common.utils.CustomEditText) bindings[7];
        this.zipCodeET = (com.eljebo.common.utils.CustomEditText) bindings[18];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        questionsLL.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (questionsLL.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeQuestionsLL((com.eljebo.databinding.SecurityQuestionsLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeQuestionsLL(com.eljebo.databinding.SecurityQuestionsLayoutBinding QuestionsLL, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(questionsLL);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentCustomerSignupBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerSignupBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentCustomerSignupBinding>inflate(inflater, com.eljebo.R.layout.fragment_customer_signup, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentCustomerSignupBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerSignupBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_customer_signup, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentCustomerSignupBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerSignupBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_customer_signup_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentCustomerSignupBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): questionsLL
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}