package uta.fisei;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuessButtonListener_CCKD implements OnClickListener {
    private MainActivityFragment_CCKD mainActivityFragmentCCKD;
    private Handler handler;

    public GuessButtonListener_CCKD(MainActivityFragment_CCKD mainActivityFragmentCCKD) {
        this.mainActivityFragmentCCKD = mainActivityFragmentCCKD;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.mainActivityFragmentCCKD.getQuizViewModel().getCorrectCountryName();
        this.mainActivityFragmentCCKD.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.mainActivityFragmentCCKD.getQuizViewModel().setCorrectAnswers(1);
            this.mainActivityFragmentCCKD.getAnswerTextView().setText(answer + "!");
            this.mainActivityFragmentCCKD.getAnswerTextView().setTextColor(
                    this.mainActivityFragmentCCKD.getResources().getColor(R.color.correct_answer));

            this.mainActivityFragmentCCKD.disableButtons();

            if (this.mainActivityFragmentCCKD.getQuizViewModel().getCorrectAnswers()
                    == QuizViewModel_CCKD.getFlagsInQuiz()) {
                ResultsDialogFragment_CCKD quizResults = new ResultsDialogFragment_CCKD();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.mainActivityFragmentCCKD.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(QuizViewModel_CCKD.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mainActivityFragmentCCKD.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.mainActivityFragmentCCKD.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
