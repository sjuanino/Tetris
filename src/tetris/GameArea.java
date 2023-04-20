package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class GameArea extends JPanel {
    
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    
    /**
     * Este tetrimino es el siguiente:
     * 1 0
     * 1 0
     * 1 1
     * Tiene forma de L, y los ceros indican las celdas no coloreadas,
     * mientras que los 1 son las celdas coloreadas
     */
    private TetrisBlock block;
    
    
    public GameArea(JPanel placeHolder, int columns) {
        placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds());
        this.setBackground(placeHolder.getBackground());
        this.setBorder(placeHolder.getBorder());
        gridColumns = columns;
        gridCellSize = this.getBounds().width /gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        
        spawnBlock();
    }
    
    private void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1,0}, {1,0}, {1,1}}, Color.blue);
    }
    private void drawBlock(Graphics g) {
        for (int row = 0; row < block.getHeight(); row++) {
            for(int col = 0; col < block.getWidth(); col++) {
                if(block.getShape()[row][col] == 1) {
                    
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * this.gridCellSize;
                    g.setColor(block.getColor());
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < this.gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawBlock(g);
    }
}

