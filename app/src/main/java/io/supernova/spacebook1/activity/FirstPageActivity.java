

package io.supernova.spacebook1.activity;

import android.util.Log;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Menu;
import android.support.v7.widget.LinearLayoutManager;
import io.supernova.uitoolkit.recycler.GridSpacingItemDecoration;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.support.v7.widget.GridLayoutManager;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import io.supernova.spacebook1.R;
import android.os.Bundle;
import io.supernova.spacebook1.adapter.FirstPageActivityWarrantiesSectionRecyclerViewAdapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirstPageActivity extends AppCompatActivity {
	private FirebaseAuth firebaseAuth;

	public static Intent newIntent(Context context) {

		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, FirstPageActivity.class);
	}

	private Toolbar toolbar;
	private RecyclerView warrantiesSectionRecyclerView;
	FirebaseDatabase secondaryDatabase;
	private List<Warrenty> warrantyList;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.first_page_activity);








		this.init();
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		firebaseAuth = FirebaseAuth.getInstance();
		if (firebaseAuth.getCurrentUser() == null) {
			finish();
			startActivity(new Intent(this, LoginActivity.class));
		}




		DatabaseReference mDatabaseRef =DBApplication.getSecondaryInstance(this).getReference("warranty_data");

		Query query=mDatabaseRef.orderByChild("userEmail").equalTo(AllConstatnts.getString(FirstPageActivity.this,AllConstatnts.USER_EMAIL));

		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				if(warrantyList==null)
					warrantyList=new ArrayList<>();

				warrantyList.clear();

				for (DataSnapshot data:dataSnapshot.getChildren()){


					Warrenty models=data.getValue(Warrenty.class);
					warrantyList.add(models);
					String productName=models.getProductName();
					String email=models.getUserEmail();

					Log.e(" DATA" , productName+" >> "+email );

				}

				warrantiesSectionRecyclerView.setLayoutManager(new GridLayoutManager(FirstPageActivity.this, 1, LinearLayoutManager.VERTICAL, false));
				warrantiesSectionRecyclerView.setAdapter(new FirstPageActivityWarrantiesSectionRecyclerViewAdapter(warrantyList));
				warrantiesSectionRecyclerView.addItemDecoration(new GridSpacingItemDecoration(FirstPageActivity.this.getResources().getDimension(R.dimen.first_page_activity_warranties_section_recycler_view_spacing)));

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.first_page_activity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		switch (menuItem.getItemId()) {
			case R.id.item_menu_item:
				FirebaseAuth.getInstance().signOut();
				finish();
				startActivity(new Intent(this, LoginActivity.class));
				break;
		}
		return true;

	}

	private void init() {


		// Configure warranties section component
		warrantiesSectionRecyclerView = this.findViewById(R.id.warranties_section_recycler_view);


	}

}
