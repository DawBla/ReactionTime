package com.example.reactiontime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize buttons
        Button btnSolo = findViewById(R.id.btnSolo);
        //set on click listeners
        btnSolo.setOnClickListener(view -> SoloActivity());
    }
    //fire the appropriate activity
    public void SoloActivity(){
        Intent intent = new Intent(this, SoloActivity.class);
        startActivity(intent);
    }

}