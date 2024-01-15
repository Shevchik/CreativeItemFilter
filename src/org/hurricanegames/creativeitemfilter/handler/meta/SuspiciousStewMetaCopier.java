package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class SuspiciousStewMetaCopier implements MetaCopier<SuspiciousStewMeta> {
	public static final SuspiciousStewMetaCopier INSTANCE = new SuspiciousStewMetaCopier();

	protected SuspiciousStewMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, SuspiciousStewMeta oldMeta, SuspiciousStewMeta newMeta) {
		if (oldMeta.hasCustomEffects()) {
			for (PotionEffect effects : oldMeta.getCustomEffects()) {
				newMeta.addCustomEffect(effects, false);
			}
		}
	}
}