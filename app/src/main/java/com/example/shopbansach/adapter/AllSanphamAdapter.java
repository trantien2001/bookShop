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

public class AllSanphamAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arraysanpham;

    public AllSanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @Override
    public int getCount() {
        return arraysanpham.size();
    }

    public void filterList(ArrayList<Sanpham> filteredList) {
        arraysanpham = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return arraysanpham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttensanpham,txtgiasanpham,txtmotasanpham;
        public ImageView imgsanpham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_vanhoc,null);
            viewHolder.txttensanpham = view.findViewById(R.id.textviewvanhoc);
            viewHolder.txtgiasanpham = view.findViewById(R.id.textviewgiavanhoc);
            viewHolder.txtmotasanpham = view.findViewById(R.id.textviewmotavanhoc);
            viewHolder.imgsanpham = view.findViewById(R.id.imageviewvanhoc);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHolder.txtmotasanpham.setMaxLines(2);
        viewHolder.txtmotasanpham.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasanpham.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsanpham);
        return view;
    }
}
