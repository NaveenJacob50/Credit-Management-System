package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="CreditUser.db";
    private static final String TABLE_NAME2="customer_tbl";
    private static final String CUSTOMER_ID="cus_id";
    private static final String CUSTOMER_COL1="user_name";
    private static final String CUSTOMER_COL2="user_phone";

    public DBHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1=
                "CREATE TABLE " + TABLE_NAME2 +
                " (" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CUSTOMER_COL1 + " TEXT, " +
                CUSTOMER_COL2 + " INTEGER);";

        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void addUser(String cname, String phoneno){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(CUSTOMER_COL1,cname);
        cv.put(CUSTOMER_COL2,phoneno);
        long cresult=db.insert(TABLE_NAME2,null,cv);
        if(cresult==-1)
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
    }

    public boolean checknamephone(String cname , String cphone){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from customer_tbl where user_name=? and user_phone=?", new String[] {cname,cphone});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    Cursor readAllData(){
        String query="SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}
