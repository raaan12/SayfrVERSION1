package com.example.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;

    ArrayList patient_id, patient_firstname, patient_lastname, patient_bluetooth;

    CustomAdapter(Context context, ArrayList patient_id, ArrayList patient_firstname, ArrayList patient_lastname, ArrayList patient_bluetooth) {
        this.context = context;
        this.patient_id = patient_id;
        this.patient_firstname = patient_firstname;
        this.patient_lastname = patient_lastname;
        this.patient_bluetooth = patient_bluetooth;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.patient_id_txt.setText(String.valueOf(patient_id.get(position)));
        holder.patient_firstname_txt.setText(String.valueOf(patient_firstname.get(position)));
        holder.patient_lastname_txt.setText(String.valueOf(patient_lastname.get(position)));
        holder.patient_bluetooth_txt.setText(String.valueOf(patient_bluetooth.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(patient_id.get(position)));
                intent.putExtra("firstname", String.valueOf(patient_firstname.get(position)));
                intent.putExtra("lastname", String.valueOf(patient_lastname.get(position)));
                intent.putExtra("bluetooth", String.valueOf(patient_bluetooth.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patient_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patient_id_txt, patient_firstname_txt, patient_lastname_txt, patient_bluetooth_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_id_txt = itemView.findViewById(R.id.patient_id_txt);
            patient_firstname_txt = itemView.findViewById(R.id.patientFirstName);
            patient_lastname_txt = itemView.findViewById(R.id.patientLastName);
            patient_bluetooth_txt = itemView.findViewById(R.id.bluetooth);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
