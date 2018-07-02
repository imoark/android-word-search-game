package com.example.administrator.wordsearch.model;

import android.graphics.Point;
import android.widget.TextView;

/**
 * Created by Administrator on 4/20/2018.
 *
 * this class stores the current selection in a grid of the word search
 *
 */

public 	class Selection {
    /**
     *
     * @param point any point
     * @param max a max value that the point's x or y can be
     * @return whether the point's x and y both fall between 0 and below the max
     */
    public static boolean isValidPoint(Point point, int max) {
        return (point.x >= 0 && point.x < max && point.y >= 0 && point.y < max);
    }
    /**
     *
     * @param pointStart a start point for a selection
     * @param pointEnd an end point for a selection
     * @return null if invalid  points or a new delta point that shows the x and y offsets that can be used to travel from start to end
     */
    public static Point getDeltas(Point pointStart, Point pointEnd) {
        if (pointEnd == null || pointStart == null) {
            return null;
        }
        Point delta = new Point();
        delta.x = pointEnd.x-pointStart.x;
        delta.y = pointEnd.y-pointStart.y;
        if (Math.abs(delta.x) != Math.abs(delta.y) && delta.x != 0 && delta.y != 0) {
            return null;
        }
        if (delta.x != 0) {
            delta.x /= Math.abs(delta.x);
        }
        if (delta.y != 0) {
            delta.y /= Math.abs(delta.y);
        }
        return delta;
    }
    /**
     *
     * @param one a point in the grid
     * @param two another point in the grid
     * @return the number of iterations required to use the getDeltas to travel from point one to point two
     */
    public static int getLength(Point one, Point two) {
        return new Double(Math.sqrt(Math.pow(two.x-one.x, 2)+Math.pow(two.y-one.y, 2))).intValue();
    }
    private TextView start = null;
    private TextView end = null;
    public TextView getEnd() {
        return end;
    }
    public TextView getStart() {
        return start;
    }
    public boolean hasBegun() {
        return (end != null && start != null);
    }
    public void reset() {
        start = null;
        end = null;
    }
    public void setEnd(TextView end) {
        this.end = end;
    }
    public void setStart(TextView start) {
        this.start = start;
        this.end = start;
    }
}

