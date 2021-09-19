/**
 *  Created by abdullah.
 */

package io.supernova.spacebook1.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.widget.EditText;
import io.supernova.spacebook1.R;
import android.text.SpannableString;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static Intent newIntent(Context context) {

        // Fill the created intent with the data you want to be passed to this Activity when it's opened.
        return new Intent(context, LoginActivity.class);
    }

    private Button contnueButttonButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button donThaveAnAccounButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login_activity);
        this.init();
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),FirstPageActivity.class));


        }
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        contnueButttonButton = (Button) findViewById(R.id.contnue_buttton_button);
        progressDialog = new ProgressDialog(this);

        contnueButttonButton.setOnClickListener(this);
    }

    private void init() {


        // Configure Don’t Have An Accoun component
        donThaveAnAccounButton = this.findViewById(R.id.don_thave_an_accoun_button);
        donThaveAnAccounButton.setOnClickListener((view) -> {
            this.onDonTHaveAnAccounPressed();
        });
    }



    public void onDonTHaveAnAccounPressed() {

        this.startSignUpActivity();
    }


    private void startSignUpActivity() {

        this.startActivity(SignUpActivity.newIntent(this));
    }
    private void userLogin(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      progressDialog.dismiss();
                      if (task.isSuccessful()){

                          AllConstatnts.setString(LoginActivity.this,AllConstatnts.USER_EMAIL,email);

                          startActivity(new Intent(getApplicationContext(), FirstPageActivity.class));
                      }
                      else{

                          Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();



                      }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        if (v == contnueButttonButton){
            userLogin();
        }


    }
}
