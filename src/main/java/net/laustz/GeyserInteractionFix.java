package net.laustz;

import org.bukkit.Bukkit;
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

		// Load settings from config
		saveDefaultConfig();
		this.configSettings = new ConfigSettings();
		this.configSettings.setFloodgatePrefix(getConfig().getString("floodgatePrefix"));
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
