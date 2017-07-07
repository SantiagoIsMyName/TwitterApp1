package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpClient;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailView extends AppCompatActivity {
    AsyncHttpClient client;
    Tweet tweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_view);

        client = new AsyncHttpClient();


        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        TextView tvBody = (TextView) findViewById(R.id.tvBody);
        TextView tvTime = (TextView) findViewById(R.id.tvTime);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

        tvUsername.setText(tweet.user.name + "@" + tweet.user.screenName);
        tvBody.setText(tweet.body);
        tvTime.setText(tweet.time);

        Glide.with(getApplicationContext())
                .load(tweet.user.profileImageURL)
                .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), 150, 0))
                .into(ivProfileImage);

    }

}
