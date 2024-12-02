package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.flowers;

import net.lmor.botanicalextramachinery.blocks.flowersGreenhouse.GenFlowers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;
import vazkii.botania.xplat.XplatAbstractions;

public class Endoflame extends GenFlowers {
    @Override
    public boolean availableFuel(ItemStack fuel){
        return !fuel.isEmpty() && !(Block.byItem(fuel.getItem()) instanceof ManaSpreaderBlock) && XplatAbstractions.INSTANCE.getSmeltingBurnTime(fuel) > 0;
    }

    @Override
    public int getPerMana(ItemStack fuel){
        return !fuel.isEmpty() && !(Block.byItem(fuel.getItem()) instanceof ManaSpreaderBlock) ? XplatAbstractions.INSTANCE.getSmeltingBurnTime(fuel) : 0;
    }
}
