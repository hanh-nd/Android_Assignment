package me.hanhngo.myfilemanager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.hanhngo.myfilemanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private ActivityMainBinding binding = null;
    private List<FileModel> fileModelList;
    String storageRootPath;
    List<String> pathHistory = new ArrayList<>();
    private File file;
    FileAdapter adapter;

    public final String[] EXTERNAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 138;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        requestForExternalStoragePermission();

        storageRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File(storageRootPath);
        List<File> files = Utils.getListFiles(file);
        if (files != null) {
            fileModelList = files.stream().map(Utils::fromFileToFileModel).collect(Collectors.toList());
        }

        adapter = new FileAdapter(fileModelList, this);
        binding.fileRcv.setAdapter(adapter);

        registerForContextMenu(binding.fileRcv);
    }

    private void log(String message) {
        Log.d("Hanh Ngo", message);
    }

    public boolean requestForExternalStoragePermission() {

        boolean isPermissionOn = true;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false;
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST);
            }
        }

        return isPermissionOn;
    }

    public boolean canAccessExternalSd() {
        return (hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private boolean hasPermission(String permission) {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permission));

    }


    @Override
    public void onItemClick(FileModel fileModel) {
        if (fileModel.getType() == FileType.DIRECTORY) {
            pathHistory.add(storageRootPath);
            storageRootPath = storageRootPath + "/" + fileModel.getFileName();
            setFileList(storageRootPath);
        }
    }

    public void setFileList(String path) {
        file = new File(path);
        List<File> files = Utils.getListFiles(file);
        if (files != null) {
            fileModelList = files.stream().map(Utils::fromFileToFileModel).collect(Collectors.toList());
        }
        adapter.setFileList(fileModelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (pathHistory.size() <= 0) {
            super.onBackPressed();
            return;
        }
        int lastIndex = pathHistory.size() - 1;
        storageRootPath = pathHistory.get(lastIndex);
        pathHistory.remove(lastIndex);
        setFileList(storageRootPath);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getPosition();
        FileModel fileModel = adapter.getFile(position);
        if (item.getTitle() == "Đổi tên") {
            StringBuilder newName = new StringBuilder(fileModel.getFileName());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Đổi tên");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("OK", (dialog, which) -> {
                newName.append(input.getText().toString());
                renameFile(fileModel, newName.toString());
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        }
        return super.onContextItemSelected(item);
    }

    public void renameFile(FileModel fileModel, String newName) {
        File oldFile = new File(fileModel.getDirectoryPath(), fileModel.getFileName());
        File newFile;

        if (fileModel.getType() == FileType.DIRECTORY) {
            newFile = new File(fileModel.getDirectoryPath(), newName);
        } else {
            newFile = new File(fileModel.getDirectoryPath(), newName + "." + fileModel.getExtension());
        }

        oldFile.renameTo(newFile);
        adapter.notifyDataSetChanged();
    }
}