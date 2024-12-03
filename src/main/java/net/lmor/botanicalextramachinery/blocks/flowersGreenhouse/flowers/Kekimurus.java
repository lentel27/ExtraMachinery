package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;

public class Kekimurus extends GenFlowers {
    @Override
    public boolean availableFuel(ItemStack fuel) {
        return Block.byItem(fuel.getItem()) instanceof CakeBlock;
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        return LibXServerConfig.GreenhouseSettings.Flowers.kekimurus;
    }
}
