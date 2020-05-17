package org.acornmc.ecotalk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void eventsMessageSend(AsyncPlayerChatEvent event) {
        EconUtil.handleMessage(event.getPlayer(), event.getMessage());
    }
}
