package com.khavronsky.edittextexp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class HolderWithEditText extends RecyclerView.ViewHolder implements View.OnClickListener {

    private EditText editableAnswer;

    private CheckBox checkBox;

    private ICheckListener listener;

    private View answerItem;

    private int pos;

    private TextWatcher mTextWatcher;

    private QuestionsModel.Answer answer;

    private final static String TAG = "HWET";

    HolderWithEditText(View view) {
        super(view);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        answerItem = view.findViewById(R.id.qstn_answer_item);
        editableAnswer = (EditText) view.findViewById(R.id.qstn_editable_answer);
        superLogger("constructor before");
        editableAnswer.setOnFocusChangeListener(createFocusListener());
        editableAnswer.setOnClickListener(this);
        answerItem.setOnClickListener(this);
//        answerItem.setFocusableInTouchMode(true);
        superLogger("constructor after");
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
        editableAnswer.setText(answer.getAnswer());
        checkBox.setChecked(answer.isSelected());
        checkBox.setBackgroundResource(backgroundSource);
        superLogger("setAnswer");
    }

    void setListener(ICheckListener listener, int pos) {
        this.listener = listener;
        this.pos = pos;
    }

    private View.OnFocusChangeListener createFocusListener() {
        return (v, hasFocus) -> {
            if (hasFocus) {
                listener.check(checkBox.isChecked(), pos);
                superLogger("createFocusListener");
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.check(checkBox.isChecked(), pos);
//            editableAnswer.setFocusable(true);
//            editableAnswer.requestFocus();
            editableAnswer.addTextChangedListener(mTextWatcher);
            if (answer.getAnswer() != null) {
                editableAnswer.setSelection(answer.getAnswer().length());
            }
            superLogger("onClick");

        }
    }

    void superLogger(String tag){
        Log.d(TAG, "<--!!- " + tag + " -!!-->----------------->------------------->\n");
        Log.d(TAG, " isClickable            " + editableAnswer.isClickable());
        Log.d(TAG, " isFocused              " + editableAnswer.isFocused());
        Log.d(TAG, " isFocusable            " + editableAnswer.isFocusable());
        Log.d(TAG, " isFocusableInTouchMode " + editableAnswer.isFocusableInTouchMode());
        Log.d(TAG, " isCursorVisible        " + editableAnswer.isCursorVisible());
//        Log.d(TAG, " " + editableAnswer.);
//        Log.d(TAG, " " + editableAnswer.);
    }

    interface ICheckListener {

        void check(boolean isChecked, int pos);
    }
}
