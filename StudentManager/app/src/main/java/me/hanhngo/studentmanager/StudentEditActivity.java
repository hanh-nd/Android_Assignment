package me.hanhngo.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.hanhngo.studentmanager.databinding.ActivityStudentEditBinding;

public class StudentEditActivity extends AppCompatActivity {
    private ActivityStudentEditBinding binding = null;
    int studentId = 0;
    String fullName = "";
    String email = "";
    String dob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        if(b != null) {
            studentId = b.getInt("id");
            fullName = b.getString("fullName");
            email = b.getString("email");
            dob = b.getString("dob");
        }

        binding.studentIdTv.setText(String.valueOf(studentId));
        binding.studentFullNameEt.setText(fullName);
        binding.studentEmailEt.setText(email);
        binding.studentDobEt.setText(dob);
    }
}