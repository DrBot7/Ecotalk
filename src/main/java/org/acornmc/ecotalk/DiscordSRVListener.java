package org.acornmc.ecotalk;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.*;
import github.scarsz.discordsrv.objects.managers.AccountLinkManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class DiscordSRVListener {

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void discordMessageReceived(DiscordGuildMessageReceivedEvent event) {
        String id = event.getMember().getUser().getId();
        AccountLinkManager alm = DiscordSRV.getPlugin().getAccountLinkManager();
        if (alm == null) {
            return;
        }
        UUID uuid = alm.getUuid(id);
        if (uuid == null) {
            return;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        String message = event.getMessage().getContentRaw();
        EconUtil.handleMessage(player, message);
    }
}