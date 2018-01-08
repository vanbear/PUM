package pl.wroc.uni.ift.android.quizactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import static pl.wroc.uni.ift.android.quizactivity.R.styleable.MenuItem;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Globals globals = Globals.getInstance();

        String pointsString = globals.getCorrectAnswersCount() + " / " + globals.getQuestionsCount();
        TextView pointsTextView = (TextView)findViewById(R.id.pointsSummary);
        pointsTextView.setText(pointsString);

        TextView tokensTextView = (TextView)findViewById(R.id.tokensSummary);
        tokensTextView.setText(globals.getCheatTokensCount()+"");

    }



}



