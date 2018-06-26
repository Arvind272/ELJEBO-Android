package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SecurityQuestionsLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.que1ET, 1);
        sViewsWithIds.put(R.id.ans1ET, 2);
        sViewsWithIds.put(R.id.ques2ET, 3);
        sViewsWithIds.put(R.id.ans2ET, 4);
        sViewsWithIds.put(R.id.ques3ET, 5);
        sViewsWithIds.put(R.id.ans3ET, 6);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomEditText ans1ET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText ans2ET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText ans3ET;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText que1ET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText ques2ET;
    @NonNull
    public final com.eljebo.common.utils.CustomEditText ques3ET;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SecurityQuestionsLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ans1ET = (com.eljebo.common.utils.CustomEditText) bindings[2];
        this.ans2ET = (com.eljebo.common.utils.CustomEditText) bindings[4];
        this.ans3ET = (com.eljebo.common.utils.CustomEditText) bindings[6];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.que1ET = (com.eljebo.common.utils.CustomEditText) bindings[1];
        this.ques2ET = (com.eljebo.common.utils.CustomEditText) bindings[3];
        this.ques3ET = (com.eljebo.common.utils.CustomEditText) bindings[5];
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
    public static SecurityQuestionsLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SecurityQuestionsLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<SecurityQuestionsLayoutBinding>inflate(inflater, com.eljebo.R.layout.security_questions_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static SecurityQuestionsLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SecurityQuestionsLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.security_questions_layout, null, false), bindingComponent);
    }
    @NonNull
    public static SecurityQuestionsLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SecurityQuestionsLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/security_questions_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new SecurityQuestionsLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}