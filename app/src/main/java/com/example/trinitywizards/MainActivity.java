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

            for (int i=1; i < userArray.length(); i++){

                JSONObject peopleName = userArray.getJSONObject(i);

                people_names.add(peopleName.getString("firstName")+ " " +peopleName.getString("lastName") );

            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }


        CustomAdapter customAdapter = new CustomAdapter(people_names,MainActivity.this);
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