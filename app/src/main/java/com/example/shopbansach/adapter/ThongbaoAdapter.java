package com.example.shopbansach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbansach.R;
import com.example.shopbansach.model.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class ThongbaoAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ThongBao> listtb;

    public ThongbaoAdapter(Context context, int layout, ArrayList<ThongBao> listtb) {
        this.context = context;
        this.layout = layout;
        this.listtb = listtb;
    }

    @Override
    public int getCount() {
        return listtb.size();
    }

    @Override
    public Object getItem(int i) {
        return listtb.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolderTB{
        TextView txtNameTB,txtMTTB;
        ImageView imageViewTB;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderTB viewHolderTB;
        if (view==null){
            viewHolderTB = new ViewHolderTB();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_thongbao,null);
            viewHolderTB.txtNameTB = view.findViewById(R.id.textviewthongbao);
            viewHolderTB.txtMTTB = view.findViewById(R.id.textviewmotatb);
            viewHolderTB.imageViewTB = view.findViewById(R.id.imageviewthongbao);
            view.setTag(viewHolderTB);
        }
        else {
            viewHolderTB = (ViewHolderTB) view.getTag();
        }
        ThongBao thongBao = listtb.get(i);

        viewHolderTB.txtNameTB.setText(thongBao.getTentb());
        viewHolderTB.txtMTTB.setText(thongBao.getMotatb());
        viewHolderTB.imageViewTB.setImageResource(thongBao.getImgagetb());
        return view;
    }
}
