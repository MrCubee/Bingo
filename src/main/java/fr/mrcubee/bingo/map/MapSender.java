package fr.mrcubee.bingo.map;

import fr.mrcubee.bingo.Versions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.awt.Image;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public interface MapSender {

    /** Updates the image on the map for a specific player.
     * @since 1.0
     * @param player The targeted player.
     * @param mapId The targeted map ID.
     * @param image The image to display.
     */
    public void send(final Player player, final int mapId, final Image image);

    /** Updates the image on the map for a specific player.
     * @since 1.0
     * @param player The targeted player.
     * @param itemStack The targeted map item.
     * @param image The image to display.
     * @return Returns true if the card was sent, otherwise returns false if the parameter is incorrect.
     */
    default public boolean send(final Player player, final ItemStack itemStack, final Image image) {
        final MapMeta itemMeta;

        if (player == null || itemStack == null || image == null || itemStack.getType() == Material.LEGACY_MAP)
            return false;
        itemMeta = (MapMeta) itemStack.getItemMeta();
        if (itemMeta == null)
            return false;
        player.sendMessage("Sending");
        send(player, itemMeta.getMapId(), image);
        return true;
    }

    /** Create an instance of this compatible class in the version currently in use.
     * @since 1.0
     * @return Returns an instance of this compatible class in the version currently in use.
     */
    public static MapSender createInstance() {
        final Versions currentVersion = Versions.getCurrent();
        final Class<?> clazz;
        final Class<? extends MapSender> craftMapSenderClass;

        try {
            clazz = Class.forName("fr.mrcubee.bingo.map." + currentVersion + ".CraftMapSender");
            craftMapSenderClass = (Class<? extends MapSender>) clazz;
            return craftMapSenderClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ignored) {}
        return null;
    }

}
