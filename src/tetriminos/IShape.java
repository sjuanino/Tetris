package tetriminos;

import java.awt.Color;
import tetris.TetrisBlock;

/**
 * 
 * @author Sonia Juanino Vega
 */
public class IShape extends TetrisBlock{

    public IShape() {
        super(new int[][] {
                            {1,1,1,1}}
        );
        this.setColor(Color.cyan);
    }
    
    @Override
    public void rotate() {
        super.rotate();
        
        if (this.getWidth() == 1) { // orientación vertical
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);            
        }
    }

}
