package net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUpgraded;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryUpgraded extends ExtraScreenBase<ContainerApothecaryUpgraded> {
    private final BlockEntityApothecaryUpgraded blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.UPGRADE);
    List<ItemStack> items = new ArrayList<>();
    Bars bars;
    SlotInfo slotInfo;

    public ScreenApothecaryUpgraded(ContainerApothecaryUpgraded screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerApothecaryUpgraded.WIDTH_GUI;
        this.imageHeight = ContainerApothecaryUpgraded.HEIGHT_GUI;

        bars.setBar(AllBars.WATER);
        bars.setDrawCoord(33, 116);

        this.blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {90, 95});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] { "botanicalextramachinery.tooltip.screen.seed_slot" });

        BuiltInRegistries.ITEM.getTagOrEmpty(Tags.Items.SEEDS).forEach(holder -> items.add(new ItemStack(holder.value())));
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI, screenAddInventory,
                new int[] {blockEntity.getFluidInventory().getFluidAmount()}, new int[] {blockEntity.getFluidInventory().getCapacity()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(guiGraphics, this.font, "block.botanicalextramachinery.upgraded_apothecary",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        if (this.blockEntity.getInventory().getStackInSlot(0).isEmpty()) {
            GhostItemRenderer.renderGhostItem(items, guiGraphics, this.leftPos + 90, this.topPos + 95);
        }

        if (this.blockEntity.getProgress() > 0) {
            float pctProgress = Math.min((float) this.blockEntity.getProgress() / (float) this.blockEntity.getMaxProgress(), 1.0F);
            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI, this.leftPos + 93, this.topPos + 41, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }

        slotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, this.blockEntity.getInventory());
        bars.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getFluidInventory().getFluidAmount(), blockEntity.getFluidInventory().getCapacity(), 0);
    }
}
