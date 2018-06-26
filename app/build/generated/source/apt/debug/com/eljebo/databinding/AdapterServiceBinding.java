package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdapterServiceBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.titleTV, 1);
        sViewsWithIds.put(R.id.clearnerLL, 2);
        sViewsWithIds.put(R.id.priceET, 3);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout clearnerLL;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText priceET;
    @NonNull
    public final android.widget.RelativeLayout selectedServiceLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView titleTV;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdapterServiceBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.clearnerLL = (android.widget.LinearLayout) bindings[2];
        this.priceET = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.selectedServiceLL = (android.widget.RelativeLayout) bindings[0];
        this.selectedServiceLL.setTag(null);
        this.titleTV = (com.eljebo.common.utils.CustomTextView) bindings[1];
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
    public static AdapterServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<AdapterServiceBinding>inflate(inflater, com.eljebo.R.layout.adapter_service, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static AdapterServiceBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.adapter_service, null, false), bindingComponent);
    }
    @NonNull
    public static AdapterServiceBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static AdapterServiceBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/adapter_service_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new AdapterServiceBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}