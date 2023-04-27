package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class SShape extends TetrisBlock{
    public SShape() {
        super(new int[][]{  
                            {0, 1, 1},
                            {1, 1, 0},
        });
        this.setColor(Color.green);
    }
}
