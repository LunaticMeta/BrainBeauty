package com.example.jhj0104.brainbeauty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PersonalData extends AppCompatActivity {

    //Here is personaldata page.
    //When u write your personaldata than i create new sharedpreference to save it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
    }

    //set Sharedpreference ID
    public void btn_savePersonalData(View view){
    }


    // 값 불러오기
    private void getPreferences_load(String name, String word){ //mode = MODE_PRIVATE
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        pref.getString("hi", word);
    }

    // 값 저장하기
    private void savePreferences_save(String name, String word){
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("hi", word);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    private void removePreferences_delete(String name, String word){
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("hi");
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    private void removeAllPreferences_reset(String name){
        SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
