package pl.wroc.uni.ift.android.quizactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuestionListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle(R.string.app_name);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        QuestionBank questionB = QuestionBank.getInstance();
        mAdapter = new QuestionAdapter(questionB.getQuestions());
        mRecyclerView.setAdapter(mAdapter);
    }
    private class QuestionHolder extends RecyclerView.ViewHolder
    {
        private TextView mQuestionTextView;

        private Question mQuestion;

        public QuestionHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.activity_question_list,parent,false));

            mQuestionTextView = (TextView) itemView.findViewById(R.id.questions_item);

        }
        public void bind(Question question)
        {
            mQuestion = question;
            mQuestionTextView.setText(mQuestion.getTextResId());
        }
    }
    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>
    {
        private List<Question> mQuestions;
        public QuestionAdapter(List<Question> questions)
        {
            mQuestions = questions;
        }
        @Override
        public void onBindViewHolder(QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(question);
        }
        @Override
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(QuestionListActivity.this);
            return new QuestionHolder(layoutInflater,parent);

        }
        @Override
        public int getItemCount() {return mQuestions.size();}
    }
}

