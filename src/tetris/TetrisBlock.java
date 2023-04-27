package tetris;

import java.awt.Color;

public class TetrisBlock {
    /*
     Las piezas del Tetris se denominan tetriminos y estan compuestos
     cuatro cuadrados. Cada uno de esos cuadrados tiene una coordenada x
     y una coordenada y, que determinan donde estan situados.
     Por ejemplo, este tetrimino tiene forma de L:
    
     +---+---+
     | 1 | 0 |
     +---+---+
     | 1 | 0 |
     +---+---+
     | 1 | 1 |
     +---+---+
    
     Esta información se guarda dentro del array shape.
     Los ceros indican las celdas no coloreadas,
     mientras que los 1 son las celdas coloreadas
     */
    private int[][] shape; // La forma del tetrimino
    private Color color; // Color del tetrimino
    private int x; // Posición x en la rejilla de juego 
    private int y; // Posición y en la rejilla de juego
    
    /*
     El array shapes contendra todas las formas que adopta una pieza
     cuando gira, que dependiendo del tetrimino pueden ser cuatro rotaciones-
    */
    private int[][][] shapes;
    private int currentRotation;
    
    /**
     * Constructor de la clase
     */
    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        this.initShapes();
    }
    
    //========================================================================//
    // FORMA Y COLOR DE UN tetrimino                                          //
    //========================================================================//
    
    /**
     * 
     * @return Devuelve un array de dos dimensiones en el que mediante 1 y 0,
     * se determina la forma del tetrimino
     */
    public int[][] getShape() {
        return shape;
    }
    
    /**
     * Acepta y asigna la forma del tetrimino
     * @param shape La forma del tetrimino
     */
    public void setShape(int[][] shape) {
        this.shape = shape;
    }
    
    /**
     * 
     * @return Devuelve el color del tetrimino 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Acepta y asigna el color del tetrimino
     * @param color El color del tetrimino
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
    
    /** 
     * El método crea un array de 3 dimensiones, cuya primera dimensión es 4, por
     * las cuatro posiciones que puede tener una pieza del Tetris cuando rota, es decir,
     * a 0º, 90º, 270º y 360º.
     * Se utiliza un for para llenar las otras dos dimensiones del array, de forma, 
     * que en cada una, las columnas y filas varien en funcion de la forma de la 
     * pieza al girar:
     * +---+---+	+---+---+---+	     +---+---+	      +---+---+---+
     * | 1 | 0 |	| 1 | 1 | 1 |	     | 1 | 1 |	      | 0 | 0 | 1 |
     * +---+---+   >>	+---+---+---+   >>   +---+---+   >>   +---+---+---+
     * | 1 | 0 |	| 1 | 0 | 0 |	     | 0 | 1 |	      | 1 | 1 | 1 |
     * +---+---+	+---+---+---+	     +---+---+	      +---+---+---+
     * | 1 | 1 |                             | 0 | 1 |		
     * +---+---+                             +---+---+		
     * 
     * Shapes se va llenando mediante las coordenadas de los numeros, es decir,
     * +---------+---------+		+---------+---------+---------+	
     * | 0,0 (1) | 0,1 (0) |		| 0,0 (1) | 0,1 (1) | 0,2 (1) |	
     * +---------+---------+		|  [2,0]  |  [1,0]  |  [0,0]  |	
     * | 1,0 (1) | 1,1 (0) |	>>	+---------+---------+---------+	
     * +---------+---------+		| 1,0 (1) | 1,1 (0) | 1,2 (2) |	
     * | 2,0 (1) | 2,1 (1) |		|  [2,1]  |  [1,1]  |  [0,1]  |	
     * +---------+---------+		+---------+---------+---------+	
     * 
     * Las coodenadas en la primera tabla, pasan a la segunda tabla entre []
     * y el valor que tenian las celdas pasan de una tabla a otra entre parentesis
     */
    private void initShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) { // El for se repite 4 veces, las formas que adopta la pieza al girar
            int rows = shape[0].length;
            int cols = shape.length;
            shapes[i] = new int[rows][cols];
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    shapes[i][j][k] = shape[cols - k - 1][j];
                    
                }
            }
            shape = shapes[i];
        }
    }

    //========================================================================//
    // POSICION Y MOVIMIENTO DE UN tetrimino                                  //
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
     * El método devuelve la posición x de la pieza del Tetris, de acuerdo,
     * a la rejilla del juego, es decir, si la pieza estuviera situada en,
    .-----.-----.-----.-----.-----.
    | x,y | x,y | x,y | x,y | x,y |
    :-----+-----+-----+-----+-----:
    | x,y | 0,0 | 1,0 | 2,0 | 3,0 |
    |     |     |  1  |  0  |     | 
    :-----+-----+-----+-----+-----:
    | x,y | 0,1 | 1,2 | 2,1 | 3,1 |
    |     |     |  1  |  0  |     | 
    :-----+-----+-----+-----+-----:
    | x,y | 0,2 | 1,3 | 2,2 | 3,2 |
    |     |     |  1  |  1  |     | 
    :-----+-----+-----+-----+-----:
    | x,y | 0,3 | 1,4 | 2,3 | 3,3 |
    '-----'-----'-----'-----'-----'
     * El metodo getX() devolvería 1, y el metodo getY() devolvería 0     * 
     * @return Devuelve la posicion x del tetrimino
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @return Devuelve la posicion y del tetrimino
     */
    public int getY() {
        return y;
    }    
    
    public void setY(int y) {
        this.y = y;
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
    
    public void rotate() {
        this.currentRotation++;
        if (this.currentRotation>3)
            this.currentRotation = 0;
        shape = shapes[this.currentRotation];
    }
    
    /**
     * Suma las filas que ha recorrido el tetrimino desde que empieza a caer,
     * la posición x del mismo y su altura, esta cifra se usara para compararla
     * con el número de filas que tiene la rejilla de juego
     * @return La altura del tetrimino y la posición en la que se encuentra
     */
    public int getBottomEdge() {
        return y + this.getHeight();
    }
    
    public int getLeftEdge() {
        return x;
    }
    
    public int getRightEdge() {
        return x + this.getWidth();
    }
    
    /**
     * Coloca la pieza por encima de la rejilla de juego y en mitad de la misma.
     * Para colocar el tetrimino en la parte media de la rejilla de juego,
     * al número de columnas de la rejilla se le resta el número de columnas
     * del tetrimino y se divide entre 2, de forma que el valor obtenido
     * se asigne a la variable x.
     * Dado que la pieza del tetris tiene que estar por encima de 
     * @param gridWidth El número de columnas de la rejilla de juego
     */
    public void spawn(int gridWidth) {
        //Random random = new Random();
        /*
         Esto hace que las piezas del Tetris tengan una rotación distinta
         cada vez que aparecen en pantalla. El shape.length es por las
         formas que adopta al girar sobre si misma.
        */
        this.currentRotation = 0;//random.nextInt(shape.length);
        this.shape = shapes[this.currentRotation];
        this.x = (gridWidth - this.getWidth()) / 2;
        this.y = -this.getHeight();
    }
}