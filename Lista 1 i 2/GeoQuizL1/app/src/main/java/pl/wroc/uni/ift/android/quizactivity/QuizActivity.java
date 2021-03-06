package pl.wroc.uni.ift.android.quizactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {


    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private TextView mQuestionTextView;

    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.question_stolica_polski, true),
            new Question(R.string.question_stolica_dolnego_slaska, false),
            new Question(R.string.question_sniezka, true),
            new Question(R.string.question_wisla, true)
    };

    private int mCurrentIndex = 0;
    private int mCorrectCount = 0;
    private int mAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        // inflating view objects
        setContentView(R.layout.activity_quiz);


        // pole tekstowe z pytaniem
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

        // przycisk prawda
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(true);

                    }
                }
        );

        // przycisk fałsz
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // przycisk następny
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

        // przycisk poprzedni
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                if (mCurrentIndex<0) mCurrentIndex = mQuestionsBank.length-1; // jeśli indeks mniejszy od zera, zmień na (długość tablicy z pytaniami -1, czyli ostatnie pytanie)
                updateQuestion();
            }
        });


        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        Question question = mQuestionsBank[mCurrentIndex];
        boolean answerIsTrue = question.isAnswerTrue();

        int toastMessageId = 0;
        if (!question.checkIsAnswered() && mAnswered!=mQuestionsBank.length)
        {
            if (userPressedTrue == answerIsTrue) {
                toastMessageId = R.string.correct_toast;
                mCorrectCount++;
            } else {
                toastMessageId = R.string.incorrect_toast;
            }
            question.setAnswered(true);
            mAnswered++;
        }
        else
        {
            toastMessageId = R.string.already_answered;
            //Toast toast = Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT);
        }

        Toast toast = Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,175);
        toast.show();

        if (mAnswered==mQuestionsBank.length)
        {
            toastMessageId = R.string.final_notification;
            String message = getString(toastMessageId)+" "+mCorrectCount+" / "+mQuestionsBank.length;
            Toast toast1 = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.BOTTOM,0,0);
            toast1.show();
        }
    }
}
