package com.andrewquitmeyer.MarkYourTerritory;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.andrewquitmeyer.MarkYourTerritory.R;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class InputController extends AccessoryController {
	private TextView mMoisture;
	ProgressBar mProgressbar;

	ArrayList<SwitchDisplayer> mSwitchDisplayers;
	private final DecimalFormat mLightValueFormatter = new DecimalFormat("##.#");
	private final DecimalFormat mMoistureFormatter = new DecimalFormat(
			"###.#");

	
	
	
	InputController(DemoKitActivity hostActivity) {
		super(hostActivity);
		mMoisture = (TextView) findViewById(R.id.moistValue);
		mProgressbar= (ProgressBar) findViewById(R.id.progressBar1);
	}

	protected void onAccesssoryAttached() {
		mSwitchDisplayers = new ArrayList<SwitchDisplayer>();
		for (int i = 0; i < 4; ++i) {
			SwitchDisplayer sd = new SwitchDisplayer(i);
			mSwitchDisplayers.add(sd);
		}
	}

	public void setMoisture(int moistureFromArduino) { // Set Text
		
		mMoisture.setText(mMoistureFormatter.format(moistureFromArduino/10.0)+"%");
		mProgressbar.setProgress(moistureFromArduino/10);
	}


/**
	public void onTemperature(int temperature) {
		setMoisture(temperature);
	}
**/
	
/**/
	class SwitchDisplayer {
		private final ImageView mTargetView;
		private final Drawable mOnImage;
		private final Drawable mOffImage;

		SwitchDisplayer(int switchIndex) {
			int viewId, onImageId, offImageId;
			switch (switchIndex) {
			default:
				viewId = R.id.Button1;
				onImageId = R.drawable.indicator_button1_on_noglow;
				offImageId = R.drawable.indicator_button1_off_noglow;
				break;
			case 1:
				viewId = R.id.Button2;
				onImageId = R.drawable.indicator_button2_on_noglow;
				offImageId = R.drawable.indicator_button2_off_noglow;
				break;
			case 2:
				viewId = R.id.Button3;
				onImageId = R.drawable.indicator_button3_on_noglow;
				offImageId = R.drawable.indicator_button3_off_noglow;
				break;
			case 3:
				viewId = R.id.Button4;
				onImageId = R.drawable.indicator_button_capacitive_on_noglow;
				offImageId = R.drawable.indicator_button_capacitive_off_noglow;
				break;
			}
			mTargetView = (ImageView) findViewById(viewId);
			mOffImage = mHostActivity.getResources().getDrawable(offImageId);
			mOnImage = mHostActivity.getResources().getDrawable(onImageId);
		}

		public void onSwitchStateChange(Boolean switchState) {
			Drawable currentImage;
			if (!switchState) {
				currentImage = mOffImage;
			} else {
				currentImage = mOnImage;
			}
			mTargetView.setImageDrawable(currentImage);
		}

	}/**/
}
