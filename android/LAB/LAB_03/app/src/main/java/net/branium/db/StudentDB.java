package net.branium.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import net.branium.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDB extends SQLiteOpenHelper {
    public static final String TableName = "StudentTable";
    public static final String Id = "Id";
    public static final String FullName = "FullName";
    public static final String Math = "Math";
    public static final String Physic = "Physic";
    public static final String Chemistry = "Chemistry";

    public StudentDB(@Nullable Context context, @Nullable String name,
                     @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + TableName + "("
//                + Id + " Text Primary Key, "
//                + FullName + " Text, "
//                + Math + " Double, "
//                + Physic + " Double, "
//                + Chemistry + " Double)";

        String sqlCreate = "Create table if not exists " + TableName + "("
                + Id + " Text Primary key, "
                + FullName + " Text, "
                + Math + " Double, "
                + Physic + " Double, "
                + Chemistry + " Double)";

        // run SQL query statement to create table
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, student.getId());
        values.put(FullName, student.getFullName());
        values.put(Math, student.getMath());
        values.put(Physic, student.getPhysic());
        values.put(Chemistry, student.getChemistry());
        db.insert(TableName, null, values);
        db.close();
    }


//    public void updateContact(int id, Contact contact) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues value = new ContentValues();
//        value.put(Id, contact.getId());
//        value.put(Image, contact.getImage());
//        value.put(Name, contact.getName());
//        value.put(Phone, contact.getImage());
//        db.update(TableName, value, Id + "=?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//
//    public void deleteContact(int id) {
//        SQLiteDatabase db = getWritableDatabase();
//        String sql = "Delete From " + TableName + " Where ID=" + id;
//        db.execSQL(sql);
//        db.close();
//    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Student student = new Student(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4)
                );
                students.add(student);
            }
        }
        return students;
    }
}
