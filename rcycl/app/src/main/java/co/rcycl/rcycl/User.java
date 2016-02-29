package co.rcycl.rcycl;

import org.json.JSONObject;

// Created by Whit on 2/29/2016.
// 
public class User extends APIObject {

    public User() {
        mEndpointName = "users";
    }

    @Override
    public boolean fromJSONObject(JSONObject json) {
        return false;
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}
