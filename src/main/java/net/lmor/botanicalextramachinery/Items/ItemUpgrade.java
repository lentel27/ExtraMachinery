package net.lmor.botanicalextramachinery.Items;

import org.moddingx.libx.base.ItemBase;
import org.moddingx.libx.mod.ModX;

public class ItemUpgrade extends ItemBase {

    private final int valueItem;

    public ItemUpgrade(ModX mod, Properties properties, int val) {
        super(mod, properties);
        valueItem = val;
    }

    public int getValueItem() {
        return this.valueItem;
    }
}
