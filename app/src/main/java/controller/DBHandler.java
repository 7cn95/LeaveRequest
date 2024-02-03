package controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import model.RequestDB;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "myapp";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "requests";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "EmployeeName";
    private static final String REASON_COL = "reason";
    private static final String RL_DATE_COL = "RequestDate";
    private static final String STATUS_COL = "status";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + REASON_COL + " TEXT,"
                + RL_DATE_COL + " TEXT,"
                + STATUS_COL + " TEXT,"
                + "comments TEXT,"  // العمود الجديد
                + "days_off INTEGER,"  // العمود الجديد
                + "employee_number TEXT,"  // العمود الجديد
                + "job_title TEXT)";  // العمود الجديد
        String query2 = "CREATE TABLE employee (id INTEGER PRIMARY KEY AUTOINCREMENT,empName TEXT , password TEXT)";
        db.execSQL(query);
        db.execSQL(query2);
    }


    public boolean addNewRequest(String name, String reason, String date, String comments, int daysOff, String employeeNumber, String jobTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(REASON_COL, reason);
        values.put(RL_DATE_COL, date);
        values.put(STATUS_COL, "null");
        values.put("comments", comments);  // القيمة للعمود الجديد
        values.put("days_off", daysOff);  // القيمة للعمود الجديد
        values.put("employee_number", employeeNumber);  // القيمة للعمود الجديد
        values.put("job_title", jobTitle);  // القيمة للعمود الجديد

        long rowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return rowId != -1;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
    }
    public List<RequestDB> get_request(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        RequestDB request = null;
        List<RequestDB> requestList = null;

        String[] columns = {ID_COL, NAME_COL, REASON_COL, RL_DATE_COL, STATUS_COL};
        String selection = NAME_COL + "=?";
        String[] selectionArgs = {String.valueOf(name)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // استخراج البيانات من النتيجة
            int id = cursor.getInt(0);
            String Ename = cursor.getString(1);
            String reason = cursor.getString(2);
            String date = cursor.getString(3);
            String status = cursor.getString(4);

            if (cursor.moveToFirst())
                do {
                    request = new RequestDB(id, Ename, reason, date, status);
                    requestList.add(request);
                } while (cursor.moveToNext());
        }
        return requestList;

    }
    public RequestDB getRequestById(int requestId) {
        SQLiteDatabase db = this.getReadableDatabase();
        RequestDB request = null;

        String[] columns = {ID_COL, NAME_COL, REASON_COL, RL_DATE_COL, STATUS_COL};
        String selection = ID_COL + "=?";
        String[] selectionArgs = {String.valueOf(requestId)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // استخراج البيانات من النتيجة
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String reason = cursor.getString(2);
            String date = cursor.getString(3);
            String status = cursor.getString(4);

            // إنشاء كائن RequestDB
            request = new RequestDB(id, name, reason, date, status);

            // إغلاق المؤشر
            cursor.close();
        }

        // إغلاق قاعدة البيانات
        db.close();

        return request;
    }


    public List<RequestDB> get_all_request(){
        SQLiteDatabase database = this.getReadableDatabase();
        List<RequestDB> requestList = new ArrayList<>();
        String getAll = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = database.rawQuery(getAll,null);


        if (cursor.moveToFirst())
            do {
                RequestDB request = new RequestDB(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                requestList.add(request);
            }while (cursor.moveToNext());

        return requestList;
    }
    public void updateStatus(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS_COL, "true");
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }
    public void updateStatusR(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS_COL, "false");
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }


    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public boolean login (String emp_name , String password){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                "employee",
                null,
                 "empName='"+emp_name+"' AND password='"+password+"'",
                null,
                null,
                null,
                null);

        return cursor.moveToFirst();
    }
    public void addNewAdmin(String name , String password ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("empName", name);
        values.put("password", password);
        db.insert("employee", null, values);
        db.close();
    }

}
