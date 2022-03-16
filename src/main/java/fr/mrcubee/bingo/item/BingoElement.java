package fr.mrcubee.bingo.item;

import fr.mrcubee.bingo.image.ImageResource;
import org.bukkit.Material;

import java.lang.ref.WeakReference;
import java.util.Objects;


/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class BingoElement {

    private final Material type;
    private final String textureName;
    private WeakReference<ImageResource> imageResource;

    private BingoElement(Material material, String textureName) {
        this.type = material;
        this.textureName = textureName;
        this.imageResource = new WeakReference<ImageResource>(null);
    }

    public Material getType() {
        return this.type;
    }

    public String getTextureName() {
        return this.textureName;
    }

    public ImageResource getImageResource() {
        ImageResource imageResource = this.imageResource.get();

        if (imageResource == null) {
            imageResource = ImageResource.getFromResource("default", this.textureName, this.type.isItem());
            if (imageResource != null)
                this.imageResource = new WeakReference<ImageResource>(imageResource);
            return null;
        }
        return imageResource;
    }

    public static BingoElement registerItem(final Material material, final String textureName) {
        if (material == null || textureName == null)
            return null;
        return new BingoElement(material, textureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type.ordinal(), this.textureName);
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof String)
            return ((String) object).equalsIgnoreCase(this.textureName);
        if (object instanceof Material)
            return ((Material) object) == this.type;
        return object instanceof BingoElement && object.hashCode() == hashCode();
    }

}
