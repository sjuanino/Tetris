package tetris;

import java.awt.Color;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class TetrisBlock {
    /*
     Las piezas del Tetris se denominan tetrominos y estan compuestos
     cuatro cuadrados. Cada uno de esos cuadrados tiene una coordenada x
     y una coordenada y, que determinan donde estan situados.
     Por ejemplo, este tetrimino tiene forma de L:
    
     1 0
     1 0
     1 1
    
     Esta información se guarda dentro del array shape.
     Los ceros indican las celdas no coloreadas,
     mientras que los 1 son las celdas coloreadas
     */
    private int[][] shape; // La forma del tetromino
    private Color color; // Color del tetromino
    private int x; // Posición x en la rejilla de juego 
    private int y; // Posición y en la rejilla de juego

    /**
     * Constructor de la clase
     */
    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
    
    //========================================================================//
    // FORMA Y COLOR DE UN TETROMINO                                          //
    //========================================================================//
    
    /**
     * 
     * @return Devuelve un array de dos dimensiones en el que mediante 1 y 0,
     * se determina la forma del tetromino
     */
    public int[][] getShape() {
        return shape;
    }
    
    /**
     * Acepta y asigna la forma del tetromino
     * @param shape La forma del tetromino
     */
    public void setShape(int[][] shape) {
        this.shape = shape;
    }
    
    /**
     * 
     * @return Devuelve el color del tetromino 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Acepta y asigna el color del tetromino
     * @param color El color del tetromino
     */
    public void setColor(Color color) {
        this.color = color;
    }    
    
    /**
     * 
     * @return Devuelve la altura de la pieza de Tetris,
     * es decir, el número de filas
     */
    public int getHeight() {
        return shape.length;
    }
    
    /**
     * 
     * @return Devuelve la anchura de la pieza de Tetris,
     * es decir, el número de columnas
     */
    public int getWidth() {
        return this.shape[0].length;
    }

    //========================================================================//
    // POSICION Y MOVIMIENTO DE UN TETROMINO                                  //
    //========================================================================//
    
    /*
    La rejilla en la que se va a desarrollar el juego, empieza en la parte superior
    izquierda con x=0 e y=0.    
    .-----.-----.-----.-----.-----.
    | x,y | x,y | x,y | x,y | x,y |
    :-----+-----+-----+-----+-----:
    | x,y | 0,0 | 1,0 | 2,0 | 3,0 |
    :-----+-----+-----+-----+-----:
    | x,y | 0,1 | 1,2 | 2,1 | 3,1 |
    :-----+-----+-----+-----+-----:
    | x,y | 0,2 | 1,3 | 2,2 | 3,2 |
    :-----+-----+-----+-----+-----:
    | x,y | 0,3 | 1,4 | 2,3 | 3,3 |
    '-----'-----'-----'-----'-----'
    */
    
    /**
     * 
     * @return Devuelve la posicion x del tetromino
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * @return Devuelve la posicion y del tetromino
     */
    public int getY() {
        return y;
    }
    
    /**
     * Mueve hacia abajo una pieza del Tetris aumentando el valor de la variable y
     */
    public void moveDown(){
        this.y++;
    }
    
    /**
     * Mueve hacia la izquierda una pieza del Tetris decrementando el valor de la variable x
     */
    public void moveLeft() {
        this.x--;
    }
    
    /**
     * Mueve hacia la derecha una pieza del Tetris incrementado el valor de la variable x
     */
    public void moveRight() {
        this.x++;
    }
    
    /**
     * Suma las filas que ha recorrido el tetromino desde que empieza a caer,
     * la posición x del mismo y su altura, esta cifra se usara para compararla
     * con el número de filas que tiene la rejilla de juego
     * @return La altura del tetromino y la posición en la que se encuentra
     */
    public int getBottomEdge() {
        return y + this.getHeight();
    }
    
    /**
     * Coloca la pieza por encima de la rejilla de juego y en mitad de la misma.
     * Para colocar el tetromino en la parte media de la rejilla de juego,
     * al número de columnas de la rejilla se le resta el número de columnas
     * del tetromino y se divide entre 2, de forma que el valor obtenido
     * se asigne a la variable x.
     * Dado que la pieza del tetris tiene que estar por encima de 
     * @param gridWidth El número de columnas de la rejilla de juego
     */
    public void spawn(int gridWidth) {
        this.x = (gridWidth - this.getWidth())/2;
        this.y = -this.getHeight();
    }
}
