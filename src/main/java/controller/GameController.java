package controller;


public class GameController {

    public boolean inputIsValidBetweenMinAndMax(String input, int min, int max){
        int numberEnteredByUser = Integer.parseInt(input.trim());
        if (numberEnteredByUser < min || numberEnteredByUser > max){
            return false;
        }

        return true;
    }
}
