package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by sospina on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{
    //Pass in the tweet array
    private List<Tweet> mTweets;
    Context context;
    private TweetsAdapterListener mListener;
    public interface TweetSelectedListener{
        public void onTweetSelected(Tweet tweet);

        void displayDetail(Tweet tweet);
    }

    public interface TweetsAdapterListener{
        public void onItemSelected(View view, int position);

        void onDetailView(View view, int position);
    }

    public TweetAdapter(List<Tweet> tweets, TweetsAdapterListener listener){
        mTweets = tweets;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Get data
        Tweet tweet = mTweets.get(position);

        //Populate
        holder.tvUsername.setText(tweet.user.name + "  @" + tweet.user.screenName);
        holder.tvBody.setText(tweet.body);
        holder.tvTime.setText(tweet.time);


        Glide.with(context)
                .load(tweet.user.profileImageURL)
                .bitmapTransform(new RoundedCornersTransformation(context, 150, 0))
                .into(holder.ivProfileImage);

    }
    //For each row, inflate the layout and cache references into the ViewHolder class


    //Bind the values

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvTime;


        public ViewHolder(View itemView) {
            super(itemView);

            //Perform findViewbyID
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);


            ivProfileImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (mListener != null){
                        int position = getAdapterPosition();
                        mListener.onItemSelected(view, position);
                    }

                }
            });

            tvBody.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (mListener != null){
                        int position = getAdapterPosition();
                        mListener.onDetailView(view, position);
                    }
                }
            });

        }
    }
}
