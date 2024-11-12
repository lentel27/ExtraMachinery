package net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryAdvanced;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryAdvanced extends ExtraScreenBase<ContainerApothecaryAdvanced> {
    private final BlockEntityApothecaryAdvanced tile;

    public ScreenApothecaryAdvanced(ContainerApothecaryAdvanced screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title, 27, 120);
        this.imageWidth = 184;
        this.imageHeight = 217;

        this.titleLabelY = -99999;
        this.inventoryLabelY = -99999;

        Map<Integer, int[]> seed = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        seed.put(0, new int[] {84, 94});
        upgrades.put(1, new int[] {47, 94});

        this.apothecarySlotInfo.setCoord(seed, upgrades);

        this.tile = (BlockEntityApothecaryAdvanced)this.menu.getLevel().getBlockEntity(this.menu.getPos());
    }

    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.ADVANCED_MECHANICAL_APOTHECARY_GUI);
        this.drawLabelText(guiGraphics);

        if (this.tile.getInventory().getStackInSlot(0).isEmpty()) {
            List<ItemStack> items = new ArrayList<>();
            BuiltInRegistries.ITEM.getTagOrEmpty(Tags.Items.SEEDS).forEach(holder -> items.add(new ItemStack(holder.value())));

            GhostItemRenderer.renderGhostItem(items, guiGraphics, this.leftPos + 84, this.topPos + 94);
        }

        if (this.tile.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(this.tile.getUpgrades(), guiGraphics, this.leftPos + 47, this.topPos + 94);
        }

        this.apothecarySlotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, this.tile.getInventory(), new boolean[]{true, true});


        if (this.tile.getProgress() > 0) {
            float pctProgress = Math.min((float)this.tile.getProgress() / (float)this.tile.getMaxProgress(), 1.0F);
            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.ADVANCED_MECHANICAL_APOTHECARY_GUI, this.leftPos + 87, this.topPos + 31, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("block.botanicalextramachinery.advanced_apothecary");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        guiGraphics.drawString(
                this.font,
                titleText,
                (int)((leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale),
                (int)((topPos + 4) / scale),
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