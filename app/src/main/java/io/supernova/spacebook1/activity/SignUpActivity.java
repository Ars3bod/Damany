/**
 *  Created by abdullah.
 */

package io.supernova.spacebook1.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import io.supernova.spacebook1.R;
import android.content.Intent;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.support.constraint.ConstraintLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


	public static Intent newIntent(Context context) {

		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, SignUpActivity.class);
	}

	private Button contnueButttonButton;
	private EditText nameEditText;
	private EditText passwordEditText;
	private ProgressDialog progressDialog;
	private FirebaseAuth firebaseAuth;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sign_up_activity);

		firebaseAuth = FirebaseAuth.getInstance();

		if (firebaseAuth.getCurrentUser() != null){
			finish();
			startActivity(new Intent(getApplicationContext(),FirstPageActivity.class));


		}

		progressDialog = new ProgressDialog(this);


		contnueButttonButton = (Button) findViewById(R.id.contnue_buttton_button);
		nameEditText = (EditText) findViewById(R.id.name_edit_text);
		passwordEditText = (EditText) findViewById(R.id.password_edit_text);
		contnueButttonButton.setOnClickListener(this);


		if (firebaseAuth.getCurrentUser() != null) {
			finish();
			startActivity(new Intent(getApplicationContext(), FirstPageActivity.class));
		}
	}


	// Configure SIGN UP component


	private void registerUser() {
		String email = nameEditText.getText().toString().trim();
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

		progressDialog.setMessage("Registering user");
		progressDialog.show();

		firebaseAuth.createUserWithEmailAndPassword(email,password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {

							AllConstatnts.setString(SignUpActivity.this,AllConstatnts.USER_EMAIL,email);


							Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
							finish();
							startActivity(new Intent(getApplicationContext(),FirstPageActivity.class));



						}else{

							Toast.makeText(SignUpActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();



						}
						progressDialog.dismiss();
					}
				});


	}

	@Override
	public void onClick(View v) {
		if (v == contnueButttonButton) {
			registerUser();

		}
	}
}