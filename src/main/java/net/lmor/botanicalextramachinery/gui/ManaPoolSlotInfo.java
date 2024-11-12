package net.lmor.botanicalextramachinery.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.noeppi_noeppi.libx.inventory.BaseItemStackHandler;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ManaPoolSlotInfo {

    private final Screen parent;
    public static int x_slot_1;
    public static int y_slot_1;
    public static int x_slot_2;
    public static int y_slot_2;
    private static int width = 18;
    private static int height = 18;
    public int guiLeft;
    public int guiTop;

    public ManaPoolSlotInfo(Screen parent) {
        this.parent = parent;
    }

    public void setCoord(int[] slot_1, int[] slot_2 ){
        x_slot_1 = slot_1[0];
        y_slot_1 = slot_1[1];

        x_slot_2 = slot_2[0];
        y_slot_2 = slot_2[1];
    }

    public void setGuiCoord(int x, int y){
        guiLeft = x;
        guiTop = y;
    }

    public boolean isMouseOverCatalystSlot(int mouseX, int mouseY) {
        if (this.guiLeft + x_slot_1 < mouseX){
            if (mouseX < this.guiLeft + x_slot_1 + width && this.guiTop + y_slot_1 < mouseY){
                if (mouseY < this.guiTop + y_slot_1 + height){
                    return true;
                }
            }

        }
        return false;
    }

    public boolean isMouseOverUpgradeSlot(int mouseX, int mouseY) {
        if (this.guiLeft + x_slot_2 < mouseX){
            if (mouseX < this.guiLeft + x_slot_2 + width && this.guiTop + y_slot_2 < mouseY){
                if (mouseY < this.guiTop + y_slot_2 + height){
                    return true;
                }
            }

        }
        return false;
    }

    public void renderHoveredToolTip(PoseStack poseStack, int mouseX, int mouseY, BaseItemStackHandler inventory, boolean[] setInfo) {
        if (setInfo[0] && this.isMouseOverCatalystSlot(mouseX, mouseY) && LibXClientConfig.slotInfo && inventory.getStackInSlot(0).isEmpty()) {
            Component text = new TranslatableComponent("botanicalextramachinery.tooltip.screen.catalyst_slot");
            this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
        }

        if (setInfo[1] && this.isMouseOverUpgradeSlot(mouseX, mouseY) && LibXClientConfig.slotInfo && inventory.getStackInSlot(1).isEmpty()) {
            Component text = new TranslatableComponent("botanicalextramachinery.tooltip.screen.upgrade_slot");
            this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
        }

    }

}
