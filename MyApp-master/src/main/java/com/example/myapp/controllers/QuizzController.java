package com.example.myapp.controllers;

import com.example.myapp.models.Quizz;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuizzController implements Initializable {
    @FXML
    Label question;
    @FXML
    Button option1;
    @FXML Button option2;
    @FXML Button option3;
    @FXML Button option4;
    @FXML Button submit;
    @FXML Button next;
    @FXML public Button back;
    @FXML public Label myScoreLabel;
    @FXML Label smallLabel;
    List<Quizz> questionList = new ArrayList<>();
    List<Quizz> randomQuestion = new ArrayList<>();
    public int currentQuestionIndex = 0;
    public int myScore = 0;
    Quizz currentQuestion;
    public int selectedAnswer = 0;
    Button selectedAnswerButton;
    Button correctAnswerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getDataFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void setQuizz() {
        setDefaultStyle();
        myScoreLabel.setText("SCORE: " + myScore);
        smallLabel.setText(currentQuestionIndex + " / " + "10");
        currentQuestion = randomQuestion.get(currentQuestionIndex);
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);
        submit.setDisable(true);
        next.setDisable(true);
        setDefaultStyle();
        question.setText(currentQuestion.getQuetion());
        option1.setText(currentQuestion.getOptionOne());
        option2.setText(currentQuestion.getOptionTwo());
        option3.setText(currentQuestion.getOptionThree());
        option4.setText(currentQuestion.getOptionFour());
        switch (currentQuestion.getCorrectAnswer()) {
            case 1 -> correctAnswerButton = option1;
            case 2 -> correctAnswerButton = option2;
            case 3 -> correctAnswerButton = option3;
            case 4 -> correctAnswerButton = option4;
        }
    }

    @FXML
    public void clickedOptionOne() {
        selectedAnswer = 1;
        selectedAnswerButton = option1;
        submit.setDisable(false);
        setDefaultStyle();
        // option1.setStyle("-fx-background-color: #00FFFF;");
        option1.getStyleClass().clear();
        option1.getStyleClass().add("selected-style");
    }

    @FXML
    public void clickedOptionTwo() {
        selectedAnswer = 2;
        selectedAnswerButton = option2;
        submit.setDisable(false);
        setDefaultStyle();
        option2.getStyleClass().clear();
        option2.getStyleClass().add("selected-style");
        // option2.setStyle("-fx-background-color: #00FFFF;");
    }

    @FXML
    public void clickedOptionThree() {
        selectedAnswer = 3;
        selectedAnswerButton = option3;
        submit.setDisable(false);
        setDefaultStyle();
        option3.getStyleClass().clear();
        option3.getStyleClass().add("selected-style");
    }

    @FXML
    public void clickedOptionFour() {
        selectedAnswer = 4;
        selectedAnswerButton = option4;
        submit.setDisable(false);
        setDefaultStyle();
        option4.getStyleClass().clear();
        option4.getStyleClass().add("selected-style");
    }

    @FXML
    public void clickedSubmitButton() {
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);
        next.setDisable(false);
        submit.setDisable(true);
        if (checkAnswer()) {
            myScore++;
            setCorrectStyle();
        } else {
            setWrongStyle();
        }
    }

    @FXML
    public void clickedNextButton() {
        currentQuestionIndex++;
        if (currentQuestionIndex > 10) {
            System.out.println("Finished");
            resultDialog();
        } else {
            setQuizz();
        }
    }

    public boolean checkAnswer() {
        return selectedAnswer == currentQuestion.getCorrectAnswer();
    }

    public void setDefaultStyle() {
        option1.getStyleClass().clear();
        option1.getStyleClass().add("answer-button");
        option2.getStyleClass().clear();
        option2.getStyleClass().add("answer-button");
        option3.getStyleClass().clear();
        option3.getStyleClass().add("answer-button");
        option4.getStyleClass().clear();
        option4.getStyleClass().add("answer-button");
    }

    public void setWrongStyle() {
        selectedAnswerButton.getStyleClass().clear();
        selectedAnswerButton.getStyleClass().add("wrong-style");
        correctAnswerButton.getStyleClass().clear();
        correctAnswerButton.getStyleClass().add("correct-style");
    }

    public void setCorrectStyle() {
        correctAnswerButton.getStyleClass().clear();
        correctAnswerButton.getStyleClass().add("correct-style");
    }

    public void getDataFromFile() throws IOException {
        Pane animationPane =
                FXMLLoader.load(
                        Objects.requireNonNull(
                                getClass().getResource("/com/example/myapp/MainFrm.fxml")));

                new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        File quizDataFile =
                                new File(
                                        "C:\\Program Files\\Java\\MyApp-master\\src\\main\\resources\\com\\example\\myapp\\QuizQuestion\\txt");
                        Scanner sc = new Scanner(quizDataFile);
                        int id = 0;
                        while (sc.hasNextLine()) {
                            Quizz newQuiz =
                                    new Quizz(
                                            id,
                                            sc.nextLine(),
                                            sc.nextLine(),
                                            sc.nextLine(),
                                            sc.nextLine(),
                                            sc.nextLine(),
                                            Integer.parseInt(sc.nextLine().replace(" ", "")));
                            questionList.add(newQuiz);
                            id++;
                        }
                        return null;
                    }

                    protected void succeeded() {

                        Collections.shuffle(questionList);
                        randomQuestion = questionList.subList(0, 11);
                        setQuizz();
                    }
                };
        Runnable task = null;
        new Thread(task).start();
    }

    public <JFXDialogLayout, JFXButton> void resultDialog() {
        JFXDialogLayout content = null;
        content.toString();
        content.notify();
        String save = "Save";
        String save1 = "Save";
        int saveButton = Integer.parseInt(("Save"));
        int saveButton1 = saveButton;
        content.getClass();

    }

    private static Object closeDialog() {
        return null;
    }
}
