package com.example.jhj0104.brainbeauty.Human;

/**
 * Created by jhj0104 on 2016-11-23.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.jhj0104.brainbeauty.R;

public class ImageActivity extends AppCompatActivity {
    Bitmap image;
    ImageView BigImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        BigImage = (ImageView)findViewById(R.id.BigImage);

        Intent intent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("image");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        BigImage.setImageBitmap(image);
    }
}
