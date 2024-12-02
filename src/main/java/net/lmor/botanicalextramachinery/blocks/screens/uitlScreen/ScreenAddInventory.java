package net.lmor.botanicalextramachinery.blocks.screens.uitlScreen;

import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;

public class ScreenAddInventory {

    private static ScreenInventory inventoryScreen;

    public ScreenAddInventory(ScreenInventory inv){
        inventoryScreen = inv;
    }

    public int[] getCoordInventorySlot(int width, int height){
        return new int[] { width / 2 - inventoryScreen.getXFirstCenterSlot(), getHeightGuiMax(height) - inventoryScreen.getYFirstBottomSlot()};
    }

    public ScreenInventory getInventoryScreen() {
        return inventoryScreen;
    }

    public int getWidthGuiMax(int width){
        return Math.max(width, inventoryScreen.getWidth());
    }

    public int getHeightGuiMax(int height){
        return height + inventoryScreen.getOffset() + inventoryScreen.getHeight();
    }

    public int getXBlitScreen(int leftPos, int imageWidth){
        return leftPos + imageWidth / 2 - inventoryScreen.getWidth() / 2;
    }

    public int getYBlitScreen(int topPos, int imageHeight){
        return topPos + imageHeight - inventoryScreen.getHeight();
    }

    public static int[] getCoordInventorySlot(ScreenInventory inv, int width, int height){
        return new int[] { width / 2 - inv.getXFirstCenterSlot(), getHeightGuiMax(inv, height) - inv.getYFirstBottomSlot()};
    }

    public static int getWidthGuiMax(ScreenInventory inv, int width){
        return Math.max(width, inv.getWidth());
    }

    public static int getHeightGuiMax(ScreenInventory inv, int height){
        return height + inv.getOffset() + inv.getHeight();
    }

}
