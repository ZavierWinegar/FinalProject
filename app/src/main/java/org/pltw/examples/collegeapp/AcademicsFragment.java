package org.pltw.examples.collegeapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by PLTW on 1/22/2016.
 */
public class AcademicsFragment extends Fragment {
    private static final String TAG = "AcademicsFragment";
    private static final String KEY_GPA = "gpa";
    private final String FILENAME = "academics.json";

    private Academics mAcademics;
    private TextView mGPA;
    private EditText mEnterGPA;
    private TextView mTestScores;
    private EditText mEnterTestScores;
    private TextView mField;
    private EditText mEnterField;
    private Context mAppContext;
    AcademicsJSONStorer  mStorer;

    public AcademicsFragment(){
        try {
            mAcademics = mStorer.load();
        } catch (Exception e) {
            mAcademics = new Academics();
            Log.e(TAG, "Error loading profile: " + FILENAME, e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAcademics = new Academics();

        if (savedInstanceState != null) {
            mAcademics.setGPA(savedInstanceState.getString(KEY_GPA));
            Log.i(TAG, "The GPA is " + mAcademics.getGPA());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_academics, container, false);

        getActivity().setTitle(R.string.academics_title);

        mGPA = (TextView)rootView.findViewById(R.id.gpa);
        mEnterGPA = (EditText)rootView.findViewById(R.id.enter_gpa);
        mTestScores = (TextView)rootView.findViewById(R.id.test_scores);
        mEnterTestScores = (EditText)rootView.findViewById(R.id.enter_test_scores);
        mField = (TextView)rootView.findViewById(R.id.field);
        mEnterField = (EditText)rootView.findViewById(R.id.enter_field);

        mGPA.setText(mAcademics.getGPA());
        mTestScores.setText(mAcademics.getTestScores());
        mField.setText(mAcademics.getField());

        GPATextChanger gpaTextChanger = new GPATextChanger();
        TestScoresTextChanger testScoresTextChanger = new TestScoresTextChanger();
        FieldTextChanger fieldTextChanger = new FieldTextChanger();

        mEnterGPA.addTextChangedListener(gpaTextChanger);

        mEnterTestScores.addTextChangedListener(testScoresTextChanger);

        mEnterField.addTextChangedListener(fieldTextChanger);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);

        mStorer = new AcademicsJSONStorer(mAppContext,FILENAME);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState got called and it was a BLAST!!!1: " + mAcademics.getGPA());
        savedInstanceState.putString(KEY_GPA, mAcademics.getGPA());
    }

    private class GPATextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mAcademics.setGPA(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mGPA.setText(mAcademics.getGPA());
        }
    }

    private class TestScoresTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mAcademics.setTestScores(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mTestScores.setText(mAcademics.getTestScores());
        }
    }

    private class FieldTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mAcademics.setField(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mField.setText(mAcademics.getField());
        }
    }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "Fragment started.");
        }


        public boolean saveAcademics() {
            try {
                mStorer.save(mAcademics);//Trying to save the profile to device
                Log.d(TAG, "profile saved to file.");//when profile is saved
                return true;
            } catch (Exception e) {//if profile is not saved catch error
                Log.e(TAG, "Error saving profile: ", e);//Says error saving profile is error occurs
                return false;
            }
        }

        public boolean loadAcademics() {
            try{
                mStorer.load();
                Log.d(TAG, "profile saved to file");
                return true;
            }
            catch(Exception e) {
                Log.e(TAG, "Error loading profile", e);
                return false;
            }
        }

        @Override
        public void onPause() {
            super.onPause();
            saveAcademics();
            Log.d(TAG, "Fragment paused.");
        }

        @Override
        public void onResume() {
            super.onResume();
            loadAcademics();
            try{
                mAcademics = mStorer.load();//loads the saved profile
                Log.d(TAG, "Loaded " + mAcademics.getGPA());//when profile loaded, update the DoB
                mGPA.setText(mAcademics.getGPA());
                mTestScores.setText(mAcademics.getTestScores());
                mField.setText(mAcademics.getField());
            }catch (Exception e){//if error catch error
                mAcademics = new Academics();//if no profile in device storage create new profile
                Log.e(TAG, "Error loading profile from: " + FILENAME, e);
            }
            Log.d(TAG, "Fragment resumed.");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "Fragment stopped.");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "Fragment destroyed.");
        }

}

