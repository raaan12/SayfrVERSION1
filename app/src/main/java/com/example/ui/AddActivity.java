package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Entity.PatientDB;

public class AddActivity extends AppCompatActivity {
    EditText firstname, lastname, bluetooth;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        firstname = findViewById(R.id.patientFirstName);
        lastname = findViewById(R.id.patientLastName);
        bluetooth = findViewById(R.id.smartwatch_Bluetooth);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientDB db = new PatientDB(AddActivity.this, "SAYFR", null, 1);
                db.addPatient(firstname.getText().toString().trim(),
                        lastname.getText().toString().trim(),
                        bluetooth.getText().toString().trim());
            }
        });
    }
}