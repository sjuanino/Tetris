package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 * @author Sonia Juanino Vega
 */
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
       
    public GameArea(JPanel placeHolder, int columns) {
        placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds());
        this.setBackground(placeHolder.getBackground());
        this.setBorder(placeHolder.getBorder());
        this.gridColumns = columns;
        this.gridCellSize = this.getBounds().width /gridColumns;
        this.gridRows = this.getBounds().height / gridCellSize;
        background = new Color[this.gridRows][this.gridColumns];
    }
    
    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1,0}, {1,0}, {1,1}}, Color.blue);
        block.spawn(this.gridColumns);
    }
    
    /**
     * Se recorre la cuadrícula que forma el tetromino utilizando dos bucles for,
     * de forma que la información sobre la pieza pase al array background.
     * En el array background, se va a guardar en que posición x e y esta cada uno 
     * de los cuadrados que forman la pieza, si tiene color o no (0 ó 1)
     * y cuál es ese color.
     */
    private void moveBlockToBackground() {
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    background[row + block.getY()][col + block.getX()] = block.getColor();
                }

            }
        }
    }
            
    private void drawBlock(Graphics g) {
        for (int row = 0; row < block.getHeight(); row++) {
            for(int col = 0; col < block.getWidth(); col++) {
                if(block.getShape()[row][col] == 1) { // Si el cuadrado tiene color (1)
                    int x = (block.getX() + col) * this.gridCellSize;
                    int y = (block.getY() + row) * this.gridCellSize;
                    drawGridSquare(block.getColor(), g, x, y);
                }
            }
        }
    }
    
    public boolean moveBlockDown() {
        if (this.checkBottom() == false) {
            moveBlockToBackground();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    
    /**
     * Para saber si un tetromino ha llegado abajo se tiene que comprobar que la 
     * altura de la pieza mas la distancia que lleva recorrida desde que empezo a 
     * bajar equivalen a la altura de la rejilla.
     * Es decir, si el tetromino ha recorrido 12 filas y tiene 3 de altura, son 15 filas.
     * Por otro lado, si la rejilla son 15 filas, quiere decir que la pieza al haber
     * recorrido 12 filas y tener 3 de alto estaría situada en la parte inferior
     * de la rejilla
     * @return Devuelve true si la pieza a llegado a la parte inferior de la rejilla,
     * y false si no ha sido así
     */
    private boolean checkBottom() {        
        if (block.getBottomEdge() == this.gridRows) {
            return false;
        }
        return true;
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
    
    private void drawGridSquare(Color color, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, this.gridCellSize, this.gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, this.gridCellSize, this.gridCellSize);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < this.gridRows; y++) {
            for (int x = 0; x < this.gridColumns; x++) {
                g.drawRect(x * this.gridCellSize, y * this.gridCellSize, this.gridCellSize, this.gridCellSize);
            }
        }
        drawBackground(g);
        drawBlock(g);
    }
}

