package com.example.shutter_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shutter_firebase.adapterAndView.RecyclerViewAdapter;
import com.example.shutter_firebase.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db;
    private BottomNavigationView mNavigationView;

    private Button buttonSave;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextCountry;
    private EditText editTextCity;
    private EditText editTextAboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        buttonSave = (Button) findViewById(R.id.saveProfile);

        editTextName = (EditText) findViewById(R.id.editName);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextCity=(EditText) findViewById(R.id.editCity);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCountry = (EditText) findViewById(R.id.editCountry);
        editTextAboutMe = (EditText) findViewById(R.id.editTextAboutMe);

        buttonSave.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

        manageNavigationBar();

    }
    public void onClick(View view){
        if(view == buttonSave){
            updateUserDataToFirebase();
        }

    }
    private void updateUserDataToFirebase() {

        db.collection("photographers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()){
                            User user = new User(
                                    querySnapshot.getId(),
                                    querySnapshot.getString("name"),
                                    querySnapshot.getString("email"),
                                    querySnapshot.getString("country"),
                                    querySnapshot.getString("city"),
                                    querySnapshot.getString("aboutme"),
                                    querySnapshot.getString("imageurl"));
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                        Log.w("Problem", e.getMessage());
                    }
                });


        String emailEdit = editTextEmail.getText().toString().trim();
        if(emailEdit.isEmpty()) {
            emailEdit = " ";
        }
        String passwordEdit = editTextPassword.getText().toString().trim();
        if(passwordEdit.isEmpty()){
            passwordEdit = " ";
        }
        String nameEdit = editTextName.getText().toString().trim();
        if(nameEdit.isEmpty()){
            nameEdit = " ";
        }
        String countryEdit = editTextCountry.getText().toString().trim();
        if(countryEdit.isEmpty()) {
            countryEdit = " ";
        }
        String cityEdit = editTextCity.getText().toString().trim();
        if(cityEdit.isEmpty()){
            cityEdit=" ";
        }
        String aboutMeEdit = editTextAboutMe.getText().toString().trim();
        if(aboutMeEdit.isEmpty()){
            aboutMeEdit=" ";
        }

        DocumentReference ref = db.collection("photographers").document();

        Map<String, Object> city = new HashMap<>();
        ArrayList<String> mCity = new ArrayList<>();
        mCity.add(cityEdit);

        Map<String, Object> aboutMe = new HashMap<>();
        ArrayList<String> mAboutMe = new ArrayList<>();
        mAboutMe.add(aboutMeEdit);

        Map<String, Object> country = new HashMap<>();
        ArrayList<String> mCountry = new ArrayList<>();
        mCountry.add(countryEdit);


        country.put("country", mCountry);
        city.put("city", mCity);
        aboutMe.put("aboutme", mAboutMe);

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("name", nameEdit);
        dataMap.put("password", passwordEdit);
        dataMap.put("email", emailEdit);

        ref.set(dataMap);
        ref.collection("city").document().set(city);
        ref.collection("aboutme").document().set(aboutMe);
        ref.collection("country").document().set(country);

        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("SUCCESS", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("FAIL1", "No such document");
                    }
                } else {
                    Log.d("FAIL2", "get failed with ", task.getException());
                }
            }
        });

    }
    private void manageNavigationBar(){
        mNavigationView = findViewById(R.id.navigationView);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = getIntent();

                switch (menuItem.getItemId()) {
                    case R.id.navigation_explore: {
                        intent.setClass(EditProfileActivity.this, ExploreActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_feed: {
                        intent.setClass(EditProfileActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.userPage: {
                        break;
                    }
                }
                return true;
            }
        });
    }
}
