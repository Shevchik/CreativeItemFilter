package org.hurricanegames.creativeitemfilter.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CreativeItemFilterHandler implements Listener {

	private static final ItemFlag[] ITEM_FLAGS_EMPTY = new ItemFlag[0];
	private static CreativeItemFilterHandler instance;
	private final Logger logger;
	private final CreativeItemFilterConfiguration configuration;

	public CreativeItemFilterHandler(Logger logger, CreativeItemFilterConfiguration configuration) {
		instance = this;
		this.logger = logger;
		this.configuration = configuration;
	}

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();

	//TODO: Specific material ItemMeta may extend multiple ItemMeta interfaces, so the code needs to be adapted to be able to handle that
	@EventHandler
	public void onCreativeItemEvent(InventoryCreativeEvent event) {
		try {
			ItemStack oldItem = event.getCursor();
			ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());

			copyValidMeta(oldItem, newItem);
			event.setCursor(newItem);
		} catch (Throwable t) {
			event.setCursor(null);
			((Player) event.getWhoClicked()).updateInventory();
			logger.log(Level.WARNING, "Unable to create safe clone of creative itemstack, removing", t);
		}
	}

	@EventHandler
	public void onPlayerEditBook(PlayerEditBookEvent event) {
		BookMeta bookMeta = event.getNewBookMeta();

		if (bookMeta.hasAuthor()) {
			bookMeta.setAuthor(StringUtils.filterValidCharacters(StringUtils.clampString(bookMeta.getAuthor(), configuration.getBookAuthorMaxLength())));
		}
		if (bookMeta.hasTitle()) {
			bookMeta.setTitle(StringUtils.filterValidCharacters(StringUtils.clampString(bookMeta.getTitle(), configuration.getBookTitleMaxLength())));
		}
		if (bookMeta.hasGeneration()) {
			bookMeta.setGeneration(bookMeta.getGeneration());
		}
		if (bookMeta.hasPages()) {
			int bookPagesMaxLength = configuration.getBookPagesMaxLength();
			bookMeta.setPages(bookMeta.getPages().stream()
							.map(string -> StringUtils.filterValidCharacters(StringUtils.clampString(string, bookPagesMaxLength)))
							.limit(configuration.getBookPagesMaxCount())
							.collect(Collectors.toList())
			);
		}

		event.setNewBookMeta(bookMeta);
	}

	public void copyValidMeta(ItemStack oldItem, ItemStack newItem) {
		if (oldItem.hasItemMeta()) {
			ItemMeta oldMeta = oldItem.getItemMeta();

			ItemMeta newMeta = Bukkit.getItemFactory().getItemMeta(newItem.getType());

			metaCopierFactory.getCopier(oldMeta).copyValidMeta(configuration, oldMeta, newMeta);

			if (oldMeta instanceof Damageable) {
				Damageable oldMetaDamageable = (Damageable) oldMeta;
				if (oldMetaDamageable.hasDamage()) {
					((Damageable) newMeta).setDamage(oldMetaDamageable.getDamage());
				}
			}

			if (oldMeta instanceof Repairable) {
				((Repairable) newMeta).setRepairCost(((Repairable) oldMeta).getRepairCost());
			}

			if (oldMeta.hasCustomModelData()) {
				newMeta.setCustomModelData(oldMeta.getCustomModelData());
			}

			newMeta.addItemFlags(oldMeta.getItemFlags().toArray(ITEM_FLAGS_EMPTY));

			if (oldMeta.hasDisplayName()) {
				newMeta.setDisplayName(StringUtils.clampString(oldMeta.getDisplayName(), configuration.getDisplayNameMaxLength()));
			}

			int loreMaxLength = configuration.getLoreMaxLength();
			if (oldMeta.hasLore()) {
				newMeta.setLore(
					oldMeta.getLore().stream()
					.map(loreLine -> StringUtils.clampString(loreLine, loreMaxLength))
					.limit(configuration.getLoreMaxCount())
					.collect(Collectors.toList())
				);
			}

			newItem.setItemMeta(newMeta);
		}

		int enchantmentMaxLevel = configuration.getEnchantmentMaxLevel();
		oldItem.getEnchantments().entrySet().stream()
		.filter(entry -> (entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
		.filter(configuration.getEnchantmentRemoveUnapplicableEnabled() ? entry -> entry.getKey().canEnchantItem(newItem) : entry -> true)
		.limit(configuration.getEnchantmentMaxCount())
		.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));
	}

	public static CreativeItemFilterHandler getInstance() {
		return instance;
	}
}
