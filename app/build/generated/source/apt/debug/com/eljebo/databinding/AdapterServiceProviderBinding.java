package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdapterServiceProviderBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlParentView, 1);
        sViewsWithIds.put(R.id.serviceProviderCIV, 2);
        sViewsWithIds.put(R.id.progressBarLoader, 3);
        sViewsWithIds.put(R.id.customTextServiceProName, 4);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomTextView customTextServiceProName;
    @NonNull
    public final android.widget.ProgressBar progressBarLoader;
    @NonNull
    public final android.widget.RelativeLayout rlParentView;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView serviceProviderCIV;
    @NonNull
    public final android.widget.RelativeLayout serviceProviderRL;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdapterServiceProviderBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.customTextServiceProName = (com.eljebo.common.utils.CustomTextView) bindings[4];
        this.progressBarLoader = (android.widget.ProgressBar) bindings[3];
        this.rlParentView = (android.widget.RelativeLayout) bindings[1];
        this.serviceProviderCIV = (de.hdodenhof.circleimageview.CircleImageView) bindings[2];
        this.serviceProviderRL = (android.widget.RelativeLayout) bindings[0];
        this.serviceProviderRL.setTag(null);
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
    public static AdapterServiceProviderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceProviderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<AdapterServiceProviderBinding>inflate(inflater, com.eljebo.R.layout.adapter_service_provider, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static AdapterServiceProviderBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceProviderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.adapter_service_provider, null, false), bindingComponent);
    }
    @NonNull
    public static AdapterServiceProviderBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceProviderBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/adapter_service_provider_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new AdapterServiceProviderBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}