package com.example.android.savemydata.Auth;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.android.savemydata.R;

import java.lang.ref.WeakReference;

public class SetPasswordFragment extends Fragment {
    private Button button;
    private SetPasswordClick activityClick;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityClick = (SetPasswordClick)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TextClicked");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view           = inflater.inflate(R.layout.set_password_fragment,container,false);
        button              = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityClick.switchFragment();
            }
        });
        return view;
    }
    interface SetPasswordClick{
        public void switchFragment();
    }
}
