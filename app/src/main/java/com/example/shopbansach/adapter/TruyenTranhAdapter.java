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

public class TruyenTranhAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytruyentranh;

    public TruyenTranhAdapter(Context context, ArrayList<Sanpham> arraytruyentranh) {
        this.context = context;
        this.arraytruyentranh = arraytruyentranh;
    }

    @Override
    public int getCount() {
        return arraytruyentranh.size();
    }

    @Override
    public Object getItem(int i) {
        return arraytruyentranh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttentruyentranh,txtgiatruyentranh,txtmotatruyentranh;
        public ImageView imgtruyentranh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_truyentranh,null);
            viewHolder.txttentruyentranh = view.findViewById(R.id.textviewtruyentranh);
            viewHolder.txtgiatruyentranh = view.findViewById(R.id.textviewgiatruyentranh);
            viewHolder.txtmotatruyentranh = view.findViewById(R.id.textviewmotatruyentranh);
            viewHolder.imgtruyentranh = view.findViewById(R.id.imageviewtruyentranh);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttentruyentranh.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiatruyentranh.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHolder.txtmotatruyentranh.setMaxLines(2);
        viewHolder.txtmotatruyentranh.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatruyentranh.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgtruyentranh);
        return view;
    }
}
