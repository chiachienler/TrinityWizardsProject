package com.example.trinitywizards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_person_details);

        getIncomingIntent();
    }
    
    private void getIncomingIntent(){
        
        if(getIntent().hasExtra("firstName") && getIntent().hasExtra("lastName"))
        {
            String firstName = getIntent().getStringExtra("firstName");
            String lastName = getIntent().getStringExtra("lastName");

            setDetails(firstName, lastName);
        }

    }

    private void setDetails(String firstname, String lastname){
        EditText firstName = findViewById(R.id.txt_person_first_name);
        EditText lastName = findViewById(R.id.txt_person_last_name);

        firstName.setText(firstname);
        lastName.setText(lastname);

    }
    
}