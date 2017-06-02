package com.khavronsky.edittextexp;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class HolderWithTextView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tv_item;

    private CheckBox checkBox;

    private ICheckListener listener;

    private View answerItem;

    HolderWithTextView(View view,ICheckListener listener) {
        super(view);
        this.listener = listener;
        tv_item = (TextView) view.findViewById(R.id.qstn_answer);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        answerItem = view.findViewById(R.id.qstn_answer_item);
    }

    void bind(QuestionsModel.Answer answer, int backgroundSource) {
        tv_item.setText(answer.getAnswer());
        answerItem.setOnClickListener(this);
        checkBox.setChecked(answer.isSelected());
        checkBox.setBackgroundResource(backgroundSource);
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
