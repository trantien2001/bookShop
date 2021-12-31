package com.example.shopbansach.activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.model.emailLogin;
import com.example.shopbansach.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongtinTaiKhoan extends AppCompatActivity {
    EditText edname,edphone,eddate,edaddress,edemail;
    Button btnUpdate;

    int idUser;
    Toolbar toolbartttk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_tai_khoan);
        AnhXa();

       // Toast.makeText(getApplicationContext(), emailLogin.email, Toast.LENGTH_SHORT).show();
//        readJson(Server.Duongdaninfor +"?email="+ emailLogin.email.trim());
        readJson(Server.Duongdaninfor +"?email="+ emailLogin.email.trim());
        ActionToolbar();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(Server.Duonganundateinfor);
             //   CheckConnection.ShowToast_Short(getApplicationContext(),"Cập nhật thành công");
            }
        });
    }

    private void update(String url) {
        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            if(response.trim().equals("ok")){
                                Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();

                                // gọi lại hàm load thông
                                readJson(Server.Duongdaninfor +"?email="+ emailLogin.email);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();

                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id",String.valueOf(idUser));
                param.put("name",edname.getText().toString().trim());
                param.put("email",edemail.getText().toString().trim());
                param.put("ngaySinh",eddate.getText().toString().trim());
                param.put("diaChi",edaddress.getText().toString().trim());
                param.put("soDienThoai",edphone.getText().toString().trim());

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbartttk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartttk.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbartttk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void readJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
            new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                   try {
                       JSONObject jsonObject = response.getJSONObject(0);
                       idUser = jsonObject.getInt("id");
                       edname.setText(jsonObject.getString("name"));
                       edaddress.setText(jsonObject.getString("diaChi"));
                       edphone.setText(String.valueOf(jsonObject.getInt("soDienThoai")));
                       eddate.setText(jsonObject.getString("ngaySinh"));
                       edemail.setText(jsonObject.getString("email"));
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

            }
        },
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }



    private void AnhXa() {
        toolbartttk = findViewById(R.id.toolbartttk);
        btnUpdate = findViewById(R.id.btnUpdate);
        edname = findViewById(R.id.edt_tk_ht);
        edaddress = findViewById(R.id.edt_tk_dchi);
        edphone = findViewById(R.id.edt_tk_sdt);
        eddate = findViewById(R.id.edt_tk_ngsinh);
        edemail = findViewById(R.id.edt_tk_em);
    }
}