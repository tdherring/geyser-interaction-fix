package net.laustz.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import net.laustz.GeyserInteractionFix;

public class PlayerInteractEventListener implements Listener {

	/**
	 * Checks if the player is violating restrictions and stops them.
	 * 
	 * @param event The event for when a player interacts with something.
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void handleInteractEvent(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			return;
		}

		Player player = event.getPlayer();
		String prefix = GeyserInteractionFix.getInstance().getConfigSettings().getFloodgatePrefix();

		// Only check bedrock players.
		if (player.getName().startsWith(prefix)) {
			Block block = event.getClickedBlock();
			BlockState state = block.getState();
			// The location to teleport the player back to if needed.
			Location playerLoc = PlayerMoveEventListener.getLocation(player.getUniqueId());

			if (state.getBlockData() instanceof Openable) {
				// Has the open event been cancelled by another plugin?
				if (event.useInteractedBlock() == Result.DENY) {
					player.teleport(playerLoc);
				}
			}
		}
	}
}
