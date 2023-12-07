package sudoku.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameTimer {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date time;

    static void init(){
        time = new Date(0, 0, 0, 0 , 0, 0);
    }

    static String getTime(){
        return dateFormat.format(time.getTime());
    }
    static void updateTime(){
        time.setTime(time.getTime()+1000);
    }
}
