package com.example.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<String> items;
    private Context mContext;

    public MyAdapter(ArrayList<String> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items_list, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.txtItem.setText(items.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        SaveData.writeData( items, mContext);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItem;
        private CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
