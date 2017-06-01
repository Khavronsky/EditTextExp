package com.khavronsky.edittextexp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tv_item;

    private EditText editableAnswer;

    private CheckBox checkBox;

    private ICheckListener listener;

    private View answerItem;

    private int pos;

    private TextWatcher mTextWatcher;

    private QuestionsModel.Answer answer;
    private final static String TAG = "Quest1";

    Holder(View view) {
        super(view);
        Log.d(TAG, "Holder: ");
        tv_item = (TextView) view.findViewById(R.id.qstn_answer);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        answerItem = view.findViewById(R.id.qstn_answer_item);
        editableAnswer = (EditText) view.findViewById(R.id.qstn_editable_answer);
        changeVisibility(false);
        setTextWatcher();
    }

    @NonNull
    private void setTextWatcher() {
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before,
                    final int count) {
                Log.d("QuestHolder",
                        "onTextChanged() s = [" + s + "], TextWatcher = " + mTextWatcher);
            }

            @Override
            public void afterTextChanged(final Editable s) {
                answer.setAnswer(String.valueOf(s));
                if (listener != null) {
//                        listener.check(checkBox.isChecked(), pos);
                }
            }
        };
    }

    void setAnswer(QuestionsModel.Answer answer, int backgroundSource) {
        this.answer = answer;
        Log.d(TAG, "setAnswer() answerID = [" + answer.getId() + "]" + "editable = " + answer.isEditable());
        editableAnswer.setOnClickListener(this);
        if (answer.isEditable()) {
            tv_item.setVisibility(View.GONE);
            editableAnswer.setVisibility(View.VISIBLE);
            editableAnswer.setText(answer.getAnswer());
        } else {
            editableAnswer.setVisibility(View.GONE);

            tv_item.setVisibility(View.VISIBLE);
            tv_item.setText(answer.getAnswer());
        }
        answerItem.setOnClickListener(this);
        checkBox.setChecked(answer.isSelected());
        checkBox.setBackgroundResource(backgroundSource);
    }

    void setListener(ICheckListener listener, int pos) {
        this.listener = listener;
        this.pos = pos;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            Log.d(TAG, "onClick: ");
            listener.check(checkBox.isChecked(), pos);

            if (answer.isEditable()) {
                changeVisibility(true);
                editableAnswer.addTextChangedListener(mTextWatcher);
                if (answer.getAnswer() != null) {
                    editableAnswer.setSelection(answer.getAnswer().length());
                }
            } else {
                changeVisibility(false);
            }
        }
    }

    private void changeVisibility(boolean visibility) {
        Log.d(TAG, "changeVisibility = [" + visibility + "]");
        editableAnswer.setClickable(visibility);
        editableAnswer.setFocusableInTouchMode(visibility);
        editableAnswer.setFocusable(visibility);
        editableAnswer.setCursorVisible(visibility);
    }

    interface ICheckListener {

        void check(boolean isChecked, int pos);
    }
}
