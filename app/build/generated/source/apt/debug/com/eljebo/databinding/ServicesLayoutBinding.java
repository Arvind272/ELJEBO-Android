package com.eljebo.databinding;
import com.eljebo.R;
import com.eljebo.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ServicesLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.holdAidServicesTV, 1);
        sViewsWithIds.put(R.id.homeServiceLL, 2);
        sViewsWithIds.put(R.id.homeCareCB, 3);
        sViewsWithIds.put(R.id.cleanerCB, 4);
        sViewsWithIds.put(R.id.babyCB, 5);
        sViewsWithIds.put(R.id.plumberCB, 6);
        sViewsWithIds.put(R.id.otherCB, 7);
        sViewsWithIds.put(R.id.cateringTV, 8);
        sViewsWithIds.put(R.id.cateringServicesLL, 9);
        sViewsWithIds.put(R.id.cooksCB, 10);
        sViewsWithIds.put(R.id.bakersCB, 11);
        sViewsWithIds.put(R.id.serversCB, 12);
        sViewsWithIds.put(R.id.cateringOtherCB, 13);
        sViewsWithIds.put(R.id.educationTV, 14);
        sViewsWithIds.put(R.id.educationServicesLL, 15);
        sViewsWithIds.put(R.id.schoolAssistanceCB, 16);
        sViewsWithIds.put(R.id.certificationTrainersCB, 17);
        sViewsWithIds.put(R.id.decorationTV, 18);
        sViewsWithIds.put(R.id.decorationServicesLL, 19);
        sViewsWithIds.put(R.id.eventDecoratorsCB, 20);
        sViewsWithIds.put(R.id.photographersCB, 21);
        sViewsWithIds.put(R.id.homeHairCB, 22);
        sViewsWithIds.put(R.id.makeUpArtistCB, 23);
        sViewsWithIds.put(R.id.designersCB, 24);
        sViewsWithIds.put(R.id.plannersTV, 25);
        sViewsWithIds.put(R.id.plannersServicesLL, 26);
        sViewsWithIds.put(R.id.buildingArchitectsCB, 27);
        sViewsWithIds.put(R.id.localIntShippersCB, 28);
        sViewsWithIds.put(R.id.technicalTV, 29);
        sViewsWithIds.put(R.id.technicalServicesLL, 30);
        sViewsWithIds.put(R.id.mechanicCB, 31);
        sViewsWithIds.put(R.id.technicianCB, 32);
        sViewsWithIds.put(R.id.otherTechnicalCB, 33);
    }
    // views
    @NonNull
    public final android.widget.CheckBox babyCB;
    @NonNull
    public final android.widget.CheckBox bakersCB;
    @NonNull
    public final android.widget.CheckBox buildingArchitectsCB;
    @NonNull
    public final android.widget.CheckBox cateringOtherCB;
    @NonNull
    public final android.widget.LinearLayout cateringServicesLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView cateringTV;
    @NonNull
    public final android.widget.CheckBox certificationTrainersCB;
    @NonNull
    public final android.widget.CheckBox cleanerCB;
    @NonNull
    public final android.widget.CheckBox cooksCB;
    @NonNull
    public final android.widget.LinearLayout decorationServicesLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView decorationTV;
    @NonNull
    public final android.widget.CheckBox designersCB;
    @NonNull
    public final android.widget.LinearLayout educationServicesLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView educationTV;
    @NonNull
    public final android.widget.CheckBox eventDecoratorsCB;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView holdAidServicesTV;
    @NonNull
    public final android.widget.CheckBox homeCareCB;
    @NonNull
    public final android.widget.CheckBox homeHairCB;
    @NonNull
    public final android.widget.LinearLayout homeServiceLL;
    @NonNull
    public final android.widget.CheckBox localIntShippersCB;
    @NonNull
    public final android.widget.CheckBox makeUpArtistCB;
    @NonNull
    public final android.widget.CheckBox mechanicCB;
    @NonNull
    public final android.widget.CheckBox otherCB;
    @NonNull
    public final android.widget.CheckBox otherTechnicalCB;
    @NonNull
    public final android.widget.CheckBox photographersCB;
    @NonNull
    public final android.widget.LinearLayout plannersServicesLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView plannersTV;
    @NonNull
    public final android.widget.CheckBox plumberCB;
    @NonNull
    public final android.widget.CheckBox schoolAssistanceCB;
    @NonNull
    public final android.widget.CheckBox serversCB;
    @NonNull
    public final android.widget.ScrollView serviceSV;
    @NonNull
    public final android.widget.LinearLayout technicalServicesLL;
    @NonNull
    public final com.eljebo.common.utils.CustomTextView technicalTV;
    @NonNull
    public final android.widget.CheckBox technicianCB;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ServicesLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds);
        this.babyCB = (android.widget.CheckBox) bindings[5];
        this.bakersCB = (android.widget.CheckBox) bindings[11];
        this.buildingArchitectsCB = (android.widget.CheckBox) bindings[27];
        this.cateringOtherCB = (android.widget.CheckBox) bindings[13];
        this.cateringServicesLL = (android.widget.LinearLayout) bindings[9];
        this.cateringTV = (com.eljebo.common.utils.CustomTextView) bindings[8];
        this.certificationTrainersCB = (android.widget.CheckBox) bindings[17];
        this.cleanerCB = (android.widget.CheckBox) bindings[4];
        this.cooksCB = (android.widget.CheckBox) bindings[10];
        this.decorationServicesLL = (android.widget.LinearLayout) bindings[19];
        this.decorationTV = (com.eljebo.common.utils.CustomTextView) bindings[18];
        this.designersCB = (android.widget.CheckBox) bindings[24];
        this.educationServicesLL = (android.widget.LinearLayout) bindings[15];
        this.educationTV = (com.eljebo.common.utils.CustomTextView) bindings[14];
        this.eventDecoratorsCB = (android.widget.CheckBox) bindings[20];
        this.holdAidServicesTV = (com.eljebo.common.utils.CustomTextView) bindings[1];
        this.homeCareCB = (android.widget.CheckBox) bindings[3];
        this.homeHairCB = (android.widget.CheckBox) bindings[22];
        this.homeServiceLL = (android.widget.LinearLayout) bindings[2];
        this.localIntShippersCB = (android.widget.CheckBox) bindings[28];
        this.makeUpArtistCB = (android.widget.CheckBox) bindings[23];
        this.mechanicCB = (android.widget.CheckBox) bindings[31];
        this.otherCB = (android.widget.CheckBox) bindings[7];
        this.otherTechnicalCB = (android.widget.CheckBox) bindings[33];
        this.photographersCB = (android.widget.CheckBox) bindings[21];
        this.plannersServicesLL = (android.widget.LinearLayout) bindings[26];
        this.plannersTV = (com.eljebo.common.utils.CustomTextView) bindings[25];
        this.plumberCB = (android.widget.CheckBox) bindings[6];
        this.schoolAssistanceCB = (android.widget.CheckBox) bindings[16];
        this.serversCB = (android.widget.CheckBox) bindings[12];
        this.serviceSV = (android.widget.ScrollView) bindings[0];
        this.serviceSV.setTag(null);
        this.technicalServicesLL = (android.widget.LinearLayout) bindings[30];
        this.technicalTV = (com.eljebo.common.utils.CustomTextView) bindings[29];
        this.technicianCB = (android.widget.CheckBox) bindings[32];
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
    public static ServicesLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServicesLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ServicesLayoutBinding>inflate(inflater, com.eljebo.R.layout.services_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ServicesLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServicesLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.eljebo.R.layout.services_layout, null, false), bindingComponent);
    }
    @NonNull
    public static ServicesLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ServicesLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/services_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ServicesLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}