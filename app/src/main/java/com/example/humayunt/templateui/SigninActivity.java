package com.example.humayunt.templateui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by HumayunT on 10/19/2017.
 */

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSignIn , btnSignUp;
    private EditText email;
    private  EditText password;
    private  TextView forget_pass;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestation 3D");
        setContentView(R.layout.signin);
        firebaseauth = FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser()!= null){
           startActivity(new Intent( getApplicationContext(),UserProfile.class));
        }
        email = (EditText) findViewById(R.id.email);
        forget_pass = (TextView) findViewById(R.id.forget_pass);
        password = (EditText)findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.sign_in);
        btnSignUp = (Button) findViewById(R.id.sign_up);

        progressdialog = new ProgressDialog(this);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        forget_pass.setOnClickListener(this);

    }
    private void userLogin(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            //email is empty
            Toast.makeText(this, "enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            //password is empty
            Toast.makeText(this, "enter password ", Toast.LENGTH_SHORT).show();
            return;
        }

        progressdialog.setMessage("Signing you in...");
        progressdialog.show();
        firebaseauth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activity
                            startActivity(new Intent( getApplicationContext(),UserProfile.class));
                        }
                    }
                });
    }
    public void onClick(View view) {
        if (view == btnSignIn){

            userLogin();

        }
        if(view == btnSignUp){
            finish();
            startActivity(new Intent( this, SignupActivity.class));
        }
        if(view == forget_pass){
            finish();
            startActivity(new Intent( this, forget_Password.class));
        }

    }
}