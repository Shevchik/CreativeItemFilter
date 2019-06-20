package creativeitemfilter;

import java.util.stream.Collectors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.java.JavaPlugin;

import creativeitemfilter.utils.StringUtils;

public class CreativeItemFilter extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();

	@EventHandler
	public void onCreativeItemEvent(InventoryCreativeEvent event) {
		ItemStack oldItem = event.getCursor();

		ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());

		if (oldItem.hasItemMeta()) {
			ItemMeta oldMeta = oldItem.getItemMeta();

			ItemMeta newMeta = metaCopierFactory.getCopier(oldMeta).copyValidMeta(oldMeta, newItem.getType());

			if (oldMeta instanceof Damageable) {
				((Damageable) newMeta).setDamage(((Damageable) oldMeta).getDamage());
			}

			if (oldMeta instanceof Repairable) {
				((Repairable) newMeta).setRepairCost(((Repairable) oldMeta).getRepairCost());
			}

			if (oldMeta.hasDisplayName()) {
				newMeta.setDisplayName(StringUtils.clampString(oldMeta.getDisplayName()));
			}
			if (oldMeta.hasLore()) {
				newMeta.setLore(oldMeta.getLore().stream().map(StringUtils::clampString).collect(Collectors.toList()));
			}

			newItem.setItemMeta(newMeta);
		}

		oldItem.getEnchantments().entrySet().stream()
		.filter(entry -> entry.getValue() > 0 && entry.getValue() <= 15)
		.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));

		event.setCursor(newItem);
	}

}
