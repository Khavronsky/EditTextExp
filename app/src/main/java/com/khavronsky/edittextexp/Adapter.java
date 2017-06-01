package com.khavronsky.edittextexp;


import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements HolderWithTextView.ICheckListener, HolderWithEditText.ICheckListener {

    private QuestionsModel mQuestion;

    private IAnswersListener mListener;

    @IntDef({
            RADIO_TYPE,
            CHECK_TYPE,
    })
    @interface CheckBoxType {

    }

    static final int RADIO_TYPE = R.drawable.radiobutton_selection;

    static final int CHECK_TYPE = R.drawable.checkbox_selection;

    Adapter(QuestionsModel question) {
        this.mQuestion = question;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qstn_holder_item, parent, false);
            return new HolderWithTextView(v);
        }
        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.qstn_holder_edit_text_item, parent, false);
            return new HolderWithEditText(v);
        }
        return null;
    }

    @Override
    public int getItemViewType(final int position) {
        if (mQuestion.getAnswers().get(position).isEditable()) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderWithTextView) {
            ((HolderWithTextView) holder).setListener(this, position);
            ((HolderWithTextView) holder).setAnswer(mQuestion.getAnswers().get(position),
                    setCheckBoxDrawable(mQuestion.isMultiChoice()));
        }
        if (holder instanceof HolderWithEditText) {
            ((HolderWithEditText)holder).setListener(this, position);
            ((HolderWithEditText)holder).setAnswer(mQuestion.getAnswers().get(position), setCheckBoxDrawable
                    (mQuestion.isMultiChoice()));
        }
    }

    @Override
    public int getItemCount() {
        return mQuestion != null && mQuestion.getAnswers() != null ? mQuestion.getAnswers().size() : 0;
    }

    void setIQDListener(IAnswersListener listener) {
        this.mListener = listener;
    }

    @Override
    public void check(boolean isChecked, int pos) {
        List<QuestionsModel.Answer> answers = mQuestion.getAnswers();

        if (!mQuestion.isMultiChoice()) {
            for (int i = 0; i < mQuestion.getAnswers().size(); i++) {
                answers.get(i).setSelected(false);
            }
            answers.get(pos).setSelected(true);
        } else {
            answers.get(pos).setSelected(!isChecked);
        }
        mListener.selectItem();
    }

    @CheckBoxType
    private int setCheckBoxDrawable(boolean multiChoice) {
        return multiChoice ? CHECK_TYPE : RADIO_TYPE;
    }

    interface IAnswersListener {

        void selectItem();
    }

}
