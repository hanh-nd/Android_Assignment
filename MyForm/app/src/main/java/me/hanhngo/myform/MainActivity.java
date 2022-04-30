package me.hanhngo.myform;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.hanhngo.myform.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        setupLayout();
    }

    private void setupLayout() {
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.ROOT);
            binding.studentDobEt.setText(dateFormat.format(myCalendar.getTime()));
        };
        binding.studentDobEt.setOnClickListener(view -> {
            new DatePickerDialog(
                    MainActivity.this,
                    date, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        binding.rbtnNo.setOnClickListener(this::onRadioButtonClicked);
        binding.rbtnYes.setOnClickListener(this::onRadioButtonClicked);

        binding.submitBtn.setOnClickListener(view -> {
            if (isFulFilledRequiredFields()) {
                Toast.makeText(this, "Submitted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fulfill required fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rbtn_no:
                if (checked)
                    binding.submitBtn.setEnabled(false);
                break;
            case R.id.rbtn_yes:
                if (checked)
                    binding.submitBtn.setEnabled(true);
                break;
        }
    }

    public boolean isFulFilledRequiredFields() {
        return !binding.studentIdEt.getText().toString().isEmpty()
                && !binding.studentNameEt.getText().toString().isEmpty()
                && !binding.studentCredentialNumberEt.getText().toString().isEmpty()
                && !binding.studentNumberEt.getText().toString().isEmpty()
                && !binding.studentEmailEt.getText().toString().isEmpty();
    }
}