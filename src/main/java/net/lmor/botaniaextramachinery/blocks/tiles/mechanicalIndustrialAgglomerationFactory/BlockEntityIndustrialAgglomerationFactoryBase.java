package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityIndustrialAgglomerationFactoryPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.IndustrialAgglomerationFactorySettings.baseAgglomeration;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityIndustrialAgglomerationFactoryBase extends BlockEntityIndustrialAgglomerationFactoryPattern {

    private static final int FIRST_INPUT_SLOT = 0;
    private static final int LAST_INPUT_SLOT = 2;
    private static final int FIRST_OUTPUT_SLOT = 3;
    private static final int LAST_OUTPUT_SLOT = 6;

    public BlockEntityIndustrialAgglomerationFactoryBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, baseAgglomeration.manaStorage, baseAgglomeration.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(baseAgglomeration.craftTime)));
    }
}
