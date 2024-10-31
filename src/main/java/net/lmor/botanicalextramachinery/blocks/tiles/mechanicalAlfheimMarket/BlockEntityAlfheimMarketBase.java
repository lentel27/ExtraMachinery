package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityAlfheimMarketPattern;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.AlfheimMarketSettings.baseAlfheimMarket;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityAlfheimMarketBase extends BlockEntityAlfheimMarketPattern {

    private static final int FIRST_INPUT_SLOT = 0;
    private static final int LAST_INPUT_SLOT = 2;
    private static final int FIRST_OUTPUT_SLOT = 3;
    private static final int LAST_OUTPUT_SLOT = 5;

    public BlockEntityAlfheimMarketBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, baseAlfheimMarket.manaStorage, baseAlfheimMarket.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT,
                        FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(baseAlfheimMarket.craftTime)));

    }
}
