package net.lmor.botanicalextramachinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.util.NumberFormatter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ManaBar {
    private final Screen parent;
    public static int x;
    public static int y;
    public final int capacity;
    private static int width = 129;
    private static int height = 5;
    public int guiLeft;
    public int guiTop;

    public ManaBar(Screen parent, int capacity, int x , int y) {
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

    public void draw(GuiGraphics guiGraphics, float mana) {
        ResourceLocation manaBar = LibResources.MANA_BAR_CURRENT;

        RenderSystem.setShaderTexture(0, manaBar);

        float pct = Math.min(mana / (float)this.capacity, 1.0F);
        int relWidth = (int)((float)(129) * pct);

        guiGraphics.blit(manaBar, this.guiLeft + x, this.guiTop + y, 0.0F, 0.0F, relWidth, 5, 129, 5);

    }

    public void renderHoveredToolTip(GuiGraphics guiGraphics, int mouseX, int mouseY, int mana) {
        if (this.isMouseOver(mouseX, mouseY) && LibXClientConfig.numericalFluid) {
            Component text = Component.translatable("botanicalextramachinery.tooltip.screen",
                    Component.literal(NumberFormatter.formatInteger(mana)),
                    Component.literal(NumberFormatter.formatInteger(this.capacity)));
            guiGraphics.renderTooltip(this.parent.getMinecraft().font, text, mouseX, mouseY);
        }

    }
}
