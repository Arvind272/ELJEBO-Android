package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCustomerProfileBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.meal_image_order, 1);
        sViewsWithIds.put(R.id.nameET, 2);
        sViewsWithIds.put(R.id.emailET, 3);
        sViewsWithIds.put(R.id.phoneNumberET, 4);
        sViewsWithIds.put(R.id.addressET, 5);
        sViewsWithIds.put(R.id.changePasswordET, 6);
        sViewsWithIds.put(R.id.payBT, 7);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomEditText addressET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText changePasswordET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText emailET;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView mealImageOrder;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText nameET;
    @NonNull
    public final com.eljebo.common.utils.CustomButton payBT;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText phoneNumberET;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentCustomerProfileBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.addressET = (com.eljebo.common.utils.CustomEditText) bindings[5];
        this.changePasswordET = (com.eljebo.common.utils.CustomEditText) bindings[6];
        this.emailET = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mealImageOrder = (de.hdodenhof.circleimageview.CircleImageView) bindings[1];
        this.nameET = (com.eljebo.common.utils.CustomEditText) bindings[2];
        this.payBT = (com.eljebo.common.utils.CustomButton) bindings[7];
        this.phoneNumberET = (com.eljebo.common.utils.CustomEditText) bindings[4];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
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
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentCustomerProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentCustomerProfileBinding>inflate(inflater, com.eljebo.R.layout.fragment_customer_profile, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentCustomerProfileBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_customer_profile, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentCustomerProfileBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerProfileBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_customer_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentCustomerProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}