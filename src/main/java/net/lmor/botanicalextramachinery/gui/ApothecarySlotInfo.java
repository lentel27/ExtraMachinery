package net.lmor.botanicalextramachinery.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.noeppi_noeppi.libx.inventory.BaseItemStackHandler;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.HashMap;
import java.util.Map;

public class ApothecarySlotInfo {

    private final Screen parent;
    public static Map<Integer, int[]> upgrade_slots = new HashMap<>();
    public static Map<Integer, int[]> seeds_slots = new HashMap<>();
    private static int width = 16;
    private static int height = 16;
    public int guiLeft;
    public int guiTop;

    public ApothecarySlotInfo(Screen parent) {
        this.parent = parent;
    }

    public void setCoord(Map<Integer, int[]> slot_1, Map<Integer, int[]> slot_2){

        if (slot_1 != null) { seeds_slots = slot_1; }
        if (slot_2 != null) { upgrade_slots = slot_2; }

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


    public void renderHoveredToolTip(PoseStack poseStack, int mouseX, int mouseY, BaseItemStackHandler inventory, boolean[] setInfo) {
        if (setInfo[0] && LibXClientConfig.slotInfo && seeds_slots.size() >= 1) {
            for (Integer key: seeds_slots.keySet()){
                int[] x_y = seeds_slots.get(key);
                if (inventory.getStackInSlot(key).isEmpty()){
                    if (isMouseOver(mouseX, mouseY, x_y[0], x_y[1])){
                        Component text = new TranslatableComponent("botanicalextramachinery.tooltip.screen.seed_slot");
                        this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
                    }
                }
            }

        }

        if (setInfo[1] && LibXClientConfig.slotInfo && upgrade_slots.size() >= 1) {
            for (Integer key: upgrade_slots.keySet()){
                int [] x_y = upgrade_slots.get(key);
                if (isMouseOver(mouseX, mouseY, x_y[0], x_y[1])){
                    if (inventory.getStackInSlot(key).isEmpty()){
                        Component text = new TranslatableComponent("botanicalextramachinery.tooltip.screen.upgrade_slot");
                        this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
                    }
                }
            }
        }

    }

}
