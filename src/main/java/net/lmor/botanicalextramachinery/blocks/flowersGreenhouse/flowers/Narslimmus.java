package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import vazkii.botania.xplat.XplatAbstractions;

public class Narslimmus extends GenFlowers {
    @Override
    public boolean availableFuel(ItemStack fuel) {
        return fuel.getItem() == Items.SLIME_BALL || fuel.getItem() == Items.SLIME_BLOCK;
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        return (XplatAbstractions.INSTANCE.gogLoaded() ? LibXServerConfig.GreenhouseSettings.Flowers.narslimmus : LibXServerConfig.GreenhouseSettings.Flowers.narslimmus * 4)
                * (fuel.getItem() == Items.SLIME_BALL ? 1 : 9);
    }
}
