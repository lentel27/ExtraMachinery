package net.lmor.botanicalextramachinery.core;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.minecraft.resources.ResourceLocation;

public class LibResources {

    private static final String GUI_PREFIX = "textures/gui/";
    public static final ResourceLocation MANA_BAR_CURRENT = loc(GUI_PREFIX + "misc/current_mana");
    public static final ResourceLocation WATER_BAR_CURRENT = loc(GUI_PREFIX + "misc/current_water");

    public static final ResourceLocation MALACHITE_SPARK = loc("malachite_spark");
    public static final ResourceLocation SAFFRON_SPARK = loc("saffron_spark");
    public static final ResourceLocation SHADOW_SPARK = loc("shadow_spark");
    public static final ResourceLocation CRIMSON_SPARK = loc("crimson_spark");

    public static final ResourceLocation BASE_MECHANICAL_MANA_POOL_GUI = gui(LibNames.BASE_MECHANICAL_MANA_POOL);
    public static final ResourceLocation UPGRADED_MECHANICAL_MANA_POOL_GUI = gui(LibNames.UPGRADED_MECHANICAL_MANA_POOL);
    public static final ResourceLocation ADVANCED_MECHANICAL_MANA_POOL_GUI = gui(LibNames.ADVANCED_MECHANICAL_MANA_POOL);
    public static final ResourceLocation ULTIMATE_MECHANICAL_MANA_POOL_GUI = gui(LibNames.ULTIMATE_MECHANICAL_MANA_POOL);

    public static final ResourceLocation BASE_MECHANICAL_RUNIC_ALTAR_GUI = gui(LibNames.BASE_MECHANICAL_RUNIC_ALTAR);
    public static final ResourceLocation UPGRADED_MECHANICAL_RUNIC_ALTAR_GUI = gui(LibNames.UPGRADED_MECHANICAL_RUNIC_ALTAR);
    public static final ResourceLocation ADVANCED_MECHANICAL_RUNIC_ALTAR_GUI = gui(LibNames.ADVANCED_MECHANICAL_RUNIC_ALTAR);
    public static final ResourceLocation ULTIMATE_MECHANICAL_RUNIC_ALTAR_GUI = gui(LibNames.ULTIMATE_MECHANICAL_RUNIC_ALTAR);

    public static final ResourceLocation BASE_MECHANICAL_DAISY_GUI = gui(LibNames.BASE_MECHANICAL_DAISY);
    public static final ResourceLocation UPGRADED_MECHANICAL_DAISY_GUI = gui(LibNames.UPGRADED_MECHANICAL_DAISY);
    public static final ResourceLocation ADVANCED_MECHANICAL_DAISY_GUI = gui(LibNames.ADVANCED_MECHANICAL_DAISY);
    public static final ResourceLocation ULTIMATE_MECHANICAL_DAISY_GUI = gui(LibNames.ULTIMATE_MECHANICAL_DAISY);

    public static final ResourceLocation BASE_MECHANICAL_APOTHECARY_GUI = gui(LibNames.BASE_MECHANICAL_APOTHECARY);
    public static final ResourceLocation UPGRADED_MECHANICAL_APOTHECARY_GUI = gui(LibNames.UPGRADED_MECHANICAL_APOTHECARY);
    public static final ResourceLocation ADVANCED_MECHANICAL_APOTHECARY_GUI = gui(LibNames.ADVANCED_MECHANICAL_APOTHECARY);
    public static final ResourceLocation ULTIMATE_MECHANICAL_APOTHECARY_GUI = gui(LibNames.ULTIMATE_MECHANICAL_APOTHECARY);


    public static final ResourceLocation BASE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI = gui(LibNames.BASE_INDUSTRIAL_AGGLOMERATION_FACTORY);
    public static final ResourceLocation UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI = gui(LibNames.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY);
    public static final ResourceLocation ADVANCED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI = gui(LibNames.ADVANCED_INDUSTRIAL_AGGLOMERATION_FACTORY);
    public static final ResourceLocation ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI = gui(LibNames.ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY);

    public static final ResourceLocation BASE_ALFHEIM_MARKET_GUI = gui(LibNames.BASE_ALFHEIM_MARKET);
    public static final ResourceLocation UPGRADED_ALFHEIM_MARKET_GUI = gui(LibNames.UPGRADED_ALFHEIM_MARKET);
    public static final ResourceLocation ADVANCED_ALFHEIM_MARKET_GUI = gui(LibNames.ADVANCED_ALFHEIM_MARKET);
    public static final ResourceLocation ULTIMATE_ALFHEIM_MARKET_GUI = gui(LibNames.ULTIMATE_ALFHEIM_MARKET);

    public static final ResourceLocation BASE_ORECHID_GUI = gui(LibNames.BASE_ORECHID);
    public static final ResourceLocation UPGRADED_ORECHID_GUI = gui(LibNames.UPGRADED_ORECHID);
    public static final ResourceLocation ADVANCED_ORECHID_GUI = gui(LibNames.ADVANCED_ORECHID);
    public static final ResourceLocation ULTIMATE_ORECHID_GUI = gui(LibNames.ULTIMATE_ORECHID);

    public static final ResourceLocation JADED_AMARANTHUS_GUI = gui(LibNames.JADED_AMARANTHUS);

    public LibResources() {
    }

    private static ResourceLocation gui(String id) {
        return loc(GUI_PREFIX + id);
    }

    private static ResourceLocation loc(String id) {
        return new ResourceLocation(ExtraMachinery.getInstance().modid, id + ".png");
    }
}
