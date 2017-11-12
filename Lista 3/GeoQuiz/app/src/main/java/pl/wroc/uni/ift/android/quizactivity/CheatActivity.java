package pl.wroc.uni.ift.android.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


public class CheatActivity extends AppCompatActivity {

    private final static String EXTRA_KEY_ANSWER = "Answer";
    private final static String KEY_QUESTION = "QUESTION";
    private final static String EXTRA_KEY_SHOWN = "wasShown";
    TextView mTextViewAnswer;
    Button mButtonShow;

    boolean wasShown;
    boolean mAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            setAnswerShown(savedInstanceState.getBoolean("wasShown"));
        }
        else
        {
            setAnswerShown(false);
        }

        setContentView(R.layout.activity_cheat);

        mAnswer = getIntent().getBooleanExtra(EXTRA_KEY_ANSWER,false);

        mTextViewAnswer = (TextView) findViewById(R.id.text_view_answer);
        mButtonShow = (Button) findViewById(R.id.button_show_answer);
        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer) {
                    mTextViewAnswer.setText("Prawda");
                } else {
                    mTextViewAnswer.setText("Fałsz");
                }
                setAnswerShown(true);
                wasShown=true;
            }
        });

        if(getIntent().getBooleanExtra(EXTRA_KEY_SHOWN,false))
        {
            if (mAnswer) {
                mTextViewAnswer.setText("Prawda");
            } else {
                mTextViewAnswer.setText("Fałsz");
            }
        }
        setAnswerShown(false);
        wasShown=false;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putBoolean("mAnswer",mAnswer);
        savedInstanceState.putBoolean(EXTRA_KEY_SHOWN,wasShown);
        //Log.i("save_wasShown","Save Instance wasShown: "+Boolean.toString(wasShown));
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        wasShown = savedInstanceState.getBoolean(EXTRA_KEY_SHOWN);
        //Log.i("restore_wasShown_before","Restore Instance wasShown before if: "+Boolean.toString(wasShown));
        if(wasShown) {
            if (mAnswer==true) {
                mTextViewAnswer.setText("Prawda");
            } else {
                mTextViewAnswer.setText("Fałsz");
            }

        }
        setAnswerShown(wasShown);
        //Log.i("restore_wasShown_after","Restore Instance wasShown after if: "+Boolean.toString(wasShown));
    }

    public static boolean wasAnswerShown(Intent data)
    {
        return data.getBooleanExtra(EXTRA_KEY_SHOWN, false);
    }

    public static Intent newIntent(Context context, boolean answerIsTrue, boolean wasShown)
    {

        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_KEY_ANSWER, answerIsTrue);
        intent.putExtra(EXTRA_KEY_SHOWN,wasShown);
        return intent;

    }


    private void setAnswerShown (boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra("wasShown", isAnswerShown);
        setResult(RESULT_OK, data);
    }




}
