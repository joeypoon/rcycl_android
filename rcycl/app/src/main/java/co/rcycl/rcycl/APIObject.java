package co.rcycl.rcycl;

import org.json.JSONObject;

// Created by Whit on 2/28/2016.
// The base behavior for object that are sent to/from the API server.
public abstract class APIObject {

    protected int mID;
    public int getID() {return mID;}

    protected String mEndpointName;
    public String getEndpointName() {return mEndpointName;}

    // Accepts JSON and attempts to populate the
    // APIObject's fields from its content.
    public abstract boolean fromJSONObject(JSONObject json);

    // Exports the APIObject's fields as a JSONObject.
    public abstract JSONObject toJSONObject();
}
