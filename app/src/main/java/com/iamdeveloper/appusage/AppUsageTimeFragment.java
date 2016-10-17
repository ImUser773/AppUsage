package com.iamdeveloper.appusage;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.UsageAppTimeAdapter;
import Model.UsageModel;
import Model.UsageTimeModel;


public class AppUsageTimeFragment extends Fragment {
    private UsageTimeModel model;
    private Context context;
    private List<UsageTimeModel> list;
    private UsageAppTimeAdapter adapter;
    private ListView listView;

    public AppUsageTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_usage_time, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        list = new ArrayList<UsageTimeModel>();
        listView = (ListView) view.findViewById(R.id.usage_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        appUsageTime();
        adapter = new UsageAppTimeAdapter(context,list);
        listView.setAdapter(adapter);
    }

    private void appUsageTime() {
        long dt = 0;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> infoList = manager.getRunningAppProcesses();
            list.clear();
            for (int i = 0; i < infoList.size(); i++) {
                int id = infoList.get(i).pid;
                String pName = infoList.get(i).processName;
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(pName, 0);
                    String name = (String) context.getPackageManager().getApplicationLabel(applicationInfo);
                    Drawable icon =  context.getPackageManager().getApplicationIcon(applicationInfo);
                    Log.i("ID", id + " :NAME :" + name);
                    dt = SystemClock.elapsedRealtime() - getStartTime(id);

                    long second = (dt / 1000) % 60;
                    long minute = (dt / (1000 * 60)) % 60;
                    long hour = (dt / (1000 * 60 * 60)) % 24;
                    Log.i("AppUsageTime", hour + " : " + minute + " : " + second);

                    model = new UsageTimeModel();
                    model.setName(name);
                    model.setDrawable(icon);
                    model.setHour(hour);
                    model.setMinute(minute);
                    model.setSecond(second);
                    list.add(model);


                } catch (PackageManager.NameNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static long getStartTime(final int pid) throws IOException {
        final String path = "/proc/" + pid + "/stat";
        final BufferedReader reader = new BufferedReader(new FileReader(path));
        final String stat;
        try {
            stat = reader.readLine();
        } finally {
            reader.close();
        }
        final String field2End = ") ";
        final String fieldSep = " ";
        final int fieldStartTime = 20;
        final int msInSec = 1000;
        try {
            final String[] fields = stat.substring(stat.lastIndexOf(field2End)).split(fieldSep);
            final long t = Long.parseLong(fields[fieldStartTime]);
            final int tckName = Class.forName("libcore.io.OsConstants").getField("_SC_CLK_TCK").getInt(null);
            final Object os = Class.forName("libcore.io.Libcore").getField("os").get(null);
            final long tck = (Long) os.getClass().getMethod("sysconf", Integer.TYPE).invoke(os, tckName);
            return t * msInSec / tck;
        } catch (final NumberFormatException e) {
            throw new IOException(e);
        } catch (final IndexOutOfBoundsException e) {
            throw new IOException(e);
        } catch (ReflectiveOperationException e) {
            throw new IOException(e);
        }
    }
}
