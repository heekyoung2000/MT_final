package com.example.imagepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.opencv.android.OpenCVLoader;

public class opencvActivity extends AppCompatActivity {
    final private static String TAG = "GILBOMI";
    final static int TAKE_PICTURE = 1;
    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;

    static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity: ","Opencv is loaded");
        }
        else {
            Log.d("MainActivity: ","Opencv failed to load");
        }
    }

    private Button camera_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opencv);


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.cameraItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeItem:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.searchItem:
                        startActivity(new Intent(getApplicationContext()
                                , searchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.cameraItem:
                        return true;

                    case R.id.calendarItem:
                        startActivity(new Intent(getApplicationContext()
                                , calendarActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });

        camera_button=findViewById(R.id.camera_button);
;
        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(opencvActivity.this,CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });



    }
}