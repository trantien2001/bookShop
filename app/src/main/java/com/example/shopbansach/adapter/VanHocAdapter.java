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

public class VanHocAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrayvanhoc;

    public VanHocAdapter(Context context, ArrayList<Sanpham> arrayvanhoc) {
        this.context = context;
        this.arrayvanhoc = arrayvanhoc;
    }

    @Override
    public int getCount() {
        return arrayvanhoc.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayvanhoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenvanhoc,txtgiavanhoc,txtmotavanhoc;
        public ImageView imgvanhoc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_vanhoc,null);
            viewHolder.txttenvanhoc = view.findViewById(R.id.textviewvanhoc);
            viewHolder.txtgiavanhoc = view.findViewById(R.id.textviewgiavanhoc);
            viewHolder.txtmotavanhoc = view.findViewById(R.id.textviewmotavanhoc);
            viewHolder.imgvanhoc = view.findViewById(R.id.imageviewvanhoc);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenvanhoc.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiavanhoc.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHolder.txtmotavanhoc.setMaxLines(2);
        viewHolder.txtmotavanhoc.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotavanhoc.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgvanhoc);
        return view;
    }
}
