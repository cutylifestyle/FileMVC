package com.sixin.filemvc.multifiles;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MultiFilesAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public MultiFilesAdapter(FragmentManager fm) {
        super(fm);
    }

    public MultiFilesAdapter(String[] titles, FragmentManager fm) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return MultiFilesFragment.newInstance(mTitles[i]);
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.length;
    }
}
