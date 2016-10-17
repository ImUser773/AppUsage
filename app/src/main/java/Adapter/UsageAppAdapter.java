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

/**
 * Created by IamDeveloper on 10/14/2016.
 */
public class UsageAppAdapter extends BaseAdapter {
    Context context;
    List<UsageModel> list = new ArrayList<>();

    public UsageAppAdapter(Context context, List<UsageModel> list) {
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
            v = LayoutInflater.from(context).inflate(R.layout.content_listview_usage, null);

        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView textView = (TextView) v.findViewById(R.id.text);

        image.setImageDrawable(list.get(i).getIcon());
        textView.setText(list.get(i).getAppName());

        return v;
    }
}
