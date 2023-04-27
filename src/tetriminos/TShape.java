package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class TShape  extends TetrisBlock{
    public TShape() {
        super(new int[][]{  
                            {1, 1, 1},
                            {0, 1, 0}
        });
        this.setColor(Color.magenta);
    }

}
