package bqds.constructioncalculator;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    static boolean languageChanged=false;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(abanoubmagdi.home.thecompletecalculator.R.xml.preferences, rootKey);

        ListPreference listPreference=(ListPreference) findPreference("language");
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                languageChanged=true;

                return true;
            }
        });
    }
}
