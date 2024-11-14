package com.aspprothes.arraylistappfromjsonarrayparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView loader;
    private LinearLayout linearLayout;
    private ListView listView;
    private String url = "https://prothes-asp.github.io/ArrayList_App_From_JSONArray_Parsing/JsonArray.json";
    private ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.sky));
        setContentView(R.layout.activity_main);

        loader = findViewById(R.id.loader);
        linearLayout = findViewById(R.id.linearLayout);
        listView = findViewById(R.id.listView);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i<response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String thumbnel = jsonObject.getString("thumbnel");
                        String url = jsonObject.getString("url");

                        hashMap = new HashMap<>();
                        hashMap.put("titles",title);
                        hashMap.put("thumbnels",thumbnel);
                        hashMap.put("urls",url);
                        arrayList.add(hashMap);
                    }

                    loader.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter();
                    listView.setAdapter(customBaseAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something Error", Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);


    }




    public class CustomBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.list_item_design_layout,parent,false);
            }
            ImageView imageViews = convertView.findViewById(R.id.imageViewItems);
            TextView textViews = convertView.findViewById(R.id.textViewItems);
            LinearLayout listLinearItems = convertView.findViewById(R.id.listLinearItems);

            HashMap<String,String> myhashMap = new HashMap<>();
            myhashMap = arrayList.get(position);
            String titles = myhashMap.get("titles");
            String thumbnels = myhashMap.get("thumbnels");
            String urls = myhashMap.get("urls");
            textViews.setText(""+titles);
            String yotube_thumnel_img = "https://img.youtube.com/vi/"+thumbnels+"/1.jpg";

            Glide.with(MainActivity.this)
                    .load(yotube_thumnel_img)
                    .placeholder(R.drawable.loading_animated)
                    .into(imageViews);


            listLinearItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyWebView.web_url = urls;
                    startActivity(new Intent(MainActivity.this, MyWebView.class));
                }
            });


            return convertView;
        }
    }




}