package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCustomerCheckInBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(7);
        sIncludes.setIncludes(1, 
            new String[] {"service_detail_layout"},
            new int[] {2},
            new int[] {R.layout.service_detail_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.timerCTV, 3);
        sViewsWithIds.put(R.id.timerOffIV, 4);
        sViewsWithIds.put(R.id.timerOnIV, 5);
        sViewsWithIds.put(R.id.extraTimeTV, 6);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomTextView extraTimeTV;
    @NonNull
    private final android.support.v4.widget.NestedScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @Nullable
    public final com.eljebo.databinding.ServiceDetailLayoutBinding questionsLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView timerCTV;
    @NonNull
    public final android.widget.Button timerOffIV;
    @NonNull
    public final android.widget.Button timerOnIV;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentCustomerCheckInBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.extraTimeTV = (com.eljebo.common.utils.CustomTextView) bindings[6];
        this.mboundView0 = (android.support.v4.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.questionsLL = (com.eljebo.databinding.ServiceDetailLayoutBinding) bindings[2];
        setContainedBinding(this.questionsLL);
        this.timerCTV = (com.eljebo.common.utils.CustomTextView) bindings[3];
        this.timerOffIV = (android.widget.Button) bindings[4];
        this.timerOnIV = (android.widget.Button) bindings[5];
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
                return onChangeQuestionsLL((com.eljebo.databinding.ServiceDetailLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeQuestionsLL(com.eljebo.databinding.ServiceDetailLayoutBinding QuestionsLL, int fieldId) {
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
    public static FragmentCustomerCheckInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerCheckInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentCustomerCheckInBinding>inflate(inflater, com.eljebo.R.layout.fragment_customer_check_in, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentCustomerCheckInBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerCheckInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_customer_check_in, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentCustomerCheckInBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentCustomerCheckInBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_customer_check_in_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentCustomerCheckInBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): questionsLL
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}