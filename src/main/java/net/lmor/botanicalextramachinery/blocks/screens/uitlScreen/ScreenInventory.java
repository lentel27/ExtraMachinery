package net.lmor.botanicalextramachinery.blocks.screens.uitlScreen;

import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.resources.ResourceLocation;

public enum ScreenInventory {

    BASE("base", LibResources.BASE_INVENTORY, 188, 95, 2, 80, 83),
    UPGRADE("upgrade", LibResources.UPGRADE_INVENTORY, 188, 95, 2, 80, 83),
    ADVANCED("advanced", LibResources.ADVANCED_INVENTORY, 188, 95, 2, 80, 83),
    ULTIMATE("ultimate", LibResources.ULTIMATE_INVENTORY, 188, 95, 2, 80, 83),
    OTHER("other", LibResources.OTHER_INVENTORY, 188, 95, 2, 80, 83);

    private final String name;
    private final ResourceLocation resourceLocation;
    private final int width;
    private final int height;
    private final int offset;
    private final int xFirstCenterSlot;
    private final int yFirstBottomSlot;

    private ScreenInventory(String name, ResourceLocation resourceLocation, int width, int height, int offset, int xFirstCenterSlot, int yFirstBottomSlot){
        this.name = name;
        this.resourceLocation = resourceLocation;
        this.width = width;
        this.height = height;
        this.offset = offset;
        this.xFirstCenterSlot = xFirstCenterSlot;
        this.yFirstBottomSlot = yFirstBottomSlot;
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

    public int getOffset() {
        return offset;
    }

    public int getXFirstCenterSlot() {
        return xFirstCenterSlot;
    }

    public int getYFirstBottomSlot() {
        return yFirstBottomSlot;
    }
}
