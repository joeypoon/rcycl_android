package co.rcycl.rcycl;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.reflect.Method;

// Created by Whit on 2/28/2016.
// A base implementation for fragments to reduce code redundancy.
public abstract class BaseFragment<T extends APIObject> extends Fragment {

    // Stores a reference to MainActivity.java for access to its UI and HTTP methods.
    protected MainActivity mParentActivity;

    // Store a copy of the APIObject because MVC is the only home I've ever known.
    protected T mAPIModel;
    public T getAPIModel() { return mAPIModel; }
    public void setAPIModel(T model) {
        mAPIModel = model;
    }

    // Callback method to be used after handling requests.
    public final Method getAPICallback() {
        return Utils.getDelegate(this, "onAPICallbackDelegate", APIObject.class);
    }

    // Override this to implement custom behavior.
    protected void onAPICallback(APIObject response) {}

    // Updates the model, then calls any custom behavior defined in the derived class.
    public void onAPICallbackDelegate(APIObject response) {
        T updatedModel = (T) response;
        if (updatedModel != null) {
            mAPIModel = updatedModel;
        }

        onAPICallback(response);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // If the fragment is created by MainActivity (and it always should be),
        // then save the instance for later access to its UI and HTTP methods.
        if (activity instanceof MainActivity) {
            mParentActivity = (MainActivity) activity;
        }
    }
}
