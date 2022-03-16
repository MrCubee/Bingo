package fr.mrcubee.bingo.image;

import java.awt.image.BufferedImage;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public interface Drawable {

    public BufferedImage draw(int frame);

    default public BufferedImage draw() {
        return draw((int) (System.currentTimeMillis() / 1000));
    }

}
