package com.example.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HangHoaAdapter extends BaseAdapter {
    List<HangHoa> list;
    Context context;
    int layout;

    public HangHoaAdapter(List<HangHoa> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvMa = convertView.findViewById(R.id.tvMa);
        TextView tvTen = convertView.findViewById(R.id.tvTen);
        TextView tvGia = convertView.findViewById(R.id.tvGia);

        tvMa.setText("Mã: "+list.get(i).getMa());
        tvTen.setText("Tên: "+list.get(i).getTen());
        tvGia.setText("Giá: "+list.get(i).getGia());


        return convertView;
    }
}
