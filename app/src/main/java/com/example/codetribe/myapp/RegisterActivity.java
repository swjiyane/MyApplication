package com.example.codetribe.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerName;
    private Button Register;

    //firebase
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerName = (EditText) findViewById(R.id.register_name);
        Register = (Button) findViewById(R.id.btn_register);

        Register.setOnClickListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    public void RegisterUser(){

        String user = registerName.getText().toString().trim();
        String password = registerPassword.getEditableText().toString().trim();

        final String email = registerEmail.getText().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        registerEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (email.matches(emailPattern) && s.length() > 0) {
                    // Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        //validation
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "please, enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(), "please, enter your name", Toast.LENGTH_LONG).show();
            return;
        }

        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();

                    String user = registerName.getText().toString().trim();
                    String email = registerEmail.getText().toString().trim();
                    String password = registerPassword.getText().toString().trim();

                    boolean valid = true;
                    Intent intent = new Intent(RegisterActivity.this, Home.class);
                    intent.putExtra("valid", valid);
                    startActivity(intent);

                    String id = task.getResult().getUser().getUid();
                    Toast.makeText(getApplicationContext(), "registered successfully", Toast.LENGTH_SHORT).show();

                    mDatabaseReference = mDatabaseReference.getRef().child("User");
                    UserDetails userDetails = new UserDetails(user, email, password);
                    mDatabaseReference.child(id).setValue(userDetails);

                } else {
                    Toast.makeText(getApplicationContext(), "failed to register user, please register again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_register);
        RegisterUser();
    }
}
