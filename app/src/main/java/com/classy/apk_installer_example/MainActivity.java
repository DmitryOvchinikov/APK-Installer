package com.classy.apk_installer_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.classy.apk_installer.APKInstall;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.example_progress_bar);
        new APKInstall(this, this, progressBar, "https://play.google.com/store/apps/details?id=com.netflix.mediaclient").execute();
    }
}