package org.acornmc.ecotalk;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;

import java.util.*;

public class EconUtil {
    static HashMap<OfflinePlayer, Long> cooldowns = new HashMap<>();
    static void handleMessage(OfflinePlayer player, String message) {
        Ecotalk plugin = Ecotalk.getPlugin(Ecotalk.class);
        int cooldownTime = plugin.config.getInt("cooldown");
        int money = plugin.config.getInt("money");
        Economy e = Ecotalk.getEcon();
        if (e!=null) {
            long secondsLeft = 0;
            if(cooldowns.containsKey(player)) {
                secondsLeft = cooldowns.get(player) / 1000 + cooldownTime - System.currentTimeMillis() / 1000;
            }
            if(secondsLeft<=0) {
                cooldowns.put(player, System.currentTimeMillis());
                e.depositPlayer(player, money);
            }
        }
    }
}
