package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamdeveloper.appusage.R;

import java.util.ArrayList;
import java.util.List;

import Model.UsageModel;
import Model.UsageTimeModel;

/**
 * Created by IamDeveloper on 10/14/2016.
 */
public class UsageAppTimeAdapter extends BaseAdapter {
    Context context;
    List<UsageTimeModel> list = new ArrayList<>();

    public UsageAppTimeAdapter(Context context, List<UsageTimeModel> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (view == null)
            v = LayoutInflater.from(context).inflate(R.layout.content_listview_usage_time, null);

        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView textName = (TextView) v.findViewById(R.id.text_name);
        TextView textTime = (TextView) v.findViewById(R.id.text_time);

        String time = list.get(i).getHour() + " : " + list.get(i).getMinute() + " : " + list.get(i).getSecond();

        image.setImageDrawable(list.get(i).getDrawable());
        textName.setText(list.get(i).getName());
        textTime.setText(time);




        return v;
    }
}
