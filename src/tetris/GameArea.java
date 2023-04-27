package tetris;

import tetriminos.ZShape;
import tetriminos.IShape;
import tetriminos.LShape;
import tetriminos.TShape;
import tetriminos.SShape;
import tetriminos.OShape;
import tetriminos.JShape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private final int gridRows;
    private final int gridColumns;
    private final int gridCellSize;
    
    /**
     * Array que contiene los colores de los bloques que han
     * llegado a la parte inferior de la rejilla, de forma que se puedan
     * eliminar cuando se haga una linea
     */
    private Color[][] background;
    
    private TetrisBlock block;
    private TetrisBlock[] blocks;
       
    public GameArea(JPanel placeHolder, int columns) {
        //placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds());
        this.setBackground(placeHolder.getBackground());
        this.setBorder(placeHolder.getBorder());
        this.gridColumns = columns;
        this.gridCellSize = this.getBounds().width /gridColumns;
        this.gridRows = this.getBounds().height / gridCellSize;
        
        blocks = new TetrisBlock[] {    new IShape(),
                                        new JShape(),
                                        new LShape(),
                                        new OShape(),
                                        new SShape(),
                                        new TShape(),
                                        new ZShape() };        
    }
    
    public void initBackground() {
        background = new Color[this.gridRows][this.gridColumns];
    }
    
    public void spawnBlock() {
        Random random = new Random();        
        block = blocks[random.nextInt(blocks.length)];
        block.spawn(this.gridColumns);
    }
            
    //========================================================================//
    // MOVIMIENTO DE LOS BLOQUES                                              //
    //========================================================================//
    
    /**
     * Para saber si un tetrimino ha llegado abajo se tiene que comprobar que la 
     * altura de la pieza mas la distancia que lleva recorrida desde que empezo a 
     * bajar equivalen a la altura de la rejilla.
     * Es decir, si el tetrimino ha recorrido 12 filas y tiene 3 de altura, son 15 filas.
     * Por otro lado, si la rejilla son 15 filas, quiere decir que la pieza al haber
     * recorrido 12 filas y tener 3 de alto estaría situada en la parte inferior
     * de la rejilla
     * @return Devuelve true si la pieza a llegado a la parte inferior de la rejilla,
     * o ha encontrado mas piezas en su descenso y false si no ha sido así
     */
    private boolean checkBottom() {
        if (block.getBottomEdge() == this.gridRows) {
            return false;
        }
        /*
         Se va a recorrer los cuadrados que forman el tetrimino primero por
         columnas de (izquierda a derecha) y despues por filas (de abajo a arriba).
         Como se quiere comprobar el fondo del tetrimino habra que empezar a 
         comprobar las filas por la parte inferior de la pieza
        */
        for (int col = 0; col < block.getWidth(); col++) {
            for (int row = block.getHeight() - 1; row >= 0; row--) {
                /*
                 Se busca dentro de la pieza, empezando por la izquierda, un cuadrado
                 que tenga valor 1 (que sea distinto de 0), cuando se encuentra,
                 se mira el cuadrado que esta a su izquierda.
                */
                if (block.getShape()[row][col] != 0) {
                    // Se buscan las coordenadas del cuadrado que esta debajo
                    // del cuadrado de la pieza del tetris que tiene como valor un 1
                    // Hay que tener en cuenta que el cuadrado con valor 1 se ha empezado
                    // a buscar desde la parte inferior del tetrimino
                    // Sin embargo, los valores de block.getX() y block.getY(),
                    // se refieren a las coordenadas de la pieza empezando por la 
                    // esquina superior derecha del mismo                
                    int xPos = col + block.getX();
                    int yPos = row + block.getY() + 1;
                    // Si xPos es negativa, es que el tetrimino no ha aparecido plenamente
                    // en pantalla, ya que al empezar, la pieza siempre esta por encima de
                    // la rejilla de juego, y por tanto su xPos es un número negativo, que se
                    // va incrementando a medida que el tetrimino baja hacia abajo. Por ello,
                    // se rompe el bucle.
                    if (yPos < 0) break;
                    // Si hay algo debajo del tetrimino, se busca en el background, 
                    // en esa posición y se devuelve false
                    if (background[yPos][xPos] != null) return false;
                    // Si no hay nada debajo de la pieza se hace un break
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkRight() {
        if (block.getRightEdge() == this.gridColumns) {
            return false;
        }
        
       /*
         Se va a recorrer los cuadrados que forman el tetrimino primero por
         filas (de arriba a abajo) y despues por las columnas (de derecha a izquierda).
         Ya que se quiere comprobar la parte derecha de la pieza
        */
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = block.getWidth() - 1; col >= 0; col--) {
                /*
                 Se busca dentro de la pieza, empezando por la derecha, un cuadrado
                 que tenga valor 1 (que sea distinto de 0), cuando se encuentra,
                 se mira el cuadrado que esta a su derecha.
                */
                if (block.getShape()[row][col] != 0) {       
                    int yPos = col + block.getX() + 1;
                    int xPos = row + block.getY();
                    if (xPos < 0) break;
                    if (this.background[xPos][yPos] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        }
       /*
         Se va a recorrer los cuadrados que forman el tetrimino primero por
         filas (de arriba a abajo)  y después por columnas (de izquierda a derecha).
         Ya que se quiere comprobar la parte izquierda de la pieza del Tetris
        */
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = block.getWidth(); col < 0; col++) {
                /*
                 Se busca dentro de la pieza, empezando por la izquierda, un cuadrado
                 que tenga valor 1 (que sea distinto de 0), cuando se encuentra,
                 se mira el cuadrado que esta a su izquierda.
                */
                if (block.getShape()[row][col] != 0) {             
                    int xPos = col + block.getX() - 1;
                    int yPos = row + block.getY();
                    if (xPos < 0) break;
                    if (background[yPos][xPos] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }
    
    public boolean moveBlockDown() {
        if (this.checkBottom() == false) {
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    
    public void moveBlockRight() {
        if (block == null) return;
        if (!this.checkRight()) return;
        block.moveRight();
        repaint();
    } 
        
    public void moveBlockLeft() {
        if (block == null) return;
        if (!this.checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    
    public void dropBlock() {
        if (block == null) return;
        while(this.checkBottom()) {
            block.moveDown();
        }        
        repaint();
    }
    
    public void rotateBlock() {
        if (block == null) {
            return;
        }
        block.rotate();
        
        if (block.getLeftEdge() < 0) {
            block.setX(0);
        }
        if (block.getRightEdge() >= this.gridColumns) {
            block.setX(this.gridColumns - block.getWidth());
        }
        if (block.getBottomEdge() >= this.gridRows) {
            block.setY(this.gridRows - block.getHeight());
        }
        repaint();
    }

    //========================================================================//
    // ELIMINAR LINEAS COMPLETAS                                              //
    //========================================================================//
    
    /**
     * Se va a recorrer el array en el que se acumulan las piezas una vez que 
     * han llegado al final de la rejilla o se han encajado con otras piezas.
     * Para recorrerlo, se va a empezar por las filas, desde abajo a arriba (en
     * la parte inferior de la rejilla es donde se han de intentar hacer las
     * lineas completas) y las columnas de izquierda a derecha.
     */
     public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;
        for (int row = this.gridRows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < this.gridColumns; col++) {
                /* Hay que comprobar que todos los cuadrados de una linea son 
                 distintos a null.
                 */
                if (background[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                linesCleared++;
                this.clearLine(row);
                this.shiftDown(row);
                this.clearLine(0);
                row++;
                repaint();
            }
        }
        if (linesCleared > 0) {
            Tetris.playClear();
        }
        return linesCleared;
    }
    
    private void clearLine(int r) {
        for (int i = 0; i < this.gridColumns; i++) {
            this.background[r][i] = null;
        }
    }

    private void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < this.gridColumns; col++) {
                this.background[row][col] = this.background[row - 1][col];
            }
        }
    }

    //========================================================================//
    // DIBUJAR COMPONENTES                                                    //
    //========================================================================//
    
    private void drawBlock(Graphics g) {
        //System.out.println("block altura: " + block.getHeight() + " ancho: " + block.getWidth() + block.getColor());
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) { // Si el cuadrado tiene color (1)
                    int x = (block.getX() + col) * this.gridCellSize;
                    int y = (block.getY() + row) * this.gridCellSize;
                    drawGridSquare(block.getColor(), g, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Color color, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, this.gridCellSize, this.gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, this.gridCellSize, this.gridCellSize);
    }
    
    private void drawGrid(Graphics g) {
        for (int r = 0; r < this.gridRows; r++) {
            for (int c = 0; c < this.gridColumns; c++) {
                g.drawRect(c * this.gridCellSize, r * this.gridCellSize, this.gridCellSize, this.gridCellSize);
            }
        }
    }
    
    private void drawBackground(Graphics g) {
        Color color;
        for (int row = 0; row < this.gridRows; row++) {
            for (int col = 0; col < this.gridColumns; col++) {
                color = background[row][col];
                if (color != null) {
                    int x = col * this.gridCellSize;
                    int y = row * this.gridCellSize;
                    drawGridSquare(color, g, x, y);
                }
            }
        }
    }
        
    /**
     * Se recorre la cuadrícula que forma el tetrimino utilizando dos bucles for,
     * de forma que la información sobre la pieza pase al array background.
     * En el array background, se va a guardar en que posición x e y esta cada uno 
     * de los cuadrados que forman la pieza, si tiene color o no (0 ó 1)
     * y cuál es ese color.
     */
    public void moveBlockToBackground() {
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    background[row + block.getY()][col + block.getX()] = block.getColor();
                }

            }
        }
    }
    
    //========================================================================//
    // PINTAR LOS COMPONENTES                                                    //
    //========================================================================//
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawGrid(g);
        this.drawBackground(g);
        if (block != null)
            this.drawBlock(g);
    }    

}

