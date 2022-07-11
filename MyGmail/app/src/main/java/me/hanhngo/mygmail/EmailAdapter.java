package me.hanhngo.mygmail;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.hanhngo.mygmail.databinding.EmailItemRowBinding;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> {
    private List<Email> emailList;

    public EmailAdapter(List<Email> emailList) {
        this.emailList = emailList;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmailItemRowBinding binding = EmailItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new EmailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        holder.bind(emailList.get(position));
    }

    @Override
    public int getItemCount() {
        return emailList.size();
    }

    public static class EmailViewHolder extends RecyclerView.ViewHolder {
        private final EmailItemRowBinding binding;

        public EmailViewHolder(@NonNull EmailItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Email email) {
            binding.nameTv.setText(email.getName());
            binding.nameTv.setTextColor(Color.BLUE);
            binding.subjectTv.setText(email.getSubject());
            binding.bodyTv.setText(email.getBody());
            binding.timeTv.setText(email.getTime());
            if (email.isMarked()) {
                binding.markIv.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_star_fill));
            } else {
                binding.markIv.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_star));
            }
            
            itemView.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "Opening email...", Toast.LENGTH_SHORT).show();
            });

            binding.markIv.setOnClickListener(view -> {
                if (email.isMarked()) {
                    email.setMarked(false);
                    binding.markIv.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_star));
                } else {
                    email.setMarked(true);
                    binding.markIv.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_star_fill));
                }
            });
        }
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }
}
