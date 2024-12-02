package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import vazkii.botania.common.block.flower.generating.EntropinnyumBlockEntity;

public class Entropinnyum extends GenFlowers {

    @Override
    public boolean availableFuel(ItemStack fuel) {
        return fuel.getItem() == Blocks.TNT.asItem();
    }

    @Override
    public int getPerMana(ItemStack fuel) {
        return LibXServerConfig.GreenhouseSettings.Flowers.entropinnyum;
    }
}
