package pl.wroc.uni.ift.android.quizactivity;

/**
 * Created by BeaR on 07.01.2018.
 */

public class Globals{
    private static Globals instance;


    // Global variable
    private int QuestionsCount = 0;
    private int AnsweredQuestionsCount = 0;
    private int CorrectAnswersCount = 0;
    private int CheatTokensCount = 3;


    public int getCheatTokensCount() {
        return CheatTokensCount;
    }

    public void setCheatTokensCount(int cheatTokensCount) {
        CheatTokensCount = cheatTokensCount;
    }

    public int getQuestionsCount() {
        return QuestionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        QuestionsCount = questionsCount;
    }

    public int getAnsweredQuestionsCount() {
        return AnsweredQuestionsCount;
    }

    public void setAnsweredQuestionsCount(int answeredQuestionsCount) {
        AnsweredQuestionsCount = answeredQuestionsCount;
    }

    public int getCorrectAnswersCount() {
        return CorrectAnswersCount;
    }

    public void setCorrectAnswersCount(int correctAnswersCount) {
        CorrectAnswersCount = correctAnswersCount;
    }


    // Restrict the constructor from being instantiated
    private Globals(){}

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
