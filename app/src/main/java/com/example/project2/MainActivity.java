package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String gender = "여자";
    String blood = "A";
    double height = 0;
    double weight = 0;
    double bmi = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);

        final RadioButton rdo1 = (RadioButton) findViewById(R.id.rdo1);
        final RadioButton rdo2 = (RadioButton) findViewById(R.id.rdo2);

        Spinner spinner = (Spinner) findViewById(R.id.spin1);

        Gallery gal = (Gallery) findViewById(R.id.gal);

        final CheckBox chk1 = (CheckBox) findViewById(R.id.chk1);
        final CheckBox chk2 = (CheckBox) findViewById(R.id.chk2);
        final CheckBox chk3 = (CheckBox) findViewById(R.id.chk3);

        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);

        Button btn1 = (Button) findViewById(R.id.btn1);

        rdo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb = (RadioButton) view;
                gender = rb.getText().toString();

            }
        });

        rdo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb = (RadioButton) view;
                gender = rb.getText().toString();

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               blood = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(),0);
                ArrayList<Integer> imgs = new ArrayList<Integer>();

                if(edt1.getText().toString().equals("")||edt2.getText().toString().equals("")){
                    tv2.setText("2. 신체질량지수는 ???입니다!");

                    new AlertDialog.Builder(MainActivity.this).setTitle("키와 체중").setView(getLayoutInflater().inflate(R.layout.custom_dialog, null))
                            .show();
                }else {
                   String str1 = edt1.getText().toString();
                   String str2 = edt2.getText().toString();

                  height = Double.parseDouble(str1);
                  weight = Double.parseDouble(str2);

                  bmi = weight/((height/100)*(height/100));

                  tv2.setText("2. 신체질량지수는"+Math.round(bmi*10)/10F+ "입니다.");
                }
                if (blood.equals("") || gender.equals("")){
                    tv1.setText("1. ?형 ??입니다.");
                    return;
                }else {
                    tv1.setText("1. "+blood+"형 "+gender+"입니다.");
                }


                if(chk1.isChecked()){
                    imgs.add(R.drawable.drinking);
                }
                if(chk2.isChecked()){
                    imgs.add(R.drawable.ciga);
                }
                if(!chk3.isChecked()){
                    imgs.add(R.drawable.running);
                }

                if(chk1.isChecked() || chk2.isChecked() ||!chk3.isChecked()){
                    gal.setAdapter(new ImgAdapter(MainActivity.this, imgs));
                }else {
                    gal.removeAllViewsInLayout();
                }


            }
        });

    }
}