package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ServiceDetailLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.meal_image_order, 1);
        sViewsWithIds.put(R.id.availabilityLL, 2);
        sViewsWithIds.put(R.id.descriptionET, 3);
        sViewsWithIds.put(R.id.serviceLL, 4);
        sViewsWithIds.put(R.id.educationLL, 5);
        sViewsWithIds.put(R.id.certificationLL, 6);
        sViewsWithIds.put(R.id.durationLL, 7);
        sViewsWithIds.put(R.id.duration, 8);
        sViewsWithIds.put(R.id.bookingDetailLL, 9);
        sViewsWithIds.put(R.id.bookingTimeTV, 10);
        sViewsWithIds.put(R.id.bookingDateTV, 11);
        sViewsWithIds.put(R.id.durationTV, 12);
        sViewsWithIds.put(R.id.cleanerLL, 13);
        sViewsWithIds.put(R.id.cleanerChargeTV, 14);
        sViewsWithIds.put(R.id.babyChargeTV, 15);
        sViewsWithIds.put(R.id.plumberCharegeTV, 16);
        sViewsWithIds.put(R.id.servicesRV, 17);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout availabilityLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView babyChargeTV;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView bookingDateTV;
    @NonNull
    public final android.widget.LinearLayout bookingDetailLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView bookingTimeTV;
    @NonNull
    public final android.widget.LinearLayout certificationLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView cleanerChargeTV;
    @NonNull
    public final android.widget.LinearLayout cleanerLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView descriptionET;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView duration;
    @NonNull
    public final android.widget.LinearLayout durationLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView durationTV;
    @NonNull
    public final android.widget.LinearLayout educationLL;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView mealImageOrder;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView plumberCharegeTV;
    @NonNull
    public final android.widget.LinearLayout profileLL;
    @NonNull
    public final android.widget.LinearLayout serviceLL;
    @NonNull
    public final android.support.v7.widget.RecyclerView servicesRV;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ServiceDetailLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds);
        this.availabilityLL = (android.widget.LinearLayout) bindings[2];
        this.babyChargeTV = (com.eljebo.common.utils.CustomTextView) bindings[15];
        this.bookingDateTV = (com.eljebo.common.utils.CustomTextView) bindings[11];
        this.bookingDetailLL = (android.widget.LinearLayout) bindings[9];
        this.bookingTimeTV = (com.eljebo.common.utils.CustomTextView) bindings[10];
        this.certificationLL = (android.widget.LinearLayout) bindings[6];
        this.cleanerChargeTV = (com.eljebo.common.utils.CustomTextView) bindings[14];
        this.cleanerLL = (android.widget.LinearLayout) bindings[13];
        this.descriptionET = (com.eljebo.common.utils.CustomTextView) bindings[3];
        this.duration = (com.eljebo.common.utils.CustomTextView) bindings[8];
        this.durationLL = (android.widget.LinearLayout) bindings[7];
        this.durationTV = (com.eljebo.common.utils.CustomTextView) bindings[12];
        this.educationLL = (android.widget.LinearLayout) bindings[5];
        this.mealImageOrder = (de.hdodenhof.circleimageview.CircleImageView) bindings[1];
        this.plumberCharegeTV = (com.eljebo.common.utils.CustomTextView) bindings[16];
        this.profileLL = (android.widget.LinearLayout) bindings[0];
        this.profileLL.setTag(null);
        this.serviceLL = (android.widget.LinearLayout) bindings[4];
        this.servicesRV = (android.support.v7.widget.RecyclerView) bindings[17];
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
    public static ServiceDetailLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServiceDetailLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ServiceDetailLayoutBinding>inflate(inflater, com.eljebo.R.layout.service_detail_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ServiceDetailLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServiceDetailLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.service_detail_layout, null, false), bindingComponent);
    }
    @NonNull
    public static ServiceDetailLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServiceDetailLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/service_detail_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ServiceDetailLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}