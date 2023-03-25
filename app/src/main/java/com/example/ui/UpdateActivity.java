package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Entity.PatientDB;

public class UpdateActivity extends AppCompatActivity {
    EditText patient_firstname, patient_lastname, patient_bluetooth;
    Button update_button;
    String id, firstname, lastname, bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        patient_firstname = findViewById(R.id.patientFirstName2);
        patient_lastname = findViewById(R.id.patientLastName2);
        patient_bluetooth = findViewById(R.id.smartwatch_Bluetooth2);
        update_button = findViewById(R.id.add_button2);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientDB db = new PatientDB(UpdateActivity.this);
                db.updatePatient(id, firstname, lastname, bluetooth);

            }
        });
        getAndSetIntentData();


    }


    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("firstname") && getIntent().hasExtra("lastname") && getIntent().hasExtra("bluetooth")) {
            //getting data from intent
            id = getIntent().getStringExtra("id");
            firstname = getIntent().getStringExtra("firstname");
            lastname = getIntent().getStringExtra("lastname");
            bluetooth = getIntent().getStringExtra("bluetooth");
            //setting data to intent

            patient_firstname.setText(firstname);
            patient_lastname.setText(lastname);
            patient_bluetooth.setText(bluetooth);


        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}