package net.lmor.botanicalextramachinery.gui;

import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.resources.ResourceLocation;

public enum AllBars {
    MANA("mana", LibResources.MANA_BAR_CURRENT, 130, 5, true, "botanicalextramachinery.tooltip.screen.mana"),
    INFINITY_MANA("infinity_mana", LibResources.INFINITY_MANA_BAR_CURRENT, 3, 15, false, "botanicalextramachinery.tooltip.screen.mana"),
    ENERGY("energy", LibResources.ENERGY_BAR_CURRENT, 130, 5, true, "botanicalextramachinery.tooltip.screen.energy"),
    WATER("water", LibResources.WATER_BAR_CURRENT, 130, 5, true, "botanicalextramachinery.tooltip.screen.water"),
    HEAT("heat", LibResources.HEAT_BAR_CURRENT, 3, 15, false, "botanicalextramachinery.tooltip.screen.heat");

    private final String name;
    private final ResourceLocation resourceLocation;
    private final int width;
    private final int height;
    private final boolean hor;
    private final String translatableCode;

    private AllBars(String name, ResourceLocation resourceLocation, int width, int height, boolean hor, String translatableCode){
        this.name = name;
        this.resourceLocation = resourceLocation;
        this.width = width;
        this.height = height;
        this.hor = hor;
        this.translatableCode = translatableCode;
    }

    public String getName(){
        return name;
    }
    public ResourceLocation getResourceLocation(){
        return resourceLocation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public boolean getHor() {
        return hor;
    }
    public String getTranslatableCode() {
        return translatableCode;
    }


}
