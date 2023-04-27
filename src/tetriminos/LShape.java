package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class LShape extends TetrisBlock {
    public LShape() {
        super(new int[][]{  
                            {1, 0},
                            {1, 0},
                            {1, 1}
        });
        
        this.setColor(Color.orange);
    }
}
