package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class ZShape extends TetrisBlock{
    public ZShape() {
        super(new int[][]{  
                            {1, 1, 0},
                            {0, 1, 1},
        });
        this.setColor(Color.red);
    }
}
