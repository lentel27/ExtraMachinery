package net.lmor.botanicalextramachinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.lmor.botanicalextramachinery.util.NumberFormatter;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import vazkii.botania.client.core.handler.ClientTickHandler;

import java.util.ArrayList;
import java.util.List;

public class Bars {
    private final Screen parent;
    private List<AllBars> bars = new ArrayList<>();

    public List<Integer> x = new ArrayList<>();
    public List<Integer> y = new ArrayList<>();

    public int guiLeft;
    public int guiTop;

    public Bars(Screen parent) {
        this.parent = parent;
    }

    public void setDrawCoord(int x_, int y_){
        this.x.add(x_);
        this.y.add(y_);
    }

    public void setBar(AllBars bar){
        this.bars.add(bar);
    }

    public void setGuiCoord(int x, int y){
        guiLeft = x;
        guiTop = y;
    }

    public boolean isMouseOver(int mouseX, int mouseY, int index) {
        if (this.guiLeft + this.x.get(index) - 1 < mouseX){
            if (mouseX < this.guiLeft + this.x.get(index) + bars.get(index).getWidth() + 1 && this.guiTop + this.y.get(index) - 1 < mouseY){
                if (mouseY < this.guiTop + this.y.get(index) + bars.get(index).getHeight() + 1){
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(PoseStack poseStack, int[] storage, int[] maxStorage, boolean infinityUpgrade) {
        for (int i = 0; i < this.x.size(); i++){
            ResourceLocation barRL = bars.get(i).getResourceLocation();

            if (infinityUpgrade && (bars.get(i) == AllBars.MANA || bars.get(i) == AllBars.WATER)){
                barRL = AllBars.INFINITY_MANA.getResourceLocation();

                int rgb = Mth.hsvToRgb(ClientTickHandler.ticksInGame * 3 % 360 / 360F, 0.25F, 1F);

                float red = ((rgb >> 16) & 0xFF) / 255F; // Старшие 8 бит
                float green = ((rgb >> 8) & 0xFF) / 255F; // Средние 8 бит
                float blue = (rgb & 0xFF) / 255F; // Младшие 8 бит

                RenderSystem.setShaderColor(red, green, blue, 1.0F);
            }

            RenderSystem.setShaderTexture(0, barRL);


            float pct = Math.min((float) storage[i] / (float) maxStorage[i], 1.0F);
            int rel = (int)((float)(bars.get(i).getHor() ? bars.get(i).getWidth() : bars.get(i).getHeight()) * pct);

            GuiComponent.blit(
                    poseStack,
                    this.guiLeft + this.x.get(i), this.guiTop + this.y.get(i),
                    0.0F, 0.0F,
                    bars.get(i).getHor() ? rel : bars.get(i).getWidth(), bars.get(i).getHor() ? bars.get(i).getHeight() : rel, bars.get(i).getWidth(),
                    bars.get(i).getHeight()
            );
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void renderHoveredToolTip(PoseStack poseStack, int mouseX, int mouseY, int storage, int maxStorage, int index) {
        if (this.isMouseOver(mouseX, mouseY, index) && LibXClientConfig.numericalTooltip) {
            Component text = Component.translatable(bars.get(index).getTranslatableCode(),
                    Component.literal(NumberFormatter.formatInteger(storage)),
                    Component.literal(NumberFormatter.formatInteger(maxStorage)));
            this.parent.renderTooltip(poseStack, text, mouseX, mouseY);
        }

    }
}
