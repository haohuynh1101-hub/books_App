package com.example.books_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books_app.R;
import com.example.books_app.databinding.FragmentHomeBinding;
import com.example.books_app.databinding.FragmentLoginBinding;


public class login extends Fragment {

    FragmentLoginBinding binding;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        addControls();

        return binding.getRoot();
    }

    private void addControls() {
        binding.txtSignUp.setOnClickListener(myClick);
        binding.txtForgotPassword.setOnClickListener(myClick);
    }

    View.OnClickListener myClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager=getParentFragmentManager();
            FragmentTransaction fragmentTransaction=manager.beginTransaction();
            Fragment fragment=null;
            if(binding.txtSignUp.getId() == view.getId()){
                fragment=new sign_up();
            }else if(binding.txtForgotPassword.getId() == view.getId()){
                fragment=new forgot_password();
            }
            fragmentTransaction.replace(R.id.layoutLoginContainer,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    };
}