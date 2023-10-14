package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username, password, birth, number, country, address, email;
    RadioGroup gender;
    Button register, cancel;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        birth = findViewById(R.id.birth);
        number = findViewById(R.id.number);
        country = findViewById(R.id.country);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);
        gender = findViewById(R.id.gender);

        preferences = getSharedPreferences("Userinfo", 0);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamevalue = username.getText().toString();
                String passwordvalue = password.getText().toString();
                String birthvalue = birth.getText().toString();
                String numbervalue = number.getText().toString();
                String countryvalue = country.getText().toString();
                String addressvalue = address.getText().toString();
                String emailvalue = email.getText().toString();
                RadioButton check = findViewById(gender.getCheckedRadioButtonId());
                String gendervalue = check.getText().toString();

                if (usernamevalue.length()>1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", usernamevalue);
                    editor.putString("password", passwordvalue);
                    editor.putString("birth", birthvalue);
                    editor.putString("number", numbervalue);
                    editor.putString("country", countryvalue);
                    editor.putString("address", addressvalue);
                    editor.putString("email", emailvalue);
                    editor.putString("gender", gendervalue);
                    editor.apply();
                    Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register.this, "Enter Value in the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                emptyField();
            }
        });

    }
//    public void emptyField(){
//        username.setText("");
//        password.setText("");
//        birth.setText("");
//        number.setText("");
//        country.setText("");
//        address.setText("");
//        email.setText("");
//    }
}