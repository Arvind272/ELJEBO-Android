package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentServiceUserDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(9);
        sIncludes.setIncludes(1, 
            new String[] {"service_detail_layout"},
            new int[] {2},
            new int[] {R.layout.service_detail_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.customTextViewMsgBtn, 3);
        sViewsWithIds.put(R.id.customTextViewCallBtn, 4);
        sViewsWithIds.put(R.id.checkInCTV, 5);
        sViewsWithIds.put(R.id.timerCTV, 6);
        sViewsWithIds.put(R.id.timerOnIV, 7);
        sViewsWithIds.put(R.id.locateUserBT, 8);
    }
    // views
    @NonNull
    public final com.eljebo.common.utils.CustomTextView checkInCTV;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView customTextViewCallBtn;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView customTextViewMsgBtn;
    @NonNull
    public final com.eljebo.common.utils.CustomButton locateUserBT;
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @Nullable
    public final com.eljebo.databinding.ServiceDetailLayoutBinding questionsLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView timerCTV;
    @NonNull
    public final android.widget.Button timerOnIV;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentServiceUserDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.checkInCTV = (com.eljebo.common.utils.CustomTextView) bindings[5];
        this.customTextViewCallBtn = (com.eljebo.common.utils.CustomTextView) bindings[4];
        this.customTextViewMsgBtn = (com.eljebo.common.utils.CustomTextView) bindings[3];
        this.locateUserBT = (com.eljebo.common.utils.CustomButton) bindings[8];
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.questionsLL = (com.eljebo.databinding.ServiceDetailLayoutBinding) bindings[2];
        setContainedBinding(this.questionsLL);
        this.timerCTV = (com.eljebo.common.utils.CustomTextView) bindings[6];
        this.timerOnIV = (android.widget.Button) bindings[7];
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
    public static FragmentServiceUserDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentServiceUserDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentServiceUserDetailBinding>inflate(inflater, com.eljebo.R.layout.fragment_service_user_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentServiceUserDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentServiceUserDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.fragment_service_user_detail, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentServiceUserDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentServiceUserDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_service_user_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentServiceUserDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): questionsLL
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}