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

//    private View answerItem;

    private int pos;

    private TextWatcher mTextWatcher;

    private QuestionsModel.Answer answer;

    private final static String TAG = "HWET";

    HolderWithEditText(View view) {
        super(view);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
//        answerItem = view.findViewById(R.id.qstn_answer_item);
        editableAnswer = (EditText) view.findViewById(R.id.qstn_editable_answer);
//        superLogger("constructor before");
//        answerItem.setFocusableInTouchMode(true);
        setTextWatcher();
        view.setOnClickListener(this);
        editableAnswer.setOnClickListener(this);
        editableAnswer.addTextChangedListener(mTextWatcher);
//        editableAnswer.setOnFocusChangeListener(createFocusListener());
//        editableAnswer.setOnTouchListener((v, event) -> {
//            Log.d(TAG, "OnTouchListener: ");
//            return true;
//        });

//        superLogger("constructor after");
    }

    @NonNull
    private void setTextWatcher() {
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {
                Log.d(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before,
                    final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                answer.setAnswer(String.valueOf(s));
                if (listener != null) {
//
                }
            }
        };
    }



    void setAnswer(QuestionsModel.Answer answer, int backgroundSource) {
//        superLogger("setAnswer_BEFORE");
        this.answer = answer;
        editableAnswer.clearFocus();
        editableAnswer.setText(answer.getAnswer());
        checkBox.setChecked(answer.isSelected());
        checkBox.setBackgroundResource(backgroundSource);
        if (answer.isSelected()){
            editableAnswer.requestFocus();
        }
//        superLogger("setAnswer_AFTER");
        editableAnswer.setOnFocusChangeListener(createFocusListener());
    }

    void setListener(ICheckListener listener) {
        this.listener = listener;

    }

    private View.OnFocusChangeListener createFocusListener() {
        return (v, hasFocus) -> {
            if (hasFocus) {
                Log.d(TAG, "createFocusListener: true");
                if (listener != null) {
//                    listener.check(checkBox.isChecked(), getAdapterPosition());
                }
            } else {
                Log.d(TAG, "createFocusListener: false");
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.check(checkBox.isChecked(), getAdapterPosition());
            editableAnswer.setFocusable(true);
            editableAnswer.setCursorVisible(true);
            editableAnswer.requestFocus();
            superLogger("click");
//            editableAnswer.setFocusable(true);
//            editableAnswer.requestFocus();


            if (answer.getAnswer() != null) {
                editableAnswer.setSelection(answer.getAnswer().length());
            }
//            superLogger("onClick");

        }
    }

    private void superLogger(String tag){
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
