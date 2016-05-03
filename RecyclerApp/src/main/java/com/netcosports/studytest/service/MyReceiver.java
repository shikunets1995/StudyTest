package com.netcosports.studytest.service;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;

        import com.netcosports.studytest.activity.DbActivity;

/**
 * Created by Шикунец on 22.04.2016.
 */
public class MyReceiver extends  BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent detected", Toast.LENGTH_LONG).show();
        DownloadService.launch(context);

    }


}
