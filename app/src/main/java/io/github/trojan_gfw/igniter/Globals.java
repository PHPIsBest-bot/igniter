package io.github.trojan_gfw.igniter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import io.github.trojan_gfw.igniter.common.constants.ConfigFileConstants;

public class Globals {

    private static String exportDir;
    private static String cacheDir;
    private static String filesDir;
    private static TrojanConfig trojanConfigInstance;
    private static TrojanConfig defaultConfig1;
    private static TrojanConfig defaultConfig2;

    public static void Init(Context ctx) {
        cacheDir = ctx.getCacheDir().getAbsolutePath();
        filesDir = ctx.getFilesDir().getAbsolutePath();
        trojanConfigInstance = new TrojanConfig();
        exportDir = exportDir(ctx, filesDir);
        defaultConfig1 = new TrojanConfig();
        defaultConfig1.setRemoteAddr("156.67.221.103");
        defaultConfig1.setRemotePort(443);
        defaultConfig1.setSNI("www3.ruok.buzz");
        defaultConfig1.setPassword("c75a8729");
        defaultConfig2 = new TrojanConfig();
        defaultConfig2.setRemoteAddr("95.179.152.149");
        defaultConfig2.setRemotePort(443);
        defaultConfig2.setSNI("www2.ruok.buzz");
        defaultConfig2.setPassword("jrt123");
    }

    private static String exportDir(Context ctx, String defaultDir) {
        try {
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                // external storage not ready
                return defaultDir;
            }
            //external storage is ready
            File externalFilesDir = ctx.getExternalFilesDir(ConfigFileConstants.CONFIGS);
            if (externalFilesDir == null) {
                return defaultDir;
            }
            return externalFilesDir.getAbsolutePath();
        } catch (Exception e) {
            Log.e("globals", "get export dir error", e);
        }
        return defaultDir;
    }

    public static String getCaCertPath() {
        return PathHelper.combine(cacheDir, "cacert.pem");
    }

    public static String getCountryMmdbPath() {
        return PathHelper.combine(filesDir, "Country.mmdb");
    }

    public static String getClashConfigPath() {
        return PathHelper.combine(filesDir, "config.yaml");
    }

    public static String getTrojanConfigPath() {
        return PathHelper.combine(filesDir, "config.json");
    }

    public static String getTrojanConfigListPath() {
        return PathHelper.combine(filesDir, "config_list.json");
    }

    public static String getIgniterExportPath() {
        return PathHelper.combine(exportDir, "config_list.txt");
    }

    public static String getPreferencesFilePath() {
        return PathHelper.combine(filesDir, "preferences.txt");
    }

    public static String getBlockedAppListPath() {
        return PathHelper.combine(filesDir, "exempted_app_list.txt");
    }

    public static String getAllowedAppListPath() {
        return PathHelper.combine(filesDir, "allow_app_list.txt");
    }

    public static void setTrojanConfigInstance(TrojanConfig config) {
        trojanConfigInstance = config;
    }

    public static TrojanConfig getTrojanConfigInstance() {
        return trojanConfigInstance;
    }
    public static TrojanConfig getBestConfigInstance() {
        return defaultConfig2;
    }
}
