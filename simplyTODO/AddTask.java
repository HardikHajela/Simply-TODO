package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {

    EditText Task, Desc, Prior;
    DatePicker Date;
    Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Task = findViewById(R.id.Task);
        Desc = findViewById(R.id.Desc);
        Prior = findViewById(R.id.Prior);
        Date = findViewById(R.id.Date);
        Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tempdate= Date.getDayOfMonth()+"/"+ (Date.getMonth() + 1)+"/"+Date.getYear();
                SQL myDB = new SQL(AddTask.this);
                myDB.addTask(Task.getText().toString().trim(),
                        Desc.getText().toString().trim(),
                        Integer.valueOf(Prior.getText().toString().trim()),
                        Tempdate
                        );
            }
        });
    }
}
