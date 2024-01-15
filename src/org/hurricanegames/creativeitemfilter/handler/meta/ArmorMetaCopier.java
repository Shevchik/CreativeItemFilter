package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.ArmorMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class ArmorMetaCopier implements MetaCopier<ArmorMeta> {

	public static final ArmorMetaCopier INSTANCE = new ArmorMetaCopier();

	protected ArmorMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, ArmorMeta oldMeta, ArmorMeta newMeta) {
		if (oldMeta.hasTrim()) newMeta.setTrim(oldMeta.getTrim());
	}

}
