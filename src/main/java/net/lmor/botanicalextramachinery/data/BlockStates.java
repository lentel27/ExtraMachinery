package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.BlockStateProviderBase;

@Datagen
public class BlockStates extends BlockStateProviderBase {
    public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(ExtraMachinery.getInstance(), gen, helper);
    }

    protected void setup() {
        this.manualModel(ModBlocks.malachiteDragonstoneBlock);
        this.manualModel(ModBlocks.saffronDragonstoneBlock);
        this.manualModel(ModBlocks.shadowDragonstoneBlock);
        this.manualModel(ModBlocks.crimsonDragonstoneBlock);

        this.manualModel(ModBlocks.baseAlfheimMarket);
        this.manualModel(ModBlocks.upgradedAlfheimMarket);
        this.manualModel(ModBlocks.advancedAlfheimMarket);
        this.manualModel(ModBlocks.ultimateAlfheimMarket);

        this.manualModel(ModBlocks.baseApothecary);
        this.manualModel(ModBlocks.upgradedApothecary);
        this.manualModel(ModBlocks.advancedApothecary);
        this.manualModel(ModBlocks.ultimateApothecary);

        this.manualModel(ModBlocks.baseDaisy);
        this.manualModel(ModBlocks.upgradedDaisy);
        this.manualModel(ModBlocks.advancedDaisy);
        this.manualModel(ModBlocks.ultimateDaisy);

        this.manualModel(ModBlocks.baseManaPool);
        this.manualModel(ModBlocks.upgradedManaPool);
        this.manualModel(ModBlocks.advancedManaPool);
        this.manualModel(ModBlocks.ultimateManaPool);

        this.manualModel(ModBlocks.baseRunicAltar);
        this.manualModel(ModBlocks.upgradedRunicAltar);
        this.manualModel(ModBlocks.advancedRunicAltar);
        this.manualModel(ModBlocks.ultimateRunicAltar);

        this.manualModel(ModBlocks.baseIndustrialAgglomerationFactory);
        this.manualModel(ModBlocks.upgradedIndustrialAgglomerationFactory);
        this.manualModel(ModBlocks.advancedIndustrialAgglomerationFactory);
        this.manualModel(ModBlocks.ultimateIndustrialAgglomerationFactory);

        this.manualModel(ModBlocks.baseOrechid);
        this.manualModel(ModBlocks.upgradedOrechid);
        this.manualModel(ModBlocks.advancedOrechid);
        this.manualModel(ModBlocks.ultimateOrechid);
    }
}
