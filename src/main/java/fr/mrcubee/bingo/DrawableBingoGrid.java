package fr.mrcubee.bingo;

import fr.mrcubee.bingo.image.Drawable;
import fr.mrcubee.bingo.item.BingoElement;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class DrawableBingoGrid extends BingoGrid implements Drawable {

    public static final int BORDER_SIZE = 5;
    public static final int ELEMENT_SIZE = 16;
    public static final Color BORDER_DEFAULT_COLOR = Color.BLACK;
    public static final Color BORDER_COMPLETE_COLOR = Color.RED;

    protected DrawableBingoGrid(int width, int height) {
        super(width, height);
    }

    public Drawable getDrawable(int x, int y) {
        final BingoElement bingoElement = getElement(x, y);

        if (bingoElement == null)
            return null;
        return bingoElement.getImageResource();
    }

    private void drawElement(Graphics graphics, int x, int y, int frame) {
        final Drawable drawable = getDrawable(x, y);

        if (drawable == null)
            return;
        graphics.drawImage(drawable.draw(frame), (x + BORDER_SIZE) * (BORDER_SIZE + ELEMENT_SIZE), (y * BORDER_SIZE) + (BORDER_SIZE + ELEMENT_SIZE), ELEMENT_SIZE, ELEMENT_SIZE, null);
    }

    private void drawElements(Graphics graphic, int frame) {
        for (int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++)
                drawElement(graphic, x, y, frame);
    }


    private void drawElementBorder(Graphics graphics, int width, int x, int y, boolean complete) {
        graphics.setColor(complete ? BORDER_COMPLETE_COLOR : BORDER_DEFAULT_COLOR);
        graphics.drawRect(x * (BORDER_SIZE + ELEMENT_SIZE), (y + BORDER_SIZE) * (BORDER_SIZE + ELEMENT_SIZE), BORDER_SIZE, ELEMENT_SIZE);
        graphics.drawRect((x + BORDER_SIZE + ELEMENT_SIZE) * (BORDER_SIZE + ELEMENT_SIZE), (y + BORDER_SIZE) * (BORDER_SIZE + ELEMENT_SIZE), BORDER_SIZE, ELEMENT_SIZE);
        graphics.drawRect(0, y * (BORDER_SIZE + ELEMENT_SIZE), width, BORDER_SIZE);
        graphics.drawRect(0, (y + BORDER_SIZE) * (BORDER_SIZE + ELEMENT_SIZE), width, BORDER_SIZE);
    }

    private void drawBorders(Graphics graphics, int width) {
        boolean[] completed = getCompleted();

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (!completed[x + (y * this.width)])
                    drawElementBorder(graphics, width, x, y, false);
            }
        }
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (!completed[x + (y * this.width)])
                    drawElementBorder(graphics, width, x, y, true);
            }
        }
    }

    @Override
    public BufferedImage draw(int frame) {
        final int width = ((ELEMENT_SIZE + BORDER_SIZE) * this.width) + BORDER_SIZE;
        final int height = ((ELEMENT_SIZE + BORDER_SIZE) * this.height) + BORDER_SIZE;
        final BufferedImage image = new BufferedImage(width, height, Image.SCALE_SMOOTH);
        final Graphics graphics = image.getGraphics();

        drawElements(graphics, frame);
        drawBorders(graphics, width);
        return image;
    }

    @Override
    public BingoGrid copy() {
        final DrawableBingoGrid bingoGrid = new DrawableBingoGrid(this.width, this.height);

        System.arraycopy(this.elements, 0, bingoGrid.elements, 0, this.elements.length);
        System.arraycopy(this.elementCompleted, 0, bingoGrid.elementCompleted, 0, this.elementCompleted.length);
        return bingoGrid;
    }
}
