package com.example.shopbansach.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopbansach.R;
import com.example.shopbansach.model.TaiKhoan;
import com.example.shopbansach.util.Server;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private String email, password;
    private ImageView imageViewHidePassword;
    public static ArrayList<TaiKhoan> mangtaikhoan;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    public static String personEmail= "", personName="";
    public static String taiKhoanDN ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageViewHidePassword = findViewById(R.id.hide_password);
        imageViewHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewHidePassword.setImageResource(R.drawable.eye_hiddent);
                }else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewHidePassword.setImageResource(R.drawable.eye_visibility);
                }

            }
        });
        email = password = "";
        etEmail = findViewById(R.id.edittextdnemail);
        etPassword =  findViewById(R.id.edittextdnpassword);
        mangtaikhoan = new ArrayList<>();

//        google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // Set the dimensions of the sign-in button.
        Button signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            Uri personPhoto;
            if (acct != null) {
                personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                String personId = acct.getId();
                personPhoto = acct.getPhotoUrl();
            }
            Bundle bundle = new Bundle();
            bundle.putString("Email",personEmail);
            bundle.putString("Name", personName);
            Intent intent = new Intent(Login.this, TaiKhoanActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


            // Signed in successfully, show authenticated UI
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Log.d("Message",e.toString());

        }
    }
    public void login(View view){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!email.equals("")&&!password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Đuongdandangnhap, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("QB", "onResponse: " + response);
                    if(response.contains("success")){
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        String tkemail = etEmail.getText().toString().trim();
                        intent.putExtra("email",tkemail);
                        startActivity(intent);
                        finish();
                        Log.e("QB", "onResponse: " + response);
                    }else if(response.contains("failure")) {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdanloginad, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.e("QB", "onResponse: " + response);
                                if(response.contains("success")){
                                    Intent intent = new Intent(Login.this, MainActivityAdmin.class);
                                    taiKhoanDN = etEmail.getText().toString().trim();
                                    intent.putExtra("email",taiKhoanDN);
                                    startActivity(intent);
                                    finish();
                                    Log.e("QB", "onResponse: " + response);
                                }else if(response.contains("failure")) {

                                    Toast.makeText(Login.this, "Nhập sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Login.this, "onResponse: " + response, Toast.LENGTH_SHORT).show();
                                }
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
                                data.put("email",email);
                                data.put("password",password);
                                return data;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                    else {
                        Toast.makeText(Login.this, "onResponse: " + response, Toast.LENGTH_SHORT).show();
                    }
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
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(Login.this, "Field can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view){
        Intent intent = new Intent(this, DangKy.class);
        startActivity(intent);
        finish();
    }
}