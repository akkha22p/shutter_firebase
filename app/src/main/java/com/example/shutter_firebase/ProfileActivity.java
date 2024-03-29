package com.example.shutter_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shutter_firebase.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Random;



public class ProfileActivity extends AppCompatActivity{


    User user;
    private Button edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        processExtraData();

        user = (User) getIntent().getSerializableExtra("USER");

        edit = (Button) findViewById(R.id.editProfile);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        processExtraData();
    }

    private void processExtraData(){
        User user = (User) getIntent().getSerializableExtra("USER");
        TextView nameField = findViewById(R.id.userProfile);
        nameField.setText(user.getName());

    }
}

