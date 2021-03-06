# Project 4 - Twitter

Twitter is an android app that allows a user to view home and mentions timelines, view user profiles with user timelines, as well as compose and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: 30 hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **sign in to Twitter** using OAuth login process
* [X] User can **view the tweets from their home timeline**
  * [X] RecyclerView is used to display listings of any tweets
  * [X] User is displayed the username, name, and body for each tweet
  * [X] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [X] User can **compose and post a new tweet**
  * [X] User can click a "Compose" icon in the App Bar on the top right
  * [X] User can then enter a new tweet from a second activity and then post this to twitter
  * [X] User is taken back to home timeline with new tweet visible in timeline
  * [X] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh

The following **optional** features are implemented:

* [X] While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
* [ ] User can **pull down to refresh tweets** in either timeline.
* [X] Improve the user interface and theme the app to feel twitter branded with colors and styles
* [ ] User can **search for tweets matching a particular query** and see results.
* [ ] When a network request is sent, user sees an [indeterminate progress indicator](http://guides.codepath.com/android/Handling-ProgressBars#progress-within-actionbar)
* [ ] User can **"reply" to any tweet on their home timeline**
  * [ ] The user that wrote the original tweet is automatically "@" replied in compose
* [X] User can click on a tweet to be **taken to a "detail view"** of that tweet
 * [X] User can take favorite (and unfavorite) or retweet actions on a tweet
* [ ] User can see embedded image media within the tweet item in list or detail view.
* [ ] Compose activity is replaced with a modal compose overlay.
* [X] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [X] Used Parcelable instead of Serializable leveraging the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler) when passing data between activities.
* [X] Replaced all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [ ] User can view following / followers list through the profile of a user
* [ ] Apply the popular Butterknife annotation library to reduce view boilerplate.
* [ ] Implement collapse scrolling effects on the Twitter profile view using `CoordinatorLayout`.
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in an offline mode.

The following **additional** features are implemented:

