package Model;

import android.graphics.drawable.Drawable;

/**
 * Created by IamDeveloper on 10/17/2016.
 */
public class UsageTimeModel {
    private long second;
    private long minute;
    private long hour;
    private String name;
    private Drawable drawable;

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

}
