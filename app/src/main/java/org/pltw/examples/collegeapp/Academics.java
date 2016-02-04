package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by PLTW on 1/22/2016.
 */
public class Academics implements ApplicantData
{
    private String mGPA;
    private String mTestScores;
    private String mField;

    static final String JSON_GPA = "gpa";
    static final String JSON_TEST_SCORES = "testScores";
    static final String JSON_FIELD = "field";

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_GPA, mGPA);
        json.put(JSON_TEST_SCORES, mTestScores);
        json.put(JSON_FIELD, mField);

        return json;
    }

    public Academics(JSONObject json) throws JSONException {
        mGPA = json.getString(JSON_GPA);
        mTestScores = json.getString(JSON_TEST_SCORES);
        mField = json.getString(JSON_FIELD);
    }

    public String getGPA() {
        return mGPA;
    }

    public void setGPA(String mGPA) {
        this.mGPA = mGPA;
    }

    public String getTestScores() {
        return mTestScores;
    }

    public void setTestScores(String mTestScores) {
        this.mTestScores = mTestScores;
    }

    public String getField() {
        return mField;
    }

    public void setField(String mField) {
        this.mField = mField;
    }

    public Academics() {
        mGPA = new String("3.5");
        mTestScores = new String("ACT: 25");
        mField = new String ("Computer Science");
    }

    public String toString() {
        return mGPA + " " + mTestScores + " " + mField;
    }
}
