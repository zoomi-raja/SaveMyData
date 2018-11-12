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
import com.example.android.savemydata.Models.User;
import com.example.android.savemydata.R;
import com.example.android.savemydata.Utility.AppDatabase;

import java.lang.ref.WeakReference;

public class LoginFragment extends Fragment {
    private EditText password;
    private Button button;
    private SetLoginClick activityClick;
    private AppDatabase db;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityClick = (SetLoginClick)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TextClicked");
        }

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.login_fragment,container,false);
        password    = view.findViewById(R.id.userPass);
        button      = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =  AppDatabase.getAppDatabase(getContext());
                User user = new User("zoomi", password.getText().toString(),"");
                new LoginFragment.loginAsyncTask(db, user, activityClick).execute();
            }
        });
        return view;

    }
    interface SetLoginClick{
        public void onLoginAction(User user);
    }
    class loginAsyncTask extends AsyncTask<Void,Void,User>{
        private AppDatabase db;
        private User user;
        private WeakReference<SetLoginClick> weakContext;

        public loginAsyncTask(AppDatabase db, User user, SetLoginClick context) {
            this.db = db;
            this.user = user;
            this.weakContext = new WeakReference<>(context);
        }

        @Override
        protected User doInBackground(Void... voids) {
            return db.userDao().findByPassword(user.getPassword());
        }

        @Override
        protected void onPostExecute(User user) {
            if(weakContext.get() != null)
                weakContext.get().onLoginAction(user);
        }
    }
}
