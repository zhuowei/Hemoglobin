package net.zhuoweizhang.hemoglobin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class HemoglobinEntityListener extends EntityListener {

	private Random random = new Random();

	public HemoglobinPlugin plugin;

	public HemoglobinEntityListener(HemoglobinPlugin plugin) {
		this.plugin = plugin;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		for (int i = 0; i < event.getDamage() * plugin.bloodMultiplier; i++){
			ItemStack stack = new ItemStack(plugin.bloodMaterial, 1, plugin.bloodMetadata);
			Item item = entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
			setRemovalTimer(item);
		}
	}

	private void setRemovalTimer(Entity item) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new RemoveItemThread(item),
			plugin.bloodRemovalDelay + (random.nextInt(plugin.bloodRemovalDelay / 2) - (plugin.bloodRemovalDelay / 4)));
	}

	private class RemoveItemThread extends Thread {
		private Entity item;
		public RemoveItemThread(Entity item) {
			this.item = item;
		}
		public void run() {
			item.remove();
		}
	}
}
