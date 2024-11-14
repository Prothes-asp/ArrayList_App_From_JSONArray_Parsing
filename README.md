
# ArrayList App From JSONArray Parsing

ListView Item And Set Item Onclick Listener with WebView and Parsing Data From JsonArray


# Roadmap this Application

- Additional Lottie Files
- Additional Volley Library
- Add more Online ImageView Glide Library


```bash
    implementation 'com.airbnb.android:lottie:3.4.0'
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.github.bumptech.glide:glide:4.16.0'
```

- Then Using For Loop Data Parsing From JSON Array. And After Parsing Data From Array then the data add with hashmap to ArrayList. And Finally Called Base Adapter And Set Data get from ArrayList.

- So Here For Loop ...

```bash
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
```

- Call Base Adapter
```bash
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
```

## ScreenShot or Demo
![Screenshot_1](https://github.com/user-attachments/assets/28ef7e75-c156-452e-811d-2af346a74223)
![Screenshot_2](https://github.com/user-attachments/assets/50481e08-117b-40d2-899d-16216986e874)

## Authors

- [Prothes Barai](https://prothes-asp.github.io/prothes/)

