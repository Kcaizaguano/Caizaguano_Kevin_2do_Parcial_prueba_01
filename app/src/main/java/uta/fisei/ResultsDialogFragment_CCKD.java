package uta.fisei;


import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class ResultsDialogFragment_CCKD extends DialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final QuizViewModel_CCKD quizViewModelCCKD = ViewModelProviders.of(getActivity()).get(QuizViewModel_CCKD.class);
        int totalGuesses = quizViewModelCCKD.getTotalGuesses();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.results, totalGuesses, (1000 / (double) totalGuesses)));

        builder.setPositiveButton(R.string.reset_quiz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    com.nadershamma.apps.androidfunwithflags.MainActivityFragment quizFragment = (com.nadershamma.apps.androidfunwithflags.MainActivityFragment) getParentFragment();
                    try{
                        quizFragment.resetQuiz();
                    }catch (Exception e){
                        Log.e(quizViewModelCCKD.getTag(),"Unable to call resetQuiz()", e);
                    }
                }
                catch (Exception e){
                    Log.e(quizViewModelCCKD.getTag(),"Unable to get ActivityMainFragment", e);
                }
            }
        });
        return builder.create();
    }
}
