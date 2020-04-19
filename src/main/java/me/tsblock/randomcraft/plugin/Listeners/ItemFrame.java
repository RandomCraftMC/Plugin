package me.tsblock.randomcraft.plugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ItemFrame implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!event.getPlayer().hasPermission("randomcraft.frame") && event.getPlayer().getWorld().getName().equals("world")) {
            if (event.getRightClicked() instanceof org.bukkit.entity.ItemFrame) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        if(!(event.getRemover() instanceof Player)) return;
        Player p = (Player) event.getRemover();
        if (event.getEntity() instanceof org.bukkit.entity.ItemFrame && !p.hasPermission("randomcraft.frame") && p.getWorld().getName().equals("world")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onHangingPlace(HangingPlaceEvent event) {
        Player p = event.getPlayer();
        if (event.getEntity() instanceof org.bukkit.entity.ItemFrame && !p.hasPermission("randomcraft.frame") && p.getWorld().getName().equals("world")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            if (event.getEntity() instanceof org.bukkit.entity.ItemFrame && !p.hasPermission("randomcraft.frame") && p.getWorld().getName().equals("world")) {
                event.setCancelled(true);
            }
        }
    }
}
