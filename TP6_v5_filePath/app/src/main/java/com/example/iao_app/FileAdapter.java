package com.example.iao_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class FileAdapter extends BaseAdapter {

    private final Context context;
    private final List<FileItem> items;

    public FileAdapter(Context context, List<FileItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_file, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.txtFile);
        FileItem item = items.get(position);

        if (item.isParent) {
            tv.setText("..");
            tv.setTextColor(ContextCompat.getColor(context, R.color.folderColor));
        } else {
            tv.setText(item.file.getName());
            if (item.file.isDirectory()) {
                tv.setTextColor(ContextCompat.getColor(context, R.color.folderColor));
            } else {
                tv.setTextColor(ContextCompat.getColor(context, R.color.fileColor));
            }
        }

        return convertView;
    }
}
