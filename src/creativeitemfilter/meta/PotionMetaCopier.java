package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class PotionMetaCopier implements MetaCopier<PotionMeta> {

	public static final PotionMetaCopier INSTANCE = new PotionMetaCopier();

	private PotionMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(PotionMeta oldMeta, Material material) {
		PotionMeta newMeta = (PotionMeta) Bukkit.getItemFactory().getItemMeta(material);
		newMeta.setBasePotionData(oldMeta.getBasePotionData());
		if (oldMeta.hasCustomEffects()) {
			oldMeta.getCustomEffects().stream()
			.filter(effect -> effect.getAmplifier() < 2)
			.filter(effect -> effect.getDuration() < 10 * 60)
			.forEach(effect -> newMeta.addCustomEffect(effect, true));
		}
		return newMeta;
	}

}
