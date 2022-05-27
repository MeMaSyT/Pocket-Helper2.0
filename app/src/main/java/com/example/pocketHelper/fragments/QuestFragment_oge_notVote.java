package com.example.pocketHelper.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pocketHelper.R;
import com.example.pocketHelper.Tasks_OGE_Activity;
import com.example.pocketHelper.db.DBHelper;
import com.example.pocketHelper.statistic.MainStatistic;

import java.io.IOException;

public class QuestFragment_oge_notVote extends Fragment {
    public interface GlobalQuest_completeTaskListener {
        public void onTaskReady(String title);
    }

    private TextView labelView, textView, proverkaButton;
    private EditText voteText;
    private LinearLayout imageBook;
    ImageView image;
    private float trueVote = -1;
    private int data_id;

    private int[] intent;
    private int intent_forTest;
    private int i_intent = 0;
    View isTrueVoteImage, falseVoteImage;

    String label, text;

    private ImageButton back_btn;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    Cursor cursor_label;

    //statistic page
    int trueVotes = 0;

    //anim
    AnimatorSet set = new AnimatorSet(), set1 = new AnimatorSet();

    private TextView falseTextVote, trueTextVote;

    Handler handler = new Handler();

    //globalQuest
    private boolean isGlobalQuest = false;
    private GlobalQuest_completeTaskListener listener;

    public QuestFragment_oge_notVote(int[] intent, int data_id) {
        this.intent = intent;
        this.data_id = data_id;
    }

    public void setListener(GlobalQuest_completeTaskListener listener) {
        this.listener = listener;
    }

    public QuestFragment_oge_notVote(int intent, int data_id) {
        this.intent_forTest = intent;
        this.data_id = data_id;
        isGlobalQuest = true;
        this.listener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db_helper = new DBHelper(getContext());

        try {
            db_helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            db = db_helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        cursor_label = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);

        Tasks_OGE_Activity.onGame = true;

        return inflater.inflate(R.layout.fragment_quest_oge_not_vote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cursor_label.moveToFirst();
        if (!isGlobalQuest) {
            cursor_label.move(intent[i_intent]);
        } else {
            cursor_label.move(intent_forTest);
        }

        label = cursor_label.getString(2);
        text = cursor_label.getString(3);
        trueVote = cursor_label.getFloat(8);

        imageBook = view.findViewById(R.id.quest_nonVote_oge_image_book);
        image = view.findViewById(R.id.image_book);
        float factor = getContext().getResources().getDisplayMetrics().density;
        if (!cursor_label.getString(4).equals("-")) {
            int id = getResources().getIdentifier(cursor_label.getString(4), "drawable", getActivity().getPackageName());
            image.setImageResource(id);
            ViewGroup.LayoutParams params = imageBook.getLayoutParams();
            params.height = (int) (180 * factor);
            imageBook.setLayoutParams(params);
            imageBook.setVisibility(View.VISIBLE);
        } else {
            System.out.println("ok");
            imageBook.removeView(imageBook);
        }


        cursor_label.close();

        back_btn = view.findViewById(R.id.buttonBack_toLevels2);
        labelView = view.findViewById(R.id.lable_oge_non_vote);
        textView = view.findViewById(R.id.text_oge_non_vote);
        proverkaButton = view.findViewById(R.id.button_isTrueVote2);
        isTrueVoteImage = view.findViewById(R.id.level_complite_quest_oge_nonVote);
        falseVoteImage = view.findViewById(R.id.level_failed_quest_oge_nonVote);
        falseTextVote = view.findViewById(R.id.falseVote_text_oge_nonVote);
        trueTextVote = view.findViewById(R.id.trueVote_text_oge_nonVote);
        voteText = view.findViewById(R.id.voteText_nonVote_oge);

        LoadDataLevel(label, text);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                set.cancel();
                set1.cancel();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag("oge_task_vote_nonVote"))
                        .commit();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });
        proverkaButton.setOnClickListener(this::onClick_isTrueVote_button);

    }

    private void LoadDataLevel(String label, String text) {
        labelView.setText(label);
        textView.setText(text);
    }

    public void onClick_isTrueVote_button(View view) {
        float voteNumber = -0.999987f;
        try {
            voteNumber = Float.parseFloat(voteText.getText().toString());
        } catch (NumberFormatException e) {

        }
        if (!isGlobalQuest) {
            if (voteNumber != -0.999987f) {
                MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_LEVELS_PLAYED, 1);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                proverkaButton.setClickable(false);
                if (voteNumber == trueVote) {
                    trueVotes++;
                    set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                    set.setTarget(isTrueVoteImage);
                    set.start();
                    if (data_id > 5 && data_id != 17 && data_id != 18) {
                        trueTextVote.setText((i_intent + 1) + " / 10");
                    } else {
                        trueTextVote.setText((i_intent + 1) + " / 5");
                    }
                } else {
                    set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                    set.setTarget(falseVoteImage);
                    set.start();
                    if (data_id > 5 && data_id != 17 && data_id != 18) {
                        falseTextVote.setText((i_intent + 1) + " / 10");
                    } else {
                        falseTextVote.setText((i_intent + 1) + " / 5");
                    }
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        i_intent++;
                        if (i_intent >= intent.length) {
                            Statistic_last_task_oge_Fragment fragmentStatistic = new Statistic_last_task_oge_Fragment(trueVotes, data_id);
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainerView, fragmentStatistic, "statistic_oge")
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            voteText.setText("");
                            Cursor cursor_restarted = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);
                            cursor_restarted.moveToFirst();
                            cursor_restarted.moveToPosition(intent[i_intent]);

                            label = cursor_restarted.getString(2);
                            text = cursor_restarted.getString(3);
                            trueVote = cursor_restarted.getFloat(8);

                            int id = getResources().getIdentifier(cursor_restarted.getString(4), "drawable", getActivity().getPackageName());
                            image.setImageResource(id);

                            cursor_restarted.close();

                            LoadDataLevel(label, text);

                            proverkaButton.setClickable(true);

                            set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                            set.setTarget(falseVoteImage);
                            set.start();
                            set1 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                            set1.setTarget(isTrueVoteImage);
                            set1.start();
                        }
                    }
                }, 2000);
            }
        } else {
            if (voteNumber != -0.999987f) {
                MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_LEVELS_PLAYED, 1);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                proverkaButton.setClickable(false);
                if (voteNumber == trueVote) {
                    trueVotes++;
                    set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                    set.setTarget(isTrueVoteImage);
                    set.start();
                    trueTextVote.setText(data_id + " / 19");
                    Tasks_OGE_Activity.yourAnswers += "1";
                } else {
                    set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                    set.setTarget(falseVoteImage);
                    set.start();
                    falseTextVote.setText(data_id + " / 19");
                    Tasks_OGE_Activity.yourAnswers += "0";
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onTaskReady("complete");
                    }
                }, 2000);
            }
        }
    }
}