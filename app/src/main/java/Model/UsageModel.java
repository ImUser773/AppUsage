package Model;

import android.graphics.drawable.Drawable;

/**
 * Created by IamDeveloper on 10/14/2016.
 */
public class UsageModel {
    String appName;
    Drawable icon;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
