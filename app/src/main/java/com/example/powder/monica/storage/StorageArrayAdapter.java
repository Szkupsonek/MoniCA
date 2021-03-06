package com.example.powder.monica.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.powder.monica.R;

import java.util.List;

public class StorageArrayAdapter extends ArrayAdapter<FileItem> {
    private LayoutInflater inflater;
    private List<FileItem> fileItems;
    private ProgressUpdater progressUpdater;

    public StorageArrayAdapter(Context context, List<FileItem> fileItems, ProgressUpdater progressUpdater) {
        super(context, R.layout.file_item, R.id.itemTextView, fileItems);
        inflater = LayoutInflater.from(context);
        this.fileItems = fileItems;
        this.progressUpdater = progressUpdater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileItem fileItem = this.getItem(position);
        CheckBox checkBox;
        TextView textView;
        convertView = inflater.inflate(R.layout.file_item, null);
        textView = convertView.findViewById(R.id.itemTextView);
        checkBox = convertView.findViewById(R.id.itemCheckBox);

        checkBox.setOnClickListener(v -> {
            CheckBox cb = (CheckBox) v;
            FileItem fi = (FileItem) cb.getTag();
            fi.setChecked(cb.isChecked());
            progressUpdater.updateProgress(fileItems);
        });


        checkBox.setTag(fileItem);
        checkBox.setChecked(fileItem.isChecked());
        textView.setText(fileItem.getName());


        ImageView imageView = convertView.findViewById(R.id.itemIcon);
        if (fileItem.getName().contains(".jpg")) {
            imageView.setImageResource(R.drawable.ic_image_gray_24dp);
        } else {
            imageView.setImageResource(R.drawable.ic_music_note_gray_24dp);
        }


        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        progressUpdater.updateProgress(fileItems);
    }
}
