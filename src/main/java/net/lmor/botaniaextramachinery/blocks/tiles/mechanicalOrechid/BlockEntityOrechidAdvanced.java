package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalOrechid;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityOrechidPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.OrechidSettings.advancedOrechid;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityOrechidAdvanced extends BlockEntityOrechidPattern {

    public static final int UPGRADE_SLOT_1 = 0;
    public static final int UPGRADE_SLOT_2 = 1;
    public static final int ORE_SLOT_1 = 2;
    public static final int ORE_SLOT_2 = 3;
    public static final int ORE_SLOT_3 = 4;
    public static final int ORE_SLOT_4 = 5;
    public static final int ORE_SLOT_5 = 6;
    public static final int ORE_SLOT_6 = 7;
    public static final int ORE_SLOT_7 = 8;

    public static final int FIRST_INPUT_SLOT = 9;
    public static final int LAST_INPUT_SLOT = 18;
    public static final int FIRST_OUTPUT_SLOT = 19;
    public static final int LAST_OUTPUT_SLOT = 28;

    public BlockEntityOrechidAdvanced(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state,
                advancedOrechid.manaStorage,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT,
                        ORE_SLOT_1, ORE_SLOT_2, ORE_SLOT_3, ORE_SLOT_4, ORE_SLOT_5, ORE_SLOT_6, ORE_SLOT_7
                },
                new int[] {UPGRADE_SLOT_1, UPGRADE_SLOT_2},
                new SettingPattern().addConfig("cooldown", Integer.toString(advancedOrechid.cooldown))
                        .addConfig("countCraft", Integer.toString(advancedOrechid.countCraft)));
    }
}
