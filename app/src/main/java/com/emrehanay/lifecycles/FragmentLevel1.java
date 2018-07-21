package com.emrehanay.lifecycles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentLevel1 extends Fragment {
    public static final String TAG = "FragmentLevel1";
    public static final String TAG_FRAGMENT1 = "FragmentLevel2-1";
    public static final String TAG_FRAGMENT2 = "FragmentLevel2-2";

    private FragmentLevel2 FragmentLevel2_1;
    private FragmentLevel2 FragmentLevel2_2;
    private String myName = TAG;
    private TextView textView;

    public FragmentLevel1() {
    }

    public static FragmentLevel1 newInstance(String myName) {
        FragmentLevel1 fragment = new FragmentLevel1();
        Bundle args = new Bundle();
        args.putString("myName", myName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putString("myName", myName);
        if (FragmentLevel2_1 != null && FragmentLevel2_1.isAdded()) {
            getActivity().getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT1, FragmentLevel2_1);
        }
        if (FragmentLevel2_2 != null && FragmentLevel2_2.isAdded()) {
            getActivity().getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT2, FragmentLevel2_2);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        Bundle bundle;
        if (savedInstanceState != null) {
            bundle = savedInstanceState;
            FragmentLevel2_1 = (FragmentLevel2) getActivity().getSupportFragmentManager().getFragment(savedInstanceState, TAG_FRAGMENT1);
            FragmentLevel2_2 = (FragmentLevel2) getActivity().getSupportFragmentManager().getFragment(savedInstanceState, TAG_FRAGMENT2);
        } else {
            bundle = getArguments();
        }
        myName = bundle.getString("myName");
        if (FragmentLevel2_1 == null) {
            FragmentLevel2_1 = FragmentLevel2.newInstance(TAG_FRAGMENT1);
            initFragment(R.id.layout1, FragmentLevel2_1);
        }
        if (FragmentLevel2_2 == null) {
            FragmentLevel2_2 = FragmentLevel2.newInstance(TAG_FRAGMENT2);
            initFragment(R.id.layout2, FragmentLevel2_2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_level1, container, false);
        textView = rootView.findViewById(R.id.textView);
        textView.setText(myName);
        textView.setOnClickListener(v -> {
            textView.setText(R.string.i_have_changed);
            myName = String.valueOf(textView.getText());
        });
        return rootView;
    }

    private void initFragment(int layoutId, Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "deAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
