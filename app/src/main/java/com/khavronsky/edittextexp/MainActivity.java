package com.khavronsky.edittextexp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;

    private QuestionsModel questionsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createModel();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        Adapter mAdapter = new Adapter(questionsModel);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setIQDListener(mAdapter::notifyDataSetChanged);
    }

    void createModel() {
        questionsModel = new QuestionsModel();
        questionsModel.setQuestion("Question");
        questionsModel.setTitle("title");
        questionsModel.setMultiChoice(false);
        questionsModel.setAnswers(new ArrayList<QuestionsModel.Answer>() {
            {
                add(new QuestionsModel.Answer()
                        .setId(1)
                        .setAnswer("answer1")
                        .setEditable(false)
                        .setSelected(true));
                add(new QuestionsModel.Answer()
                        .setId(2)
                        .setAnswer("answer2")
                        .setEditable(false)
                        .setSelected(false));
                add(new QuestionsModel.Answer()
                        .setId(3)
                        .setAnswer("")
                        .setEditable(true)
                        .setSelected(false));
            }
        });
    }
}
