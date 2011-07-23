package net.zhuoweizhang.hemoglobin;

import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class HemoglobinPlayerListener extends PlayerListener {
	public HemoglobinPlugin plugin;

	public HemoglobinPlayerListener(HemoglobinPlugin plugin) {
		this.plugin = plugin;
	}

	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		Item item = event.getItem();
		ItemStack stack = item.getItemStack();
		if (stack.getType().equals(plugin.bloodMaterial) && stack.getDurability() == plugin.bloodMetadata) {
			//Pickup is blood
			event.setCancelled(true);
			if (plugin.bloodPickupToRemove)
				item.remove();
		}
	}
}
