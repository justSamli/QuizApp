package com.example.android.generalsafetyquiz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method returning String for name of quiz participant
     *
     * @return Name of Participant
     */
    private String candidateName() {
        EditText name = (EditText) findViewById(R.id.candidate_name);
        return name.getText().toString();
    }

    /**
     * Method returning String for name of question two written answer
     *
     * @return answer
     */
    private String answer_two() {
        EditText answer = (EditText) findViewById(R.id.questionTwo_editText);
        return answer.getText().toString();
    }


    /**
     * Method returning a view with score in a toast message when answers are submitted
     */
    public void submitAnswers(View v) {

//        Text Colors and score set to defaults before any new calculation is done
        score = 0;
        ((RadioButton) findViewById(R.id.question_one)).setTextColor(Color.BLACK);
        ((EditText) findViewById(R.id.questionTwo_editText)).setTextColor(Color.BLACK);
        ((RadioButton) findViewById(R.id.question_four)).setTextColor(Color.BLACK);
        ((RadioButton) findViewById(R.id.question_five)).setTextColor(Color.BLACK);
        ((CheckBox) findViewById(R.id.checkbox_one)).setTextColor(Color.BLACK);
        ((CheckBox) findViewById(R.id.checkbox_two)).setTextColor(Color.BLACK);

//        Checking that the person wrote a name.

        if (candidateName().matches("")) {
            Toast.makeText(this, getString(R.string.noName), Toast.LENGTH_SHORT).show();
            return;
        }
//        Method calls for all the score calculations and toast message with the final score.
        pointsAwarded();
        scoreToast();
    }

    /**
     * Method for checking correct answers and awarding points for each correctly answered question
     *
     * @return total score
     */
    private int pointsAwarded() {
        //Correct answer booleans declared for radio buttons and checkboxes.

        RadioButton question_one = (RadioButton) findViewById(R.id.question_one);
        boolean answer_one = question_one.isChecked();

        CheckBox risk_assess = (CheckBox) findViewById(R.id.checkbox_one);
        boolean checkbox1 = risk_assess.isChecked();

        CheckBox toolbox_talk = (CheckBox) findViewById(R.id.checkbox_two);
        boolean checkbox2 = toolbox_talk.isChecked();

        CheckBox permit = (CheckBox) findViewById(R.id.checkbox_three);
        boolean checkbox3 = permit.isChecked();

        RadioButton question_four = (RadioButton) findViewById(R.id.question_four);
        boolean answer_four = question_four.isChecked();

        RadioButton question_five = (RadioButton) findViewById(R.id.question_five);
        boolean answer_five = question_five.isChecked();

//        Marks awarded when correct answers are selected.
//        Conditions set for correctly answered questions.


        if (answer_one) {
            score = score + 2;
            question_one.setTextColor(Color.GREEN);
        }
        if (answer_two().matches(getString(R.string.emergency)) || answer_two().matches(getString(R.string.emergency_number))) {
            score = score + 2;

//           Change EditText text color if answered correctly
            ((EditText) findViewById(R.id.questionTwo_editText)).setTextColor(Color.GREEN);
        } else {
            score = score + 0;
        }
        if (answer_four) {
            score = score + 2;

//           Change RadioButton text color if answered correctly
            question_four.setTextColor(Color.GREEN);
        }
        if (answer_five) {
            score = score + 2;

//           Change RadioButton text color if answered correctly
            question_five.setTextColor(Color.GREEN);
        }

//        This if/else if statement validates the correct checkboxes only and changes text color

        if (checkbox3) {
            score = score + 0;

        } else if (checkbox1 && checkbox2) {
            score = score + 2;
            ((CheckBox) findViewById(R.id.checkbox_one)).setTextColor(Color.GREEN);
            ((CheckBox) findViewById(R.id.checkbox_two)).setTextColor(Color.GREEN);
        }
        return score;
    }

    /**
     * Method for displaying toast message
     * Score is collated and toast message displays name and score of the candidate.
     */

    private void scoreToast() {

        if (score >= 6) {
            Context context = getApplicationContext();
            CharSequence text = candidateName() + getString(R.string.score_toast, score) + "\n" + getString(R.string.good_job);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Context context = getApplicationContext();
            CharSequence text = candidateName() + getString(R.string.score_toast, score);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}

