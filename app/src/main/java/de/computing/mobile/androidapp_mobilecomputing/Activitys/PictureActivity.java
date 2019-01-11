package de.computing.mobile.androidapp_mobilecomputing.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import de.computing.mobile.androidapp_mobilecomputing.Connector;
import de.computing.mobile.androidapp_mobilecomputing.ImageController.ImageCompressor;
import de.computing.mobile.androidapp_mobilecomputing.R;

public class PictureActivity extends AppCompatActivity {
    private String image = "I";
    private ImageCompressor compr;
    private Connector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        connector = new Connector();
        compr = new ImageCompressor();

        //Open Gallery >>
        Button buttonToGallery = findViewById(R.id.activityPicture_findImage);
        buttonToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
            }
        });
        // <<<<

        Button buttonToSend = findViewById(R.id.activityPicture_buttonToSend);
        buttonToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connector.sendVolleyMessage(image, PictureActivity.this);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        String s = compr.changeToImageString(bitmap);
                        connector.sendVolleyMessage(s,PictureActivity.this);
                        Log.d("Gallery", "opended");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
