# Geyser Interaction Fix

Plugins which cancel openable object events are ignored by Geyser. This gives Bedrock players the ability to phase through doors and trapdoors.

This plugin addresses the issue by providing a simple mechanism which tracks player locations prior to interacting with the openable object. If the event is cancelled, it will teleport them to their original location so they cannot phase through doors.
## Requirements

This plugin requires Floodgate to be installed on all of your backend servers. See the [Floodgate Documentation](https://wiki.geysermc.org/floodgate/setup/#installing-floodgate-also-on-spigot-servers-behind-bungeecord-or-velocity) for more information on configuring this.
## config.yml

Configuration settings for `config.yml`:

- `locationUpdateInterval` - How often to update bedrock player locations in ms. Lower values mean less rubber banding, but are more CPU intensive. (Default: 1000)