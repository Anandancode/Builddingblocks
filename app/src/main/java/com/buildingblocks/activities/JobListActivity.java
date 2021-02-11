package com.buildingblocks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.Utils.DBHelper;
import com.buildingblocks.adapters.JobListRecylerAdapter;
import com.buildingblocks.pojo.JobInfoPojo;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView myJobListRCYLV;
    private LinearLayout myBackLAY;
    private DBHelper myDBHelper;
    private ArrayList<JobInfoPojo> myJobInfoList = null;
    private JobListRecylerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblist);
        classAndWidgetInitialize();
    }

    private void classAndWidgetInitialize() {
        myDBHelper = new DBHelper(JobListActivity.this);
        myJobListRCYLV = (RecyclerView) findViewById(R.id.activity_joblist_RCYLV_job);
        myBackLAY = (LinearLayout) findViewById(R.id.activity_joblist_LAY_back);
        myJobListRCYLV.setLayoutManager(new LinearLayoutManager(JobListActivity.this, LinearLayoutManager.VERTICAL, false));

        clickListener();
        loadData();
    }

    private void loadData() {
        myJobInfoList = myDBHelper.getJobListDetails();
        myAdapter = new JobListRecylerAdapter(JobListActivity.this, myJobInfoList);
        myJobListRCYLV.setAdapter(myAdapter);
    }

    private void clickListener() {
        myBackLAY.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_joblist_LAY_back:
                finish();
                break;
        }
    }

    public void navigateScreen(String aJobTitleStr) {
        Intent aIntent = new Intent(JobListActivity.this, MaterialDetailActivity.class);
        aIntent.putExtra("JOB_TITLE", aJobTitleStr);
        startActivity(aIntent);
    }
}
