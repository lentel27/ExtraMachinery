package net.lmor.botanicalextramachinery.data;

import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.ItemModelProviderBase;
import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

@Datagen
public class ItemModels extends ItemModelProviderBase {
    public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
        super(ExtraMachinery.getInstance(), gen, helper);
    }

    protected void setup() {
    }
}