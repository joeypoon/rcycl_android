package co.rcycl.rcycl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

// Created by Whit on 2/28/2016.
// 
public class RequestPickupFragment extends BaseFragment<PickupRequest> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_pickup, container, false);

        // Attach a delegate to the request pickup button.
        Button requestPickupButton = (Button) view.findViewById(R.id.button_request_pickup);
        requestPickupButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAPIModel = new PickupRequest();
                        mAPIModel.setAddress(
                                Utils.getTextFromViewField(view, R.id.request_pickup_address));

                        if (mParentActivity != null) {
                            mParentActivity.postToAPI(mAPIModel, getAPICallback());
                        } else {
                            Utils.cheers(view.getContext(), "There was an error on our end. Thanks for being a great person!");
                        }
                    }
                }
        );

        return view;
    }

    @Override
    public void onAPICallback(APIObject response) {
        mParentActivity.swapFragment(new SignInFragment());
    }
}
