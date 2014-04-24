
package com.zeroapp.action.util;

public class Log {

    private static int debugLevel;
    private static boolean isDebug = true;
    public static final String TAG = "ZeroApp";

    /**
     * Send a DEBUG log message.
     * 
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug && debugLevel <= android.util.Log.DEBUG)
            android.util.Log.d(TAG, msg);
    }

    /**
     * Send a DEBUG log message.
     * 
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug && debugLevel <= android.util.Log.DEBUG)
            android.util.Log.d(tag, msg);
    }

    /**
     * Send a DEBUG log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     */
    public static void d(String tag, String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.DEBUG)
            android.util.Log.d(tag, msg, thr);
    }

    /**
     * Send a DEBUG log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     */
    public static void d(String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.DEBUG)
            android.util.Log.d(TAG, msg, thr);
    }

    /**
     * Send an ERROR log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void e(String msg) {
        if (isDebug && debugLevel <= android.util.Log.ERROR)
            android.util.Log.e(TAG, msg);
    }

    /**
     * Send an ERROR log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (isDebug && debugLevel <= android.util.Log.ERROR)
            android.util.Log.e(tag, msg);
    }

    /**
     * Send an ERROR log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void e(String tag, String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.ERROR)
            android.util.Log.e(tag, msg, thr);
    }

    /**
     * Send an ERROR log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void e(String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.ERROR)
            android.util.Log.e(TAG, msg, thr);
    }

    public static void i(String msg) {
        if (debugLevel <= android.util.Log.INFO)
            android.util.Log.i(TAG, msg);
    }

    /**
     * Send an INFO log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (debugLevel <= android.util.Log.INFO)
            android.util.Log.i(tag, msg);
    }

    /**
     * Send a INFO log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void i(String tag, String msg, Throwable thr) {
        if (debugLevel <= android.util.Log.INFO)
            android.util.Log.i(tag, msg, thr);
    }

    /**
     * Send a INFO log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void i(String msg, Throwable thr) {
        if (debugLevel <= android.util.Log.INFO)
            android.util.Log.i(TAG, msg, thr);
    }

    public static void setDebug(boolean isdebug) {
        isDebug = isdebug;
    }

    public static void setDebugLevel(int db) {
        debugLevel = db;
    }

    /**
     * Send a VERBOSE log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void v(String msg) {
        if (isDebug && debugLevel <= android.util.Log.VERBOSE)
            android.util.Log.v(TAG, msg);
    }

    /**
     * Send a VERBOSE log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (isDebug && debugLevel <= android.util.Log.VERBOSE)
            android.util.Log.v(tag, msg);
    }

    /**
     * Send a VERBOSE log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void v(String tag, String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.VERBOSE)
            android.util.Log.v(tag, msg, thr);
    }

    /**
     * Send a VERBOSE log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void v(String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.VERBOSE)
            android.util.Log.v(TAG, msg, thr);
    }

    /**
     * Send a WARN log message
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void w(String msg) {
        if (isDebug && debugLevel <= android.util.Log.WARN)
            android.util.Log.w(TAG, msg);
    }

    /**
     * Send a WARN log message
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (isDebug && debugLevel <= android.util.Log.WARN)
            android.util.Log.w(tag, msg);
    }

    /**
     * Send a WARN log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void w(String tag, String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.WARN)
            android.util.Log.w(tag, msg, thr);
    }

    /**
     * Send a WARN log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void w(String msg, Throwable thr) {
        if (isDebug && debugLevel <= android.util.Log.WARN)
            android.util.Log.w(TAG, msg, thr);
    }

    public Log() {
    }

}
