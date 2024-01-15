package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class AxolotlBucketMetaCopier implements MetaCopier<AxolotlBucketMeta> {

    public static final AxolotlBucketMetaCopier INSTANCE = new AxolotlBucketMetaCopier();

    protected AxolotlBucketMetaCopier() {
    }

    @Override
    public void copyValidMeta(CreativeItemFilterConfiguration configuration, AxolotlBucketMeta oldMeta, AxolotlBucketMeta newMeta) {
        if (oldMeta.hasVariant()) {
            newMeta.setVariant(oldMeta.getVariant());
        }
    }
}
