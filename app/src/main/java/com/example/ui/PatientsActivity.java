package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Entity.PatientDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PatientsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    PatientDB db;
    ArrayList<String> patient_id, patient_firstName, patient_lastName, patient_bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientsActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        db = new PatientDB(PatientsActivity.this);
        patient_id = new ArrayList<>();
        patient_firstName = new ArrayList<>();
        patient_lastName = new ArrayList<>();
        patient_bluetooth= new ArrayList<>();
        storeDataInArrays();


        CustomAdapter customAdapter = new CustomAdapter(PatientsActivity.this, patient_id, patient_firstName, patient_lastName, patient_bluetooth );
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PatientsActivity.this));


    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                patient_id.add(cursor.getString(0));
                patient_firstName.add(cursor.getString(1));
                patient_lastName.add(cursor.getString(2));
                patient_bluetooth.add(cursor.getString(3));

            }
        }
    }
}
