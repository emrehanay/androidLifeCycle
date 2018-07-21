package com.emrehanay.lifecycles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FragmentLevel1 fragmentLevel1;

    private String myName = TAG;
    private TextView textView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putString("myName", myName);
        if (fragmentLevel1 != null && fragmentLevel1.isAdded()) {
            getSupportFragmentManager().putFragment(outState, FragmentLevel1.TAG, fragmentLevel1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            fragmentLevel1 = (FragmentLevel1) getSupportFragmentManager().getFragment(savedInstanceState, FragmentLevel1.TAG);
            myName = savedInstanceState.getString("myName");
        }
        textView = findViewById(R.id.textView);
        textView.setText(myName);
        if (fragmentLevel1 == null) {
            fragmentLevel1 = FragmentLevel1.newInstance("Fragment Level1");
            initFragment(fragmentLevel1);
        }
        textView.setOnClickListener(v -> {
            textView.setText(R.string.i_have_changed);
            myName = String.valueOf(textView.getText());
        });
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutBottom, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
