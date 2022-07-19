package me.hanhngo.studentmanager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.hanhngo.studentmanager.databinding.StudentRowItemBinding;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    ArrayList<StudentModel> studentModelArrayList;
    OnClickListener listener;

    public StudentAdapter(ArrayList<StudentModel> studentModelArrayList, OnClickListener listener) {
        this.studentModelArrayList = studentModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentRowItemBinding binding = StudentRowItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new StudentViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(studentModelArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        private final StudentRowItemBinding binding;
        private final OnClickListener listener;

        public StudentViewHolder(@NonNull StudentRowItemBinding binding, OnClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(StudentModel studentModel) {
            binding.studentIdTv.setText(String.valueOf(studentModel.getStudentID()));
            binding.studentFullNameTv.setText(studentModel.getFullName());

            itemView.setOnClickListener(view -> {
                listener.onItemClick(studentModel);
            });
        }
    }
}
