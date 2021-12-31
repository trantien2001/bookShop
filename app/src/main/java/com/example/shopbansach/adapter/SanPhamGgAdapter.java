package com.example.shopbansach.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopbansach.R;
import com.example.shopbansach.activity.Chitietsanpham;
import com.example.shopbansach.model.Sanpham;
import com.example.shopbansach.util.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

    public class SanPhamGgAdapter extends RecyclerView.Adapter<SanPhamGgAdapter.ItemHolder1> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

        public SanPhamGgAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
            this.context = context;
            this.arraysanpham = arraysanpham;
        }

        @NonNull
    @Override
    public ItemHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphamgiamgia,null);
        ItemHolder1 itemHolder = new ItemHolder1(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder1 itemHolder1, int i) {
        Sanpham sanpham = arraysanpham.get(i);
        itemHolder1.txtTensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder1.txtGiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(itemHolder1.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder1 extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txtTensanpham,txtGiasanpham;

        public ItemHolder1(View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.imageviewsanpham);
            txtTensanpham = itemView.findViewById(R.id.textviewtensanpham);
            txtGiasanpham = itemView.findViewById(R.id.textviewgiasanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Chitietsanpham.class);
                    intent.putExtra("thongtinsanpham",arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraysanpham.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
