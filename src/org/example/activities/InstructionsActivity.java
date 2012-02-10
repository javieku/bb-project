package org.example.activities;

import org.example.R;
import org.example.tinyEngineClasses.TTS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class InstructionsActivity extends Activity{

	private TTS textToSpeech;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Intent i = getIntent();
		
		int type = i.getIntExtra(MainActivity.KEY_TYPE_INSTRUCTIONS, 0);
		
		String speech;
		
		if(type == 0){
			setContentView(R.layout.instructions_controls);
			speech = getString(R.string.instructions_controls_label) + " " + i.getStringExtra(MainActivity.KEY_INSTRUCTIONS_CONTROLS);
		}else{
			setContentView(R.layout.instructions_general);
			speech = getString(R.string.instructions_general_label) + " " + i.getStringExtra(MainActivity.KEY_INSTRUCTIONS_GENERAL);
		}
		// This initialize TTS engine
		textToSpeech = (TTS) getIntent().getParcelableExtra(MainActivity.KEY_TTS);
		textToSpeech.setContext(this);
		textToSpeech.setInitialSpeech(speech);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}
	
	/**
	 *  Turns off TTS engine
	 */
	@Override
	protected void onDestroy() {
		 super.onDestroy();
		 textToSpeech.stop();
	}
	
}
