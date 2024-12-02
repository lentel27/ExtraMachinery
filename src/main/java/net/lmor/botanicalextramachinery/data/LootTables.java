package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ModBlocks;
import org.moddingx.libx.datagen.DatagenContext;
import org.moddingx.libx.datagen.provider.loot.BlockLootProviderBase;

public class LootTables extends BlockLootProviderBase {
    public LootTables(DatagenContext context) {
        super(context);
    }

    public static final String MANA = "mana";
    public static final String FLUID = "fluid";
    public static final String ENERGY = "energyStorage";

    protected void setup() {
        this.drops(ModBlocks.baseAlfheimMarket, this.copyNBT(MANA));
        this.drops(ModBlocks.upgradedAlfheimMarket, this.copyNBT(MANA));
        this.drops(ModBlocks.advancedAlfheimMarket, this.copyNBT(MANA));
        this.drops(ModBlocks.ultimateAlfheimMarket, this.copyNBT(MANA));

        this.drops(ModBlocks.baseIndustrialAgglomerationFactory, this.copyNBT(MANA));
        this.drops(ModBlocks.upgradedIndustrialAgglomerationFactory, this.copyNBT(MANA));
        this.drops(ModBlocks.advancedIndustrialAgglomerationFactory, this.copyNBT(MANA));
        this.drops(ModBlocks.ultimateIndustrialAgglomerationFactory, this.copyNBT(MANA));

        this.drops(ModBlocks.baseApothecary, this.copyNBT(FLUID));
        this.drops(ModBlocks.upgradedApothecary, this.copyNBT(FLUID));
        this.drops(ModBlocks.advancedApothecary, this.copyNBT(FLUID));
        this.drops(ModBlocks.ultimateApothecary, this.copyNBT(FLUID));

        this.drops(ModBlocks.baseDaisy, this.copyNBT());
        this.drops(ModBlocks.upgradedDaisy, this.copyNBT());
        this.drops(ModBlocks.advancedDaisy, this.copyNBT());
        this.drops(ModBlocks.ultimateDaisy, this.copyNBT());

        this.drops(ModBlocks.baseManaPool, this.copyNBT(MANA));
        this.drops(ModBlocks.upgradedManaPool, this.copyNBT(MANA));
        this.drops(ModBlocks.advancedManaPool, this.copyNBT(MANA));
        this.drops(ModBlocks.ultimateManaPool, this.copyNBT(MANA));

        this.drops(ModBlocks.baseRunicAltar, this.copyNBT(MANA));
        this.drops(ModBlocks.upgradedRunicAltar, this.copyNBT(MANA));
        this.drops(ModBlocks.advancedRunicAltar, this.copyNBT(MANA));
        this.drops(ModBlocks.ultimateRunicAltar, this.copyNBT(MANA));

        this.drops(ModBlocks.baseOrechid, this.copyNBT(MANA));
        this.drops(ModBlocks.upgradedOrechid, this.copyNBT(MANA));
        this.drops(ModBlocks.advancedOrechid, this.copyNBT(MANA));
        this.drops(ModBlocks.ultimateOrechid, this.copyNBT(MANA));

        this.drops(ModBlocks.jadedAmaranthus, this.copyNBT(MANA));
        this.drops(ModBlocks.greenhouse, this.copyNBT(MANA, ENERGY));
    }
}