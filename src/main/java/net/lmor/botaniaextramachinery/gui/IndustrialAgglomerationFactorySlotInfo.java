package net.lmor.botaniaextramachinery.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.config.LibXClientConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import java.util.HashMap;
import java.util.Map;

public class IndustrialAgglomerationFactorySlotInfo {

    private final Screen parent;
    public static Map<Integer, int[]> upgrade_slots = new HashMap<>();
    private static int width = 16;
    private static int height = 16;
    public int guiLeft;
    public int guiTop;

    public IndustrialAgglomerationFactorySlotInfo(Screen parent) {
        this.parent = parent;
    }


    public void setCoord(Map<Integer, int[]> slot_1){
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


    public void renderHoveredToolTip(PoseStack poseStack, int mouseX, int mouseY, BaseItemStackHandler inventory) {
        if (LibXClientConfig.slotInfo && upgrade_slots.size() >= 1) {
            for (Integer key: upgrade_slots.keySet()){
                int [] x_y = upgrade_slots.get(key);
                if (isMouseOver(mouseX, mouseY, x_y[0], x_y[1])){
                    if (inventory.getStackInSlot(key).isEmpty()){
                        Component text = Component.translatable("botaniaextramachinery.tooltip.screen.upgrade_slot");
                        this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
                    }
                }
            }
        }

    }

}
