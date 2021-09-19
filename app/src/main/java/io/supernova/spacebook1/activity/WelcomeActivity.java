/**
 *  Created by abdullah.
 */

package io.supernova.spacebook1.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.content.Context;
import io.supernova.spacebook1.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {
	private static int SPLASH_TIME = 3000; //This is 3 seconds
	public static Intent newIntent(Context context) {

		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, WelcomeActivity.class);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.welcome_activity);
		this.init();
		final boolean b = new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//Do any action here. Now we are moving to next page
				Intent homeintent = new Intent(WelcomeActivity.this, LoginActivity.class);
				startActivity(homeintent);
				/* This 'finish()' is for exiting the app when back button pressed
				 *  from Home page which is ActivityHome
				 */
				finish();
			}
		}, SPLASH_TIME);
	}

	private void init() {

	}
}
