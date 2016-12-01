package com.example.jhj0104.brainbeauty.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by jhj0104 on 2016-11-15.
 */

public class DBHelper extends SQLiteOpenHelper {
// DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음

    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DO_LIST_DB (_id INTEGER PRIMARY KEY AUTOINCREMENT, DL_YMD TEXT, DL_TIME TEXT, DL_TITLE TEXT, DL_CONTENT TEXT, DL_FLAG TEXT);");
        db.execSQL("CREATE TABLE DIARY_DB (_id INTEGER PRIMARY KEY AUTOINCREMENT, DI_YMD TEXT, DI_TIME TEXT, DI_TITLE TEXT, DI_CONTENT TEXT, DI_WEATHER TEXT, DI_FLAG TEXT);");
        db.execSQL("CREATE TABLE DIARY_CHECK_DB (_id INTEGER PRIMARY KEY AUTOINCREMENT, DC_YMD TEXT, DC_WRITE TEXT)");
    }

    //------------------------------ ↓↓ INSERT ↓↓ ------------------------------//
    public void insert_DL(String DL_YMD, String DL_TIME, String DL_TITLE, String DL_CONTENT, String DL_FLAG) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DO_LIST_DB VALUES(null, '" + DL_YMD + "', '"+DL_TIME+"', '" + DL_TITLE + "', '" + DL_CONTENT + "', '" + DL_FLAG + "');");
        db.close();
    }
    public void insert_DI(String DI_YMD, String DI_TIME, String DI_TITLE, String DI_CONTENT, String DI_WEATHER, String DI_FLAG) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DIARY_DB VALUES(null, '" + DI_YMD + "', '"+DI_TIME+"', '" + DI_TITLE + "', '" + DI_CONTENT + "', '"+DI_WEATHER+"', '" + DI_FLAG + "');");
        db.close();
    }
    public void insert_DC(String DC_YMD, String DC_WRITE){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DIARY_CHECK_DB VALUES(null, '" + DC_YMD+ "','" + DC_WRITE+ "');");
        db.close();
    }

    public void delete_DL(String DL_YMD, String DL_TITLE) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DO_LIST_DB WHERE DL_YMD= '" + DL_YMD + "' AND DL_TITLE = '" + DL_TITLE + "';");
        db.close();
    }
    public void delete_DI(String DI_id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DIARY_DB WHERE id = '" + DI_id + "';");
        db.close();
    }
    public void delete_DI_TITLE(String DI_TITLE) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DIARY_DB WHERE DI_TITLE = '" + DI_TITLE + "';");
        db.close();
    }

    //------------------------------ ↓↓ UPDATE ↓↓ ------------------------------//
    //update_DO_LIST_DB는 함수가 나뉘어 타이틀과 내용을 각각 수정하게 될 수도 있음.
    public void update_DL(String DL_YMD, String DL_id, String DL_TITLE, String DL_CONTENT) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DO_LIST_DB SET DL_TITLE=" + DL_TITLE + "AND DL_CONTENT =" + DL_CONTENT + " WHERE DL_YMD ='" + DL_YMD + "';");
        db.close();
    }

    public void update_DL_LIST(String DL_prevYMD, String DL_prevTitle, String DL_YMD, String DL_TIME, String DL_TITLE, String DL_CONTENT) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DO_LIST_DB SET DL_YMD ='" + DL_YMD + "' AND DL_TITLE = '" + DL_TITLE + "' AND DL_TIME= '" + DL_TIME +  "' WHERE DL_YMD= '" + DL_prevYMD + "' AND DL_TITLE = '" +DL_prevTitle+ "';");
        db.close();
    }

    public void update_DI_LIST(String DI_prevTitle, String DI_createDate, String DI_Title, String DI_Weather, String DI_Content) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DIARY_DB SET DI_CONTENT = '" + DI_Content+"' WHERE DI_TITLE = '"+DI_prevTitle +"';");
        db.execSQL("UPDATE DIARY_DB SET DI_WEATHER = '" + DI_Weather+"' WHERE DI_TITLE = '"+DI_prevTitle +"';");
        db.execSQL("UPDATE DIARY_DB SET DI_TITLE = '" + DI_Title+"' WHERE DI_TITLE = '"+DI_prevTitle +"';");
        db.close();
    }

    // 해야할 일/한 일 update FLAG
    public void update_DL_FLAG(String DL_ymd, String DL_title, String DL_flag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DO_LIST_DB SET DL_FLAG ='" + DL_flag+ "'WHERE DL_TITLE ='" + DL_title + "';");
        db.close();
    }

    public void update_DI_FLAG(String DI_title, String DI_flag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DIARY_DB SET DL_FLAG ='" + DI_flag+ "'WHERE DI_TITLE ='" + DI_title + "';");
        db.close();
    }

    // 해야할 일/한 일 update FLAG
    public void update_DC_WRITE_NUM(String DC_ymd, String DC_write) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DIARY_CHECK_DB SET DC_WRITE ='" + DC_write+ "'WHERE DC_YMD ='" + DC_ymd + "';");
        db.close();
    }

    //------------------------------ ↓↓ GET!! GET!!↓↓ ------------------------------//
    public int DC_Date_check(String DC_ymd){
        SQLiteDatabase db = getReadableDatabase();
        int writeNum=-1;

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_CHECK_DB WHERE DC_YMD='" + DC_ymd + "';", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                writeNum = Integer.parseInt(cursor.getString(2));
            }
        }
        cursor.close();
        db.close();

        return writeNum;
    }
    public int DC_TITLE_check(String DC_title){
        SQLiteDatabase db = getReadableDatabase();
        int writeNum=0;

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_CHECK_DB WHERE DC_YMD='" + DC_title + "';", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                writeNum = Integer.parseInt(cursor.getString(2));
            }
        }
        cursor.close();
        db.close();

        return writeNum;
    }

    public ArrayList<String> get_DI_Date(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DI_date = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_DB ;", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DI_date.add(cursor.getString(1));
            }
        }
        cursor.close();
        db.close();

        return DI_date;
    }

    public ArrayList<String> get_DI_Title(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DI_title = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_DB ;", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DI_title.add(cursor.getString(3));
            }
        }
        cursor.close();
        db.close();

        return DI_title;
    }
    public ArrayList<String> get_DI_Content(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DI_content = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_DB ;", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DI_content.add(cursor.getString(4));
            }
        }
        cursor.close();
        db.close();

        return DI_content;
    }

    public ArrayList<String> get_DI_Weather(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DI_weather= new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_DB ;", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DI_weather.add(cursor.getString(5));
            }
        }
        cursor.close();
        db.close();

        return DI_weather;
    }

    public ArrayList<ArrayList<String>> get_DL_LIST(String DL_ymd){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DL_ListTitle = new ArrayList<>();
        ArrayList<String> DL_ListContent = new ArrayList<>();
        ArrayList<String> DL_ListBool = new ArrayList<>();
        ArrayList<String> DL_ListCreateDate = new ArrayList<>();
        ArrayList<ArrayList<String>> DL_List = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DO_LIST_DB WHERE DL_YMD='" + DL_ymd + "';", null);
        while (cursor.moveToNext()){
            DL_ListCreateDate.add(cursor.getString(1));
            DL_ListTitle.add(cursor.getString(3));
            DL_ListContent.add(cursor.getString(4));
            DL_ListBool.add(cursor.getString(5));
        }
        DL_List.add(DL_ListCreateDate);
        DL_List.add(DL_ListTitle);
        DL_List.add(DL_ListContent);
        DL_List.add(DL_ListBool);

        cursor.close();
        db.close();

        return DL_List;
    }

    public String[] get_DL_DATA(String DL_ymd, String DL_title){

        SQLiteDatabase db = getReadableDatabase();
        String[] DL_DATAforUPDATE = new String[4];

        Cursor cursor = db.rawQuery("SELECT * FROM DO_LIST_DB WHERE DL_YMD='" + DL_ymd + "' AND DL_TITLE= '" + DL_title + "';", null);

        while (cursor.moveToNext()){
            DL_DATAforUPDATE[0] = cursor.getString(1); //Date
            DL_DATAforUPDATE[1] = cursor.getString(2); //Time
            DL_DATAforUPDATE[2] = cursor.getString(3); //Title
            DL_DATAforUPDATE[3] = cursor.getString(4); //Content
        }
        return DL_DATAforUPDATE;
    }

    public String get_DI_TITLE(String position){

        SQLiteDatabase db = getReadableDatabase();
        int p = Integer.parseInt(position)-1;
        String title = null;

        Cursor cursor = db.rawQuery("SELECT * FROM DO_LIST_DB WHERE id = '" + p + "';", null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                title = cursor.getString(1);
            }
        }
        cursor.close();
        db.close();

        return title;
    }

    public int get_DI_count() {
        String countQuery = "SELECT  * FROM DIARY_DB";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
    public ArrayList<ArrayList<String>> get_DC_ALL() {
        String countQuery = "SELECT  * FROM DIARY_CHECK_DB";
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<ArrayList<String>> DC_List = new ArrayList<>();
        ArrayList<String> diaryDate = new ArrayList<>();
        ArrayList<String> diaryNum = new ArrayList<>();

        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                diaryDate.add(cursor.getString(1));
                diaryNum.add(cursor.getString(2));
            }
        }
        DC_List.add(diaryDate);
        DC_List.add(diaryNum);

        cursor.close();
        return DC_List;
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}

