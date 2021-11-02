package com.example.books_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.books_app.R;
import com.example.books_app.databinding.ActivityLoginBinding;
import com.example.books_app.fragments.forgot_password;
import com.example.books_app.fragments.login;
import com.example.books_app.fragments.sign_up;

public class Login extends AppCompatActivity {
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.layoutLoginContainer,new login());
        transaction.commit();
    }
}