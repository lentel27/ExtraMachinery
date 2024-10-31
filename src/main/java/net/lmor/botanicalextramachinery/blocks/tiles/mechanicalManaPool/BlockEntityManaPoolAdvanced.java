package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool;


import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityManaPoolPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.ManaPoolSettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.ManaPoolSettings.advancedManaPool;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class BlockEntityManaPoolAdvanced extends BlockEntityManaPoolPattern {

    private static final int CATALYSTS_SLOT = 0;
    private static final int UPGRADE_SLOT = 1;
    private static final int FIRST_INPUT_SLOT = 2;
    private static final int LAST_INPUT_SLOT = 10;
    private static final int FIRST_OUTPUT_SLOT = 11;
    private static final int LAST_OUTPUT_SLOT = 16;



    public BlockEntityManaPoolAdvanced(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, BotaniaRecipeTypes.MANA_INFUSION_TYPE, pos, state, advancedManaPool.manaStorage,
                new int[] {
                        CATALYSTS_SLOT, FIRST_INPUT_SLOT, LAST_INPUT_SLOT,
                        FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADE_SLOT
                },
                true, advancedManaPool.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalManaPoolRender", Boolean.toString(ManaPoolSettings.manaPoolAdvanced))
                        .addConfig("craftTime", Integer.toString(advancedManaPool.craftTime)));
    }
}
