package com.example.trinitywizards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    ArrayList<String> people_names = new ArrayList<>();
    ArrayList<String> people_firstnames = new ArrayList<>();
    ArrayList<String> people_lastnames = new ArrayList<>();
    ArrayList<String> people_emails = new ArrayList<>();
    ArrayList<String> people_phones = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerPeople);

        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //JSON
        try {
            JSONObject obj = new JSONObject(loadJSONfromAssets());

            JSONArray userArray = obj.getJSONArray("user");

            for (int i=0; i < userArray.length(); i++){

                JSONObject peopleDetail = userArray.getJSONObject(i);

                people_names.add(peopleDetail.getString("firstName")+ " " +peopleDetail.getString("lastName") );
                people_firstnames.add(peopleDetail.getString("firstName"));
                people_lastnames.add(peopleDetail.getString("lastName"));
               // people_emails.add(peopleDetail.getString("email"));
               // people_phones.add(peopleDetail.getString("phone"));

            }


        }catch (JSONException e)
        {
            e.printStackTrace();
        }


        CustomAdapter customAdapter = new CustomAdapter(people_names,people_firstnames,people_lastnames,MainActivity.this);
        recyclerview.setAdapter(customAdapter);


    }

    private String loadJSONfromAssets() {
        String json = null;

        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}