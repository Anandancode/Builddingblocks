package com.buildingblocks.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.Utils.DBHelper;
import com.buildingblocks.adapters.MaterialDetailRecylerAdapter;
import com.buildingblocks.pojo.MaterialInfoPojo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MaterialDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView myMaterialRCYLV;
    private String myJobTitleStr;
    private LinearLayout myBackLAY;
    private DBHelper myDBHelper;
    private ArrayList<MaterialInfoPojo> myMaterialInfoList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);
        getIntentValues();
        classAndWidgetInitialize();
    }

    private void getIntentValues() {
        if (getIntent() != null) {
            myJobTitleStr = getIntent().getExtras().getString("JOB_TITLE");
        }
    }

    private void classAndWidgetInitialize() {
        myDBHelper = new DBHelper(MaterialDetailActivity.this);
        myMaterialRCYLV = (RecyclerView) findViewById(R.id.activity_jobdetail_RCYLV_material);
        myBackLAY = (LinearLayout) findViewById(R.id.activity_jobdetail_LAY_back);
        myMaterialRCYLV.setLayoutManager(new LinearLayoutManager(MaterialDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        clickListener();
        loadData();
    }

    private void clickListener() {
        myBackLAY.setOnClickListener(this);
    }

    private void loadData() {
        String aMaterialInfo = myDBHelper.getMaterialDetails(myJobTitleStr);
        myMaterialInfoList = new Gson().fromJson(aMaterialInfo, new TypeToken<ArrayList<MaterialInfoPojo>>() {
        }.getType());
        MaterialDetailRecylerAdapter aAdapter = new MaterialDetailRecylerAdapter(MaterialDetailActivity.this, myMaterialInfoList);
        myMaterialRCYLV.setAdapter(aAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_jobdetail_LAY_back:
                finish();
                break;
        }
    }
}
