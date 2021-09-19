/**
 *  Created by abdullah.
 */

package io.supernova.spacebook1.activity;

import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Menu;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import io.supernova.spacebook1.R;
import android.os.Bundle;


public class SendToActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, SendToActivity.class);
	}
	
	private Toolbar toolbar;
	private Button createButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.send_to_activity);
		this.init();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
	
		switch (menuItem.getItemId()) {
			case android.R.id.home: 
				this.onItemPressed();
				return true;
			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}
	
	private void init() {
	
		// Configure Navigation Bar #3 component
		toolbar = this.findViewById(R.id.toolbar);
		
		// Configure create component
		createButton = this.findViewById(R.id.create_button);
		createButton.setOnClickListener((view) -> {
	this.onCreatePressed();
});
		
		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onCreatePressed() {
	
		this.startFirstPageActivity();
	}
	
	public void onItemPressed() {
	
		this.finish();
	}
	
	private void startFirstPageActivity() {
	
		this.startActivity(FirstPageActivity.newIntent(this));
	}
}
