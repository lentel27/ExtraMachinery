package net.lmor.botanicalextramachinery;

import net.lmor.botanicalextramachinery.Items.ItemCrimsonManaSpark;
import net.lmor.botanicalextramachinery.Items.ItemMalachiteManaSpark;
import net.lmor.botanicalextramachinery.Items.ItemSaffronManaSpark;
import net.lmor.botanicalextramachinery.Items.ItemShadowManaSpark;
import net.minecraft.world.item.Item;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.ItemBase;


@RegisterClass(registry = "ITEMS", priority = 1)
public class ModItems {

    public static final Item malachiteDragonstone;
    public static final Item malachiteIngot;

    public static final Item saffronDragonstone;
    public static final Item saffronIngot;

    public static final Item shadowDragonstone;
    public static final Item shadowIngot;

    public static final Item crimsonDragonstone;
    public static final Item crimsonIngot;

    public static final Item crystalDragonstone;
    public static final Item crystalIngot;


    public static final Item catalystManaInfinity;
    public static final Item catalystLivingRockInfinity;
    public static final Item catalystWaterInfinity;
    public static final Item catalystSeedInfinity;
    public static final Item catalystSpeed;
    public static final Item catalystStoneInfinity;
    public static final Item catalystWoodInfinity;
    public static final Item catalystPetal;
    public static final Item catalystPetalBlock;

    public static final Item malachiteSpark;
    public static final Item saffronSpark;
    public static final Item shadowSpark;
    public static final Item crimsonSpark;

    public ModItems() {
    }

    static {
        malachiteSpark = new ItemMalachiteManaSpark(ExtraMachinery.getInstance(), new Item.Properties());
        saffronSpark = new ItemSaffronManaSpark(ExtraMachinery.getInstance(), new Item.Properties());
        shadowSpark = new ItemShadowManaSpark(ExtraMachinery.getInstance(), new Item.Properties());
        crimsonSpark = new ItemCrimsonManaSpark(ExtraMachinery.getInstance(), new Item.Properties());

        malachiteDragonstone = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        saffronDragonstone = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        shadowDragonstone = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        crimsonDragonstone = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        crystalDragonstone = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());

        malachiteIngot = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        saffronIngot = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        shadowIngot = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        crimsonIngot = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        crystalIngot = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());

        catalystManaInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystLivingRockInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystWaterInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystSeedInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystSpeed = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystStoneInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystWoodInfinity = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystPetal = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
        catalystPetalBlock = new ItemBase(ExtraMachinery.getInstance(), new Item.Properties());
    }

}