package com.example.shopbansach.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.model.diachi;
import com.example.shopbansach.model.emailLogin;
import com.example.shopbansach.util.Server;

import java.util.HashMap;
import java.util.Map;

public class ThemDiaChi extends AppCompatActivity {
    Toolbar toolbartdc;
    Button btnsave, btnExit;
    EditText editAddressName, editAddress, editNumPhone, editReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_chi);
        AnhXa();
        ActionToolbar();
        setActionButton();
    }

    private void setActionButton() {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editAddress.getText().toString().trim().isEmpty()||
                        editNumPhone.getText().toString().trim().isEmpty() ||
                        editAddressName.getText().toString().trim().isEmpty() ||
                        editReceiver.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(), "Bạn phải điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                else {
                    addAddress();
                    finish();
                }

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addAddress(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongdaninsertAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(), "Đã thêm địa chỉ mới !", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("DIACHI",";;;;");
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Xảy ra lỗi chèn!", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Xảy ra lỗi !", Toast.LENGTH_SHORT).show();
                Log.d("aaa","õi"+error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("tenDiaChi",editAddressName.getText().toString().trim());
                param.put("diaChi",editAddress.getText().toString().trim());
                param.put("soDienThoaiDc",editNumPhone.getText().toString().trim());
                param.put("tenNguoiNhan",editReceiver.getText().toString().trim());
                param.put("email", emailLogin.email);
                return param;
            }
        };

        requestQueue.add(stringRequest);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbartdc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartdc.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbartdc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        btnExit =findViewById(R.id.btnExit);

        editAddressName =findViewById(R.id.editAddressName);
        editAddress = findViewById(R.id.editAddress);
        editNumPhone = findViewById(R.id.editnumPhone);
        editReceiver = findViewById(R.id.editReceiver);
        toolbartdc = findViewById(R.id.toolbarthemdc);
        btnsave = findViewById(R.id.btnsaveAddress);
    }
}