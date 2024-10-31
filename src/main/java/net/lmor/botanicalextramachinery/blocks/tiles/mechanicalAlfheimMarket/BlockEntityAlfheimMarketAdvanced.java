package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityAlfheimMarketPattern;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.AlfheimMarketSettings.advancedAlfheimMarket;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityAlfheimMarketAdvanced extends BlockEntityAlfheimMarketPattern {

    private static final int UPGRADE_SLOT = 0;
    private static final int FIRST_INPUT_SLOT = 1;
    private static final int LAST_INPUT_SLOT = 9;
    private static final int FIRST_OUTPUT_SLOT = 10;
    private static final int LAST_OUTPUT_SLOT = 18;

    public BlockEntityAlfheimMarketAdvanced(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, advancedAlfheimMarket.manaStorage, advancedAlfheimMarket.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT,
                        FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADE_SLOT
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(advancedAlfheimMarket.craftTime)));

    }
}
