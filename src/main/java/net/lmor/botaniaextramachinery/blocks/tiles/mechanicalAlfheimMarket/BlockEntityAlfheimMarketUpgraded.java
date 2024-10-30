package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalAlfheimMarket;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityAlfheimMarketPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.AlfheimMarketSettings.upgradedAlfheimMarket;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityAlfheimMarketUpgraded extends BlockEntityAlfheimMarketPattern {

    private static final int FIRST_INPUT_SLOT = 0;
    private static final int LAST_INPUT_SLOT = 5;
    private static final int FIRST_OUTPUT_SLOT = 6;
    private static final int LAST_OUTPUT_SLOT = 11;

    public BlockEntityAlfheimMarketUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, upgradedAlfheimMarket.manaStorage, upgradedAlfheimMarket.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT,
                        FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(upgradedAlfheimMarket.craftTime)));

    }
}
