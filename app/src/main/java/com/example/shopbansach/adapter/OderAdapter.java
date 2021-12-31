package com.example.shopbansach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopbansach.R;
import com.example.shopbansach.model.DonHang;
import com.example.shopbansach.model.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class OderAdapter extends BaseAdapter {
    Context context;

    List<DonHang> donHangList;

    public OderAdapter(Context context,ArrayList<DonHang> listtb) {
        this.context = context;

        this.donHangList = listtb;
    }

    @Override
    public int getCount() {
        return donHangList.size();
    }

    @Override
    public Object getItem(int i) {
        return donHangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtOrderName,txtTotal, txtQuantity, txtStatus;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_item,null);
            viewHolder.txtOrderName = view.findViewById(R.id.txtOrderName);
            viewHolder.txtTotal = view.findViewById(R.id.txtTotal);
            viewHolder.txtQuantity = view.findViewById(R.id.txtQuantity);
            viewHolder.txtStatus = view.findViewById(R.id.txtStatus);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DonHang donHang = donHangList.get(i);
        viewHolder.txtOrderName.setText(donHang.getOrderName());
        viewHolder.txtTotal.setText(donHang.getTotal());
        viewHolder.txtQuantity.setText(donHang.getQuantity());
        viewHolder.txtStatus.setText(donHang.getStatus());
        return view;
    }
}
