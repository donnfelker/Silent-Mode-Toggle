package com.dummies.android.silentmodetoggle;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
   
	private boolean mPhoneIsSilent;
	 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        
        setButtonClickListener(); 
    }
    
    @Override
    protected void onResume() {
    	super.onResume(); 
    	checkIfPhoneIsSilent(); 
    	toggleUi(); 
    };
    

	private void toggleUi() {
		
		ImageView imageView = (ImageView)findViewById(R.id.phone_icon);
		Drawable newPhoneImage; 
		
		if(mPhoneIsSilent)
		{
			newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);  
			 
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
		}
		
		imageView.setImageDrawable(newPhoneImage);
	}

	private void setButtonClickListener() {
		Button toggleButton = (Button)findViewById(R.id.toggleButton); 
		toggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE); 
				if(mPhoneIsSilent)
				{
					// Change back to normal mode
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL); 
					mPhoneIsSilent = false; 
				} else {
					// Change to silent mode
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					mPhoneIsSilent = true; 
				}
				
				// Now toggle the UI again 
				toggleUi(); 
			}
		}); 
		
	}

	private void checkIfPhoneIsSilent() {
		AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE); 
		int ringerMode = audioManager.getRingerMode(); 
		if(ringerMode == AudioManager.RINGER_MODE_SILENT)
		{
			mPhoneIsSilent = true; 
		} else {
			mPhoneIsSilent = false; 
		}
			
	}
}