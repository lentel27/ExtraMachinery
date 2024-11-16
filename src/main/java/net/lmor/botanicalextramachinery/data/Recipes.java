package net.lmor.botanicalextramachinery.data;

import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.recipe.RecipeProviderBase;
import io.github.noeppi_noeppi.libx.data.provider.recipe.crafting.CompressionExtension;
import io.github.noeppi_noeppi.libx.data.provider.recipe.crafting.CraftingExtension;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.FlowerBlock;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.block.ModSubtiles;
import vazkii.botania.common.lib.ModTags;

@Datagen
public class Recipes extends RecipeProviderBase implements CraftingExtension, CompressionExtension {
    public Recipes(ModX mod, DataGenerator generator) {super(mod, generator);}

    @Override
    protected void setup() {
        this.compress(ModItems.malachiteDragonstone, ModBlocks.malachiteDragonstoneBlock);
        this.compress(ModItems.saffronDragonstone, ModBlocks.saffronDragonstoneBlock);
        this.compress(ModItems.shadowDragonstone, ModBlocks.shadowDragonstoneBlock);
        this.compress(ModItems.crimsonDragonstone, ModBlocks.crimsonDragonstoneBlock);
        this.compress(ModItems.crystalDragonstone, ModBlocks.crystalDragonstoneBlock);

        this.compress(ModItems.malachiteIngot, ModBlocks.malachiteIngotBlock);
        this.compress(ModItems.saffronIngot, ModBlocks.saffronIngotBlock);
        this.compress(ModItems.shadowIngot, ModBlocks.shadowIngotBlock);
        this.compress(ModItems.crimsonIngot, ModBlocks.crimsonIngotBlock);
        this.compress(ModItems.crystalIngot, ModBlocks.crystalIngotBlock);

        this.machine(ModBlocks.jadedAmaranthus, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.crystalIngot, ModSubtiles.jadedAmaranthus, ModBlocks.crystalDragonstoneBlock);

        this.machine(ModBlocks.baseIndustrialAgglomerationFactory, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.industrialAgglomerationFactory, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseManaPool, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalManaPool, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseRunicAltar, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalRunicAltar, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseDaisy, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalDaisy, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseApothecary, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalApothecary, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseAlfheimMarket, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.alfheimMarket, ModBlocks.malachiteDragonstoneBlock);
        this.machine(ModBlocks.baseOrechid, vazkii.botania.common.item.ModItems.manaRingGreater, ModItems.malachiteIngot, ModSubtiles.orechid, ModBlocks.malachiteDragonstoneBlock);

        this.machine(ModBlocks.upgradedIndustrialAgglomerationFactory, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseIndustrialAgglomerationFactory, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedManaPool, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseManaPool, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedRunicAltar, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseRunicAltar, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedDaisy, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseDaisy, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedApothecary, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseApothecary, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedAlfheimMarket, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseAlfheimMarket, ModBlocks.saffronDragonstoneBlock);
        this.machine(ModBlocks.upgradedOrechid, vazkii.botania.common.block.ModBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseOrechid, ModBlocks.saffronDragonstoneBlock);

        this.machine(ModBlocks.advancedIndustrialAgglomerationFactory, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedIndustrialAgglomerationFactory, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedManaPool, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedManaPool, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedRunicAltar, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedRunicAltar, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedDaisy, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedDaisy, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedApothecary, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedApothecary, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedAlfheimMarket, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedAlfheimMarket, ModBlocks.shadowDragonstoneBlock);
        this.machine(ModBlocks.advancedOrechid, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedOrechid, ModBlocks.shadowDragonstoneBlock);

        this.machine(ModBlocks.ultimateIndustrialAgglomerationFactory, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedIndustrialAgglomerationFactory, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateManaPool, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedManaPool, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateRunicAltar, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedRunicAltar, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateDaisy, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedDaisy, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateApothecary, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedApothecary, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateAlfheimMarket, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedAlfheimMarket, ModBlocks.crimsonDragonstoneBlock);
        this.machine(ModBlocks.ultimateOrechid, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedOrechid, ModBlocks.crimsonDragonstoneBlock);

        this.upgrade_2(ModItems.catalystManaInfinity, ModBlocks.crimsonDragonstoneBlock, ModBlocks.ultimateManaPool);
        this.upgrade(ModItems.catalystSpeed, ModBlocks.upgradedIndustrialAgglomerationFactory);
        this.upgrade(ModItems.catalystSeedInfinity, ModBlocks.ultimateApothecary);
        this.upgrade(ModItems.catalystLivingRockInfinity, ModBlocks.upgradedRunicAltar);
        this.upgrade(ModItems.catalystWaterInfinity, ModBlocks.advancedApothecary);
        this.upgrade(ModItems.catalystStoneInfinity, ModItems.catalystLivingRockInfinity);
        this.upgrade(ModItems.catalystWoodInfinity, ModBlocks.ultimateDaisy);

        this.shaped( ModItems.catalystPetal, "aba", "bcb", "aba", 'a', Items.DIAMOND, 'b', ModTags.Items.PETALS, 'c', Items.IRON_BLOCK);
        this.upgrade_2(ModItems.catalystPetalBlock, ModItems.catalystPetal, ModBlocks.jadedAmaranthus);
    }

    private void machine(Object output, Object special1, Object special2, Object special3, Object special4) {
        this.shaped(output, "aba", "bcb", "ddd", 'a', special1, 'b', special2, 'c', special3, 'd', special4);
    }

    private void upgrade(Object output, Object special1) {
        this.shaped(output, "abc", "bdb", "cba", 'a', ModBlocks.saffronDragonstoneBlock, 'b', ModBlocks.shadowDragonstoneBlock, 'c', ModBlocks.crimsonDragonstoneBlock, 'd', special1);
    }

    private void upgrade_2(Object output, Object special1, Object special2) {
        this.shaped(output, "aaa", "aba", "aaa", 'a', special1, 'b', special2);
    }

}
