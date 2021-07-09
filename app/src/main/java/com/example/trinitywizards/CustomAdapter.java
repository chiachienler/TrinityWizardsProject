package com.example.trinitywizards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> people_names;
    ArrayList<String> people_firstnames;
    ArrayList<String> people_lastnames;
    Context ctx;

    public CustomAdapter(ArrayList<String> people_names, ArrayList<String> people_firstnames, ArrayList<String> people_lastnames, Context ctx) {
        this.people_names = people_names;
        this.people_firstnames = people_firstnames;
        this.people_lastnames = people_lastnames;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_people,parent,false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    
    
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(people_names.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, people_names.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra("firstName", people_firstnames.get(position));
                intent.putExtra("lastName", people_lastnames.get(position));

                ctx.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return people_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, firstname, lastname, email, phone;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_people_name);
        }
    }


}
