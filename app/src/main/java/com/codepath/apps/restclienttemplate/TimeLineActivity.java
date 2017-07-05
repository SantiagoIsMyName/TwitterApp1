package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TimeLineActivity extends AppCompatActivity implements TweetAdapter.TweetSelectedListener{


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == 1) {
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            //tweets.add(0, tweet);
            //tweetAdapter.notifyItemInserted(0);
            //rvTweets.scrollToPosition(0);
        }
    }
    public boolean onComposeAction(MenuItem mi) {
        switch(mi.getItemId()){
            case R.id.miCompose:
                launchActivity();
                return true;
            default:
                return super.onOptionsItemSelected(mi);
        }
    }

    private void launchActivity() {
        Intent intent = new Intent(TimeLineActivity.this, ComposeActivity.class);
        startActivityForResult(intent, 1);
    }


    public void onProfileView(MenuItem item) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("screen_name", "Santiago0spina");
        startActivity(i);
    }

    @Override
    public void onTweetSelected(Tweet tweet) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("screen_name", tweet.user.screenName);
        startActivity(i);
    }
}
