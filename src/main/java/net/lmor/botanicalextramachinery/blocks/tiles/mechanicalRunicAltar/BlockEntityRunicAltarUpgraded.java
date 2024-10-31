package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar;


import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityRunicAltarPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.RunicAltarSettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.RunicAltarSettings.upgradedRunicAltar;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityRunicAltarUpgraded extends BlockEntityRunicAltarPattern {
    private static final int LIVINGROCK_SLOT_1 = 0;
    private static final int LIVINGROCK_SLOT_2 = 1;
    private static final int LIVINGROCK_SLOT_3 = 2;
    private static final int UPGRADE_SLOT_1 = -1;
    private static final int UPGRADE_SLOT_2 = -1;
    private static final int FIRST_INPUT_SLOT = 3;
    private static final int LAST_INPUT_SLOT = 18;
    private static final int FIRST_OUTPUT_SLOT = 19;
    private static final int LAST_OUTPUT_SLOT = 34;


    public BlockEntityRunicAltarUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, upgradedRunicAltar.manaStorage,
                new int[] {
                        LIVINGROCK_SLOT_1, FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT,
                        UPGRADE_SLOT_1, UPGRADE_SLOT_2, LIVINGROCK_SLOT_2, LIVINGROCK_SLOT_3
                },
                new boolean[] {false, false}, upgradedRunicAltar.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalRunicAltarRender", Boolean.toString(RunicAltarSettings.runicAltarUpgraded))
                        .addConfig("craftTime", Integer.toString(upgradedRunicAltar.craftTime)));
    }
}