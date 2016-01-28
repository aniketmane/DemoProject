package com.example.aniket.testdemo.adapter;

/**
 * CustomListAdapter.java .
 * @author  Aniket Mane
 * @version 1.0
 *
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.aniket.testdemo.app.AppController;
import com.example.aniket.testdemo.app.R;
import com.example.aniket.testdemo.models.SearchResultModel;
import java.util.*;


public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<SearchResultModel> searchItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	//constructor to set the data for adapter
	public CustomListAdapter(Activity activity, List<SearchResultModel> searchItems) {
		this.activity = activity;
		this.searchItems = searchItems;
	}

	@Override
	public int getCount() {
		return searchItems.size();
	}

	@Override
	public Object getItem(int location) {
		return searchItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.custom_list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
	

		// getting search data for the row
		SearchResultModel m = searchItems.get(position);

		thumbNail.setMaxHeight(m.getImageModel().getHeight());
		thumbNail.setMaxWidth(m.getImageModel().getWidth());

		// thumbnail image
		thumbNail.setImageUrl(m.getImageModel().getUrlSource(), imageLoader);

		// title
		title.setText(m.getTitle());


		return convertView;
	}

}