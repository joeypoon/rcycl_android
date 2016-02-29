package co.rcycl.rcycl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

// Created by Whit on 2/29/2016.
// 
public class SignInFragment extends BaseFragment<User> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Attach a delegate to the sign in button.
        Button signInButton = (Button) view.findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.cheers(mParentActivity, "Button works.");
                    }
                }
        );

        // Attach a delegate to the sign up button.
        Button signUpButton = (Button) view.findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.cheers(mParentActivity, "Button works.");
                    }
                }
        );

        return view;
    }
}
