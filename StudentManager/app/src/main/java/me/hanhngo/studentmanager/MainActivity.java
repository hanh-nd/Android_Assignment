package me.hanhngo.studentmanager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import me.hanhngo.studentmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private ActivityMainBinding binding = null;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Open DB
        String path = getFilesDir() + "/mydb";
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        createTable();

        ArrayList<StudentModel> studentModelArrayList = DBUtil.selectAll(db);
        StudentAdapter adapter = new StudentAdapter(studentModelArrayList, this);
        binding.studentRcv.setAdapter(adapter);
    }

    public void createTable() {
        db.beginTransaction();
        try {
            db.execSQL("create table tbStudent(" +
                    "student_id integer PRIMARY KEY autoincrement," +
                    "full_name text," +
                    "email text," +
                    "dob text)");

            db.execSQL("insert into tbStudent(full_name, email, dob) values('ngo', 'ngo@gmail.com', '12/12/2021')");
            db.execSQL("insert into tbStudent(full_name, email, dob) values('ngo1', 'ngo@gmail.com', '13/12/2021')");
            db.execSQL("insert into tbStudent(full_name, email, dob) values('ngo2', 'ngo@gmail.com', '14/12/2021')");
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public void onItemClick(StudentModel studentModel) {
        Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
        Bundle b = new Bundle();
        b.putInt("id", studentModel.getStudentID());
        b.putString("fullName", studentModel.getFullName());
        b.putString("email", studentModel.getEmail());
        b.putString("dob", studentModel.getDob());
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_student) {
            Intent intent = new Intent(MainActivity.this, StudentEditActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}