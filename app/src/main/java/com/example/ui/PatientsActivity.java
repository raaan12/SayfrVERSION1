package com.example.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Entity.PatientDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PatientsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Button details;
    PatientDB db;
    TextView no_data;

    ArrayList<String> patient_id, patient_firstName, patient_lastName, patient_bluetooth;
    CustomAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
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
        db = new PatientDB(PatientsActivity.this, "SAYFR", null, 1);
        patient_id = new ArrayList<>();
        patient_firstName = new ArrayList<>();
        patient_lastName = new ArrayList<>();
        patient_bluetooth= new ArrayList<>();
        storeDataInArrays();


        customAdapter = new CustomAdapter(PatientsActivity.this,this,  patient_id, patient_firstName, patient_lastName, patient_bluetooth );
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PatientsActivity.this));


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PatientDB myDB = new PatientDB(PatientsActivity.this, "SAYFR", null, 1);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(PatientsActivity.this, PatientsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
