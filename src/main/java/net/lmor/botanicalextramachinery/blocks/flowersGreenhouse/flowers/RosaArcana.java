package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;

public class RosaArcana extends GenFlowers {

    private Random random = new Random();

    @Override
    public boolean availableFuel(ItemStack fuel) {
        return fuel.getItem() == Items.EXPERIENCE_BOTTLE;
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        return random.nextInt(3, 33) * LibXServerConfig.GreenhouseSettings.Flowers.rosaArcana;
    }
}

