package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import static com.example.jhj0104.brainbeauty.R.id.editText1;
import static com.example.jhj0104.brainbeauty.R.id.genderGroup;

public class PersonalData extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    String[] et_name = {"Name","Gender","Age","Height","Weight"};
    EditText[] et_value = new EditText[5];
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        //editText (index : 1은 radio button)
        et_value[0] = (EditText) findViewById(editText1);
        et_value[2] = (EditText) findViewById(R.id.editText3);
        et_value[3] = (EditText) findViewById(R.id.editText4);
        et_value[4] = (EditText) findViewById(R.id.editText5);

        //radioGroup
        radioGroup = (RadioGroup) findViewById(genderGroup);
        radioGroup.setOnCheckedChangeListener(this);

        for(int i=0;i<5;i++){
            SharedPreferences sf = getSharedPreferences(et_name[i],0);
            if(i!=1){
                String str = sf.getString(et_name[i],"");
                et_value[i].setText(str);
            }
            else{
                radioGroup.check(sf.getInt(et_name[1],0));
                radioGroup.getCheckedRadioButtonId();
            }
        }
        SharedPreferences sf = getSharedPreferences("savedPersonalData",0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
    }

    public void btn_savePersonalData(View view){

        boolean IsVoid = false;

        for(int i=0; i<5;i++){
            if(i==1) continue;
            String str = et_value[i].getText().toString();
            if(str.isEmpty()){
                switch(i){
                    case 0: et_value[0].setError("성함을 입력해주세요"); break;
                    case 2: et_value[2].setError("나이를 입력해주세요"); break;
                    case 3: et_value[3].setError("키를 입력해주세요"); break;
                    case 4: et_value[4].setError("몸무게를 입력해주세요"); break;
                }
                IsVoid=true;
            }
        }
        if(IsVoid==true) return;

        for (int i = 0; i < 5; i++) {
            SharedPreferences sf = getSharedPreferences(et_name[i], 0);
            SharedPreferences.Editor editor = sf.edit();
            if (i != 1) {
                String str = et_value[i].getText().toString();
                editor.putString(et_name[i], str);
            }
            else {
                editor.putInt(et_name[1], radioGroup.getCheckedRadioButtonId());
                radioGroup.getCheckedRadioButtonId();
            }
            editor.commit();
        }
        SharedPreferences sf = getSharedPreferences("isSecond", 0);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("isSecond", true);
        editor.commit();

        Intent myIntent = new Intent(PersonalData.this, BB_menu.class);
        PersonalData.this.startActivity(myIntent);
        finish();
    }

    // 값 불러오기
    private void getPreferences_load(String name, String word){
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
