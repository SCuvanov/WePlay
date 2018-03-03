package com.scuvanov.weplay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.scuvanov.weplay.fragment.SignInFragment;
import com.scuvanov.weplay.fragment.SignUpFragment;

public class EntryActivity extends AppCompatActivity implements SignUpFragment.OnSignUpFragmentInteractionListener, SignInFragment.OnSignInFragmentInteractionListener{

    private final String TAG = EntryActivity.class.getCanonicalName();

    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        fm = getSupportFragmentManager();

        showHideFragment(fm.findFragmentById(R.id.fragmentSignUp));
    }

    void showHideFragment(final Fragment fragment) {
        ft = fm.beginTransaction();
        if (fragment.isHidden()) {
            ft.show(fragment);
        } else {
            ft.hide(fragment);
        }
        ft.commit();
    }

    private void showHideSignInFragment(final Fragment signInFragment){
        ft = fm.beginTransaction();
        if (signInFragment.isHidden()) {
            ft.setCustomAnimations(R.anim.enter_from_left,
                    R.anim.enter_from_left);
            ft.show(signInFragment);
        } else {
            ft.setCustomAnimations(R.anim.exit_to_left,
                    R.anim.exit_to_left);
            ft.hide(signInFragment);
        }
        ft.commit();
    }

    private void showHideSignUpFragment(final Fragment signUpfragment){
        ft = fm.beginTransaction();
        if (signUpfragment.isHidden()) {
            ft.setCustomAnimations(R.anim.enter_from_right,
                    R.anim.enter_from_right);
            ft.show(signUpfragment);
        } else {
            ft.setCustomAnimations(R.anim.exit_to_right,
                    R.anim.exit_to_right);
            ft.hide(signUpfragment);
        }
        ft.commit();
    }

    public void goToMainActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    public void switchToSignUpFragment() {
        showHideSignInFragment(fm.findFragmentById(R.id.fragmentSignIn));
        showHideSignUpFragment(fm.findFragmentById(R.id.fragmentSignUp));
    }

    @Override
    public void switchToSignInFragment() {
        showHideSignInFragment(fm.findFragmentById(R.id.fragmentSignIn));
        showHideSignUpFragment(fm.findFragmentById(R.id.fragmentSignUp));
    }

    @Override
    public void switchToMainActivity() {
        goToMainActivity();
    }
}
