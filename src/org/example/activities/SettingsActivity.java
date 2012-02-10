package org.example.activities;

import org.example.R;
import org.example.tinyEngineClasses.TTS;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity implements
		OnPreferenceClickListener {

	private CheckBoxPreference music, infoTarget, on_up_event,
			vibration_feedback, sound_feedback, profileA, profileB, doppler_effect_feedback;

	// Option names and default values
	private static final String OPT_MUSIC = "music";
	private static final boolean OPT_MUSIC_DEF = false;
	private static final String OPT_INFO_TARGET = "infoTarget";
	private static final boolean OPT_INFO_TARGET_DEF = false;
	private static final String OPT_UP = "On up event";
	private static final boolean OPT_UP_DEF = false;
	private static final String OPT_VIBRATION_FEEDBACK = "vibration feedback";
	private static final boolean OPT_VIBRATION_FEEDBACK_DEF = false;
	private static final String OPT_SOUND_FEEDBACK = "sound feedback";
	private static final boolean OPT_SOUND_FEEDBACK_DEF = false;
	private static final String OPT_SOUND_DOPPLER_EFFECT = "doppler effect";
	private static final boolean OPT_SOUND_DOPPLER_EFFECT_DEF = false;
	private static final String OPT_PROFILEA = "profile A";
	private static final String OPT_PROFILEB = "profile B";
	private TTS textToSpeech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		// Get the checkbox preference
		music = (CheckBoxPreference) findPreference(OPT_MUSIC);
		music.setOnPreferenceClickListener(this);

		profileA = (CheckBoxPreference) findPreference(OPT_PROFILEA);
		profileA.setOnPreferenceClickListener(this);
		profileB = (CheckBoxPreference) findPreference(OPT_PROFILEB);
		profileB.setOnPreferenceClickListener(this);
		
		infoTarget = (CheckBoxPreference) findPreference(OPT_INFO_TARGET);
		infoTarget.setOnPreferenceClickListener(this);

		on_up_event = (CheckBoxPreference) findPreference(OPT_UP);
		on_up_event.setOnPreferenceClickListener(this);

		vibration_feedback = (CheckBoxPreference) findPreference(OPT_VIBRATION_FEEDBACK);
		vibration_feedback.setOnPreferenceClickListener(this);

		sound_feedback = (CheckBoxPreference) findPreference(OPT_SOUND_FEEDBACK);
		sound_feedback.setOnPreferenceClickListener(this);
		
		doppler_effect_feedback = (CheckBoxPreference) findPreference(OPT_SOUND_DOPPLER_EFFECT);
		doppler_effect_feedback.setOnPreferenceClickListener(this);

		// Initialize TTS engine
		textToSpeech = (TTS) getIntent().getParcelableExtra(MainActivity.KEY_TTS);
		textToSpeech.setContext(this);
		textToSpeech.setInitialSpeech("Click any option");
	}

	/**
	 * Turns off TTS engine
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		textToSpeech.stop();
	}

	/** Get the current value of the music option */
	public static boolean getMusic(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_MUSIC, OPT_MUSIC_DEF);
	}

	/** Get the current value of the info target option */
	public static boolean getNotifyTarget(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_INFO_TARGET, OPT_INFO_TARGET_DEF);
	}

	/** Get the current value of the on up event option */
	public static boolean getOnUp(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_UP, OPT_UP_DEF);
	}

	/** Get the current value of the vibration feedback option */
	public static boolean getVibrationFeedback(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_VIBRATION_FEEDBACK, OPT_VIBRATION_FEEDBACK_DEF);
	}

	/** Get the current value of the sound feedback option */
	public static boolean getSoundFeedBack(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_SOUND_FEEDBACK, OPT_SOUND_FEEDBACK_DEF);
	}
	
	/** Get the current value of the sound feedback option */
	public static boolean getDopplerEffect(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_SOUND_DOPPLER_EFFECT, OPT_SOUND_DOPPLER_EFFECT_DEF);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (OPT_MUSIC.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_MUSIC).toString()
					+ music.isChecked());
		} else if (OPT_INFO_TARGET.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_INFO_TARGET).toString()
					+ infoTarget.isChecked());
			manageCustomProfile();
		} else if (OPT_UP.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_UP).toString()
					+ on_up_event.isChecked());
			manageCustomProfile();
		} else if (OPT_VIBRATION_FEEDBACK.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_VIBRATION_FEEDBACK)
					.toString() + vibration_feedback.isChecked());
			manageCustomProfile();
		} else if (OPT_SOUND_FEEDBACK.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_SOUND_FEEDBACK).toString()
					+ sound_feedback.isChecked());
			manageCustomProfile();
		} else if (OPT_SOUND_DOPPLER_EFFECT.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_SOUND_DOPPLER_EFFECT).toString()
					+ doppler_effect_feedback.isChecked());
			manageCustomProfile();
		} else if (OPT_PROFILEA.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_PROFILEA).toString()
					+ profileA.isChecked());
			manageProfileA();
		} else if (OPT_PROFILEB.equals(preference.getKey())) {
			textToSpeech.speak(findPreference(OPT_PROFILEB).toString()
					+ profileB.isChecked());
			manageProfileB();
		}
		return true;
	}

	private void manageProfileA() {
		 infoTarget.setChecked(profileA.isChecked()); 
		 sound_feedback.setChecked(profileA.isChecked());
		 doppler_effect_feedback.setChecked(profileA.isChecked());
		 
	     profileB.setChecked(false);;
		 on_up_event.setChecked(profileB.isChecked());
		 vibration_feedback.setChecked(profileB.isChecked());
	}
	
	private void manageProfileB() {
		 infoTarget.setChecked(profileB.isChecked());
		 on_up_event.setChecked(profileB.isChecked());
		 vibration_feedback.setChecked(profileB.isChecked());
		 doppler_effect_feedback.setChecked(profileB.isChecked());

		 profileA.setChecked(false);
		 sound_feedback.setChecked(profileA.isChecked());
	}
	
	private void manageCustomProfile() {
		profileA.setChecked(false);
		profileB.setChecked(false);
	}
}