package com.example.codetribe.myapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mCamera;
    private static final int GALLERY_INTENT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCamera = (FloatingActionButton) findViewById(R.id.image_camera);
        mCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view ==mCamera){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,GALLERY_INTENT);
        }

    }
}
