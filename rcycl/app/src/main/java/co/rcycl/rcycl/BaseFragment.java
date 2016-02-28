package co.rcycl.rcycl;

import android.app.Activity;
import android.support.v4.app.Fragment;

// Created by Whit on 2/28/2016.
// A base implementation for fragments to reduce code redundancy.
public abstract class BaseFragment extends Fragment {

    // Stores a reference to MainActivity.java for access to its UI and HTTP methods.
    protected MainActivity mParent;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // If the fragment is created by MainActivity (and it always should be),
        // then save the instance for later access to its UI and HTTP methods.
        if (activity instanceof MainActivity) {
            mParent = (MainActivity) activity;
        }
    }
}
