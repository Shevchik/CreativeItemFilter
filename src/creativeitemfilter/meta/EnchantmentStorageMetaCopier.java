package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentStorageMetaCopier implements MetaCopier<EnchantmentStorageMeta> {

	public static final EnchantmentStorageMetaCopier INSTANCE = new EnchantmentStorageMetaCopier();

	private EnchantmentStorageMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(EnchantmentStorageMeta oldMeta, Material material) {
		EnchantmentStorageMeta newEnchBookMeta = (EnchantmentStorageMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasStoredEnchants()) {
			oldMeta.getStoredEnchants().entrySet().stream()
			.filter(entry -> entry.getValue() <= 15)
			.forEach(entry -> newEnchBookMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true));
		}
		return newEnchBookMeta;
	}

}
