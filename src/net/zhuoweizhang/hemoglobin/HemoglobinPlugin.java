package net.zhuoweizhang.hemoglobin;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.command.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class HemoglobinPlugin extends JavaPlugin {

	public Material bloodMaterial = Material.REDSTONE;

	public short bloodMetadata = 1337;

	public int bloodMultiplier = 1;

	/** how many ticks pass before the blood item is removed */
	public int bloodRemovalDelay = 300;

	public boolean bloodPickupToRemove = false;

	public HemoglobinEntityListener entityListener = new HemoglobinEntityListener(this);

	public HemoglobinPlayerListener playerListener = new HemoglobinPlayerListener(this);

	@Override
	public void onEnable() {
		Configuration config = getConfiguration();
		Material mat = Material.matchMaterial(config.getString("material", "REDSTONE"));
		if (mat != null)
			bloodMaterial = mat;
		bloodMetadata = (short) config.getInt("metadata", 1337);
		bloodMultiplier = config.getInt("multiplier", 1);
		bloodRemovalDelay = config.getInt("delay", 300);
		bloodPickupToRemove = config.getBoolean("pickuptoremove", false);
		config.save();	
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, playerListener, Event.Priority.Normal, this);
	}

	public void onDisable() {
	}

}
