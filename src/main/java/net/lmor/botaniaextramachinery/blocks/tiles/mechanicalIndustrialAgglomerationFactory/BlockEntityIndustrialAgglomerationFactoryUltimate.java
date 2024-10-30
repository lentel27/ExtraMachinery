package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityIndustrialAgglomerationFactoryPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.IndustrialAgglomerationFactorySettings.ultimateAgglomeration;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityIndustrialAgglomerationFactoryUltimate extends BlockEntityIndustrialAgglomerationFactoryPattern {


    private static final int UPGRADED_1 = 0;
    private static final int UPGRADED_2 = 1;
    private static final int FIRST_INPUT_SLOT = 2;
    private static final int LAST_INPUT_SLOT = 15;
    private static final int FIRST_OUTPUT_SLOT = 16;
    private static final int LAST_OUTPUT_SLOT = 27;

    public BlockEntityIndustrialAgglomerationFactoryUltimate(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, ultimateAgglomeration.manaStorage, ultimateAgglomeration.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADED_1, UPGRADED_2
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(ultimateAgglomeration.craftTime)));
    }
}
