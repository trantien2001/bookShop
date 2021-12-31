package com.example.shopbansach.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import com.example.shopbansach.model.emailLogin;
import com.example.shopbansach.util.Server;

import java.util.HashMap;
import java.util.Map;


public class DoimatkhauActivity extends AppCompatActivity {
    EditText editOldPass, editNewPass;
    Button btnResetPass;


    Toolbar toolbardmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        AnhXa();
        ActionToolbar();
        setBtnResetPass();
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbardmm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardmm.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbardmm.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public  void setBtnResetPass(){
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String NewPass = editNewPass.getText().toString().trim();
               String OldPass = editOldPass.getText().toString().trim();
                if(!NewPass.equals("") && !OldPass.equals("")){
                    StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.DuongdanresetPass, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.contains("success")){
                                Toast.makeText(getApplicationContext(), "Cập nhật mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                            }
                          else  if(response.contains("failure")){
                                Toast.makeText(getApplicationContext(), "Sai mật khẩu !", Toast.LENGTH_SHORT).show();
                            }
                          else
                                Toast.makeText(getApplicationContext(), "onResponse: " + response, Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_SHORT).show();

                        }


                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("OldPass",OldPass);
                            data.put("NewPass",NewPass);
                            data.put("email", emailLogin.email);
                            return data;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                else
                    Toast.makeText(getApplicationContext(), "Field can not be empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void AnhXa(){
        editNewPass = (EditText) findViewById(R.id.editNewPass);
        editOldPass = (EditText) findViewById(R.id.editOldPass);
        btnResetPass = (Button) findViewById(R.id.btnResetPass);
        toolbardmm = findViewById(R.id.toolbardmm);
    }
}