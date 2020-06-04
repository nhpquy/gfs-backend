package com.gfs.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

public class LoggerUtil {
    private final Logger logger = LoggerFactory.getLogger("LoggerUtil");

    private static final LoggerUtil instance = new LoggerUtil();

    static {
        System.setOut(new PrintStream(System.out) {
            private String TAG = "System.out";

            @Override
            public void println(String x) {
                LoggerUtil.i(TAG, x);
            }

            @Override
            public void println(boolean x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(char x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(int x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(long x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(float x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(double x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(char[] x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }

            @Override
            public void println(Object x) {
                LoggerUtil.i(TAG, String.valueOf(x));
            }
        });
        System.setErr(new PrintStream(System.err) {
            private String TAG = "System.err";

            @Override
            public void println(String x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(boolean x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(char x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(int x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(long x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(float x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(double x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(char[] x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }

            @Override
            public void println(Object x) {
                LoggerUtil.e(TAG, String.valueOf(x));
            }
        });
    }

    private LoggerUtil() {
    }

    /**
     * Info log
     *
     * @param tag
     * @param log
     */
    public static void i(String tag, String log) {
        instance.logger.info(String.format("%s: %s", tag, log));
    }

    /**
     * Debug log
     *
     * @param tag
     * @param log
     */
    public static void d(String tag, String log) {
        instance.logger.debug(String.format("%s: %s", tag, log));
    }

    /**
     * Error log
     *
     * @param tag
     * @param log
     */
    public static void e(String tag, String log) {
        instance.logger.error(String.format("%s: %s", tag, log));
    }

    /**
     * Warning log
     *
     * @param tag
     * @param log
     */
    public static void w(String tag, String log) {
        instance.logger.warn(String.format("%s: %s", tag, log));
    }

    /**
     * handle exception
     *
     * @param tag
     * @param ex
     * @param printStackTrace
     */
    public static void exception(String tag, Exception ex, boolean printStackTrace) {
        if (ex != null) {
            e(tag, ex.getMessage());
            if (printStackTrace)
                ex.printStackTrace();
        }
    }

    /**
     * Info log
     *
     * @param tag
     * @param log
     */
    public static void i(Object tag, String log) {
        i(tag.getClass().getName(), log);
    }

    /**
     * Debug log
     *
     * @param tag
     * @param log
     */
    public static void d(Object tag, String log) {
        d(tag.getClass().getName(), log);
    }

    /**
     * Error log
     *
     * @param tag
     * @param log
     */
    public static void e(Object tag, String log) {
        e(tag.getClass().getName(), log);
    }

    /**
     * Warning log
     *
     * @param tag
     * @param log
     */
    public static void w(Object tag, String log) {
        w(tag.getClass().getName(), log);
    }

    /**
     * handle exception
     *
     * @param tag
     * @param ex
     * @param printStackTrace
     */
    public static void exception(Object tag, Exception ex, boolean printStackTrace) {
        exception(tag.getClass().getName(), ex, printStackTrace);
    }
}
