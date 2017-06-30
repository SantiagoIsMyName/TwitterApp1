package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by sospina on 6/26/17.
 */
@Parcel
public class User {
    //list all the attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageURL;

    //Deserialize JSON
    public static User fromJSON(JSONObject json) throws JSONException{
        User user = new User();

        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageURL = json.getString("profile_image_url");


        return user;
    }
}