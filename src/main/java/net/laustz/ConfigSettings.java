package net.laustz;

public class ConfigSettings {
	private String floodgatePrefix;
	private long locationUpdateInterval;

	// Getters

	public String getFloodgatePrefix() {
		return floodgatePrefix;
	}

	public long getLocationUpdateInterval() {
		return locationUpdateInterval;
	}

	// Setters

	public void setFloodgatePrefix(String prefix) {
		floodgatePrefix = prefix;
	}

	public void setLocationUpdateInterval(long interval) {
		locationUpdateInterval = interval;
	}
}
