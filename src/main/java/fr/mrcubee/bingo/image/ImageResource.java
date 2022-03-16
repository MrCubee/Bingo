package fr.mrcubee.bingo.image;

import fr.mrcubee.finder.plugin.PluginFinder;
import org.bukkit.plugin.Plugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class ImageResource implements Drawable {

    private final BufferedImage image;

    protected ImageResource(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage draw(final int frame) {
        int frames = getFrames();
        int height;

        if (frame < 0)
            return null;
        height = this.image.getHeight() / frames;
        return this.image.getSubimage(0, height * (frame % frames), this.image.getWidth(), height);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getFrames() {
        return this.image.getHeight() / this.image.getWidth();
    }

    public static ImageResource getFromResource(String resourcePack, String textureName, boolean item) {
        final Plugin plugin;
        final InputStream resourceInputStream;
        BufferedImage image = null;

        if (resourcePack == null || textureName == null)
            return null;
        plugin = (Plugin) PluginFinder.INSTANCE.findPlugin();
        if (plugin == null)
            return null;
        resourceInputStream = plugin.getResource(String.format(item ? "textures/%s/item/%s.png" : "textures/%s/block/%s.png", resourcePack, textureName));
        if (resourceInputStream == null)
            return null;
        try {
            image = ImageIO.read(resourceInputStream);
        } catch (IOException ignored) {}
        try {
            resourceInputStream.close();
        } catch (IOException ignored) {}
        if (image == null)
            return null;
        return new ImageResource(image);
    }

}
