package de.computing.mobile.androidapp_mobilecomputing.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.computing.mobile.androidapp_mobilecomputing.Connector;
import de.computing.mobile.androidapp_mobilecomputing.R;

public class TextActivity extends AppCompatActivity {
    private String text = "S";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        final Connector connector = new Connector();

        Button buttonToSend = findViewById(R.id.activityText_buttonToSend);
        buttonToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textToSend = findViewById(R.id.activityText_textToSend);
                text += textToSend.getText().toString();
                connector.sendVolleyMessage(text, TextActivity.this);
            }
        });

    }
}
