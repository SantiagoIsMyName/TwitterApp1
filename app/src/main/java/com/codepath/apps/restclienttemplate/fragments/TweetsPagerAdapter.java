package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by sospina on 7/3/17.
 */

public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter{
    private String tabTitle[] = new String[] {"Home", "Mentions"};
    private Context context;

    public TweetsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HomeTimelineFragment();
        }
        else if(position == 1){
            return new MentionsTimelineFragment();
        }
        else{
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
