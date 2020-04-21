package me.tsblock.randomcraft.plugin.Listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SendOpPackets implements Listener {
    private RandomCraftPlugin plugin;

    public SendOpPackets(RandomCraftPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("randomcraft.admin")) {
            PacketContainer opStatusPacket = new PacketContainer(PacketType.Play.Server.ENTITY_STATUS);
            opStatusPacket.getIntegers().write(0, player.getEntityId());
            opStatusPacket.getBytes().write(0, (byte) 26);

        }
    }
}
