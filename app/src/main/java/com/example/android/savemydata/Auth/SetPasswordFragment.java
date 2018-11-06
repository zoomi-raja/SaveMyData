package com.example.android.savemydata.Auth;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.savemydata.DB.UserDao;
import com.example.android.savemydata.Models.User;
import com.example.android.savemydata.R;
import com.example.android.savemydata.Utility.AppDatabase;

import java.lang.ref.WeakReference;

public class SetPasswordFragment extends Fragment {
    private Button button;
    private SetPasswordClick activityClick;
    private EditText passwordField;
    private EditText hintField;
    private AppDatabase db;

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
        passwordField       = view.findViewById(R.id.userPass);
        hintField           = view.findViewById(R.id.passHint);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                activityClick.onRegOperationSuccess();
                if(isInputValid()){
                    db =  AppDatabase.getAppDatabase(getContext());
                    User user = new User("zoomi", passwordField.getText().toString(),hintField.getText().toString());
                    new dbOperationAsyncTask(activityClick, user, db).execute();
                }
            }
        });
        return view;
    }
    class dbOperationAsyncTask extends AsyncTask<Void,Void, User>{
        private WeakReference<SetPasswordClick> weakContext;
        private User user;
        private AppDatabase db;
        public dbOperationAsyncTask(SetPasswordClick context, User user, AppDatabase db) {
            this.weakContext    = new WeakReference<>(context);
            this.user           = user;
            this.db             = db;
        }


        @Override
        protected User doInBackground(Void... Void) {
            long id = db.userDao().insert(user);
            return db.userDao().findByID(id);
        }

        @Override
        protected void onPostExecute(User user) {
            if(weakContext.get() != null)
                weakContext.get().onRegistrationAction(user);
        }
    }

    private boolean isInputValid() {
        boolean valid = true;
        if(passwordField.getText().toString().length() < 6){
            passwordField.setHint("Minimum required length is 6");
            passwordField.setHintTextColor(getResources().getColor(R.color.colorAccent));
            passwordField.requestFocus();
            valid = false;
        }
        if(hintField.getText().toString().length() == 0){
            hintField.setHint("Hint is required");
            hintField.setHintTextColor(getResources().getColor(R.color.colorAccent));
            if(valid) {
                hintField.requestFocus();
                valid = false;
            }
        }
        return  valid;
    }

    interface SetPasswordClick{
        public void onRegistrationAction(User user);
    }
}
