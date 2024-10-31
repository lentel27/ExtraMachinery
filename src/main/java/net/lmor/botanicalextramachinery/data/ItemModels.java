package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.ItemModelProviderBase;

@Datagen
public class ItemModels extends ItemModelProviderBase {
    public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
        super(ExtraMachinery.getInstance(), gen, helper);
    }

    protected void setup() {
    }
}