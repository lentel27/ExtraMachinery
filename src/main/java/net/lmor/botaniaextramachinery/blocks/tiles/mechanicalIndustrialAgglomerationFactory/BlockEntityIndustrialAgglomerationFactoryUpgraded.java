package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityIndustrialAgglomerationFactoryPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.IndustrialAgglomerationFactorySettings.upgradedAgglomeration;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityIndustrialAgglomerationFactoryUpgraded extends BlockEntityIndustrialAgglomerationFactoryPattern {

    private static final int FIRST_INPUT_SLOT = 0;
    private static final int LAST_INPUT_SLOT = 4;
    private static final int FIRST_OUTPUT_SLOT = 5;
    private static final int LAST_OUTPUT_SLOT = 10;

    public BlockEntityIndustrialAgglomerationFactoryUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, upgradedAgglomeration.manaStorage, upgradedAgglomeration.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(upgradedAgglomeration.craftTime)));
    }
}
