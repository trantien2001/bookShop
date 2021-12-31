package com.example.shopbansach.activity;

import static com.example.shopbansach.activity.Login.personEmail;
import static com.example.shopbansach.activity.Login.personName;
import static com.example.shopbansach.activity.Login.taiKhoanDN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.model.emailLogin;
import com.example.shopbansach.util.CheckConnection;
import com.example.shopbansach.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaiKhoanADActivity extends AppCompatActivity {
    Button btndangxuat,btntttk,btndoimk;
    Toolbar toolbartk;
    TextView txtEmail, txtName;
    TextView txtTen;
    int idUser;
    LinearLayout ln_home,ln_tk,ln_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_adactivity);
        AnhXa();
        GetEmail();
        OnClickcontent();
        AcctionToolbar();
        CheckConnection.ShowToast_Short(getApplicationContext(),taiKhoanDN.trim());
        readJson(Server.Duongdaninfor +"?email="+ Login.taiKhoanDN.trim());
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
                            txtTen.setText(jsonObject.getString("name"));
                            txtEmail.setText(jsonObject.getString("email"));
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

    private void OnClickcontent() {
        ln_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TimKiemSachAd.class);
                startActivity(intent);
            }
        });
        ln_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivityAdmin.class);
                startActivity(intent);
            }
        });
        ln_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TaiKhoanADActivity.class);
                startActivity(intent);
            }
        });

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        btndoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DoiMatKhauAD.class);
                startActivity(intent);
            }
        });

        btntttk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TTTKAdmin.class);
                startActivity(intent);
            }
        });
    }

    private void GetEmail() {
        txtEmail.setText(personEmail);
        txtName.setText(personName);
    }

    private void AcctionToolbar() {
        setSupportActionBar(toolbartk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartk.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbartk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.shopbansach.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void AnhXa() {
        btndangxuat = findViewById(R.id.buttonDangXuatAD);
        toolbartk = findViewById(R.id.toolbartaikhoanAD);
        txtName = findViewById(R.id.textviewtenAD);
        txtEmail = findViewById(R.id.textviewemailAD);
        btntttk = findViewById(R.id.btnthongtintkAD);
        ln_home = findViewById(R.id.ln_home_ad);
        ln_tk = findViewById(R.id.ln_tk_ad);
        ln_search = findViewById(R.id.ln_search_ad);
        btndoimk = findViewById(R.id.btndoimatkhauAD);
        txtTen = findViewById(R.id.textviewtenAD);
    }
}