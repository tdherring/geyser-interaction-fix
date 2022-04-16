package net.laustz.listeners;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import net.laustz.ConfigSettings;
import net.laustz.GeyserInteractionFix;

public class PlayerMoveEventListener implements Listener {
	private long lastLoggedTime;
	private static HashMap<UUID, Location> locations = new HashMap<UUID, Location>();
	private static FloodgateApi floodgateApi = FloodgateApi.getInstance();

	/**
	 * Checks if a bedrock player moves and updates their location in the HashMap if they are not in a
	 * door.
	 * 
	 * @param event The event for when a player moves.
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void handleMoveEvent(PlayerMoveEvent event) {
		ConfigSettings configSettings = GeyserInteractionFix.getInstance().getConfigSettings();
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		boolean isBedrockPlayer = floodgateApi.getPlayer(uuid) != null;

		// Only log player location once every interval.
		if (System.currentTimeMillis() - lastLoggedTime >= configSettings.getLocationUpdateInterval() && isBedrockPlayer) {
			Location loc = player.getLocation();

			if (!loc.getBlock().getType().toString().contains("DOOR")) {
				setLocation(uuid, loc);
			}

			lastLoggedTime = System.currentTimeMillis();
		}
	}

	/**
	 * Sets the location of a bedrock player in the HashMap.
	 * 
	 * @param uuid The player UUID to update.
	 * @param location The location to set.
	 */
	public static void setLocation(UUID uuid, Location location) {
		locations.put(uuid, location);
	}

	/**
	 * Gets the location of a bedrock player from the HashMap.
	 */
	public static Location getLocation(UUID uuid) {
		return locations.get(uuid);
	}
}
