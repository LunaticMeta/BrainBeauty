package com.example.jhj0104.brainbeauty.Human;

/**
 * Created by jhj0104 on 2016-11-23.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jhj0104.brainbeauty.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SMainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Human> h_info_list;
    HumanAdapter myadapter;
    Human myHuman1,myHuman2,myHuman3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smain);
        listView = (ListView)findViewById(R.id.Imagelistview);
        myHuman1 = new Human("A", "학생", "26", BitmapFactory.decodeResource(getResources(), R.drawable.heart));
        myHuman2 = new Human("B", "학생", "22", BitmapFactory.decodeResource(getResources(), R.drawable.heart_blank));
        myHuman3 = new Human("C", "학생", "21", BitmapFactory.decodeResource(getResources(), R.drawable.heart_blank2));
        h_info_list = new ArrayList<Human>();
        h_info_list.add(myHuman1);
        h_info_list.add(myHuman2);
        h_info_list.add(myHuman3);
        h_info_list.add(myHuman1);
        h_info_list.add(myHuman2);
        h_info_list.add(myHuman3);
        myadapter = new HumanAdapter(getApplicationContext(),R.layout.human_info, h_info_list);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class); // 다음넘어갈 화면
                Bitmap sendBitmap = h_info_list.get(position).image;

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });
    }
}
