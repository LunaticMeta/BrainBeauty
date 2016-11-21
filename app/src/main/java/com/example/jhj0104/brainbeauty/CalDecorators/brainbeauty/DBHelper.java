package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

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
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE DO_LIST_DB (_id INTEGER PRIMARY KEY AUTOINCREMENT, DL_YMD TEXT, DL_TIME TEXT, DL_TITLE TEXT, DL_CONTENT TEXT, DL_FLAG TEXT);");
    }

    //------------------------------ ↓↓ INSERT ↓↓ ------------------------------//
    public void insert_DL(String DL_YMD, String DL_TIME, String DL_TITLE, String DL_CONTENT, String DL_FLAG) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DO_LIST_DB VALUES(null, '" + DL_YMD + "', '"+DL_TIME+"', '" + DL_TITLE + "', '" + DL_CONTENT + "', '" + DL_FLAG + "');");
        db.close();
    }

    //------------------------------ ↓↓ DELETE ↓↓ ------------------------------//
    public void delete_DL(String DL_YMD, String DL_TITLE) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DO_LIST_DB WHERE DL_YMD= '" + DL_YMD + "' AND DL_TITLE = '" + DL_TITLE + "';");
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
        //db.execSQL("UPDATE DO_LIST_DB SET DL_TITLE=" + DL_TITLE + "AND DL_CONTENT =" + DL_CONTENT + " WHERE DL_YMD='" + DL_YMD + "';");
        db.execSQL("UPDATE DO_LIST_DB SET DL_YMD ='" + DL_YMD + "' AND DL_TITLE = '" + DL_TITLE + "' AND DL_TIME= '" + DL_TIME +  "' WHERE DL_YMD= '" + DL_prevYMD + "' AND DL_TITLE = '" +DL_prevTitle+ "';");

        //UPDATE DO_LIST_DB SET DL_TITLE = DLTITLE AND DL_CONTENT=DL_CONTENT AND DL_DATE = DL_DATE WHERE DL_YMD = DL_YMD AND DL_TITLE = DL_prevTITLE;
        db.close();
    }

    // 해야할 일/한 일 update FLAG
    public void update_DL_FLAG(String DL_ymd, String DL_title, String DL_flag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DO_LIST_DB SET DL_FLAG ='" + DL_flag+ "'WHERE DL_TITLE ='" + DL_title + "';");
        db.close();
    }

    //------------------------------ ↓↓ GET!! GET!!↓↓ ------------------------------//

    public ArrayList<ArrayList<String>> get_DL_LIST(String DL_ymd){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> DL_ListTitle = new ArrayList<>();
        ArrayList<String> DL_ListBool = new ArrayList<>();
        ArrayList<String> DL_ListCreateDate = new ArrayList<>();
        ArrayList<ArrayList<String>> DL_List = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM DO_LIST_DB WHERE DL_YMD='" + DL_ymd + "';", null);
        while (cursor.moveToNext()){
            DL_ListTitle.add(cursor.getString(3));
            DL_ListBool.add(cursor.getString(5));
            DL_ListCreateDate.add(cursor.getString(1));
        }
        DL_List.add(DL_ListTitle);
        DL_List.add(DL_ListBool);
        DL_List.add(DL_ListCreateDate);

        cursor.close();
        db.close();

        return DL_List;
    }

    public String[] get_DL_itemDATA(String DL_ymd, String DL_title){

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

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

