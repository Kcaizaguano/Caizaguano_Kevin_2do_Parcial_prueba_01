package uta.fisei;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsActivityFragment_CCKD extends PreferenceFragment {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
