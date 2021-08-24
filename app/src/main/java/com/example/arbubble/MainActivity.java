package com.example.arbubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    String str;
    ImageButton speech,thought;
    String bubble="no_bubble";
    boolean selected = false;
    private static final int CAMERA_PERMISSION_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        speech = findViewById(R.id.imgbtn_speech);
        thought = findViewById(R.id.imgbtn_thought);
        editText = findViewById(R.id.editText);

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected){
                    bubble="no_bubble";
                    selected = false;
                }else{
                    speech.setBackgroundColor(Color.parseColor("#F38181"));
                    thought.setBackgroundColor(Color.TRANSPARENT);
                    bubble = "speech";
                    selected = true;
                }
            }
        });

        thought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected){
                    bubble="no_bubble";
                    selected = false;
                }else{
                    thought.setBackgroundColor(Color.parseColor("#F38181"));
                    speech.setBackgroundColor(Color.TRANSPARENT);
                    bubble = "thought";
                    selected = true;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ARActivity.class);
                str = String.valueOf(editText.getText());
                intent.putExtra("yazi",str);
                intent.putExtra("bubble", bubble);
                startActivity(intent);
            }
        });
        checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);
    }
    public void checkPermission(String permission,int requestCode){
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new String []{permission},requestCode);
        }

    }

}