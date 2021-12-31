package com.example.shopbansach.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.adapter.AdapterLoaiSach;
import com.example.shopbansach.util.CheckConnection;
import com.example.shopbansach.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ThemTheLoaiSach extends AppCompatActivity{
    private Button btnchon,btnupload;
    private ImageView imgha;
    private EditText edtTen;
    private final int IMG_REQUEST = 999;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai_sach);
        AnhXa();
        btnchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ThemTheLoaiSach.this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},IMG_REQUEST);
            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoaiSach();
            }
        });
    }

    private void AnhXa() {
        edtTen = findViewById(R.id.edtTenLoaiSach);
        imgha = findViewById(R.id.imageViewLS);
        btnchon = findViewById(R.id.btnChonHALS);
        btnupload = findViewById(R.id.btnUpload);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgha.setImageBitmap(bitmap);
                imgha.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == IMG_REQUEST){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Chọn hình ảnh "),IMG_REQUEST);
            }
            else {
                Toast.makeText(getApplicationContext(),"Không có quyền truy cập",Toast.LENGTH_LONG).show();
            }

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void addLoaiSach(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdaninsertloaisach, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                imgha.setImageResource(0);
                imgha.setVisibility(View.GONE);
                edtTen.setText("");
                CheckConnection.ShowToast_Short(getApplicationContext(),"Them thanh cong");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemTheLoaiSach.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",edtTen.getText().toString().trim());
                params.put("image",imageToString(bitmap));
                return params;
            }
        };
        AdapterLoaiSach.getInstance(ThemTheLoaiSach.this).addToRequesQue(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}