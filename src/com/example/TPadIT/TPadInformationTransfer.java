/*
 * Copyright 2014 TPad Tablet Project. All rights reserved.
 *
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ARSHAN POURSOHI OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied.
 */

package com.example.TPadIT;

import java.util.Arrays;
import java.util.Collections;

import com.example.TPadIT.R;

import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.consts.TPadVibration;
import nxr.tpad.lib.views.FrictionMapView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TPadInformationTransfer extends Activity {

	// Define 'View' classes that will link to the .xml file
	View basicView;
	View timeView;
	
	// Define 'FrictionMapView' class that will link to the .xml file
	FrictionMapView fricView;

	// Define buttons
	Button num1Button;
	Button num2Button;
	Button num3Button;
	Button num4Button;
	Button num5Button;

	int timeArray[] = {60,30,20,10,5,1};
	int iconArray[] = {1,2,3,4,5,6,7,8,9,10};
	int timelength = timeArray.length;
	int iconlength = iconArray.length;
	
	int[][] data = new int[timelength][iconlength];
	int[][] reficon = new int[timelength][iconlength];
	int i;
	int j;
	int state = 1;
	int choice = 0; 

//	public static final String[] optionsNames = new String[] {"bumpsbig","bumpssmallsobel","bumpssmall", "bumpssmallvert","bumpssmall2", "defaultmap", "crescendo", "decrescendo", "dipdipdiiiip", "forward100px50px", "forwardback100px25px25px", "reflectedchirp125pxto5px",
//		"reflectedlinear5px", "reflectedlinear10px", "reflectedlinear10px_lightened", "reflectedlinear10px85dark", "reflectedlinear10px170dark", "reflectedlinear20px", "reflectedlinear30px",
//		"reflectedlinear60px", "repeatedlinear5px", "repeatedlinear10px", "repeatedlinear30px", "repeatedlinear60px", "speedbump5px", "whitenoise50", "whitenoise200", "whitenoise400",
//		"widebumps2", "widedimples2" };
//	public Bitmap[] hapMaps;
	
	// Instantiate a new TPad object
	TPad mTpad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tpadit);

		// Get TPad reference from the TPad Implementation Library
		mTpad = new TPadImpl(this);
		// Creating Buttons
		num1Button = (Button) findViewById(R.id.Button01);
		num2Button = (Button) findViewById(R.id.Button02);
		num3Button = (Button) findViewById(R.id.Button03);
		num4Button = (Button) findViewById(R.id.Button04);
		num5Button = (Button) findViewById(R.id.Button05);
		// Creating bitmaps
//		hapMaps = new Bitmap[optionsNames.length];
		// Link FrictionMapView to the .xml file
		fricView = (FrictionMapView) findViewById(R.id.view3);
		
		// Set the TPad of the FrictionMapView to the current TPad
		fricView.setTpad(mTpad);
		
		// Load an image from resources
		final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.crescendo);
		final Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.decrescendo);
		final Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.reflectedlinear20px);
		final Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.repeatedlinear10px);
		final Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.spacialgrad);

		// Set the friction data bitmap to the test image
		fricView.setDataBitmap(bitmap1);
		num1Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fricView.setDataBitmap(bitmap1);
				choice = 1;
			}
		});
		num2Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fricView.setDataBitmap(bitmap2);
				choice = 2;				
			}
		});
		num3Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fricView.setDataBitmap(bitmap3);
				choice = 3;				
			}
		});			
		num4Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fricView.setDataBitmap(bitmap4);
				choice = 4;				
			}
		});
		num5Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fricView.setDataBitmap(bitmap5);
				choice = 5;				
			}
		});
		
		RunExperiment();
	}

	@Override
	protected void onDestroy() {
		mTpad.disconnectTPad();
		super.onDestroy();
	}

	void RunExperiment(){
		final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.crescendo);
		final Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.decrescendo);
		final Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.reflectedlinear20px);
		final Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.repeatedlinear10px);
		final Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.spacialgrad);
		Bitmap[] bitArr; // Don't know how to create an array of these bitmaps
		fricView.setDisplayBitmap(bitmap1); //This is what is showing in the place of the icon
		
			while(i<timelength){
				Collections.shuffle(Arrays.asList(iconArray));
				while (j<iconlength){
					switch(state){
					case 1:
						fricView.setDataBitmap(bitmap1);  // display corresponding icon
						// ^ This should choose the bitmap from the array
						fricView.setDisplayShowing(true); 
						fricView.setDataDisplayed(true);
						Thread.sleep(1000*timeArray[i]); //Pause for desired length of time
						state = 2;
					case 2:
						//Activate buttons
						fricView.setDisplayShowing(false);
						fricView.setDataDisplayed(false);
						// Stop showing icon and display message "Choose icon"
						Thread.sleep(2000);
						state = 3; 
					case 3:
						//Deactivate buttons
						//Get user input
						reficon[i][j] = iconArray[j];
						data[i][j] = choice;//HOWEVER TO REGISTER ICON NUMBER
						choice = 0;
						//Pause before next symbol
						Thread.sleep(1000);
						state = 1;
					}
			}
		}
	}
}
