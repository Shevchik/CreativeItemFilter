package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilter;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;

public class BlockStateMetaCopier implements MetaCopier<BlockStateMeta> {

    public static final BlockStateMetaCopier INSTANCE = new BlockStateMetaCopier();

    protected BlockStateMetaCopier() {
    }

    @Override
    public void copyValidMeta(CreativeItemFilterConfiguration configuration, BlockStateMeta oldMeta, BlockStateMeta newMeta) {
        BlockState state = oldMeta.getBlockState();

        if (state instanceof ShulkerBox oldBox) {
            for (int i = 0; i < oldBox.getInventory().getSize(); i++) {
                ItemStack stack = oldBox.getInventory().getItem(i);

                if (stack != null) {
                    ItemStack newItem = new ItemStack(stack.getType(), stack.getAmount());

                    CreativeItemFilterHandler.getInstance().copyValidMeta(stack, newItem);

                    oldBox.getInventory().setItem(i, newItem);
                }
            }
            state.update();

            newMeta.setBlockState(state);
        }
    }
}
