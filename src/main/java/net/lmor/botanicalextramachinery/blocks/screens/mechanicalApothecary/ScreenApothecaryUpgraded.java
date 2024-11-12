package net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryUpgraded;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUpgraded;
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

public class ScreenApothecaryUpgraded extends ExtraScreenBase<ContainerApothecaryUpgraded> {
    private final BlockEntityApothecaryUpgraded tile;

    public ScreenApothecaryUpgraded(ContainerApothecaryUpgraded screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title, 27, 110);
        this.imageWidth = 184;
        this.imageHeight = 206;

        this.titleLabelY = -99999;
        this.inventoryLabelY = -99999;

        Map<Integer, int[]> seed = new HashMap<>();

        seed.put(0, new int[] {84, 89});

        this.apothecarySlotInfo.setCoord(seed, null);


        this.tile = (BlockEntityApothecaryUpgraded)((ContainerApothecaryUpgraded)this.menu).getLevel().getBlockEntity(((ContainerApothecaryUpgraded)this.menu).getPos());
    }

    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI);
        this.drawLabelText(guiGraphics);

        if (this.tile.getInventory().getStackInSlot(0).isEmpty()) {
            List<ItemStack> items = new ArrayList<>();
            BuiltInRegistries.ITEM.getTagOrEmpty(Tags.Items.SEEDS).forEach(holder -> items.add(new ItemStack(holder.value())));
            GhostItemRenderer.renderGhostItem(items, guiGraphics, this.leftPos + 84, this.topPos + 89);
        }

        this.apothecarySlotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, this.tile.getInventory(), new boolean[]{true, false});


        if (this.tile.getProgress() > 0) {
            float pctProgress = Math.min((float) this.tile.getProgress() / (float) this.tile.getMaxProgress(), 1.0F);
            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI, this.leftPos + 87, this.topPos + 31, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("block.botanicalextramachinery.upgraded_apothecary");
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
