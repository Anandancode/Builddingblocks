package com.buildingblocks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.activities.MainActivity;
import com.buildingblocks.pojo.JobInfoPojo;

import java.util.ArrayList;

public class JobTopRecylerAdapter extends RecyclerView.Adapter<JobTopRecylerAdapter.MyViewHolder> {

    private ArrayList<JobInfoPojo> myJobInfoList = null;
    private Context myContext;

    public JobTopRecylerAdapter(Context aContext, ArrayList<JobInfoPojo> aJobInfoList) {
        this.myContext = aContext;
        this.myJobInfoList = aJobInfoList;
    }

    @NonNull
    @Override
    public JobTopRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inflate_job_title_list_item, null);
        JobTopRecylerAdapter.MyViewHolder aView = new JobTopRecylerAdapter.MyViewHolder(layoutView);
        return aView;
    }

    @Override
    public void onBindViewHolder(@NonNull JobTopRecylerAdapter.MyViewHolder holder, int position) {
        holder.aTitleTXT.setText(myJobInfoList.get(position).getJobTitleInfo());

        if (myJobInfoList.get(position).isSelected()) {
            holder.aParentLAY.setBackground(myContext.getResources().getDrawable(R.drawable.bg_half_roundcorner_selected));
        } else {
            holder.aParentLAY.setBackground(myContext.getResources().getDrawable(R.drawable.bg_half_roundcorner_grey));
        }
        holder.aParentLAY.setOnClickListener(new jobClickListener(position));
    }

    @Override
    public int getItemCount() {
        return myJobInfoList.size();
    }

    public ArrayList<JobInfoPojo> getArrayList() {
        return myJobInfoList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView aTitleTXT;
        private LinearLayout aParentLAY;

        public MyViewHolder(View itemView) {
            super(itemView);
            aTitleTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_title_list_item_TXT_title);
            aParentLAY = (LinearLayout) itemView.findViewById(R.id.layout_inflate_job_title_list_item_LAY_title);
        }
    }

    private class jobClickListener implements View.OnClickListener {

        int myPosition;

        public jobClickListener(int aPosition) {
            myPosition = aPosition;
        }

        @Override
        public void onClick(View view) {
            MainActivity aActivity = (MainActivity) myContext;
            aActivity.updateViews(myPosition);
        }
    }
}
