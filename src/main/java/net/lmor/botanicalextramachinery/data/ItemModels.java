package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.RegItemsAppbotItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.ItemModelProviderBase;

@Datagen
public class ItemModels extends ItemModelProviderBase {
    public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
        super(ExtraMachinery.getInstance(), gen, helper);

        this.manualModel(ModItems.catalystPetal);
        this.manualModel(ModItems.catalystPetalBlock);
        this.manualModel(ModBlocks.greenhouse.asItem());

        if (ModList.get().isLoaded("appbot")){
            this.manualModel(RegItemsAppbotItems.manaStorageCell_1m.get());
            this.manualModel(RegItemsAppbotItems.manaStorageCell_4m.get());
            this.manualModel(RegItemsAppbotItems.manaStorageCell_16m.get());
            this.manualModel(RegItemsAppbotItems.manaStorageCell_64m.get());
            this.manualModel(RegItemsAppbotItems.manaStorageCell_256m.get());
        }
    }

    @Override
    protected void setup() {
    }

}