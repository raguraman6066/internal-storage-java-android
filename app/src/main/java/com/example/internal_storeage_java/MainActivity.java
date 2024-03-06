package com.example.internal_storeage_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText person_name,person_age;
    Button save,load;
    TextView person_result;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person_name=findViewById(R.id.person_name);
        person_age=findViewById(R.id.person_age);
        person_result=findViewById(R.id.person_result);
        save=findViewById(R.id.button);
        load=findViewById(R.id.button2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=  person_name.getText().toString();
                int age= Integer.parseInt(person_age.getText().toString());

                FileOutputStream fos;
                try {
                    fos=openFileOutput("my_file.txt",MODE_PRIVATE);
                    fos.write((name+"\n").getBytes());
                    fos.write((age+"\n").getBytes());
                    fos.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(MainActivity.this, "Data is saved successfully", Toast.LENGTH_SHORT).show();
                person_name.getText().clear();
                person_age.getText().clear();
                person_name.requestFocus();

            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileInputStream;
                try {
                    fileInputStream=openFileInput("my_file.txt");
                    BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream));
                    String line="";

                    StringBuilder builder=new StringBuilder();
                    while((line=br.readLine())!=null){
                        builder.append(line+"\n");
                    }
                    person_result.setText(builder.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });




    }
}