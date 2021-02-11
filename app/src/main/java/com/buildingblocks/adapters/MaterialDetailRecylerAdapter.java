package com.buildingblocks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.activities.MaterialDetailActivity;
import com.buildingblocks.pojo.MaterialInfoPojo;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MaterialDetailRecylerAdapter extends RecyclerView.Adapter<MaterialDetailRecylerAdapter.MyViewHolder> {

    private Context myContext;
    private ArrayList<MaterialInfoPojo> myMaterialInfoList;

    public MaterialDetailRecylerAdapter(Context aContext, ArrayList<MaterialInfoPojo> aMaterialInfoList) {
        this.myContext = aContext;
        this.myMaterialInfoList = aMaterialInfoList;
    }

    @NonNull
    @Override
    public MaterialDetailRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(myContext).inflate(R.layout.layout_inflate_material_detail_item, viewGroup, false);
        MaterialDetailRecylerAdapter.MyViewHolder aView = new MaterialDetailRecylerAdapter.MyViewHolder(layoutView);
        return aView;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialDetailRecylerAdapter.MyViewHolder holder, int position) {
        DecimalFormat aDecimalFormat = new DecimalFormat("0.00");
        aDecimalFormat.setMaximumFractionDigits(2);

        holder.aItemNameTXT.setText(myMaterialInfoList.get(position).getMaterialItemName());
        holder.aQtyTXT.setText(myMaterialInfoList.get(position).getMaterialItemQty());
        holder.aCostTXT.setText("$" + aDecimalFormat.format(Float.parseFloat(myMaterialInfoList.get(position).getMaterialItemCost())));
        holder.aTaxTXT.setText(myMaterialInfoList.get(position).getMaterialItemTax());
        holder.aTotalTXT.setText("$" + aDecimalFormat.format(Float.parseFloat(myMaterialInfoList.get(position).getMaterialItemTotal())));
    }

    @Override
    public int getItemCount() {
        return myMaterialInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView aItemNameTXT, aQtyTXT, aCostTXT, aTaxTXT, aTotalTXT;

        public MyViewHolder(View itemView) {
            super(itemView);
            aItemNameTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_itemname);
            aQtyTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_qty);
            aCostTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_cost);
            aTaxTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_tax);
            aTotalTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_total);
        }
    }
}
