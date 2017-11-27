package pl.wroc.uni.ift.android.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

public class QuizPagerActivity extends AppCompatActivity {

    private static final String EXTRA_QUESTION_ID = "question_id";

    private ViewPager mViewPager;
    List<Question> mQuestions;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent (packageContext, QuizPagerActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, crimeId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pager);

        UUID questionID = (UUID) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);

        mViewPager = (ViewPager) findViewById(R.id.quiz_view_pager);
        mQuestions = QuestionBank.getInstance().getQuestions();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter() {
            @Override
            public Fragment getItem(int position) {
                //return null;
                Question question = mQuestions.get(position);
                return QuizActivityFragment.newInstance(question.getTextResId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

    }

}
