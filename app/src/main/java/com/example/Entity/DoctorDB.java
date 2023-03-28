package com.example.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.Dao.DoctorDao;

public class DoctorDB extends SQLiteOpenHelper implements DoctorDao {

    private Context context;
    private static final String DATABASE_NAME = "Doctor.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Doctor";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "doctor_name";
    private static final String COLUMN_EMAIL = "doctor_email";
    private static final String COLUMN_PASSWORD = "doctor_password";
       private static final String COLUMN_SPECIALITY = "specialty";


    public DoctorDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " text NOT NULL, " +
                        COLUMN_EMAIL + " text NOT NULL, " +
                        COLUMN_PASSWORD + " text NOT NULL, " +
                        COLUMN_SPECIALITY+ " text);";
        db.execSQL(qry1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDoctor(String n, String mail, String pass, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, n);
        cv.put(COLUMN_EMAIL, mail);
        cv.put(COLUMN_PASSWORD, pass);
        cv.put(COLUMN_SPECIALITY, s);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show();
            readAllData();
        }
    }
    public Cursor readAllData() {
        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase dbs = this.getReadableDatabase();
        Cursor cursor = null;
        if (dbs != null) {
            cursor = dbs.rawQuery(query, null);
        }
        return cursor;
    }
    /*
    public void updatePatient(String row_id, String fn, String ln, String bl) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRSTNAME, fn);
        cv.put(COLUMN_LASTNAME, ln);
        cv.put(COLUMN_BLUETOOTH, bl);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_LONG).show();

        }
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    */


}
