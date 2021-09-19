/**
 *  Created by abdullah.
 */

package io.supernova.spacebook1.adapter;

import android.view.LayoutInflater;
import android.widget.Button;
import io.supernova.spacebook1.R;
import io.supernova.spacebook1.activity.Warrenty;

import android.view.ViewGroup;
import java.util.*;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


public class FirstPageActivityWarrantiesSectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int WARRANTY1_VIEW_HOLDER_VIEW_TYPE = 1;
	public static final int WARRANTY2_VIEW_HOLDER_VIEW_TYPE = 2;
	
	public List<Warrenty> dataList;

	public FirstPageActivityWarrantiesSectionRecyclerViewAdapter(List<Warrenty> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	
//		switch (viewType) {
//			case WARRANTY1_VIEW_HOLDER_VIEW_TYPE:
//				return new Warranty1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.warranty1_view_holder, parent, false));
//			case WARRANTY2_VIEW_HOLDER_VIEW_TYPE:
//				return new Warranty2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.warranty2_view_holder, parent, false));
//		}

		return new Warranty1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.warranty1_view_holder, parent, false));
		
		//throw new RuntimeException("Unsupported view type");
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
	
		// Here you can bind RecyclerView item data.

		final Warrenty warrenty= dataList.get(position);
		final Warranty1ViewHolder mainHolder = (Warranty1ViewHolder)viewHolder;

		mainHolder.iphone_xtext_view.setText(warrenty.getProductName());
		mainHolder.date_text_view.setText("Warranty will expire at "+warrenty.getExpireDate());




	}
	
//	@Override
//	public int getItemViewType(int position) {
//
//		return MOCK_DATA.get(position);
//	}
	
	@Override
	public int getItemCount() {
	
		return dataList.size();
	}
	
	
	public static class Warranty1ViewHolder extends RecyclerView.ViewHolder {
		private Button moreDetailsButton;
		private TextView iphone_xtext_view,date_text_view;


		public Warranty1ViewHolder(View itemView) {
			super(itemView);
			init();
		}
		
		public void init() {
		
			// Configure more Details component
			moreDetailsButton = this.itemView.findViewById(R.id.more_details_button);
			iphone_xtext_view = this.itemView.findViewById(R.id.iphone_xtext_view);
			date_text_view = this.itemView.findViewById(R.id.date_text_view);
			moreDetailsButton.setOnClickListener((view) -> {
	this.onMoreDetailsPressed();
});
		}
		
		public void onMoreDetailsPressed() {
		
			this.startMoreDetailsActivity();
		}

		private void startMoreDetailsActivity() {
		}
	}
	
	
	public static class Warranty2ViewHolder extends RecyclerView.ViewHolder {
		public Warranty2ViewHolder(View itemView) {
			super(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
}
