package com.buildingblocks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.Utils.Commonvalues;
import com.buildingblocks.Utils.DBHelper;
import com.buildingblocks.adapters.JobTopRecylerAdapter;
import com.buildingblocks.adapters.MaterialRecylerAdapter;
import com.buildingblocks.pojo.JobInfoPojo;
import com.buildingblocks.pojo.MaterialInfoPojo;
import com.buildingblocks.pojo.ParentJobPojo;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Commonvalues {

    private RecyclerView myTopRCYLV, myMaterialRCYLV;
    private ArrayList<JobInfoPojo> myJobInfoList = null;
    private Button myAddJobBTN;
    private JobTopRecylerAdapter myAdapter;
    private ArrayList<MaterialInfoPojo> myMaterialInfoList = null;
    private TextView myAddMaterialTXT;
    private MaterialRecylerAdapter myMaterialListAdapter;
    private Button mySubmitBTN;
    private ArrayList<ParentJobPojo> myParentInfoList = null;
    private int myPosition = 0;
    private EditText myDescriptionET;
    private DBHelper myDBHelper;
    private TextView mySubTotalTXT;
    private TextView myFinalTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classAndWidgetInitialize();
    }

    private void classAndWidgetInitialize() {
        myDBHelper = new DBHelper(MainActivity.this);
        myJobInfoList = new ArrayList<>();
        myParentInfoList = new ArrayList<>();
        myMaterialInfoList = new ArrayList<>();
        myTopRCYLV = (RecyclerView) findViewById(R.id.activity_main_RCYLV_top);
        myAddJobBTN = (Button) findViewById(R.id.activity_main_BTN_addjob);
        myAddMaterialTXT = (TextView) findViewById(R.id.activity_main_TXT_addmaterial);
        myMaterialRCYLV = (RecyclerView) findViewById(R.id.activity_main_RCYLV_material);
        mySubmitBTN = (Button) findViewById(R.id.activity_main_BTN_submit);
        myDescriptionET = (EditText) findViewById(R.id.activity_main_ET_description);
        mySubTotalTXT = (TextView) findViewById(R.id.activity_main_TXT_subtotal);
        myFinalTXT = (TextView) findViewById(R.id.activity_main_TXT_finaltotal);

        myDBHelper.truncateTable(JOBINFO_TABLE_NAME);
        myTopRCYLV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        myTopRCYLV.setHasFixedSize(true);

        myMaterialRCYLV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        clickListener();
        loadData();
        textchangedListener();
        //   loadMaterialData();
    }

    private void textchangedListener() {
        myDescriptionET.addTextChangedListener(new TextWatcher() {
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
                        ParentJobPojo aParentPojo = myParentInfoList.get(myPosition);
                        aParentPojo.setParentdescription(myDescriptionET.getText().toString());
                        myParentInfoList.set(myPosition, aParentPojo);
                    }
                }, 100);
            }
        });

    }


    private void clickListener() {
        myAddJobBTN.setOnClickListener(this);
        myAddMaterialTXT.setOnClickListener(this);
        mySubmitBTN.setOnClickListener(this);
    }

    private void loadData() {
        int aCount = 0;
        JobInfoPojo aPojo = new JobInfoPojo();
        aPojo.setJobTitleInfo("Job" + " " + aCount);
        aPojo.setSelected(true);
        aPojo.setTobTitleId(String.valueOf(aCount));
        myJobInfoList.add(aPojo);

        ArrayList<MaterialInfoPojo> aMaterialPojoInfoList = new ArrayList<>();
        MaterialInfoPojo aMaterialPojo = new MaterialInfoPojo();
        aMaterialPojo.setMaterialItemCost("");
        aMaterialPojo.setMaterialItemName("");
        aMaterialPojo.setMaterialItemQty("");
        aMaterialPojo.setMaterialItemTax("");
        aMaterialPojo.setMaterialItemTotal("");
        aMaterialPojo.setMaterialCount("1");
        aMaterialPojoInfoList.add(aMaterialPojo);

        ParentJobPojo aParentPojo = new ParentJobPojo();
        aParentPojo.setParentJobTitle("Job" + " " + aCount);
        aParentPojo.setMaterialPojo(aMaterialPojoInfoList);
        aParentPojo.setParentdescription("");
        myParentInfoList.add(aParentPojo);

        myAdapter = new JobTopRecylerAdapter(MainActivity.this, myJobInfoList);
        myTopRCYLV.setAdapter(myAdapter);

        myMaterialListAdapter = new MaterialRecylerAdapter(MainActivity.this, aMaterialPojoInfoList, myPosition, myParentInfoList);
        myMaterialRCYLV.setAdapter(myMaterialListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.activity_main_BTN_addjob:

                ArrayList<JobInfoPojo> myPojoInfoList = myAdapter.getArrayList();
                int aCount = myPojoInfoList.size();
                JobInfoPojo aPojo = new JobInfoPojo();
                aPojo.setJobTitleInfo("Job" + " " + aCount);
                aPojo.setTobTitleId(String.valueOf(aCount));
                myJobInfoList.add(aPojo);
                myAdapter.notifyDataSetChanged();

                ArrayList<MaterialInfoPojo> aMaterialInfoList = new ArrayList<>();

                MaterialInfoPojo aMaterialPojo = new MaterialInfoPojo();
                aMaterialPojo.setMaterialItemCost("");
                aMaterialPojo.setMaterialItemName("");
                aMaterialPojo.setMaterialItemQty("");
                aMaterialPojo.setMaterialItemTax("");
                aMaterialPojo.setMaterialItemTotal("");
                aMaterialPojo.setMaterialCount("1");
                aMaterialInfoList.add(aMaterialPojo);
                myMaterialListAdapter.notifyDataSetChanged();

                ParentJobPojo aParentPojo = new ParentJobPojo();
                aParentPojo.setParentJobTitle("Job" + " " + aCount);
                aParentPojo.setMaterialPojo(aMaterialInfoList);
                aParentPojo.setParentdescription("");
                myParentInfoList.add(aParentPojo);
                break;

            case R.id.activity_main_TXT_addmaterial:
                ArrayList<MaterialInfoPojo> aMaterialInfoList1 = myMaterialListAdapter.getArrayList();
                int aMaterialCount1 = aMaterialInfoList1.size() + 1;
                MaterialInfoPojo aMaterialPojo1 = new MaterialInfoPojo();
                aMaterialPojo1.setMaterialItemCost("");
                aMaterialPojo1.setMaterialItemName("");
                aMaterialPojo1.setMaterialItemQty("");
                aMaterialPojo1.setMaterialItemTax("");
                aMaterialPojo1.setMaterialItemTotal("");
                aMaterialPojo1.setMaterialCount(String.valueOf(aMaterialCount1));
                aMaterialInfoList1.add(aMaterialPojo1);
                myMaterialListAdapter.notifyDataSetChanged();

                ParentJobPojo aParentPojo1 = myParentInfoList.get(myPosition);
                aParentPojo1.setMaterialPojo(aMaterialInfoList1);
                myParentInfoList.set(myPosition, aParentPojo1);
                break;

            case R.id.activity_main_BTN_submit:
                myDBHelper.truncateTable(JOBINFO_TABLE_NAME);
                myDBHelper.updateJobDetails(myParentInfoList);
                Intent aIntent = new Intent(MainActivity.this, JobListActivity.class);
                startActivity(aIntent);
                break;
        }
    }

    public void updateViews(int aPosition) {
        ArrayList<MaterialInfoPojo> aInfoList = myParentInfoList.get(aPosition).getMaterialPojo();
        myMaterialListAdapter = new MaterialRecylerAdapter(MainActivity.this, aInfoList, aPosition, myParentInfoList);
        myMaterialRCYLV.setAdapter(myMaterialListAdapter);
        myPosition = aPosition;
        ArrayList<JobInfoPojo> myPojoInfoList = myAdapter.getArrayList();
        for (int y = 0; y < myPojoInfoList.size(); y++) {
            myPojoInfoList.get(y).setSelected(false);
        }
        JobInfoPojo aPojo = myPojoInfoList.get(aPosition);
        aPojo.setSelected(true);
        myPojoInfoList.set(aPosition, aPojo);
        myAdapter.notifyDataSetChanged();
        myDescriptionET.setText(myParentInfoList.get(myPosition).getParentdescription());
        updateTotal();
    }

    public void updateTotal() {
        DecimalFormat aDecimalFormat = new DecimalFormat("0.00");
        aDecimalFormat.setMaximumFractionDigits(2);
        int aTotal = 0;
        ArrayList<MaterialInfoPojo> aMaterialInfoList = myMaterialListAdapter.getArrayList();
        if (aMaterialInfoList.size() > 0) {
            for (int y = 0; y < aMaterialInfoList.size(); y++) {
                if (!aMaterialInfoList.get(y).getMaterialItemTotal().equalsIgnoreCase("")) {
                    aTotal = aTotal + Integer.parseInt(aMaterialInfoList.get(y).getMaterialItemTotal());
                }
            }
            mySubTotalTXT.setText("$" + aDecimalFormat.format(Float.parseFloat(String.valueOf(aTotal))));
        }
        updateFinalTotal();
    }

    private void updateFinalTotal() {
        DecimalFormat aDecimalFormat = new DecimalFormat("0.00");
        aDecimalFormat.setMaximumFractionDigits(2);
        int aTotal = 0;
        if (myParentInfoList.size() > 0) {
            for (int i = 0; i < myParentInfoList.size(); i++) {
                for (int y = 0; y < myParentInfoList.get(i).getMaterialPojo().size(); y++) {
                    if (!myParentInfoList.get(i).getMaterialPojo().get(y).getMaterialItemTotal().equalsIgnoreCase("")) {
                        aTotal = aTotal + Integer.parseInt(myParentInfoList.get(i).getMaterialPojo().get(y).getMaterialItemTotal());
                    }
                }
            }
            myFinalTXT.setText("$" + aDecimalFormat.format(Float.parseFloat(String.valueOf(aTotal))));
        }
    }


}
