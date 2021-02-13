package com.classy.apk_installer_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.classy.apk_installer.APKInstall;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView example_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        example_text = findViewById(R.id.example_text);
        progressBar = findViewById(R.id.example_progress_bar);
        new APKInstall(this, this, progressBar, example_text, "https://www.apkmirror.com/wp-content/themes/APKMirror/download.php?id=1968520").execute();
    }
}