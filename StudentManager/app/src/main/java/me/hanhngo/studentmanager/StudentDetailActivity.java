package me.hanhngo.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import me.hanhngo.studentmanager.databinding.ActivityMainBinding;
import me.hanhngo.studentmanager.databinding.ActivityStudentDetailBinding;

public class StudentDetailActivity extends AppCompatActivity {
    private ActivityStudentDetailBinding binding = null;

    int studentId = 0;
    String fullName = "";
    String email = "";
    String dob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        if(b != null) {
            studentId = b.getInt("id");
            fullName = b.getString("fullName");
            email = b.getString("email");
            dob = b.getString("dob");
        }

        binding.studentIdTv.setText(String.valueOf(studentId));
        binding.studentFullNameTv.setText(fullName);
        binding.studentEmailTv.setText(email);
        binding.studentDobTv.setText(dob);

        binding.editBtn.setOnClickListener(view -> {
            Intent intent = new Intent(StudentDetailActivity.this, StudentEditActivity.class);
            Bundle b1 = new Bundle();
            b1.putInt("id", studentId);
            b1.putString("fullName", fullName);
            b1.putString("email", email);
            b1.putString("dob", dob);
            intent.putExtras(b1);
            startActivity(intent);
        });
    }
}