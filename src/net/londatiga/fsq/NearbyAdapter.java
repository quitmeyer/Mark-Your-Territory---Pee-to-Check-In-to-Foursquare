package net.londatiga.fsq;

import android.content.Context;
import android.graphics.Color;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.andrewquitmeyer.MarkYourTerritory.R;

public class NearbyAdapter extends BaseAdapter {
	private ArrayList<FsqVenue> mVenueList;
	private LayoutInflater mInflater;
int selectedPos=-1;
static int oldSelection=-2;
	public NearbyAdapter(Context c) {
        mInflater 			= LayoutInflater.from(c);
    }

	public void setData(ArrayList<FsqVenue> poolList) {
		mVenueList = poolList;
	}
	
	public int getCount() {
		return mVenueList.size();
	}

	public Object getItem(int position) {
		if(oldSelection!=selectedPos){
//			Log.e("MYT", "AAAAHHH current sel="+selectedPos+"  old sel="+oldSelection+"  pos="+position);
	
		}
	
		selectedPos=position;
        notifyDataSetChanged();
		return mVenueList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView	=  mInflater.inflate(R.layout.nearby_list, null);
			convertView.setBackgroundColor(R.color.lightgrey);

			holder = new ViewHolder();
			
			holder.mNameTxt 		= (TextView) convertView.findViewById(R.id.tv_name);
			holder.mAddressTxt 		= (TextView) convertView.findViewById(R.id.tv_address);
			holder.mHereNowTxt 		= (TextView) convertView.findViewById(R.id.tv_here_now);
			holder.mDistanceTxt 	= (TextView) convertView.findViewById(R.id.tv_distance);
			holder.mRibbonImg		= (ImageView) convertView.findViewById(R.id.iv_ribbon);
			holder.mLayout			= (View) convertView.findViewById(R.id.iv_layout);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//Handle the Highlighting
		if(oldSelection==position){
			//Toggle Off
			holder.mNameTxt.setBackgroundColor(Color.TRANSPARENT);
        	holder.mNameTxt.setTextColor(Color.GRAY);
        	holder.mAddressTxt.setBackgroundColor(Color.TRANSPARENT);
        	holder.mAddressTxt.setTextColor(Color.WHITE);		

			holder.mHereNowTxt.setBackgroundColor(Color.TRANSPARENT);
			holder.mHereNowTxt.setTextColor(Color.WHITE); 
			holder.mLayout.setBackgroundColor(R.color.lightgrey);
			oldSelection=-500;
		}
		else{
			//See about current selection
			if(selectedPos == position){
				//Toggle On visuals
	        	holder.mNameTxt.setBackgroundColor(Color.YELLOW);
	        	holder.mNameTxt.setTextColor(Color.BLACK);
	        	
	        	holder.mAddressTxt.setBackgroundColor(Color.YELLOW);
	        	holder.mAddressTxt.setTextColor(Color.BLACK);		

				holder.mHereNowTxt.setBackgroundColor(Color.YELLOW);
				holder.mHereNowTxt.setTextColor(Color.BLACK); 		

				
				holder.mLayout.setBackgroundColor(Color.YELLOW);
//				Log.e("MYT", "current sel="+selectedPos+"  old sel="+oldSelection+"  pos="+position);
				oldSelection=selectedPos;


	        }
			else{
				//Toggle off visuals
	        	holder.mNameTxt.setBackgroundColor(Color.TRANSPARENT);
	        	holder.mNameTxt.setTextColor(Color.GRAY);
	        	holder.mAddressTxt.setBackgroundColor(Color.TRANSPARENT);
	        	holder.mAddressTxt.setTextColor(Color.WHITE);		

				holder.mHereNowTxt.setBackgroundColor(Color.TRANSPARENT);
				holder.mHereNowTxt.setTextColor(Color.WHITE); 
				holder.mLayout.setBackgroundColor(R.color.lightgrey);
	        }
		}
		
		

		FsqVenue venue 	= mVenueList.get(position);
	
		holder.mNameTxt.setText(venue.name);
		holder.mAddressTxt.setText(venue.address);
		holder.mHereNowTxt.setText("(" + String.valueOf(venue.herenow) + " people here)");
		holder.mDistanceTxt.setText(formatDistance(venue.distance));

		holder.mRibbonImg.setVisibility((venue.type.equals("trending")) ? View.VISIBLE : View.INVISIBLE);

        return convertView;
	}
	
	private String formatDistance(double distance) {
		String result = "";
		
		DecimalFormat dF = new DecimalFormat("00");
		
		dF.applyPattern("0.#");
		
		if (distance < 1000)
			result = dF.format(distance) + " m";
		else {
			distance = distance / 1000.0;
			result   = dF.format(distance) + " km";
		}
		
		return result;
	}
	static class ViewHolder {
		TextView mNameTxt;
		TextView mAddressTxt;
		TextView mHereNowTxt;
		TextView mDistanceTxt;
		ImageView mRibbonImg;
		View mLayout;
	}
	public void unselectAll() {
		// TODO Auto-generated method stub
ViewHolder holder;
		
		View convertView = null;
		
			convertView	=  mInflater.inflate(R.layout.nearby_list, null);
			convertView.setBackgroundColor(R.color.lightgrey);

			holder = new ViewHolder();
			
			holder.mNameTxt 		= (TextView) convertView.findViewById(R.id.tv_name);
			holder.mAddressTxt 		= (TextView) convertView.findViewById(R.id.tv_address);
			holder.mHereNowTxt 		= (TextView) convertView.findViewById(R.id.tv_here_now);
			holder.mDistanceTxt 	= (TextView) convertView.findViewById(R.id.tv_distance);
			holder.mRibbonImg		= (ImageView) convertView.findViewById(R.id.iv_ribbon);
			holder.mLayout			= (View) convertView.findViewById(R.id.iv_layout);
			
			convertView.setTag(holder);
		 
		holder.mNameTxt.setBackgroundColor(Color.TRANSPARENT);
    	holder.mNameTxt.setTextColor(Color.GRAY);
    	holder.mAddressTxt.setBackgroundColor(Color.TRANSPARENT);
    	holder.mAddressTxt.setTextColor(Color.WHITE);		

		holder.mHereNowTxt.setBackgroundColor(Color.TRANSPARENT);
		holder.mHereNowTxt.setTextColor(Color.WHITE); 
		holder.mLayout.setBackgroundColor(R.color.lightgrey);
        notifyDataSetChanged();

	}

}