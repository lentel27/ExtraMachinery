package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityDaisyPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.DaisySettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.DaisySettings.ultimateDaisy;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDaisyUltimate extends BlockEntityDaisyPattern {

    public static final int SLOT_INVENTORY = 18;

    public BlockEntityDaisyUltimate(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, SLOT_INVENTORY,
                new SettingPattern().addConfig("durationTime", Integer.toString(ultimateDaisy.durationTime))
                        .addConfig("renderingDaisy", Boolean.toString(DaisySettings.daisyUltimate))
                        .addConfig("sizeSlots", Integer.toString(ultimateDaisy.sizeItemSlots)));
    }
}
