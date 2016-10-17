package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.iamdeveloper.appusage.AppInstalledFragment;
import com.iamdeveloper.appusage.AppUsageFragment;
import com.iamdeveloper.appusage.AppUsageTimeFragment;

/**
 * Created by IamDeveloper on 10/12/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private static int NUMBER = 3;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AppUsageFragment();
            case 1:
                return new AppInstalledFragment();
            case 2:
                return new AppUsageTimeFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NUMBER;
    }
}
