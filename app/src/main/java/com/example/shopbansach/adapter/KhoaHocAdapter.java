package com.example.shopbansach.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbansach.R;
import com.example.shopbansach.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KhoaHocAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraythieunhi;

    public KhoaHocAdapter(Context context, ArrayList<Sanpham> arraythieunhi) {
        this.context = context;
        this.arraythieunhi = arraythieunhi;
    }

    @Override
    public int getCount() {
        return arraythieunhi.size();
    }

    @Override
    public Object getItem(int i) {
        return arraythieunhi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenthieunhi, txtgiathieunhi, txtmotathieunhi;
        public ImageView imgthieunhi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_khoahoc,null);
            viewHolder.txttenthieunhi = view.findViewById(R.id.textviewthieunhi);
            viewHolder.txtgiathieunhi = view.findViewById(R.id.textviewgiathieunhi);
            viewHolder.txtmotathieunhi = view.findViewById(R.id.textviewmotathieunhi);
            viewHolder.imgthieunhi = view.findViewById(R.id.imageviewthieunhi);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenthieunhi.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathieunhi.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHolder.txtmotathieunhi.setMaxLines(2);
        viewHolder.txtmotathieunhi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotathieunhi.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgthieunhi);
        return view;
    }
}
