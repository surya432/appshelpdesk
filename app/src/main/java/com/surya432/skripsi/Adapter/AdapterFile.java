package com.surya432.skripsi.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipaulpro.afilechooser.utils.FileUtils;
import com.surya432.skripsi.R;

import java.util.ArrayList;

public class AdapterFile  extends BaseAdapter {
    private Context context;
    private ArrayList<Uri> arrayList;
    public AdapterFile(Context context, ArrayList<Uri> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mInflater != null) {
            convertView = mInflater.inflate(R.layout.list_items, null);
        }
        TextView imagePath = convertView.findViewById(R.id.imagePath);
        imagePath.setText(FileUtils.getPath(context, arrayList.get(position)));

        return convertView;
    }
}
