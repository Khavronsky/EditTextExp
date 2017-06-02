package com.khavronsky.edittextexp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class HolderWithEditText extends RecyclerView.ViewHolder implements View.OnClickListener {

    private EditText editableAnswer;

    private CheckBox checkBox;

    private ICheckListener listener;

    private boolean onBind;

    private TextWatcher mTextWatcher;

    private QuestionsModel.Answer answer;

    HolderWithEditText(View view, ICheckListener listener) {
        super(view);
        this.listener = listener;
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        editableAnswer = (EditText) view.findViewById(R.id.qstn_editable_answer);
        setTextWatcher();
        view.setOnClickListener(this);
        editableAnswer.addTextChangedListener(mTextWatcher);
        editableAnswer.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!onBind && hasFocus) {
                listener.check(checkBox.isChecked(), getAdapterPosition());
            }
        });
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
            }

            @Override
            public void afterTextChanged(final Editable s) {
                answer.setAnswer(String.valueOf(s));
            }
        };
    }

    void bind(QuestionsModel.Answer answer, int backgroundSource) {
        onBind = true;
        this.answer = answer;
        editableAnswer.setText(answer.getAnswer());
        checkBox.setChecked(answer.isSelected());
        checkBox.setBackgroundResource(backgroundSource);
        if (answer.isSelected()) {
            if (!editableAnswer.isFocused()) {
                editableAnswer.requestFocus();
            }
        } else if (editableAnswer.isFocused()) {
            editableAnswer.clearFocus();
        }
        onBind = false;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.check(checkBox.isChecked(), getAdapterPosition());
        }
    }


    interface ICheckListener {

        void check(boolean isChecked, int pos);
    }
}
