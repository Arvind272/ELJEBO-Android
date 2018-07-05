package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPersonalInfoBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.nameET, 1);
        sViewsWithIds.put(R.id.customEditTextEmail, 2);
        sViewsWithIds.put(R.id.customEditTextMobile, 3);
        sViewsWithIds.put(R.id.customEditTextAddress, 4);
        sViewsWithIds.put(R.id.saveBT, 5);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomEditText customEditTextAddress;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText customEditTextEmail;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText customEditTextMobile;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText nameET;
    @NonNull
    public final com.eljebo.common.utils.CustomButton saveBT;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPersonalInfoBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.customEditTextAddress = (com.eljebo.common.utils.CustomEditText) bindings[4];
        this.customEditTextEmail = (com.eljebo.common.utils.CustomEditText) bindings[2];
        this.customEditTextMobile = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nameET = (com.eljebo.common.utils.CustomEditText) bindings[1];
        this.saveBT = (com.eljebo.common.utils.CustomButton) bindings[5];
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
    public static FragmentPersonalInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentPersonalInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentPersonalInfoBinding>inflate(inflater, com.eljebo.R.layout.fragment_personal_info, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentPersonalInfoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentPersonalInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_personal_info, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentPersonalInfoBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentPersonalInfoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_personal_info_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentPersonalInfoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}