package controller;


public class GameController {

    private int numberEnteredByUser;

    public boolean inputIsValidBetweenMinAndMax(String input, int min, int max){

        if (input == null){
            return false;
        }

        try {
            numberEnteredByUser =Integer.parseInt(input.trim());
        }catch (NumberFormatException e){
            return false;
        }

        if (numberEnteredByUser < min || numberEnteredByUser > max){
            return false;
        }

        return true;
    }
}
