package net.laustz.listeners;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import net.laustz.GeyserInteractionFix;

public class PlayerMoveEventListener implements Listener {
	private static HashMap<UUID, Location> locations = new HashMap<UUID, Location>();

	/**
	 * Checks if a bedrock player moves and updates their location in the HashMap if they are not in a
	 * door.
	 * 
	 * @param event The event for when a player moves.
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void handleMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		Location loc = player.getLocation();

		String prefix = GeyserInteractionFix.getInstance().getConfigSettings().getFloodgatePrefix();

		if (!loc.getBlock().getType().toString().contains("DOOR") && player.getName().startsWith(prefix)) {
			setLocation(uuid, loc);
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
