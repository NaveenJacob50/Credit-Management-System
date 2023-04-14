package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper3 extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Transactions.db";
    private static final String TABLE_NAME3="transaction_tbl";
    private static final String TRANSACTION_ID="tr_id";
    private static final String TRANSACTION_COL1="tr_date";
    private static final String TRANSACTION_COL2="tr_gave";
    private static final String TRANSACTION_COL3="tr_got";

    public DBHelper3(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2=
                "CREATE TABLE " + TABLE_NAME3 +
                        " (" + TRANSACTION_ID + " INTEGER, " +
                        TRANSACTION_COL1 + " TEXT, " +
                        TRANSACTION_COL2 + " INTEGER, " +
                        TRANSACTION_COL3 + " INTEGER);";

        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    void addTransaction(String id, String date,String gave,String got){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(TRANSACTION_ID,id);
        cv.put(TRANSACTION_COL1,date);
        cv.put(TRANSACTION_COL2,gave);
        cv.put(TRANSACTION_COL3,got);
        long result=db.insert(TABLE_NAME3,null,cv);
        if(result==-1)
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Transaction Added",Toast.LENGTH_SHORT).show();
    }

    Cursor readTransactions(String t_id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery("Select * from "+ TABLE_NAME3 + " where tr_id=? ", new String[] {t_id},null);
        }
        return cursor;
    }

    public String gaveSum(String t_id) {
        SQLiteDatabase db = getReadableDatabase();
        String sumGave;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT SUM(" + TRANSACTION_COL2 + ") FROM " + TABLE_NAME3 + " where tr_id=? ", new String[]{t_id}, null);
        if (cursor.moveToFirst()) {
            sumGave = String.valueOf(cursor.getInt(0));
        }
        else{
            sumGave="0";
        }
        cursor.close();
        db.close();
        return sumGave;
    }

    public String gotSum(String t_id) {
        SQLiteDatabase db = getReadableDatabase();
        String sumGot;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT SUM(" + TRANSACTION_COL3 + ") FROM " + TABLE_NAME3 + " where tr_id=? ", new String[]{t_id}, null);
        if (cursor.moveToFirst()) {
            sumGot = String.valueOf(cursor.getInt(0));
        }
        else{
            sumGot="0";
        }
        cursor.close();
        db.close();
        return sumGot;
    }

    public String TotalgaveSum() {
        SQLiteDatabase db = getReadableDatabase();
        String TotalsumGave;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT SUM(" + TRANSACTION_COL2 + ") FROM " + TABLE_NAME3 , null);
        if (cursor.moveToFirst()) {
            TotalsumGave = String.valueOf(cursor.getInt(0));
        }
        else{
            TotalsumGave="0";
        }
        cursor.close();
        db.close();
        return TotalsumGave;
    }

    public String TotalgotSum() {
        SQLiteDatabase db = getReadableDatabase();
        String TotalsumGot;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT SUM(" + TRANSACTION_COL3 + ") FROM " + TABLE_NAME3 , null);
        if (cursor.moveToFirst()) {
            TotalsumGot = String.valueOf(cursor.getInt(0));
        }
        else{
            TotalsumGot="0";
        }
        cursor.close();
        db.close();
        return TotalsumGot;
    }

    Cursor readReport(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery("Select * from "+ TABLE_NAME3 ,null);
        }
        return cursor;
    }
}
