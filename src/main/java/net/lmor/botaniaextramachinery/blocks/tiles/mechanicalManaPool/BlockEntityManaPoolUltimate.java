package net.lmor.botaniaextramachinery.blocks.tiles.mechanicalManaPool;


import net.lmor.botaniaextramachinery.blocks.pattern.BlockEntityManaPoolPattern;
import net.lmor.botaniaextramachinery.config.LibXClientConfig.RenderingVisualContent.ManaPoolSettings;
import net.lmor.botaniaextramachinery.config.LibXServerConfig.ManaPoolSettings.ultimateManaPool;
import net.lmor.botaniaextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class BlockEntityManaPoolUltimate extends BlockEntityManaPoolPattern {

    private static final int CATALYSTS_SLOT = 0;
    private static final int UPGRADE_SLOT = 1;
    private static final int FIRST_INPUT_SLOT = 2;
    private static final int LAST_INPUT_SLOT = 13;
    private static final int FIRST_OUTPUT_SLOT = 14;
    private static final int LAST_OUTPUT_SLOT = 22;


    public BlockEntityManaPoolUltimate(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, BotaniaRecipeTypes.MANA_INFUSION_TYPE, pos, state, ultimateManaPool.manaStorage,
                new int[] {
                        CATALYSTS_SLOT, FIRST_INPUT_SLOT, LAST_INPUT_SLOT,
                        FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADE_SLOT
                },
                true, ultimateManaPool.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalManaPoolRender", Boolean.toString(ManaPoolSettings.manaPoolUltimate))
                        .addConfig("craftTime", Integer.toString(ultimateManaPool.craftTime)));

    }
}
