package com.example.jobscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView my_image;
    TextView tvUpdateDownload;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_image = findViewById(R.id.iv);
        tvUpdateDownload = findViewById(R.id.tv);

    }

    public void startJob(View v){
        String imagePath = Environment.getExternalStorageDirectory().getPath()+"/coba/";
        File dir = new File(imagePath);
        if (dir.exists()) {
            File imageFIle = new File(imagePath + "downloadfile.jpg");
            imageFIle.delete();
        }

        Dexter.withContext(getApplication())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        my_image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher_background));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Util.scheduleJob(getApplicationContext());
                        }
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    public void endJob(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Util.stopJob(getApplicationContext());
            updateImage();
        }
    }

    public void updateImage(){
        String imagePath = Environment.getExternalStorageDirectory().getPath()+"/coba/downloadfile.jpg";
        my_image.setImageDrawable(Drawable.createFromPath(imagePath));

    }

}