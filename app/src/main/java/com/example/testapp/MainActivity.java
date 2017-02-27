package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.entity.DaoSession;
import com.example.testapp.entity.User;
import com.example.testapp.entity.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;
    private EditText username;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();

        textView = (TextView) findViewById(R.id.message);
        username = (EditText) findViewById(R.id.username);

    }

    public void addUser(View v){
        if(username.getText().toString().equals("")){
            textView.setText("Enter Username first");
        }else{
            User user = new User(null, username.getText().toString());
            userDao.insert(user);
            Log.d(TAG, "Inserted new User, ID: " + user.getId());
            textView.setText("Inserted new User, ID: " + user.getId());
            username.setText("");
        }
    }

    public void viewUser(View v){
        List<User> users = userDao.loadAll();
        String message = "";
        for (User user: users){
            message += user.getName() + '\n' ;
        }
        textView.setText(message);
    }
}
