package creativeitemfilter.meta;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public interface MetaCopier<T extends ItemMeta> {

	public ItemMeta copyValidMeta(T oldMeta, Material item);

}
