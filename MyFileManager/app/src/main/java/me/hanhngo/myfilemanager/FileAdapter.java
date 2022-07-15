package me.hanhngo.myfilemanager;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.hanhngo.myfilemanager.databinding.FileItemRowBinding;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FileModel> fileList;
    private OnClickListener listener;
    private int position = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FileModel getFile(int position) {
        if (position < 0) {
            return  null;
        }

        return fileList.get(position);
    }

    public FileAdapter(List<FileModel> fileList, OnClickListener listener) {
        this.fileList = fileList;
        this.listener = listener;
    }

    public void setFileList(List<FileModel> fileList) {
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FileItemRowBinding binding = FileItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        switch (viewType) {
            case 0:
                return new DirectoryViewHolder(binding, listener);

            case 1:
                return new FileViewHolder(binding, listener);

        }
        return new FileViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FileViewHolder) {
            ((FileViewHolder) holder).bind(fileList.get(position));
        } else if (holder instanceof DirectoryViewHolder) {
            ((DirectoryViewHolder) holder).bind(fileList.get(position));
        }

        holder.itemView.setOnLongClickListener(view -> {
            setPosition(position);
            return false;
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (fileList.get(position).getType() == FileType.DIRECTORY) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final FileItemRowBinding binding;
        private final OnClickListener listener;

        public FileViewHolder(@NonNull FileItemRowBinding binding, OnClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(FileModel fileModel) {
            binding.fileNameTv.setText(fileModel.getFileName());
            if (fileModel.getType() == FileType.DIRECTORY) {
                binding.iconIv.setBackgroundResource(R.drawable.folder);
            } else {
                binding.iconIv.setBackgroundResource(R.drawable.file);
            }

            itemView.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "Opening file...", Toast.LENGTH_SHORT).show();
                listener.onItemClick(fileModel);
            });

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Chọn hành động:");
            contextMenu.add(0, view.getId(), 0, "Đổi tên");
            contextMenu.add(0, view.getId(), 0, "Sao chép");
            contextMenu.add(0, view.getId(), 0, "Di chuyển");
            contextMenu.add(0, view.getId(), 0, "Xoá");
        }

    }

    public static class DirectoryViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final FileItemRowBinding binding;
        private final OnClickListener listener;

        public DirectoryViewHolder(@NonNull FileItemRowBinding binding, OnClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(FileModel fileModel) {
            binding.fileNameTv.setText(fileModel.getFileName());
            if (fileModel.getType() == FileType.DIRECTORY) {
                binding.iconIv.setBackgroundResource(R.drawable.folder);
            } else {
                binding.iconIv.setBackgroundResource(R.drawable.file);
            }

            itemView.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "Opening directory...", Toast.LENGTH_SHORT).show();
                listener.onItemClick(fileModel);
            });


            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Chọn hành động:");
            contextMenu.add(0, view.getId(), 0, "Đổi tên");
            contextMenu.add(0, view.getId(), 0, "Xoá");
        }
    }
}
