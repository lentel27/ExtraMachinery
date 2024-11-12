package net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar;

import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarBase;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarBase extends ExtraScreenBase<ContainerRunicAltarBase> {
    BlockEntityRunicAltarBase blockEntity;
    public ScreenRunicAltarBase(ContainerRunicAltarBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 120);

        this.imageWidth = 184;
        this.imageHeight = 216;

        Map<Integer, int[]> livingrock = new HashMap<>();

        livingrock.put(0, new int[] {84, 94});

        this.runicAltarSlotInfo.setCoord(livingrock, null);

        blockEntity = this.menu.getBlockEntity();
    }

    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.BASE_MECHANICAL_RUNIC_ALTAR_GUI);
        this.drawLabelText(guiGraphics);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), guiGraphics, this.leftPos + 84, this.topPos + 94);
        }

        this.runicAltarSlotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            guiGraphics.blit(LibResources.BASE_MECHANICAL_RUNIC_ALTAR_GUI, this.leftPos + 87, this.topPos + 31, this.imageWidth, 0, Math.round(11.0F * pct), 37);
        }
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("block.botanicalextramachinery.base_runic_altar");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        guiGraphics.drawString(
                this.font,
                titleText,
                (int)((leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale),
                (int)((topPos + 5) / scale),
                0x00, false
        );
        guiGraphics.pose().popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
