package com.example.amit.avologyapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
  EditText Name,Email,MobileNo;
    Spinner Gender,Qualification,Bpo_Experience,Work_Experience;
    Button Submit;
    String genderType[]=null;
    String qualificationType[]=null;
    String bpo_experience[]=null;
    String work_experience[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=(EditText)findViewById(R.id.edit_Name);
        Email=(EditText)findViewById(R.id.edit_email);
        MobileNo=(EditText)findViewById(R.id.edit_mobileNo);
        Gender=(Spinner)findViewById(R.id.spinner_gender);
        Qualification=(Spinner) findViewById(R.id.spinner_qualification);
        Bpo_Experience=(Spinner)findViewById(R.id.spinner_bpi_experience);
        Work_Experience=(Spinner)findViewById(R.id.spinner_work_experience);
        Submit=(Button)findViewById(R.id.btn_submit);
        //Spinner1->Gender Type
        genderType=getResources().getStringArray(R.array.Select_gender);
        ArrayAdapter<String>arrayAdapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,genderType);
        Gender.setAdapter(arrayAdapter1);

        //Spinner2->Qualification Type
        qualificationType=getResources().getStringArray(R.array.Select_qualification);
        ArrayAdapter<String>arrayAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,qualificationType);
        Qualification.setAdapter(arrayAdapter2);

        //Spinner3->BPO Experience
        bpo_experience=getResources().getStringArray(R.array.Select_bpo_experience);
        ArrayAdapter<String>arrayAdapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,bpo_experience);
        Bpo_Experience.setAdapter(arrayAdapter3);

        //Spinner4->Work Experience
        work_experience=getResources().getStringArray(R.array.Select_work_experience);
        ArrayAdapter<String>arrayAdapter4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,work_experience);
        Work_Experience.setAdapter(arrayAdapter4);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Spinner_Gender=Gender.getSelectedItem().toString();
                String Spinner_Qualification=Qualification.getSelectedItem().toString();
                String Spinner_BPO_Experience=Bpo_Experience.getSelectedItem().toString();
                String Spinner_Work_Experience=Work_Experience.getSelectedItem().toString();
                String Edit_Name=Name.getText().toString();
                String Edit_EmailId=Email.getText().toString();
                String Edit_Mobile_Number=MobileNo.getText().toString();
                Name.setText("");
                Email.setText("");
                MobileNo.setText("");
                Gender.setSelection(0);
                Qualification.setSelection(00);
                Bpo_Experience.setSelection(0);
                Work_Experience.setSelection(0);

                String Method="userdata";
                BackgroundTask backgroundTask=new BackgroundTask(MainActivity.this);
                backgroundTask.execute(Method,Edit_Name,Edit_EmailId,Edit_Mobile_Number,Spinner_Gender,
                        Spinner_Qualification,Spinner_BPO_Experience,Spinner_Work_Experience);

            }
        });
    }
}
