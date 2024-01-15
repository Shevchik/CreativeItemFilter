package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class SkullMetaCopier implements MetaCopier<SkullMeta> {
	public static final SkullMetaCopier INSTANCE = new SkullMetaCopier();

	protected SkullMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, SkullMeta oldMeta, SkullMeta newMeta) {
		if (oldMeta.hasOwner()) {
			newMeta.setPlayerProfile(oldMeta.getPlayerProfile());
		}
	}
}