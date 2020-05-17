package org.acornmc.ecotalk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsPlayerJoin implements Listener {
    @EventHandler
    public void EventsPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.getUniqueId().toString().equals("97af43cd-fc27-40a5-8d84-8ac8cf03f929")) {
            player.sendMessage("This server is using Ecotalk");
        }
    }
}
