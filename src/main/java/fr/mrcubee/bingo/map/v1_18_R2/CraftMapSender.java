package fr.mrcubee.bingo.map.v1_18_R2;

import fr.mrcubee.bingo.map.MapSender;
import net.minecraft.network.protocol.game.PacketPlayOutMap;
import net.minecraft.world.level.saveddata.maps.MapIcon;
import net.minecraft.world.level.saveddata.maps.WorldMap;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.map.MapPalette;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CraftMapSender implements MapSender {

    @Override
    public void send(final Player player, final int mapId, final Image image) {
        final BufferedImage resizedImage;
        final byte[] bytes;
        final PacketPlayOutMap packetPlayOutMap;

        if (player == null || image == null)
            return;
        resizedImage = MapPalette.resizeImage(image);
        bytes = MapPalette.imageToBytes(resizedImage);
        packetPlayOutMap = new PacketPlayOutMap(mapId, (byte) 3, true, new ArrayList<MapIcon>(),
        new WorldMap.b(0, 0, resizedImage.getWidth(), resizedImage.getHeight(), bytes));
        ((CraftPlayer) player).getHandle().b.a(packetPlayOutMap);
    }

}
