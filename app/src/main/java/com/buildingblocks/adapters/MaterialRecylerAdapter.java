package com.buildingblocks.adapters;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.activities.MainActivity;
import com.buildingblocks.pojo.MaterialInfoPojo;
import com.buildingblocks.pojo.ParentJobPojo;

import java.util.ArrayList;

public class MaterialRecylerAdapter extends RecyclerView.Adapter<MaterialRecylerAdapter.MyViewHolder> {

    private Context myContext;
    private ArrayList<MaterialInfoPojo> myMaterialInfoList;
    private int myParentPosition;
    private ArrayList<ParentJobPojo> myParentInfoList;

    public MaterialRecylerAdapter(Context aContext, ArrayList<MaterialInfoPojo> aMaterialInfoList, int aPosition, ArrayList<ParentJobPojo> aParentInfoList) {
        this.myContext = aContext;
        this.myMaterialInfoList = aMaterialInfoList;
        this.myParentPosition = aPosition;
        this.myParentInfoList = aParentInfoList;
    }

    @NonNull
    @Override
    public MaterialRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(myContext).inflate(R.layout.layout_inflate_material_list_item, viewGroup, false);
        MyViewHolder aView = new MyViewHolder(layoutView);
        return aView;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialRecylerAdapter.MyViewHolder holder, int position) {


        holder.myItemNameET.setText(myMaterialInfoList.get(position).getMaterialItemName());
        holder.myTotalET.setText(myMaterialInfoList.get(position).getMaterialItemTotal());
        holder.myQtyET.setText(myMaterialInfoList.get(position).getMaterialItemQty());
        holder.myCostET.setText(myMaterialInfoList.get(position).getMaterialItemCost());
        holder.myTaxET.setText(myMaterialInfoList.get(position).getMaterialItemTax());
        holder.myMaterialCountTXT.setText("Material" + " " + myMaterialInfoList.get(position).getMaterialCount());

        holder.myItemNameET.addTextChangedListener(new itemTextChangeListener(position, holder));
        holder.myTotalET.addTextChangedListener(new totalTextChangeListener(position, holder));
        holder.myQtyET.addTextChangedListener(new qtyTextChangeListener(position, holder));
        holder.myCostET.addTextChangedListener(new costTextChangeListener(position, holder));
        holder.myTaxET.addTextChangedListener(new taxTextChangeListener(position, holder));

    }

    @Override
    public int getItemCount() {
        return myMaterialInfoList.size();
    }

    public ArrayList<MaterialInfoPojo> getArrayList() {
        return myMaterialInfoList;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private EditText myItemNameET, myTotalET;
        private EditText myQtyET, myCostET, myTaxET;
        private TextView myMaterialCountTXT;

        public MyViewHolder(View itemView) {
            super(itemView);
            myItemNameET = (EditText) itemView.findViewById(R.id.layout_inflate_material_list_item_ET_itemname);
            myQtyET = (EditText) itemView.findViewById(R.id.layout_inflate_material_list_item_ET_qty);
            myCostET = (EditText) itemView.findViewById(R.id.layout_inflate_material_list_item_ET_cost);
            myTaxET = (EditText) itemView.findViewById(R.id.layout_inflate_material_list_item_ET_tax);
            myTotalET = (EditText) itemView.findViewById(R.id.layout_inflate_material_list_item_ET_total);
            myMaterialCountTXT = (TextView) itemView.findViewById(R.id.layout_inflate_material_list_item_TXT_material);
        }
    }

    private class itemTextChangeListener implements TextWatcher {
        int myPosition;
        MyViewHolder myHolder;

        public itemTextChangeListener(int aPosition, MyViewHolder aHolder) {
            myPosition = aPosition;
            myHolder = aHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaterialInfoPojo aPojo = myMaterialInfoList.get(myPosition);
                    aPojo.setMaterialItemName(myHolder.myItemNameET.getText().toString());
                    myMaterialInfoList.set(myPosition, aPojo);
                    ParentJobPojo aParentPojo = myParentInfoList.get(myParentPosition);
                    aParentPojo.setMaterialPojo(myMaterialInfoList);
                    myParentInfoList.set(myParentPosition, aParentPojo);
                }
            }, 500);
        }
    }

    private class totalTextChangeListener implements TextWatcher {
        int myPosition;
        MyViewHolder myHolder;

        public totalTextChangeListener(int aPosition, MyViewHolder aHolder) {
            myPosition = aPosition;
            myHolder = aHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaterialInfoPojo aPojo = myMaterialInfoList.get(myPosition);
                    aPojo.setMaterialItemTotal(myHolder.myTotalET.getText().toString());
                    myMaterialInfoList.set(myPosition, aPojo);
                    ParentJobPojo aParentPojo = myParentInfoList.get(myParentPosition);
                    aParentPojo.setMaterialPojo(myMaterialInfoList);
                    myParentInfoList.set(myParentPosition, aParentPojo);

                    MainActivity aActivity = (MainActivity) myContext;
                    aActivity.updateTotal();
                }
            }, 500);
        }
    }

    private class qtyTextChangeListener implements TextWatcher {
        int myPosition;
        MyViewHolder myHolder;

        public qtyTextChangeListener(int aPosition, MyViewHolder aHolder) {
            myPosition = aPosition;
            myHolder = aHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaterialInfoPojo aPojo = myMaterialInfoList.get(myPosition);
                    aPojo.setMaterialItemQty(myHolder.myQtyET.getText().toString());
                    myMaterialInfoList.set(myPosition, aPojo);
                    ParentJobPojo aParentPojo = myParentInfoList.get(myParentPosition);
                    aParentPojo.setMaterialPojo(myMaterialInfoList);
                    myParentInfoList.set(myParentPosition, aParentPojo);
                }
            }, 500);
        }
    }

    private class costTextChangeListener implements TextWatcher {
        int myPosition;
        MyViewHolder myHolder;

        public costTextChangeListener(int aPosition, MyViewHolder aHolder) {
            myPosition = aPosition;
            myHolder = aHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaterialInfoPojo aPojo = myMaterialInfoList.get(myPosition);
                    aPojo.setMaterialItemCost(myHolder.myCostET.getText().toString());
                    myMaterialInfoList.set(myPosition, aPojo);
                    ParentJobPojo aParentPojo = myParentInfoList.get(myParentPosition);
                    aParentPojo.setMaterialPojo(myMaterialInfoList);
                    myParentInfoList.set(myParentPosition, aParentPojo);
                }
            }, 500);
        }
    }

    private class taxTextChangeListener implements TextWatcher {
        int myPosition;
        MyViewHolder myHolder;

        public taxTextChangeListener(int aPosition, MyViewHolder aHolder) {
            myPosition = aPosition;
            myHolder = aHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaterialInfoPojo aPojo = myMaterialInfoList.get(myPosition);
                    aPojo.setMaterialItemTax(myHolder.myTaxET.getText().toString());
                    myMaterialInfoList.set(myPosition, aPojo);
                    ParentJobPojo aParentPojo = myParentInfoList.get(myParentPosition);
                    aParentPojo.setMaterialPojo(myMaterialInfoList);
                    myParentInfoList.set(myParentPosition, aParentPojo);
                }
            }, 500);
        }
    }
}
