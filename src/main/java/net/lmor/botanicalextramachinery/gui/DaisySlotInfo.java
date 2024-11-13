package net.lmor.botanicalextramachinery.gui;

import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import java.util.HashMap;
import java.util.Map;

public class DaisySlotInfo {

    private final Screen parent;
    public static int[] upgrade_slots;
    private static int width = 16;
    private static int height = 16;
    public int guiLeft;
    public int guiTop;

    public DaisySlotInfo(Screen parent) {
        this.parent = parent;
    }


    public void setCoord(int[] slot_1){
        if (slot_1 != null) { upgrade_slots = slot_1; }
    }

    public void setGuiCoord(int x, int y){
        guiLeft = x;
        guiTop = y;
    }

    public boolean isMouseOver(int mouseX, int mouseY, int x, int y){
        if (this.guiLeft + x <= mouseX){
            if (mouseX <= this.guiLeft + x + width  && this.guiTop + y <= mouseY){
                if (mouseY <= this.guiTop + y + height){
                    return true;
                }
            }
        }
        return false;
    }


    public void renderHoveredToolTip(GuiGraphics guiGraphics, int mouseX, int mouseY, BaseItemStackHandler inventory) {
        if (upgrade_slots != null && this.isMouseOver(mouseX, mouseY, upgrade_slots[0], upgrade_slots[1])
                && LibXClientConfig.slotInfo && inventory.getStackInSlot(0).isEmpty()) {
            Component text = Component.translatable("botanicalextramachinery.tooltip.screen.upgrade_slot");
            guiGraphics.renderTooltip(this.parent.getMinecraft().font, text, mouseX, mouseY);
        }
    }

}
