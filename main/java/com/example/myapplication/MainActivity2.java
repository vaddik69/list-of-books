package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class MainActivity2 extends AppCompatActivity {
    HashMap<String,String> users = new HashMap<>();
    public static String currentUser = "gulin1@gmail.com";
    public static Integer currentUserId = 1;
    int id = 1;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String[] logins = getResources().getStringArray(R.array.Emails);
        String[] passwords = getResources().getStringArray(R.array.Passwords);
        Button button = findViewById(R.id.button1);

        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "BooksBD").build();

        for (int i = 0; i < 5; i++) {
            Users newUser = new Users(logins[i], passwords[i]);
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Users user = db.getDaoUsers().getUser(newUser.login);
                    if (user == null) {
                        db.getDaoUsers().addUser(newUser);
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editTextText);
                boolean check = false;
                EditText editPass = findViewById(R.id.editTextTextPassword);
                String curUserName = editText.getText().toString();
                String passWord = editPass.getText().toString();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Users> userBD = db.getDaoUsers().getAllUsers();
                        for (Users currentUser : userBD) {
                            users.put(currentUser.getLogin(), currentUser.getPassword());
                            System.out.println(currentUser.getId() + " " + currentUser.getLogin()
                                                                   + " " + currentUser.getPassword());
                        }
                        id = db.getDaoUsers().getUserId(curUserName);
                    }
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                editPass.setTextColor(getResources().getColor(R.color.black));
                editText.setTextColor(getResources().getColor(R.color.black));

                if (users.containsKey(curUserName)) {
                    String RightPass = users.get(curUserName);

                    if (Objects.equals(RightPass, passWord)) {
                        check = true;

                        currentUser = curUserName;
                        currentUserId = id;
                        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        editPass.setTextColor(getResources().getColor(com.google.android.material.R.color.design_default_color_error));
                    }
                }
                if (!check) {
                    editText.setTextColor(getResources().getColor(com.google.android.material.R.color.design_default_color_error));
                }
            }
        });
    }
}