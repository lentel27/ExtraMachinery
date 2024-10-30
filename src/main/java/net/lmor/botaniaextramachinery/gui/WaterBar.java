package net.lmor.botaniaextramachinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.config.LibXClientConfig;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.lmor.botaniaextramachinery.util.NumberFormatter;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WaterBar {
    private final Screen parent;
    public static int x;
    public static int y;
    public final int capacity;
    private static int width = 129;
    private static int height = 5;
    public int guiLeft;
    public int guiTop;

    public WaterBar(Screen parent, int capacity, int x , int y) {
        this.parent = parent;
        this.capacity = capacity;

        this.x = x;
        this.y = y;
    }

    public void setGuiCoord(int x, int y){
        guiLeft = x;
        guiTop = y;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        if (this.guiLeft + x < mouseX){
            if (mouseX < this.guiLeft + x + width + 2 && this.guiTop + y < mouseY){
                if (mouseY < this.guiTop + y + height + 2){
                    return true;
                }
            }

        }
        return false;
    }

    public void draw(PoseStack poseStack, float fluid) {
        RenderSystem.setShaderTexture(0, LibResources.WATER_BAR_CURRENT);

        float pctFluid = Math.min(fluid / this.capacity, 1.0F);
        int relWidth = (int)((float)(129) * pctFluid);

        Screen.blit(poseStack,  this.guiLeft + x, this.guiTop + y, 0.0F, 0.0F, relWidth, 5, 129, 5);
    }

    public void renderHoveredToolTip(PoseStack ms, int mouseX, int mouseY, int countFluid) {
        if (this.isMouseOver(mouseX, mouseY) && LibXClientConfig.numericalFluid) {

            Component fluid = Component.literal(countFluid + " / " + NumberFormatter.formatIntegerWater(this.capacity));

            this.parent.renderTooltip(ms, fluid, mouseX, mouseY);
        }

    }
}
