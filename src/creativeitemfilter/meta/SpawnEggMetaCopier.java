package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

public class SpawnEggMetaCopier implements MetaCopier<SpawnEggMeta> {

	public static final SpawnEggMetaCopier INSTANCE = new SpawnEggMetaCopier();

	private SpawnEggMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(SpawnEggMeta oldMeta, Material material) {
		SpawnEggMeta newMeta = (SpawnEggMeta) Bukkit.getItemFactory().getItemMeta(material);
		newMeta.setSpawnedType(oldMeta.getSpawnedType());
		return newMeta;
	}

}
