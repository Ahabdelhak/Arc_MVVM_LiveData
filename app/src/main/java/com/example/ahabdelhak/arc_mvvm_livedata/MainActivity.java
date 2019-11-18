package com.example.ahabdelhak.arc_mvvm_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahabdelhak.arc_mvvm_livedata.databinding.ActivityMainBinding;

public class MainActivity  extends AppCompatActivity {


    private ActivityMainBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        initUI();
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginResult();
            }
        });


        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.loginStatus.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String str) {
                binding.progressLogin.setVisibility(View.INVISIBLE);
                System.out.println("onChanged: "+str);
                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void loginResult(){

        binding.progressLogin.setVisibility(View.VISIBLE);
        String userName = binding.etName.getText().toString();
        String password = binding.etPassword.getText().toString();

        loginViewModel.doLogin(userName, password);
    }



    private void initUI(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.progressLogin.setVisibility(View.INVISIBLE);
    }
}