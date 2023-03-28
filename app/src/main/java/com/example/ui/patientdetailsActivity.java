package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class patientdetailsActivity extends AppCompatActivity {
    CardView cardGen, cardppg, cardecg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientdetails_activity);
        cardGen = findViewById(R.id.cardGeneralInfor);
        cardppg = findViewById(R.id.cardPPG);
        cardecg = findViewById(R.id.cardECG);
        cardGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(patientdetailsActivity.this, GeneralinformationActivity.class));
            }
        });

        cardppg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(patientdetailsActivity.this, cardPPGActivity.class));
            }
        });

        cardecg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(patientdetailsActivity.this, cardECGActivity.class));
            }
        });

    }


}