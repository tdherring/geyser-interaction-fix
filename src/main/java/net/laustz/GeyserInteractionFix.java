package net.laustz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.laustz.listeners.PlayerInteractEventListener;
import net.laustz.listeners.PlayerMoveEventListener;

public class GeyserInteractionFix extends JavaPlugin {
	private ConfigSettings configSettings;

	/**
	 * Returns the plugin instance.
	 * 
	 * @return Return an instance of the plugin.
	 */
	public static GeyserInteractionFix getInstance() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("GeyserInteractionFix");
		if (plugin instanceof GeyserInteractionFix) {
			return (GeyserInteractionFix) plugin;
		} else {
			return null;
		}
	}

	/**
	 * Run when the plugin starts.
	 */
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveEventListener(), this);
		Bukkit.getLogger().info("Registered PlayerMoveEvent Listener");
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
		Bukkit.getLogger().info("Registered PlayerInteractEvent Listener");

		FileConfiguration config = getConfig();

		// Create the config if it's not there already.
		saveDefaultConfig();

		// Update config settings if changes made.
		try {
			File configFile = new File(getInstance().getDataFolder(), "config.yml");
			ConfigUpdater.update(getInstance(), "config.yml", configFile, new ArrayList<String>());
		} catch (IOException err) {
			err.printStackTrace();
		}

		// Load settings from config.
		this.configSettings = new ConfigSettings();
		this.configSettings.setFloodgatePrefix(config.getString("floodgatePrefix"));
		this.configSettings.setLocationUpdateInterval(config.getLong("locationUpdateInterval"));
	}

	/**
	 * Returns the config settings instance.
	 * 
	 * @return The config settings instance.
	 */
	public ConfigSettings getConfigSettings() {
		return configSettings;
	}
}
