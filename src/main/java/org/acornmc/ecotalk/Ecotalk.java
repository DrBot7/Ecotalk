package org.acornmc.ecotalk;

import github.scarsz.discordsrv.DiscordSRV;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ecotalk extends JavaPlugin {
    private static Economy econ;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("discord", true);
        config.addDefault("money", 1.00);
        config.addDefault("cooldown", 15);
        config.options().copyDefaults(true);
        saveConfig();

        getLogger().info("Checking for Vault...");
        boolean vaultExists = getServer().getPluginManager().getPlugin("Vault") != null;

        if (!vaultExists) {
            getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("Vault found");

        if (!this.setupEconomy()) {
            getLogger().severe("Failed to initialize economy");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("Registering Chat");
        this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        if (config.getBoolean("discord")) {
            getLogger().info("Checking for DiscordSRV...");
            try {
                DiscordSRVListener discordsrvListener = new DiscordSRVListener();
                DiscordSRV.api.subscribe(discordsrvListener);
                getLogger().info("DiscordSRV found!");
            } catch (NoClassDefFoundError error) {
                getLogger().info("DiscordSRV not found!");
            }
        }
        this.getServer().getPluginManager().registerEvents(new EventsPlayerJoin(), this);
    }

    @Override
    public void onDisable() {
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = (Economy) rsp.getProvider();
        return true;
    }

    public static Economy getEcon() {
        return econ;
    }
}
