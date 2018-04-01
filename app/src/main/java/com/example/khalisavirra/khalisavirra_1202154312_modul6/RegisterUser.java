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

public class RegisterUser extends AppCompatActivity {
    //Deklarasi variabel
    Button daftar;
    EditText edit_email_register, edit_pass_register;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //Mencari variabel berdasarkan id
        edit_email_register = (EditText)findViewById(R.id.edit_email_register);
        edit_pass_register = (EditText)findViewById(R.id.edit_pass_register);
        daftar = (Button)findViewById(R.id.btn_signup_register);

        mProgressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user!=null){
                    Intent moveToHome = new Intent(RegisterUser.this,PopotoanHome.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(moveToHome);
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

        //Listener ketika button daftar di klik
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setTitle("Create Account");
                mProgressDialog.setMessage("Wait while account is being created...");
                mProgressDialog.show();

                createUserAccount();
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

    private void createUserAccount() {

        String emailUser, passUser;

        emailUser = edit_email_register.getText().toString().trim();
        passUser = edit_pass_register.getText().toString().trim();

        if(!TextUtils.isEmpty(emailUser) && !TextUtils.isEmpty(passUser)){
            mAuth.createUserWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(RegisterUser.this,"Account Created Success",Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();

                        Intent moveToHome = new Intent(RegisterUser.this,Login.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        startActivity(moveToHome);

                    }else{
                        Toast.makeText(RegisterUser.this,"Account Created Failed",Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }

                }
            });
        }
    }
}
