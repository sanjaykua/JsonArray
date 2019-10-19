package com.example.jsonarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jsonarray.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url="http://androindian.com/apps/blog_links/api.php";
    ActivityMainBinding binding;
    ArrayList<String> TitleArray=new ArrayList<String>();
    ArrayList<String> IDArray=new ArrayList<String>();
    ArrayList<String> URLArray=new ArrayList<String>();
    String Title,ID,AppURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_all_links");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Res",jsonObject.toString());

        LoadData loadData=new LoadData();
        loadData.execute(jsonObject.toString());
    }

    private class LoadData extends AsyncTask<String,String,String> {

       Dialog dialog=new Dialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setContentView(R.layout.cuttom);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {
            JSONObject jsonObject=JsonFuntion.getdata(url,param[0]);
            Log.i("Res",jsonObject.toString());

            return jsonObject.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            try {
                JSONObject object=new JSONObject(s);

                String res=object.getString("response");
                if (res.equalsIgnoreCase("success")){

                    //array
                    JSONArray jsonArray=object.getJSONArray("data");
                    Log.i("Res",jsonArray.toString());
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject j2=jsonArray.getJSONObject(i);
                        //array closed

                        Title=j2.getString("title");
                        Log.i("Res", Title);

                        ID=j2.getString("id");
                        Log.i("Res",ID);

                        AppURL=j2.getString("url");
                        Log.i("Res",AppURL);

                        TitleArray.add(Title);
                        Log.i("Res",TitleArray.toString());
                        IDArray.add(ID);
                        Log.i("Res",IDArray.toString());
                        URLArray.add(AppURL);
                        Log.i("Res",URLArray.toString());

                    }

                    //rec
                    LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false);
                    binding.recycler.setLayoutManager(layoutManager);
                    binding.recycler.setAdapter(new AppAdb(MainActivity.this,TitleArray,IDArray,URLArray));
                }
                else {
                    Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
