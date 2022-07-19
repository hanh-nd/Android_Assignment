package me.hanhngo.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import me.hanhngo.studentmanager.databinding.ActivityStudentEditBinding;

public class StudentEditActivity extends AppCompatActivity {
    private ActivityStudentEditBinding binding = null;

    SQLiteDatabase db;

    int studentId = 0;
    String fullName = "";
    String email = "";
    String dob = "";

    boolean isUpdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Open DB
        String path = getFilesDir() + "/mydb";
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Bundle b = getIntent().getExtras();
        if(b != null) {
            studentId = b.getInt("id");
            fullName = b.getString("fullName");
            email = b.getString("email");
            dob = b.getString("dob");
            isUpdate = true;
        }

        binding.studentIdEt.setText(String.valueOf(studentId));
        binding.studentFullNameEt.setText(fullName);
        binding.studentEmailEt.setText(email);
        binding.studentDobEt.setText(dob);

        binding.submitBtn.setOnClickListener(view -> {
            String newId = binding.studentIdEt.getText().toString();
            String newFullName = binding.studentFullNameEt.getText().toString();
            String newEmail = binding.studentEmailEt.getText().toString();
            String newDob = binding.studentDobEt.getText().toString();

            if (isUpdate) {
                DBUtil.update(db, new StudentModel(Integer.parseInt(newId), newFullName, newEmail, newDob));
                Intent intent = new Intent(StudentEditActivity.this, StudentDetailActivity.class);
                Bundle b1 = new Bundle();
                b1.putInt("id", Integer.parseInt(newId));
                b1.putString("fullName", newFullName);
                b1.putString("email", newEmail);
                b1.putString("dob", newDob);
                intent.putExtras(b1);
                startActivity(intent);
            } else {
                DBUtil.insert(db, new StudentModel(Integer.parseInt(newId), newFullName, newEmail, newDob));
                Intent intent = new Intent(StudentEditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}