package com.buildingblocks.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.buildingblocks.R;
import com.buildingblocks.Utils.Commonvalues;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashActivity extends AppCompatActivity implements Commonvalues {

    private String myDBFileName;
    private DBInsert myDBLoadTask = null;
    private TextView myWelcomeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_page);

        myWelcomeTXT = (TextView) findViewById(R.id.actvity_splash_TXT_welcome);
        YoYo.with(Techniques.ZoomIn)
                .duration(1100)
                .playOn(myWelcomeTXT);
        myDBFileName = getApplicationContext().getDatabasePath(DATABASE_NAME).toString();

        if (!isDBExist(myDBFileName)) {
            Log.e("TAG", "NOT PRESENT & Started async task");
            myDBLoadTask = new DBInsert();
            myDBLoadTask.execute();
        } else {
            Log.e("TAG", "DB PRESENT");
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent aintent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(aintent);
            }
        }, 2000);
    }

    private boolean isDBExist(String aDBFileName) {

        boolean aStatus = false;

        SQLiteDatabase aCheck = null;
        try {

            File file = new File(aDBFileName);
            if (file.exists() && !file.isDirectory()) {
                aCheck = SQLiteDatabase.openDatabase(aDBFileName, null,
                        SQLiteDatabase.OPEN_READONLY
                                | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

                aStatus = (aCheck != null) ? true : false;

                if (aStatus)
                    aCheck.close();
            } else {
                aStatus = false;
            }

        } catch (SQLiteException e) {
            e.printStackTrace();

        }
        return aStatus;
    }


    public void constructNewFileFromResources(String DBFile) {

        try {
            String packageName = getApplicationContext().getPackageName();

            String appDatabaseDirectory = String.format(
                    "/data/data/%s/databases", packageName);
            (new File(appDatabaseDirectory)).mkdir();
            OutputStream dos = new FileOutputStream(appDatabaseDirectory + "/"
                    + DATABASE_NAME);

            InputStream dis = getResources().openRawResource(
                    DB_RAW_RESOURCES_ID);
            byte[] buffer = new byte[1028];
            while ((dis.read(buffer)) > 0) {
                dos.write(buffer);
            }
            dos.flush();
            dos.close();
            dis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class DBInsert extends AsyncTask<Void, Void, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Long doInBackground(Void... params) {

            constructNewFileFromResources(myDBFileName);
            return null;
        }

        protected void onPostExecute(Long result) {
            super.onPostExecute(result);

            Log.d("SPLASH", "Completed" + result);

        }
    }
}
