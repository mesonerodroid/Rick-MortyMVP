package com.sergio.mesonero.rickmorty.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergio.mesonero.rickmorty.R;
import com.sergio.mesonero.rickmorty.main_activity.RecyclerItemClickListener;
import com.sergio.mesonero.rickmorty.model.Json.Person;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private ArrayList<Person> data;
    private RecyclerItemClickListener recyclerItemClickListener;

    public PersonAdapter(ArrayList<Person> data, RecyclerItemClickListener recyclerItemClickListener) {
        this.data = data;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.person_row, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.txtPersonName.setText(data.get(position).getName());
        holder.txtPersonSpecie.setText(data.get(position).getSpecies());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView txtPersonName, txtPersonSpecie;

        PersonViewHolder(View itemView) {
            super(itemView);
            //todo butter
            txtPersonName =  itemView.findViewById(R.id.person_rw_name);
            txtPersonSpecie =  itemView.findViewById(R.id.person_rw_specie);
        }
    }
}