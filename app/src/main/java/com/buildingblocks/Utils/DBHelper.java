package com.buildingblocks.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.buildingblocks.pojo.JobInfoPojo;
import com.buildingblocks.pojo.ParentJobPojo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DBHelper implements Commonvalues {

    private DataBaseHelper myDBHelper;
    private SQLiteDatabase myDataBase;
    private Context myContext;
    private int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        myContext = context;
        myDBHelper = new DataBaseHelper(context);
        myDataBase = myDBHelper.getWritableDatabase();
        open();
    }


    public class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase aDB, int aOldVersion, int aNewVersion) {
            String aAlter = "";

            Log.e("TAG", "onUpgrade called " + aOldVersion + " NEW" + aNewVersion);
            String aInsert = "";

        }
    }

    /**
     * Function to make DB as readable and writable
     */
    private void open() {
        try {
            if (myDataBase == null) {
                myDataBase = myDBHelper.getWritableDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Function to Close the database
     */
    public void close() {
        try {
            Log.d("TAG", "mySQLiteDatabase Closed");
            // ---Closing the database---
            myDBHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Truncate Table
     *
     * @param aTableName
     */
    public void truncateTable(String aTableName) {
        try {
            String aQuery = "DELETE  FROM " + aTableName;
            myDataBase.execSQL(aQuery);

        } catch (SQLiteException e) {
            Log.e("TAG", e.getMessage().toString());
        }
    }

    /**
     * update Parent details
     *
     * @param aParentInfoList
     */
    public void updateJobDetails(ArrayList<ParentJobPojo> aParentInfoList) {
        for (int i = 0; i < aParentInfoList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(JOB_TITLE, aParentInfoList.get(i).getParentJobTitle());
            values.put(JOB_DESCRIPTION, aParentInfoList.get(i).getParentdescription());
            values.put(MATERIAL_INFO, new Gson().toJson(aParentInfoList.get(i).getMaterialPojo()));
            myDataBase.insert(JOBINFO_TABLE_NAME, null, values);
        }
    }


    /**
     * Get the Job List Details
     *
     * @return
     */
    public ArrayList<JobInfoPojo> getJobListDetails() {

        ArrayList<JobInfoPojo> aRecentAddressInfos = new ArrayList<JobInfoPojo>();
        try {
            String aQuery = "SELECT * FROM " + JOBINFO_TABLE_NAME;

            Cursor aCursor = myDataBase.rawQuery(aQuery, null);
            aCursor.moveToFirst();
            if (aCursor.getCount() > 0) {
                while (!aCursor.isAfterLast()) {
                    JobInfoPojo aRecentAddressInfo = new JobInfoPojo();
                    aRecentAddressInfo.setJobTitleInfo(aCursor.getString(aCursor.getColumnIndex(JOB_TITLE)));
                    aRecentAddressInfos.add(aRecentAddressInfo);
                    aCursor.moveToNext();
                }
            }
            aCursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aRecentAddressInfos;
    }

    public String getMaterialDetails(String aJobTitleStr) {

        String aMaterialInfoListStr = "";

        String aQuery = "SELECT * FROM " + JOBINFO_TABLE_NAME + " WHERE job_title = '" + aJobTitleStr + "'";

        Cursor aCursor = myDataBase.rawQuery(aQuery, null);

        aCursor.moveToFirst();

        if (aCursor.getCount() > 0) {
            while (!aCursor.isAfterLast()) {
                aMaterialInfoListStr = aCursor.getString(aCursor.getColumnIndex(MATERIAL_INFO));
                aCursor.moveToNext();
            }
        }

        aCursor.close();

        return aMaterialInfoListStr;
    }
}
