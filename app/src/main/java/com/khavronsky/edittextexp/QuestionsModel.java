package com.khavronsky.edittextexp;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionsModel implements Serializable {
    private int imgResource;
    private int questionId;
    private String title;
    private String question;
    private boolean multiChoice;
    private boolean isRequered;
    private List<Answer> answers;
//    private List<Surveys.ChoiceStruct_v1> choiceStruct_v1s;
    private boolean noQuestionContinue,skipQuestion;

    public static class Answer {
        private String answer;
        private int id;
        private boolean selected;
        private boolean editable;

        public boolean isEditable() {
            return editable;
        }

        public Answer setEditable(final boolean editable) {
            this.editable = editable;
            return this;
        }

        public String getAnswer() {
            return answer;
        }

        public Answer setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public boolean isSelected() {
            return selected;
        }

        public Answer setSelected(boolean selected) {
            this.selected = selected;
            return this;
        }

        public int getId() {
            return id;
        }

        public Answer setId(int id) {
            this.id = id;
            return this;
        }
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isMultiChoice() {
        return multiChoice;
    }

    public void setMultiChoice(boolean multiChoice) {
        this.multiChoice = multiChoice;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isNoQuestionContinue() {
        return noQuestionContinue;
    }

    public void setNoQuestionContinue(boolean noQuestionContinue) {
        this.noQuestionContinue = noQuestionContinue;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isRequered() {
        return isRequered;
    }

    public void setRequered(boolean requered) {
        isRequered = requered;
    }

    public boolean isSkipQuestion() {
        return skipQuestion;
    }

    public void setSkipQuestion(boolean skipQuestion) {
        this.skipQuestion = skipQuestion;
    }

    public List<Integer> getSelectedAnswersPosition(){
        List<Integer> answers=new ArrayList<>();
        if(this.answers!=null&&this.answers.size()>0){
            for (int i=0;i<this.answers.size();i++){
                if(this.answers.get(i).isSelected()){
                    answers.add(i);
                }

            }
            return answers;
        }
        return answers;
    }

//    public List<Surveys.ChoiceStruct_v1> getChoiceStruct_v1s() {
//        return choiceStruct_v1s;
//    }
//
//    public void setChoiceStruct_v1s(List<Surveys.ChoiceStruct_v1> choiceStruct_v1s) {
//        this.choiceStruct_v1s = choiceStruct_v1s;
//    }
}
