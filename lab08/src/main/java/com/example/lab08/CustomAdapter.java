package com.example.lab08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<perfume> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<perfume> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_perfume, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPhoto())
                .into(holder.iv_photo);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_brand.setText(arrayList.get(position).getBrand());
        holder.tv_type.setText(arrayList.get(position).getType());
        holder.tv_scent.setText(arrayList.get(position).getScent());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;
        TextView tv_name;
        TextView tv_brand;
        TextView tv_type;
        TextView tv_scent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_photo = itemView.findViewById(R.id.iv_photo);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_brand = itemView.findViewById(R.id.tv_brand);
            this.tv_type = itemView.findViewById(R.id.tv_type);
            this.tv_scent = itemView.findViewById(R.id.tv_scent);
        }
    }
}
