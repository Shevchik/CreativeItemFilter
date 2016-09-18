package creativeitemfilter;

import java.util.HashMap;

import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import creativeitemfilter.meta.BannerMetaCopier;
import creativeitemfilter.meta.BookMetaCopier;
import creativeitemfilter.meta.NoOpMetaCopier;
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
		}
		return NoOpMetaCopier.INSTANCE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected MetaCopier<ItemMeta> cache(Class<? extends ItemMeta> clazz, MetaCopier copier) {
		copierCache.put(clazz, copier);
		return copier;
	}

}
