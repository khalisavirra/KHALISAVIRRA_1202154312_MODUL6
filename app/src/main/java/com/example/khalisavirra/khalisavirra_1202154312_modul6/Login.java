package com.example.khalisavirra.khalisavirra_1202154312_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    //Deklarasi variabel
    Button daftar,masuk;
    EditText edit_email_login, edit_pass_login;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mencari variabel berdasarkan id
        edit_email_login = (EditText)findViewById(R.id.edit_email_login);
        edit_pass_login = (EditText)findViewById(R.id.edit_pass_login);
        masuk = (Button)findViewById(R.id.btn_login);
        daftar = (Button)findViewById(R.id.btn_signup);

        //Context untuk progress dialog
        mProgressDialog = new ProgressDialog(this);

        //Firebase Authentication Instances UTHENTICATION INSTANCES
        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //Checking User Presence
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if( user != null )
                {
                    //Intent ke aktivitas selanjutnya
                    Intent moveToHome = new Intent(Login.this, PopotoanHome.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

        //Menambahkan listener untuk button id daftar ketika diklik
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,RegisterUser.class);
                startActivity(intent);
            }
        });

        //Menambahkan listener untuk button id masuk ketika diklik
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setTitle("Loging in the user");
                mProgressDialog.setMessage("Please wait....");
                mProgressDialog.show();
                loginUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void loginUser() {
        String userEmail, userPassword;

        userEmail = edit_email_login.getText().toString().trim();
        userPassword = edit_pass_login.getText().toString().trim();

        if( !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword))
        {
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if( task.isSuccessful())
                    {
                        mProgressDialog.dismiss();
                        Intent moveToHome = new Intent(Login.this, PopotoanHome.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);
                    }else
                    {
                        Toast.makeText(Login.this, "Unable to login user", Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }
                }
            });
        }else
        {
            Toast.makeText(Login.this, "Please enter user email and password", Toast.LENGTH_LONG).show();
            mProgressDialog.dismiss();

        }
    }

}
