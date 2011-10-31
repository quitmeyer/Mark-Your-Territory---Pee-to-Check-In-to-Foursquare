package com.andrewquitmeyer.MarkYourTerritory;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class MYTLaunch extends Activity {
	static final String TAG = "DemoKitLaunch";

	static Intent createIntent(Activity activity) {
		//This intent, when modified to check for device dimensions can be used to choose between a Tablet or Phone UI. 
//		We are just going to use a phone UI
		
		//Creating a "createIntent" function like this also allows us to make an activity to check if a USB device gets attached to the phone
//		see "USBaccessoryActivity"

			Log.i(TAG, "starting phone ui");
//			Intent intent = new Intent(activity, DemoKitPhone.class);
			Intent intent = new Intent(activity, BaseActivity.class);

		return intent;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = createIntent(this);

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Log.e(TAG, "unable to start DemoKit activity", e);
		}
		finish();
	}
}
