package sudoku.puzzleRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class Repo {

    public static ArrayList<String> easy = new ArrayList<>();
    public static ArrayList<String> intermediate= new ArrayList<>();
    public static ArrayList<String> challenging= new ArrayList<>();
    public static ArrayList<String> tough= new ArrayList<>();
    public static ArrayList<String> superTough= new ArrayList<>();
    public static ArrayList<String> insane= new ArrayList<>();

    public static void repoInit(){
        Scanner scEasy = new Scanner(Repo.class.getResourceAsStream("../resources/0.txt"));
        Scanner scIntermediate = new Scanner(Repo.class.getResourceAsStream("../resources/1.txt"));
        Scanner scChallenging = new Scanner(Repo.class.getResourceAsStream("../resources/2.txt"));
        Scanner scTough = new Scanner(Repo.class.getResourceAsStream("../resources/3.txt"));
        Scanner scSuperTough = new Scanner(Repo.class.getResourceAsStream("../resources/4.txt"));
        Scanner scInsane = new Scanner(Repo.class.getResourceAsStream("../resources/5.txt"));
        while(scEasy.hasNext()){
            easy.add(scEasy.nextLine());
            intermediate.add(scIntermediate.nextLine());
            challenging.add(scChallenging.nextLine());
            tough.add(scTough.nextLine());
            superTough.add(scSuperTough.nextLine());
            insane.add(scInsane.nextLine());
        }
    }

}