* [ ] User can view more tweets as they scroll with [infinite pagination](http://guides.codepath.com/android/Endless-Scrolling-with-AdapterViews-and-RecyclerView). Number of tweets is unlimited.

## Video Walkthrough

Here's a walkthrough of implemented user stories: https://i.makeagif.com/media/7-07-2017/_Ntnju.gif

<img src='https://i.makeagif.com/media/7-07-2017/_Ntnju.gif'  title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

Learning how to pass information between the fragments and the activities was a challenge. Populating the view in the Detail View was also difficult until I used Parcel.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2017 Santiago Ospina

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


# RestClientTemplate [![Build Status](https://travis-ci.org/codepath/android-rest-client-template.svg?branch=master)](https://travis-ci.org/codepath/android-rest-client-template)

## Overview

RestClientTemplate is a skeleton Android project that makes writing Android apps sourced from OAuth JSON REST APIs as easy as possible. This skeleton project
combines the best libraries and structure to enable quick development of rich API clients. The following things are supported out of the box:

 * Authenticating with any OAuth 1.0a or OAuth 2 API
 * Sending requests for and parsing JSON API data using a defined client
 * Persisting data to a local SQLite store through an ORM layer
 * Displaying and caching remote image data into views

The following libraries are used to make this possible:

 * [scribe-java](https://github.com/fernandezpablo85/scribe-java) - Simple OAuth library for handling the authentication flow.
 * [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
 * [codepath-oauth](https://github.com/thecodepath/android-oauth-handler) - Custom-built library for managing OAuth authentication and signing of requests
 * [Glide](https://github.com/bumptech/glide) - Used for async image loading and caching them in memory and on disk.
 * [DBFlow](https://github.com/Raizlabs/DBFlow) - Simple ORM for persisting a local SQLite database on the Android device

## Usage

### 1. Configure the REST client

Open `src/com.codepath.apps.restclienttemplate/RestClient.java`. Configure the `REST_API_INSTANCE`, `REST_URL`, `REST_CONSUMER_KEY`, `REST_CONSUMER_SECRET` based on the values needed to connect to your particular API. The `REST_URL` should be the base URL used for connecting to the API (i.e `https://api.twitter.com`). The `REST_API_INSTANCE` should be the class defining the service you wish to connect to. Check out the [full list of services](https://github.com/scribejava/scribejava/tree/master/scribejava-apis/src/main/java/com/github/scribejava/apis) you can select (i.e `FlickrApi.instance()`).

For example if I wanted to connect to Twitter:

```java
// RestClient.java
public class RestClient extends OAuthBaseClient {
    public static final BaseApi REST_API_INSTANCE = TwitterApi.instance();
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "57fdgdfh345195e071f9a761d763ca0";
    public static final String REST_CONSUMER_SECRET = "d657sdsg34435435";
    // ...constructor and endpoints
}
```

Next, change the `intent_scheme` and `intent_host` in `strings.xml` to a unique name that is special for this application.
This is used for the OAuth authentication flow for launching the app through web pages through an [Android intent](https://developer.chrome.com/multidevice/android/intents).

```xml
<string name="intent_scheme">oauth</string>
<string name="intent_host">codepathtweets</string>
```

Next, you want to define the endpoints which you want to retrieve data from or send data to within your client:

```java
// RestClient.java
public void getHomeTimeline(int page, AsyncHttpResponseHandler handler) {
  String apiUrl = getApiUrl("statuses/home_timeline.json");
  RequestParams params = new RequestParams();
  params.put("page", String.valueOf(page));
  getClient().get(apiUrl, params, handler);
}
```

Note we are using `getApiUrl` to get the full URL from the relative fragment and `RequestParams` to control the request parameters.
You can easily send post requests (or put or delete) using a similar approach:

```java
// RestClient.java
public void postTweet(String body, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("statuses/update.json");
    RequestParams params = new RequestParams();
    params.put("status", body);
    getClient().post(apiUrl, params, handler);
}
```

These endpoint methods will automatically execute asynchronous requests signed with the authenticated access token. To use JSON endpoints, simply invoke the method
with a `JsonHttpResponseHandler` handler:

```java
// SomeActivity.java
RestClient client = RestApplication.getRestClient();
client.getHomeTimeline(1, new JsonHttpResponseHandler() {
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
    // Response is automatically parsed into a JSONArray
    // json.getJSONObject(0).getLong("id");
  }
});
```

Based on the JSON response (array or object), you need to declare the expected type inside the OnSuccess signature i.e
`public void onSuccess(JSONObject json)`. If the endpoint does not return JSON, then you can use the `AsyncHttpResponseHandler`:

```java
RestClient client = RestApplication.getRestClient();
client.getSomething(new AsyncHttpResponseHandler() {
    @Override
    public void onSuccess(int statusCode, Header[] headers, String response) {
        System.out.println(response);
    }
});
```
Check out [Android Async HTTP Docs](http://loopj.com/android-async-http/) for more request creation details.

### 2. Define the Models

In the `src/com.codepath.apps.restclienttemplate.models`, create the models that represent the key data to be parsed and persisted within your application.
For example, if you were connecting to Twitter, you would want a Tweet model as follows:

```java
// models/Tweet.java
package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;


@Table(database = MyDatabase.class)
public class Tweet extends BaseModel {
  // Define database columns and associated fields
  @PrimaryKey @Column
  Long id;
  @Column
  String userId;
  @Column
  String userHandle;
  @Column
  String timestamp;
  @Column
  String body;
}
```

Notice here we specify the SQLite table for a resource, the columns for that table, and a constructor for
turning the JSON object fetched from the API into this object. For more information on creating a model,
check out the [DBFlow Wiki](https://github.com/Raizlabs/DBFlow/blob/master/usage2/Intro.md).

In addition, we can also add functions into the model to support parsing JSON attributes in order to instantiate the model based on API data. This might look like:

```java
// models/Tweet.java
@Table(database = MyDatabase.class)
public class Tweet extends BaseModel {
  // ...existing code from above...

  // Add a constructor that creates an object from the JSON response
  public Tweet(JSONObject object){
    super();

    try {
      this.userId = object.getString("user_id");
      this.userHandle = object.getString("user_username");
      this.timestamp = object.getString("timestamp");
      this.body = object.getString("body");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
    ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

    for (int i=0; i < jsonArray.length(); i++) {
        JSONObject tweetJson = null;
        try {
            tweetJson = jsonArray.getJSONObject(i);
        } catch (Exception e) {
            e.printStackTrace();
            continue;
        }

        Tweet tweet = new Tweet(tweetJson);
        tweet.save();
        tweets.add(tweet);
    }

    return tweets;
  }
}
```

Now you have a model that supports proper creation based on JSON. Create models for all the resources
necessary for your mobile client.

### 3. Setup Your Authenticated Activities

Open `src/com.codepath.apps.restclienttemplate/LoginActivity.java` and configure the `onLoginSuccess` method
which fires once your app has access to the authenticated API. Launch an activity and begin using your REST client:

```java
// LoginActivity.java
@Override
public void onLoginSuccess() {
  Intent i = new Intent(this, TimelineActivity.class);
  startActivity(i);
}
```

In your new authenticated activity, you can access your client anywhere with:

```java
RestClient client = RestApplication.getRestClient();
client.getHomeTimeline(1, new JsonHttpResponseHandler() {
  public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
    Log.d("DEBUG", "timeline: " + jsonArray.toString());
    // Load json array into model classes
  }
});
```

You can then load the data into your models from a `JSONArray` using:

```java
ArrayList<Tweet> tweets = Tweet.fromJSON(jsonArray);
```

or load the data from a single `JSONObject` with:

```java
Tweet t = new Tweet(json);
// t.body = "foo"
t.save();
```

That's all you need to get started. From here, hook up your activities and their behavior, adjust your models and add more REST endpoints.

### Extras

#### Loading Images with Glide

If you want to load a remote image url into a particular ImageView, you can use Glide to do that with:

```java
Glide.with(this).load(imageUrl)
     .into(imageView);
```

This will load an image into the specified ImageView and resize the image to fit.

#### Logging Out

You can log out by clearing the access token at any time through the client object:

```java
RestClient client = RestApplication.getRestClient();
client.clearAccessToken();
```

### Troubleshooting

* If you receive the following error `org.scribe.exceptions.OAuthException: Cannot send unauthenticated requests for TwitterApi client. Please attach an access token!` then check the following:
 * Is your intent-filter with `<data>` attached to the `LoginActivity`? If not, make sure that the `LoginActivity` receives the request after OAuth authorization.
 * Is the `onLoginSuccess` method being executed in the `LoginActivity`. On launch of your app, be sure to start the app on the LoginActivity so authentication routines execute on launch and take you to the authenticated activity.
 * If you are plan to test with Android API 24 or above, you will need to use Chrome to launch the OAuth flow.  
 * Note that the emulators (both the Google-provided x86 and Genymotion versions) for API 24+ versions can introduce intermittent issues when initiating the OAuth flow for the first time.  For best results, use an device for this project.
