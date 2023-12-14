/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
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
        Scanner scEasy = new Scanner(Repo.class.getResourceAsStream("../resources/easy.txt"));
        Scanner scIntermediate = new Scanner(Repo.class.getResourceAsStream("../resources/intermediate.txt"));
        Scanner scChallenging = new Scanner(Repo.class.getResourceAsStream("../resources/challenging.txt"));
        Scanner scTough = new Scanner(Repo.class.getResourceAsStream("../resources/tough.txt"));
        Scanner scSuperTough = new Scanner(Repo.class.getResourceAsStream("../resources/supertough.txt"));
        Scanner scInsane = new Scanner(Repo.class.getResourceAsStream("../resources/insane.txt"));
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
