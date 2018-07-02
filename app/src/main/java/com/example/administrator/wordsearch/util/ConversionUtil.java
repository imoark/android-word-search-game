package com.example.administrator.wordsearch.util;

import android.graphics.Point;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 4/20/2018.
 */

public class ConversionUtil {
    /**
     * used to format the time a game took to play
     */
    final public static SimpleDateFormat formatTime = new SimpleDateFormat("mm:ss");
    /**
     * convert a gridId to a x y point
     *
     * @param id from grid TextView indicating which position in the grid it is
     * @return point equivalent of id parameter
     */
    public static Point convertIDToPoint(int id, int size) {
        Point point = new Point();
        point.x = id % size;
        point.y = id / size;
        return point;
    }
    /**
     * convert a x y point to a gridId
     *
     * @param point with x and y set to convert into an Id
     * @return Id equivalent of point parameter
     */
    public static int convertPointToID(Point point, int size) {
        return (point.x + point.y * size);
    }
}
