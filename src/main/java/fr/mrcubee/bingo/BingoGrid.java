package fr.mrcubee.bingo;

import fr.mrcubee.bingo.item.BingoElement;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class BingoGrid {

    protected final int width;
    protected final int height;
    protected final BingoElement[] elements;
    protected final boolean[] elementCompleted;

    protected BingoGrid(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.elements = new BingoElement[this.width * this.height];
        this.elementCompleted = new boolean[this.width * this.height];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public BingoElement[] getElements() {
        return Arrays.copyOf(this.elements, this.elements.length);
    }

    public boolean[] getCompleted() {
        return Arrays.copyOf(this.elementCompleted, this.elementCompleted.length);
    }

    public BingoElement getElement(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height)
            return null;
        return this.elements[x + (y * this.width)];
    }

    public Material getMaterial(int x, int y) {
        final BingoElement bingoElement = getElement(x, y);

        if (bingoElement == null)
            return null;
        return bingoElement.getType();
    }

    public boolean isCompleted(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height)
            return false;
        return this.elementCompleted[x + (y * this.width)];
    }

    public void setCompleted(final int x, final int y, final  boolean complete) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height)
            return;
        this.elementCompleted[x + (y * this.width)] = complete;
    }

    public BingoGrid copy() {
        final BingoGrid bingoGrid = new BingoGrid(this.width, this.height);

        System.arraycopy(this.elements, 0, bingoGrid.elements, 0, this.elements.length);
        System.arraycopy(this.elementCompleted, 0, bingoGrid.elementCompleted, 0, this.elementCompleted.length);
        return bingoGrid;
    }

    public static BingoGrid createFromMaterial(Material... materials) {
        return null;
    }

}
