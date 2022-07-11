package me.hanhngo.mygmail;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.hanhngo.mygmail.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    private final List<Email> emailList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Button
        EmailAdapter adapter = new EmailAdapter(getEmailList());
        binding.rcv.setAdapter(adapter);
    }

    private List<Email> getEmailList() {
        if (emailList.size() > 0) {
            return emailList;
        }
        for (int i = 0; i < 20; ++i) {
            emailList.add(
                    new Email(
                            "Hanh Ngo",
                            "Them nhieu dau phay, can them nhieu dau phay",
                            "Them nhieu thu bay, can them nhieu thu bay",
                            "12:34 PM"
                    )
            );
        }
        return emailList;
    }
}