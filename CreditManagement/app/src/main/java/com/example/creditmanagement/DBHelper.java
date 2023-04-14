package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME="CreditManagement.db";
    public static final String TABLE_NAME1="login_tbl";
    public static final String COL1="login_name";
    public static final String COL2="login_business";
    public static final String COL3="login_phone";
    public static final String COL4="login_email";
    public static final String COL5="login_password";
    public static final String TABLE_NAME2="customer_tbl";
    private static final String CUSTOMER_ID="cus_id";
    public static final String CUSTOMER_COL1="user_name";
    public static final String CUSTOMER_COL2="user_phone";

    public DBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1="CREATE TABLE "+ TABLE_NAME1 +
                "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL1 + " TEXT, "+
                COL2 + " TEXT, "+
                COL3 + " INTEGER, "+
                COL4 + " TEXT, "+
                COL5 + " TEXT)";

        String query2=
                "CREATE TABLE " + TABLE_NAME2 +
                        " (" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CUSTOMER_COL1 + " TEXT, " +
                        CUSTOMER_COL2 + " INTEGER);";

        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);
    }

    public Boolean createUser(String name,String business,String phone,String email,String password,String cpassword){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COL1,name);
        cv.put(COL2,business);
        cv.put(COL3,phone);
        cv.put(COL4,email);
        cv.put(COL5,password);
        long result=db.insert(TABLE_NAME1,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checkusername(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+ TABLE_NAME1 + " where login_email=? ", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public boolean checkusernamepassword(String email , String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from login_tbl where login_email=? and login_password=?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    Cursor getPhonenumber(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery("Select * from "+ TABLE_NAME1 + " where tr_id=? ", new String[] {email},null);
        }
        return cursor;
    }


}
