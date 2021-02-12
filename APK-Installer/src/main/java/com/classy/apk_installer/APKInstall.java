package com.classy.apk_installer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class APKInstall extends AsyncTask<String, Integer, Boolean> {

    private ProgressBar bar;
    private TextView text;
    private final Context context;
    private final String url_string;
    private Activity activity;

    public APKInstall(Context context, Activity activity, ProgressBar bar, TextView textView, String url_string) {
        this.context = context;
        this.activity = activity;
        this.bar = bar;
        this.text = textView;
        this.url_string = url_string;
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        Log.d("AAAT", "doInBackGround: ");
        boolean flag = false;
        try {
            URL url = new URL(url_string);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(false);
            c.connect();
            String PATH = Environment.getExternalStorageDirectory() + "/Download/";
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "app.apk");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            int total_size = 4581692;
            //int total_size = is.available();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            int per = 0;
            int downloaded = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
                downloaded += len1;
                per = (int) (downloaded * 100 / total_size);
                publishProgress(per);
            }
            fos.close();
            is.close();
            installAPK(PATH);
            flag = true;
        } catch (Exception e) {
            Log.e("AAAT", "Update Error: " + e.getMessage());
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    void installAPK(String location) {
        Log.d("AAAT", "installAPK: ");
        Uri uri = FileProvider.getUriForFile(activity, BuildConfig.LIBRARY_PACKAGE_NAME + ".fileprovider", new File(location + "ApkName.apk"));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
    }

    @Override
    protected void onPreExecute() {
        Log.d("AAAT", "onPreExecute: ");
        super.onPreExecute();
        bar = new ProgressBar(activity);
        text.setText("Downloading...");
        bar.setIndeterminate(true);
        bar.setVisibility(View.VISIBLE);
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.d("AAAT", "onProgressUpdate: ");
        super.onProgressUpdate(progress);
        bar.setIndeterminate(false);
        bar.setMax(100);
        bar.setProgress(progress[0]);
        String msg = "";
        if (progress[0] > 99) {
            msg = "Finishing... ";
        } else {
            msg = "Downloading... " + progress[0] + "%";
        }
        text.setText(msg);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("AAAT", "onPostExecute: ");
        super.onPostExecute(result);
        activity.runOnUiThread(() -> bar.setVisibility(View.INVISIBLE));
        Log.d("AAAT", "Done I guess... Sadge");
        if (result) {
            Toast.makeText(activity.getApplicationContext(), "Done!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Error: Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
