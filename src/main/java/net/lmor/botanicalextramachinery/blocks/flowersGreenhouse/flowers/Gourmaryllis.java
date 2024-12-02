package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.minecraft.world.item.ItemStack;

public class Gourmaryllis extends GenFlowers {

    @Override
    public boolean availableFuel(ItemStack fuel) {
        return fuel.getItem().isEdible();
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        int food = Math.min(12, fuel.getItem().getFoodProperties().getNutrition());

        return (int)((double)(food * food * 70) * 1.8);
    }
}

