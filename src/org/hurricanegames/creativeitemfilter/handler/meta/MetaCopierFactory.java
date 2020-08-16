package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.hurricanegames.creativeitemfilter.utils.CachedInstanceOfChain;

public class MetaCopierFactory {

	private final CachedInstanceOfChain<MetaCopier<? extends ItemMeta>> copiers = new CachedInstanceOfChain<>();

	public MetaCopierFactory() {
		copiers.setKnownPath(LeatherArmorMeta.class, LeatherArmorMetaCopier.INSTANCE);
		copiers.setKnownPath(CompassMeta.class, CompassMetaCopier.INSTANCE);
		copiers.setKnownPath(TropicalFishBucketMeta.class, TropicalFishBucketMetaCopier.INSTANCE);
		copiers.setKnownPath(BannerMeta.class, BannerMetaCopier.INSTANCE);
		copiers.setKnownPath(EnchantmentStorageMeta.class, EnchantmentStorageMetaCopier.INSTANCE);
		copiers.setKnownPath(BookMeta.class, BookMetaCopier.INSTANCE);
		copiers.setKnownPath(KnowledgeBookMeta.class, KnowledgeBookMetaCopier.INSTANCE);
		copiers.setKnownPath(PotionMeta.class, PotionMetaCopier.INSTANCE);
		copiers.setKnownPath(FireworkEffectMeta.class, FireworkEffectMetaCopier.INSTANCE);
		copiers.setKnownPath(FireworkMeta.class, FireworkMetaCopier.INSTANCE);
		copiers.setKnownPath(ItemMeta.class, NoOpMetaCopier.INSTANCE);
	}

	@SuppressWarnings("unchecked")
	public MetaCopier<ItemMeta> getCopier(ItemMeta oldMeta) {
		return (MetaCopier<ItemMeta>) copiers.selectPath(oldMeta.getClass());
	}

}
