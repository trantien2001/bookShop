package com.example.shopbansach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.adapter.DiaChiAdapter;
import com.example.shopbansach.adapter.TruyenTranhAdapter;
import com.example.shopbansach.model.Sanpham;
import com.example.shopbansach.model.diachi;
import com.example.shopbansach.model.emailLogin;
import com.example.shopbansach.util.CheckConnection;
import com.example.shopbansach.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Diachigiaohang extends AppCompatActivity {
    Button btnthemdc;
    Toolbar toolbardc;
    DiaChiAdapter diaChiAdapter;
    ListView listView;
    ArrayList<diachi> diachiArrayList;
    int REQUEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachigiaohang);
        AnhXa();
        ActionToolbar();
        GetData();
        btnthemdiachi();
    }



    public void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdanalldiachi;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                diachiArrayList.clear();
                String  soDienThoai , tenDiaChi, diaChi ,email ,  tenNguoiDung;
                int id;
                if(response != null && response.length() != 2  ){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("idDiaChi");
                            email = jsonObject.getString("email");
                            diaChi =  jsonObject.getString("diaChi");
                            tenDiaChi =   (jsonObject.getString("tenDiaChi"));
                            soDienThoai = (jsonObject.getString("soDienThoaiDc"));
                            tenNguoiDung = (jsonObject.getString("tenNguoiNhan"));
                            diachiArrayList.add(new diachi(id,tenDiaChi, diaChi, tenNguoiDung, soDienThoai));
                            diaChiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("email",String.valueOf(emailLogin.email));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void btnthemdiachi(){
        btnthemdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),ThemDiaChi.class);
                startActivityForResult(intent,REQUEST);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(REQUEST == requestCode){
            GetData();
        }
        else
            GetData();
           // Toast.makeText(getApplicationContext(), "kkkkk", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbardc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardc.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbardc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void deleteAddress(int iddc){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongdandeleteAdress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.toString().trim().equals("success")){
                        Toast.makeText(getApplicationContext(), "Xóa địa chỉ thành công", Toast.LENGTH_SHORT).show();
                        GetData();
                    }
                   else if(response.toString().trim().equals("error")){
                        Toast.makeText(getApplicationContext(), "Xóa địa chỉ thất bại", Toast.LENGTH_SHORT).show();

                    }
                   else
                        Toast.makeText(getApplicationContext(), "Xóa k dc", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramas = new HashMap<>();
                paramas.put("email",emailLogin.email);
                paramas.put("idDiaChi",String.valueOf(iddc));
                return paramas;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void AnhXa() {
        btnthemdc = findViewById(R.id.btnthemdiachi);
        toolbardc = findViewById(R.id.toolbardiachi);
        listView = findViewById(R.id.listViewDiaChi);
        diachiArrayList = new ArrayList<>();
        diaChiAdapter = new DiaChiAdapter(diachiArrayList,Diachigiaohang.this);
        listView.setAdapter(diaChiAdapter);
    }
}