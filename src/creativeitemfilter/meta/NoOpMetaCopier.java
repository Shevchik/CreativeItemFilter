package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class NoOpMetaCopier implements MetaCopier<ItemMeta> {

	public static final NoOpMetaCopier INSTANCE = new NoOpMetaCopier();

	private NoOpMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(ItemMeta oldMeta, Material material) {
		return Bukkit.getItemFactory().getItemMeta(material);
	}

}
