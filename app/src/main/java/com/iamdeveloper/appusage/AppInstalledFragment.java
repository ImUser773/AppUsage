package com.iamdeveloper.appusage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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


public class AppInstalledFragment extends Fragment {
    private ListView listview;
    private Context context;
    private List<UsageModel> appList;
    private UsageModel model;
    private UsageAppAdapter adapter;


    public AppInstalledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_installed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        appList = new ArrayList<UsageModel>();
        listview = (ListView) view.findViewById(R.id.usage_list);
    }

    @Override
    public void onResume() {
        super.onResume();

        getAppInstalled();
        adapter = new UsageAppAdapter(context, appList);
        listview.setAdapter(adapter);
    }

    private void getAppInstalled() {
        PackageManager manager = context.getPackageManager();
        List<ApplicationInfo> list = manager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo ai : list) {
            String name = (String) context.getPackageManager().getApplicationLabel(ai);
            Drawable icon = context.getPackageManager().getApplicationIcon(ai);

            model = new UsageModel();
            model.setAppName(name);
            model.setIcon(icon);
            appList.add(model);
        }
    }


}
