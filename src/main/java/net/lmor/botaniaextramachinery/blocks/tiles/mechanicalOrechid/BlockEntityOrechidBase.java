package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalOrechid;

import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityOrechidPattern;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.OrechidSettings.baseOrechid;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class BlockEntityOrechidBase extends BlockEntityOrechidPattern {

    public static final int ORE_SLOT_1 = 0;
    public static final int ORE_SLOT_2 = 1;
    public static final int ORE_SLOT_3 = 2;

    public static final int FIRST_INPUT_SLOT = 3;
    public static final int LAST_INPUT_SLOT = 7;
    public static final int FIRST_OUTPUT_SLOT = 8;
    public static final int LAST_OUTPUT_SLOT = 12;

    public BlockEntityOrechidBase(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state,
                baseOrechid.manaStorage,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, ORE_SLOT_1, ORE_SLOT_2, ORE_SLOT_3
                }, null, new SettingPattern().addConfig("cooldown", Integer.toString(baseOrechid.cooldown))
                        .addConfig("countCraft", Integer.toString(baseOrechid.countCraft)));
    }
}
