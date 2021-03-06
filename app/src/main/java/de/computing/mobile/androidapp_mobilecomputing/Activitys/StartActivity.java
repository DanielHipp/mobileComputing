package de.computing.mobile.androidapp_mobilecomputing.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.computing.mobile.androidapp_mobilecomputing.Connector;
import de.computing.mobile.androidapp_mobilecomputing.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Connector connector = new Connector();
        connector.sendVolleyMessage("Hi", this);

        //Button OnClick Listeners >>>>
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
