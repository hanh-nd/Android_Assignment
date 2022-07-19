package me.hanhngo.studentmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    public static ArrayList<StudentModel> selectAll(SQLiteDatabase db) {
        String[] columns = {"student_id", "full_name", "email", "dob"};
        Cursor cs = db.query("tbStudent", columns,
                null, null, null, null, null);
        ArrayList<StudentModel> studentModelArrayList = new ArrayList<>();
        if (cs.moveToFirst()) {
            do {
                studentModelArrayList.add(new StudentModel(cs.getInt(0), cs.getString(1),
                        cs.getString(2), cs.getString(3)));
            } while (cs.moveToNext());
        }
        cs.close();
        return studentModelArrayList;
    }

    public static void insert(SQLiteDatabase db, StudentModel studentModel) {
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("full_name", studentModel.getFullName());
            cv.put("email", studentModel.getEmail());
            cv.put("dob", studentModel.getDob());
            db.insert("tbStudent", null, cv);
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static void update(SQLiteDatabase db, StudentModel studentModel) {
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("full_name", studentModel.getFullName());
            cv.put("email", studentModel.getEmail());
            cv.put("dob", studentModel.getDob());
            db.update("tbStudent", cv, "student_id=?",  new String[]{String.valueOf(studentModel.getStudentID())});
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static void delete(SQLiteDatabase db, StudentModel studentModel) {
        db.beginTransaction();
        try {
            db.delete("tbStudent", "student_id=?", new String[]{String.valueOf(studentModel.getStudentID())});
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
