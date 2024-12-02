package net.lmor.botanicalextramachinery.gui;

import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import java.util.*;

public class SlotInfo {

    private final Screen parent;
    private Map<Integer, int[]> slots = new HashMap<>();
    private List<String> translatables = new ArrayList<>();
    private static int width = 16;
    private static int height = 16;
    private int guiLeft;
    private int guiTop;

    public SlotInfo(Screen parent) {
        this.parent = parent;
    }

    public List<Integer> getSlotUpgrade(){
        if (this.slots == null) return null;

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < this.slots.size(); i++){
            String[] str = translatables.get(i).split("\\.");
            if (Objects.equals(str[str.length - 1], "upgrade_slot")){
                res.add(i);
            }
        }
        return res;
    }

    public void setCoord(Map<Integer, int[]> slots){
        if (slots != null) {
            this.slots = slots;
        }
    }

    public void setTranslatableText(String[] translatableTexts){
        if (translatableTexts != null){
            translatables.addAll(Arrays.asList(translatableTexts));
        }
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
        if (LibXClientConfig.slotInfo && slots.size() >= 1) {
            for (Integer key: slots.keySet()){
                int [] x_y = slots.get(key);
                if (isMouseOver(mouseX, mouseY, x_y[0], x_y[1])){
                    if (inventory.getStackInSlot(key).isEmpty()){
                        Component text = Component.translatable(translatables.get(key));
                        guiGraphics.renderTooltip(this.parent.getMinecraft().font, text, mouseX, mouseY);
                    }
                }
            }
        }
    }

}
