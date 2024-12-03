package net.lmor.botanicalextramachinery.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.ContainerJadedAmaranthus;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityJadedAmaranthus;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenJadedAmaranthus extends ExtraScreenBase<ContainerJadedAmaranthus> {
    BlockEntityJadedAmaranthus blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.OTHER);
    List<ItemStack> items = new ArrayList<>();
    Bars bars;
    SlotInfo slotInfo;


    public ScreenJadedAmaranthus(ContainerJadedAmaranthus menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerJadedAmaranthus.WIDTH_GUI;
        this.imageHeight = ContainerJadedAmaranthus.HEIGHT_GUI;

        blockEntity = this.menu.getBlockEntity();

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(33, 102);

        Map<Integer, int[]> slots = new HashMap<>();

        slots.put(0, new int[]{72, 23});
        slots.put(1, new int[]{108, 23});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] { "botanicalextramachinery.tooltip.screen.upgrade_slot", "botanicalextramachinery.tooltip.screen.upgrade_slot" });

        items.add(new ItemStack(ModItems.catalystManaInfinity));
        items.add(new ItemStack(ModItems.catalystPetal));
        items.add(new ItemStack(ModItems.catalystPetalBlock));
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.JADED_AMARANTHUS_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.jaded_amaranthus",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        for (int i = 0; i < 2; i++) {
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 72 + 36 * i, this.topPos + 23);
            }
        }

        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}