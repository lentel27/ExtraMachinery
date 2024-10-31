package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool;


import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityManaPoolPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.ManaPoolSettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.ManaPoolSettings.baseManaPool;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class BlockEntityManaPoolBase extends BlockEntityManaPoolPattern {

    private static final int CATALYSTS_SLOT = 0;
    private static final int FIRST_INPUT_SLOT = 1;
    private static final int LAST_INPUT_SLOT = 3;
    private static final int FIRST_OUTPUT_SLOT = 4;
    private static final int LAST_OUTPUT_SLOT = 5;



    public BlockEntityManaPoolBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, BotaniaRecipeTypes.MANA_INFUSION_TYPE, pos, state, baseManaPool.manaStorage,
                new int[] {
                        CATALYSTS_SLOT, FIRST_INPUT_SLOT,
                        LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                false, baseManaPool.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalManaPoolRender", Boolean.toString(ManaPoolSettings.manaPoolBase))
                        .addConfig("craftTime", Integer.toString(baseManaPool.craftTime)));
    }
}
