package net.laustz;

public class ConfigSettings {
	private String floodgatePrefix;

	/**
	 * Returns the floodgate prefix.
	 * 
	 * @return The floodgate prefix.
	 */
	public String getFloodgatePrefix() {
		return floodgatePrefix;
	}

	/**
	 * Sets the prefix for the bedrock players.
	 * 
	 * @param floodgatePrefix The prefix to set.
	 */
	public void setFloodgatePrefix(String prefix) {
		floodgatePrefix = prefix;
	}
}
