package com.example.project;

import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeTask extends AppCompatActivity {

    TextView TaskC, DescC, PriorC, DateC;
    Button Update, Done;

    String Tasks, Desciptions, Prioritys, Dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task);

        TaskC = findViewById(R.id.TaskC);
        DescC = findViewById(R.id.DescC);
        PriorC = findViewById(R.id.PriorC);
        DateC = findViewById(R.id.DateC);
        Update = findViewById(R.id.btn_update);
        Done = findViewById(R.id.btnDone);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(Tasks);
        }

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                SQL myDB = new SQL(ChangeTask.this);
                Tasks = TaskC.getText().toString().trim();
                Desciptions = DescC.getText().toString().trim();
                Prioritys = PriorC.getText().toString().trim();
                Dates = DateC.getText().toString().trim();
                myDB.updateData(Tasks, Desciptions, Prioritys, Dates);
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            Tasks = getIntent().getStringExtra("task");
            Desciptions = getIntent().getStringExtra("title");
            Prioritys = getIntent().getStringExtra("author");
            Dates = getIntent().getStringExtra("pages");

            //Setting Intent Data
            TaskC.setText(Tasks);
            DescC.setText(Desciptions);
            PriorC.setText(Prioritys);
            DateC.setText(Dates);
            Log.d("stev", TaskC+" "+TaskC+" "+TaskC);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Done with " + TaskC + " ?");
        builder.setMessage("Are you sure you want to remove " + TaskC + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQL myDB = new SQL(ChangeTask.this);
                myDB.deleteOneRow(TaskC.toString());
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}