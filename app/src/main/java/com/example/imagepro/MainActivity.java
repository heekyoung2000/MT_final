package com.example.imagepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //bottomnavigationview 출력
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeItem);

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
                        startActivity(new Intent(getApplicationContext()
                                , opencvActivity.class));
                        overridePendingTransition(0, 0);
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
        //오늘의 날짜 출력
        tvDate = (TextView) findViewById(R.id.homeItem);
        tvDate.setText(getTime());
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 회원님의 식단입니다");
        String getTime = dateFormat.format(date);

        return getTime;
    }

}