package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityApothecaryPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.ApothecarySettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.ApothecarySettings.advancedApothecary;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityApothecaryAdvanced extends BlockEntityApothecaryPattern {

    private static final int SEEDS_SLOT = 0;
    private static final int UPGRADE_SLOT = 1;
    private static final int FIRST_INPUT_SLOT = 2;
    private static final int LAST_INPUT_SLOT = 17;
    private static final int FIRST_OUTPUT_SLOT = 18;
    private static final int LAST_OUTPUT_SLOT = 33;

    public BlockEntityApothecaryAdvanced(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, new int[] {
                SEEDS_SLOT, FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADE_SLOT
        }, advancedApothecary.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalApothecaryRender", Boolean.toString(ApothecarySettings.apothecaryAdvanced))
                        .addConfig("craftTime", Integer.toString(advancedApothecary.craftTime))
                        .addConfig("fluidStorage", Integer.toString(advancedApothecary.fluidStorage)));
    }
}
