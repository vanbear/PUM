package pl.wroc.uni.ift.android.quizactivity;

/**
 * Created by BeaR on 10.10.2017.
 */

public class Question
{
    int mResourceId;
    private boolean mAnswerTrue;

    Question(int resourceId, boolean answerTrue )
    {
        mResourceId=resourceId;
        mAnswerTrue=answerTrue;
    }

    public void setIsAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue=answerTrue;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public int getmResourceId()
    {
        return mResourceId;
    }
}
