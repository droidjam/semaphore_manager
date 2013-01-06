/*  Semaphore Manager
 *  
 *   Copyright (c) 2012 Stratos Karafotis (stratosk@semaphore.gr)
 *   
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package com.semaphore.sm;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;

public class TabInfoFragment extends PreferenceListFragment {

    public TabInfoFragment() {
        super(R.xml.preferences_info);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Commander cm = Commander.getInstance();
        cm.readFile("/proc/version");
        Preference pref = findPreference("kernel_version");
        pref.setSummary(cm.getOutResult().get(0));
        
        MemoryInfo mi = new MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
//        long availableMegs = mi.availMem / 1048576L;
        long totalMegs = mi.totalMem / 1048576L;

        pref = findPreference("system_memory");
        pref.setSummary(String.valueOf(totalMegs) + " MB");
        
    }
}