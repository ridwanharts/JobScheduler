package com.example.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Util {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context){
        ComponentName componentName = new ComponentName(context, JobSchedulerService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        builder.setMinimumLatency(1 * 1000);
        builder. setOverrideDeadline(3 * 1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresCharging(false);

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void stopJob(Context context){
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        if (jobScheduler != null){
            jobScheduler.cancelAll();
        }
    }
}
