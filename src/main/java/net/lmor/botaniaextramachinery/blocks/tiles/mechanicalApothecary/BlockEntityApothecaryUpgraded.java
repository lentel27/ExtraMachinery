package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalApothecary;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityApothecaryPattern;
import net.lmor.botaniaextramachinery.config.LibXClientConfig.RenderingVisualContent.ApothecarySettings;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.ApothecarySettings.upgradedApothecary;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityApothecaryUpgraded extends BlockEntityApothecaryPattern {

    private static final int SEEDS_SLOT = 0;
    private static final int FIRST_INPUT_SLOT = 1;
    private static final int LAST_INPUT_SLOT = 12;
    private static final int FIRST_OUTPUT_SLOT = 13;
    private static final int LAST_OUTPUT_SLOT = 24;

    public BlockEntityApothecaryUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, new int[] {
                SEEDS_SLOT, FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
        }, upgradedApothecary.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalApothecaryRender", Boolean.toString(ApothecarySettings.apothecaryUpgraded))
                        .addConfig("craftTime", Integer.toString(upgradedApothecary.craftTime))
                        .addConfig("fluidStorage", Integer.toString(upgradedApothecary.fluidStorage)));
    }
}
