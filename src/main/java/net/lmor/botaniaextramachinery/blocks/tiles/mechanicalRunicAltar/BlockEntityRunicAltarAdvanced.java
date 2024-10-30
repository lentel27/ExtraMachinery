package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalRunicAltar;


import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityRunicAltarPattern;
import net.lmor.botaniaextramachinery.config.LibXClientConfig.RenderingVisualContent.RunicAltarSettings;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.RunicAltarSettings.advancedRunicAltar;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityRunicAltarAdvanced extends BlockEntityRunicAltarPattern {
    private static final int LIVINGROCK_SLOT_1 = 0;
    private static final int LIVINGROCK_SLOT_2 = 1;
    private static final int LIVINGROCK_SLOT_3 = 2;
    private static final int UPGRADE_SLOT_1 = 3;
    private static final int UPGRADE_SLOT_2 = -1;
    private static final int FIRST_INPUT_SLOT = 4;
    private static final int LAST_INPUT_SLOT = 19;
    private static final int FIRST_OUTPUT_SLOT = 20;
    private static final int LAST_OUTPUT_SLOT = 35;


    public BlockEntityRunicAltarAdvanced(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, advancedRunicAltar.manaStorage,
                new int[] {
                        LIVINGROCK_SLOT_1, FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT,
                        UPGRADE_SLOT_1, UPGRADE_SLOT_2, LIVINGROCK_SLOT_2, LIVINGROCK_SLOT_3
                },
                new boolean[] {true, false}, advancedRunicAltar.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalRunicAltarRender", Boolean.toString(RunicAltarSettings.runicAltarAdvanced))
                        .addConfig("craftTime", Integer.toString(advancedRunicAltar.craftTime)));
    }
}