package com.iamdeveloper.appusage;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import Adapter.UsageAppAdapter;
import Model.UsageModel;


public class AppUsageFragment extends Fragment {
    private ListView listview;
    private Context context;
    private List<UsageModel> list;
    private UsageModel model;
    private UsageAppAdapter adapter;
    public AppUsageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_usage, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        list = new ArrayList<UsageModel>();
        listview = (ListView) view.findViewById(R.id.usage_list);

    }

    @Override
    public void onResume() {
        super.onResume();
        getAppRunning();
        adapter = new UsageAppAdapter(context, list);
        listview.setAdapter(adapter);

    }

    private void getAppRunning() {
        // Below Android Lollipop
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> infoList = manager.getRunningTasks(Integer.MAX_VALUE);
            list.clear();
            for(int i = 0;i<infoList.size();i++){
                ComponentName info = infoList.get(i).topActivity;

                try {
                    ApplicationInfo ai = context.getPackageManager().getApplicationInfo(info.getPackageName(),0);
                    String name = (String) context.getPackageManager().getApplicationLabel(ai);
                    Drawable icon =  context.getPackageManager().getApplicationIcon(ai);

                    model = new UsageModel();
                    model.setAppName(name);
                    model.setIcon(icon);
                    list.add(model);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
