/*
 * Copyright (C) 2014-2016 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flare.zone.categories;

import android.content.Context;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import com.flare.zone.preferences.SystemSettingSeekBarPreference;
import com.flare.zone.preferences.SystemSettingListPreference;
import com.flare.zone.fragments.DeviceUtils;

public class StatusBar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = "StatusBar";

    private static final String STATUS_BAR_CLOCK_STYLE = "status_bar_clock";

    private SystemSettingListPreference mStatusBarClock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.status_bar);

        final Context mContext = getActivity().getApplicationContext();

        mStatusBarClock = findPreference(STATUS_BAR_CLOCK_STYLE);
        if (mStatusBarClock != null) {
            // Adjust status bar preferences for RTL
            if (getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                if (DeviceUtils.hasCenteredCutout(mContext)) {
                    mStatusBarClock.setEntries(R.array.status_bar_clock_position_entries_notch_rtl);
                    mStatusBarClock.setEntryValues(R.array.status_bar_clock_position_values_notch_rtl);
                } else {
                    mStatusBarClock.setEntries(R.array.status_bar_clock_position_entries_rtl);
                    mStatusBarClock.setEntryValues(R.array.status_bar_clock_position_values_rtl);
                }
            } else if (DeviceUtils.hasCenteredCutout(mContext)) {
                mStatusBarClock.setEntries(R.array.status_bar_clock_position_entries_notch);
                mStatusBarClock.setEntryValues(R.array.status_bar_clock_position_values_notch);
            }
        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.FLARE_ZONE;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // Handle preference changes here if needed
        return true;
    }
}
