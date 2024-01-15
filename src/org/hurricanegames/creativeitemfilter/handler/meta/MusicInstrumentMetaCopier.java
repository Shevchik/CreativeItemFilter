package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class MusicInstrumentMetaCopier implements MetaCopier<MusicInstrumentMeta> {
	public static final MusicInstrumentMetaCopier INSTANCE = new MusicInstrumentMetaCopier();

	protected MusicInstrumentMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, MusicInstrumentMeta oldMeta, MusicInstrumentMeta newMeta) {
		if (oldMeta.getInstrument() != null) {
			newMeta.setInstrument(oldMeta.getInstrument());
		}
	}
}