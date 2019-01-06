package creativeitemfilter;

import java.util.stream.Collectors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		// generate new item
		ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());
		// handle meta
		if (oldItem.hasItemMeta()) {
			// get meta
			ItemMeta oldMeta = oldItem.getItemMeta();
			// copy specific meta
			ItemMeta newMeta = metaCopierFactory.getCopier(oldMeta).copyValidMeta(oldMeta, newItem.getType());
			// copy displayname
			if (oldMeta.hasDisplayName()) {
				newMeta.setDisplayName(StringUtils.clampString(oldMeta.getDisplayName()));
			}
			// copy lore
			if (oldMeta.hasLore()) {
				newMeta.setLore(oldMeta.getLore().stream().map(StringUtils::clampString).collect(Collectors.toList()));
			}
			// copy enchantments
			oldItem.getEnchantments().entrySet().stream()
			.filter(entry -> entry.getValue() <= 15)
			.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));
			// set new meta
			newItem.setItemMeta(newMeta);
		}
		// set new item
		event.setCursor(newItem);
	}

}
