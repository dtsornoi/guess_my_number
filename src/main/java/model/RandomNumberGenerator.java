package model;

public class RandomNumberGenerator {

    public static int randomNumberBetweenMinAndMax(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
