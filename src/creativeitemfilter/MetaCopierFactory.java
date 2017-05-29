package creativeitemfilter;

import java.util.HashMap;

import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

import creativeitemfilter.meta.BannerMetaCopier;
import creativeitemfilter.meta.BookMetaCopier;
import creativeitemfilter.meta.NoOpMetaCopier;
import creativeitemfilter.meta.PotionMetaCopier;
import creativeitemfilter.meta.SpawnEggMetaCopier;
import creativeitemfilter.meta.EnchantmentStorageMetaCopier;
import creativeitemfilter.meta.MetaCopier;

public class MetaCopierFactory {

	private final HashMap<Class<? extends ItemMeta>, MetaCopier<ItemMeta>> copierCache = new HashMap<>();

	public MetaCopier<ItemMeta> getCopier(ItemMeta oldMeta) {
		Class<? extends ItemMeta> metaClass = oldMeta.getClass();
		MetaCopier<ItemMeta> cached = copierCache.get(metaClass);
		if (cached != null) {
			return cached;
		}
		if (oldMeta instanceof BannerMeta) {
			return cache(metaClass, BannerMetaCopier.INSTANCE);
		} else if (oldMeta instanceof EnchantmentStorageMeta) {
			return cache(metaClass, EnchantmentStorageMetaCopier.INSTANCE);
		} else if (oldMeta instanceof BookMeta) {
			return cache(metaClass, BookMetaCopier.INSTANCE);
		} else if (oldMeta instanceof PotionMeta) {
			return cache(metaClass, PotionMetaCopier.INSTANCE);
		} else if (oldMeta instanceof SpawnEggMeta) {
			return cache(metaClass, SpawnEggMetaCopier.INSTANCE);
		}
		return NoOpMetaCopier.INSTANCE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected MetaCopier<ItemMeta> cache(Class<? extends ItemMeta> clazz, MetaCopier copier) {
		copierCache.put(clazz, copier);
		return copier;
	}

}
