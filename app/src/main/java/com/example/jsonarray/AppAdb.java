package com.example.jsonarray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppAdb extends RecyclerView.Adapter<AppAdb.MyViewHolder> {
    ArrayList<String> adptitleArray;
    ArrayList<String> adpidArray;
    ArrayList<String> adpurlArray;
    Context context;
    public AppAdb(MainActivity mainActivity,
                  ArrayList<String> titleArray,
                  ArrayList<String> idArray,
                  ArrayList<String> urlArray) {
        context=mainActivity;
        adptitleArray=titleArray;
        adpidArray=idArray;
        adpurlArray=urlArray;
    }

    @NonNull
    @Override
    public AppAdb.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.listdata,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdb.MyViewHolder holder, int position) {
        holder.tv.setText(adptitleArray.get(position));
        holder.tv1.setText(adpidArray.get(position));
        holder.tv2.setText(adpurlArray.get(position));
    }

    @Override
    public int getItemCount() {
        return adptitleArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textTitle);
            tv1=itemView.findViewById(R.id.textID);
            tv2=itemView.findViewById(R.id.textURL);
        }
    }
}
