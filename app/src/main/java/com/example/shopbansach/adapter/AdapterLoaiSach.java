package com.example.shopbansach.adapter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AdapterLoaiSach {
    private static AdapterLoaiSach mAdapterLoaiSach;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private AdapterLoaiSach(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue==null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }

    public static synchronized AdapterLoaiSach getInstance(Context context){
        if(mAdapterLoaiSach==null)
        {
            mAdapterLoaiSach = new AdapterLoaiSach(context);
        }
        return mAdapterLoaiSach;
    }

    public<T> void addToRequesQue(Request<T> request){
        getRequestQueue().add(request);
    }
}
