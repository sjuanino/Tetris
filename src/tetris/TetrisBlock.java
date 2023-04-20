package tetris;

import java.awt.Color;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x;
    private int y;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }    
    
    /**
     * 
     * @return Devuelve la altura de la pieza de Tetris, es decir, el número de filas
     */
    public int getHeight() {
        return shape.length;
    }
    
    /**
     * 
     * @return Devuelve la anchura de la pieza de Tetris, es decir, el número de columnas
     */
    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
