package com.buildingblocks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.activities.JobListActivity;
import com.buildingblocks.pojo.JobInfoPojo;

import java.util.ArrayList;

public class JobListRecylerAdapter extends RecyclerView.Adapter<JobListRecylerAdapter.MyViewHolder> {

    private Context myContext;
    private ArrayList<JobInfoPojo> myJobInfoList;

    public JobListRecylerAdapter(Context aContext, ArrayList<JobInfoPojo> aJobInfoList) {
        this.myContext = aContext;
        this.myJobInfoList = aJobInfoList;
    }

    @NonNull
    @Override
    public JobListRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(myContext).inflate(R.layout.layout_inflate_job_list_item, viewGroup, false);
        JobListRecylerAdapter.MyViewHolder aView = new JobListRecylerAdapter.MyViewHolder(layoutView);
        return aView;
    }

    @Override
    public void onBindViewHolder(@NonNull JobListRecylerAdapter.MyViewHolder holder, int position) {
        holder.aTitleTXT.setText(myJobInfoList.get(position).getJobTitleInfo());
        holder.aTitleCRDVW.setOnClickListener(new cardViewClickListener(position));
    }

    @Override
    public int getItemCount() {
        return myJobInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView aTitleTXT;
        private CardView aTitleCRDVW;

        public MyViewHolder(View itemView) {
            super(itemView);
            aTitleTXT = (TextView) itemView.findViewById(R.id.layout_inflate_job_list_item_TXT_title);
            aTitleCRDVW = (CardView) itemView.findViewById(R.id.layout_inflate_job_list_item_CRDVW_title);
        }
    }

    private class cardViewClickListener implements View.OnClickListener {
        int myPosition;

        public cardViewClickListener(int aPosition) {
            myPosition = aPosition;
        }

        @Override
        public void onClick(View view) {
            JobListActivity aActivity = (JobListActivity) myContext;
            aActivity.navigateScreen(myJobInfoList.get(myPosition).getJobTitleInfo());

        }
    }
}
