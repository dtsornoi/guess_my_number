package gui;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.RandomNumberGenerator;

import java.net.URL;
import java.util.ResourceBundle;

public class GuessMyNumberController implements Initializable {

    private static int score;
    private final int POINTS_ADDED_TO_SCORE = 1;
    private static int guesses;
    private int randomNumber;
    private static int previousRandomNumber;
    private final int MIN_RANDOM_NUMBER = 1;
    private final int MAX_RANDOM_NUMBER = 100;
    private final int DEFAULT_VALUE = 0;

    private GameController gameController;

    private Stage stage;
    private Scene scene;
    private Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML
    private Label displayScore;

    @FXML
    private Label displayGuesses;

    @FXML
    private Label numberGenerated;

    @FXML
    private TextField playerInput;

    public GuessMyNumberController(){
        randomNumber = RandomNumberGenerator.randomNumberBetweenMinAndMax(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER);
        gameController = new GameController();
    }

    @FXML
    public void guess(ActionEvent event){

        if (!gameController.inputIsValidBetweenMinAndMax(playerInput.getText(), MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER)){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Please enter numbers between " + MIN_RANDOM_NUMBER + " and " + MAX_RANDOM_NUMBER);
            alert.show();
        }

        if (!alert.isShowing()) {
            if (Integer.toString(randomNumber).equals(playerInput.getText())) score = score + POINTS_ADDED_TO_SCORE;
            previousRandomNumber = randomNumber;
            guesses++;
            changeScreen(event);
        }
    }

    @FXML
    public void playAgain(ActionEvent event){
        score = DEFAULT_VALUE;
        guesses = DEFAULT_VALUE;

        changeScreen(event);
    }

    private void changeScreen(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
            scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("gui/guessMyNumber.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void giveUp(ActionEvent event){
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (guesses == DEFAULT_VALUE){
            numberGenerated.setText(Integer.toString(DEFAULT_VALUE));
        }else {
            numberGenerated.setText(Integer.toString(previousRandomNumber));
        }

        displayGuesses.setText(Integer.toString(guesses));
        displayScore.setText(Integer.toString(score));
    }
}
