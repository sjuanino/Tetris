package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class OShape extends TetrisBlock{
    public OShape() {
        super(new int[][]{  
                            {1, 1},
                            {1, 1},
        });
        this.setColor(Color.yellow);
    }
}
