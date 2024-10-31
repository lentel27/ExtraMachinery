package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityDaisyPattern;
import net.lmor.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.DaisySettings;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.DaisySettings.upgradedDaisy;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDaisyUpgraded extends BlockEntityDaisyPattern {

    public static final int SLOT_INVENTORY = 12;

    public BlockEntityDaisyUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, SLOT_INVENTORY,
                new SettingPattern().addConfig("durationTime", Integer.toString(upgradedDaisy.durationTime))
                        .addConfig("renderingDaisy", Boolean.toString(DaisySettings.daisyUpgraded))
                        .addConfig("sizeSlots", Integer.toString(upgradedDaisy.sizeItemSlots)));
    }
}
