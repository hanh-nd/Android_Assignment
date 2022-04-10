package me.hanhngo.hellotoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private Button zeroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        zeroButton = findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }

        if (mCount % 2 == 0) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
        }

        if (mCount != 0 && zeroButton != null) {
            zeroButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pink));
        }
    }

    public void resetCount(View view) {
        mCount = 0;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }

        view.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
    }
}