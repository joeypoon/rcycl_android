package co.rcycl.rcycl;

import org.json.JSONObject;

// Created by Whit on 2/28/2016.
// 
public class PickupRequest extends APIObject {

    private String mAddress;
    public String getAddress() {return mAddress;}
    public void setAddress(String address) {mAddress = address;}

    public PickupRequest() {
        this.mEndpointName = "request"; //TODO: Verify this with Joey.
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
