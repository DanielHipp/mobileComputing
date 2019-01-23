package de.computing.mobile.androidapp_mobilecomputing.Activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import de.computing.mobile.androidapp_mobilecomputing.Connector;
import de.computing.mobile.androidapp_mobilecomputing.Notifications.NotificationListener;
import de.computing.mobile.androidapp_mobilecomputing.R;

public class StartActivity extends AppCompatActivity {
    public static boolean notificationOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Set Color for Switch
        final Switch notificationSwitch = findViewById(R.id.start_switch);
        int[][] states = new int[][] {
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_checked}  // pressed
        };

        int[] colors = new int[] {
                Color.rgb(255,161,0),
                Color.rgb(0,255,12)
        };

        ColorStateList tintList = new ColorStateList(states, colors);
        notificationSwitch.setThumbTintList(tintList);
        notificationSwitch.setTrackTintList(tintList);

        //Button OnClick Listeners >>>>
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(notificationSwitch.isChecked()){
                    notificationOn = true;
                    notificationSwitch.setTextOn("On");
                } else {
                    notificationOn = false;
                    notificationSwitch.setTextOff("Off");
                }
            }
        });

        Button buttonText = findViewById(R.id.start_button_text);
        buttonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, TextActivity.class));
            }
        });

        Button buttonImage = findViewById(R.id.start_button_image);
        buttonImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(StartActivity.this, PictureActivity.class));
            }
        }); //<<<<

    }
}
