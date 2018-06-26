package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentLoginBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView, 1);
        sViewsWithIds.put(R.id.emailET, 2);
        sViewsWithIds.put(R.id.passwordET, 3);
        sViewsWithIds.put(R.id.forPasswordTV, 4);
        sViewsWithIds.put(R.id.loginBT, 5);
        sViewsWithIds.put(R.id.bottomLL, 6);
        sViewsWithIds.put(R.id.dontHaveAccountTV, 7);
        sViewsWithIds.put(R.id.signUpLL, 8);
        sViewsWithIds.put(R.id.signUpTV, 9);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout bottomLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView dontHaveAccountTV;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText emailET;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView forPasswordTV;
    @NonNull
    public final android.widget.ImageView imageView;
    @NonNull
    public final com.eljebo.common.utils.CustomButton loginBT;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText passwordET;
    @NonNull
    public final android.widget.LinearLayout signUpLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView signUpTV;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentLoginBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.bottomLL = (android.widget.LinearLayout) bindings[6];
        this.dontHaveAccountTV = (com.eljebo.common.utils.CustomTextView) bindings[7];
        this.emailET = (com.eljebo.common.utils.CustomEditText) bindings[2];
        this.forPasswordTV = (com.eljebo.common.utils.CustomTextView) bindings[4];
        this.imageView = (android.widget.ImageView) bindings[1];
        this.loginBT = (com.eljebo.common.utils.CustomButton) bindings[5];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.passwordET = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.signUpLL = (android.widget.LinearLayout) bindings[8];
        this.signUpTV = (com.eljebo.common.utils.CustomTextView) bindings[9];
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
    public static FragmentLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentLoginBinding>inflate(inflater, com.eljebo.R.layout.fragment_login, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentLoginBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_login, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentLoginBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentLoginBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_login_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentLoginBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}