package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class JShape extends TetrisBlock {
    
    public JShape() {
        super(new int[][]{  
                            {0, 1},
                            {0, 1},
                            {1, 1}
        });
        this.setColor(Color.blue);        
    }
}
