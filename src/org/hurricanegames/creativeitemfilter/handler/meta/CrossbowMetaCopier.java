package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.CrossbowMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class CrossbowMetaCopier implements MetaCopier<CrossbowMeta> {
	public static final CrossbowMetaCopier INSTANCE = new CrossbowMetaCopier();

	protected CrossbowMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, CrossbowMeta oldMeta, CrossbowMeta newMeta) {
		if (oldMeta.hasChargedProjectiles()) {
			newMeta.setChargedProjectiles(oldMeta.getChargedProjectiles());
		}
	}
}