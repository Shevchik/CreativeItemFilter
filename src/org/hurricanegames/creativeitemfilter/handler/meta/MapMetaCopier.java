package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class MapMetaCopier implements MetaCopier<MapMeta> {

	public static final MapMetaCopier INSTANCE = new MapMetaCopier();

	protected MapMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, MapMeta oldMeta, MapMeta newMeta) {
		if (oldMeta.hasColor()) {
			newMeta.setColor(oldMeta.getColor());
		}
		if (oldMeta.hasLocationName()) {
			newMeta.setLocationName(oldMeta.getLocationName());
		}
		if (oldMeta.hasMapView()) {
			MapView newMapView = oldMeta.getMapView();
			newMapView.setTrackingPosition(false);

			newMeta.setMapView(newMapView);
		}
		if (oldMeta.isScaling()) {
			newMeta.setScaling(true);
		}
	}
}