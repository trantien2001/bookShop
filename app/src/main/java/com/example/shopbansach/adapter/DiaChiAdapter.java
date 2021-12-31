package com.example.shopbansach.adapter;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopbansach.R;
import com.example.shopbansach.activity.Diachigiaohang;
import com.example.shopbansach.activity.Thongtinkhachhang;
import com.example.shopbansach.model.Sanpham;
import com.example.shopbansach.model.diachi;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DiaChiAdapter extends BaseAdapter {
    private ArrayList<diachi> diachiArrayList;
    private Diachigiaohang context;

    public DiaChiAdapter(ArrayList<diachi> diachiArrayList, Diachigiaohang context) {
        this.diachiArrayList = diachiArrayList;
        this.context = context;
    }

    public class ViewHolder{
        LinearLayout linearLayoutDc;
        public Button btnDeleteDc;
        public TextView txtTenDiaChi ,txtTenNguoiDung, txtDiaChi, txtSoDienThoai;
    }
    @Override
    public int getCount() {
        return diachiArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.diachi_item,null);
            viewHolder.txtDiaChi = view.findViewById(R.id.txtDiaChi);
            viewHolder.txtTenDiaChi = view.findViewById(R.id.txtTenDiaChi);
            viewHolder.txtSoDienThoai = view.findViewById(R.id.txtSoDienThoai);
            viewHolder.txtTenNguoiDung = view.findViewById(R.id.txtTenNguoiDung);
            viewHolder.btnDeleteDc = (Button) view.findViewById(R.id.btnDeleteDc);
            viewHolder.linearLayoutDc = view.findViewById(R.id.linearLayoutDc);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final diachi dc = diachiArrayList.get(i);
        viewHolder.txtTenDiaChi.setText(dc.getTenDiaChi());
        viewHolder.txtDiaChi.setText(dc.getDiaChi());
        viewHolder.txtTenNguoiDung.setText(dc.getTenNguoiDung());
        viewHolder.txtSoDienThoai.setText(dc.getSoDienThoai());
        viewHolder.btnDeleteDc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogConfirm(dc.getTenDiaChi(), dc.getIdDc());
            }
        });

        viewHolder.linearLayoutDc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Thongtinkhachhang.class);
                diachi dc = diachiArrayList.get(i);
                intent.putExtra("DIACHI",dc);
                context.setResult(RESULT_OK,intent);
                context.finish();

            }
        });
        return view;
    }


    private void diaLogConfirm(String name,final int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setMessage("Bạn có chắc muốn xóa địa chỉ : " +name +" ?");
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    context.deleteAddress(id);
            }
        });
        dialogDelete.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();
    }
}
