package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.lib.BotaniaTags;

public class Rafflowsia extends GenFlowers {
    @Override
    public boolean availableFuel(ItemStack fuel) {
        return Block.byItem(fuel.getItem()).defaultBlockState().is(BotaniaTags.Blocks.MYSTICAL_FLOWERS);
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        return LibXServerConfig.GreenhouseSettings.Flowers.rafflowsia;
    }
}
