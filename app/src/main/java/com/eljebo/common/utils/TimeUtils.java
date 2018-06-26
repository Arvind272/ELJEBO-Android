package com.eljebo.common.utils;

/**
 * Created by TOXSL\paramveer.rana on 5/7/17.
 */


public class TimeUtils {
    public enum DIFFERNECE_FACTOR {SEC, MIN, HOUR, DAY, MONTH, YEAR}

    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;

    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long MINUTES = 60;

    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long HOURS = 24;

    public final static long ONE_DAY = ONE_HOUR * 24;
    public final static long ONE_MONTH = ONE_DAY * 30;
    public final static long ONE_YEAR = ONE_MONTH * 365;

    private TimeUtils() {
    }

    /**
     * converts time (in milliseconds) to human-readable format
     * "<w> days, <x> hours, <y> minutes and (z) seconds"
     */
    public static String millisToLongDHMS(long duration) {
        StringBuffer res = new StringBuffer();
        long temp = 0;
        if (duration >= ONE_SECOND) {
            temp = duration / ONE_DAY;
            if (temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" day").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" hour").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" min").append(temp > 1 ? "s" : "");
            }

            if (!res.toString().equals("") && duration >= ONE_SECOND) {
                res.append(" and ");
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                res.append(temp).append(" s");
            }
            return res.toString();
        } else {
            return "0 second";
        }
    }

    public static boolean checkDateDiff(long timeInMillis, long compareWithTimeInMillis, DIFFERNECE_FACTOR differenceFactor, int differenceValue) {
        long difference = 0;
        switch (differenceFactor) {
            case SEC:
                difference = ONE_SECOND * differenceValue;
                break;
            case MIN:
                difference = ONE_MINUTE * differenceValue;
                break;
            case HOUR:
                difference = ONE_HOUR * differenceValue;
                break;
            case DAY:
                difference = ONE_DAY * differenceValue;
                break;
            case MONTH:
                difference = ONE_MONTH * differenceValue;
                break;
            case YEAR:
                difference = ONE_YEAR * differenceValue;
                break;
        }
        return compareWithTimeInMillis - timeInMillis > difference;
    }

    public static String getShortDHMS(long duration) {

        if (duration < ONE_MINUTE)
            return (duration / ONE_SECOND) + " s";
        else if (duration < ONE_HOUR)
            return (duration / ONE_MINUTE) + " min";
        else if (duration < ONE_DAY)
            return (duration / ONE_HOUR) + " hour";
        else
            return (duration / ONE_DAY) + " day";
    }

    public static String getAgoTime(long timeInMillis) {
        return getShortDHMS(System.currentTimeMillis() - timeInMillis) + " ago";
    }

}

