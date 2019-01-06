package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.BannerMeta;

public class BannerMetaCopier implements MetaCopier<BannerMeta> {

	public static final BannerMetaCopier INSTANCE = new BannerMetaCopier();

	private BannerMetaCopier() {
	}

	@Override
	public BannerMeta copyValidMeta(BannerMeta oldMeta, Material material) {
		BannerMeta newMeta = (BannerMeta) Bukkit.getItemFactory().getItemMeta(material);
		oldMeta.getPatterns().forEach(newMeta::addPattern);
		return newMeta;
	}

}
