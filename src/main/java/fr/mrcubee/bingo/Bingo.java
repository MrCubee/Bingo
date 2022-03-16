package fr.mrcubee.bingo;

import fr.mrcubee.bingo.item.BingoElement;
import fr.mrcubee.bingo.map.MapSender;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class Bingo extends JavaPlugin {

    private Set<BingoElement> registeredElements;

    @Override
    public void onEnable() {
        this.registeredElements = new HashSet<BingoElement>();
        if (MapSender.createInstance() == null) {
            getLogger().severe("The plugin is not compatible with this server version.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public boolean registerElement(final BingoElement bingoElement) {
        if (bingoElement == null || bingoElement.getImageResource() == null)
            return false;
        this.registeredElements.add(bingoElement);
        return true;
    }

    public boolean registerElement(final Material material, final String textureName) {
        return registerElement(BingoElement.registerItem(material, textureName));
    }

    public boolean unRegisterElement(final Material material) {
        if (material == null)
            return false;
        return this.registeredElements.remove(material);
    }

    public boolean unRegisterElement(final String textureName) {
        if (textureName == null)
            return false;
        return this.registeredElements.remove(textureName);
    }

    public Set<BingoElement> getRegisteredElements() {
        return this.registeredElements;
    }

}
