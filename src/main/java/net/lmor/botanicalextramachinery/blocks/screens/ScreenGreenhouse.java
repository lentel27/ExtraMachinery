package net.lmor.botanicalextramachinery.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.ContainerGreenhouse;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityGreenhouse;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ScreenGreenhouse extends ExtraScreenBase<ContainerGreenhouse> {

    BlockEntityGreenhouse blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ULTIMATE);
    Bars bars;
    SlotInfo slotInfo;


    public ScreenGreenhouse(ContainerGreenhouse menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerGreenhouse.WIDTH_GUI;
        this.imageHeight = ContainerGreenhouse.HEIGHT_GUI;

        Map<Integer, int[]> slots = new HashMap<>();
        String[] translates = new String[8];

        for (int i = 0; i < 4; i++){
            slots.put(i, new int[]{22, 34 + i * 20});
            slots.put(i + 4, new int[]{178, 34 + i * 20});
            translates[i] = "botanicalextramachinery.tooltip.screen.upgrade_slot";
            translates[i + 4] = "botanicalextramachinery.tooltip.screen.upgrade_slot";
        }

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(translates);

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(43, 104);

        bars.setBar(AllBars.ENERGY);
        bars.setDrawCoord(43, 114);

        bars.setBar(AllBars.HEAT);
        bars.setBar(AllBars.HEAT);
        bars.setDrawCoord(10, 60);
        bars.setDrawCoord(202, 60);

        blockEntity = this.menu.getBlockEntity();
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.GREENHOUSE_GUI, screenAddInventory,
                new int[] { blockEntity.getCurrentMana(), blockEntity.getEnergyStored(), blockEntity.getCountHeat(), blockEntity.getCountHeat() },
                new int[] { blockEntity.getMaxMana(), blockEntity.getMaxEnergyStored(), blockEntity.getMaxCountHeat(), blockEntity.getMaxCountHeat() },
                bars, slotInfo
        );

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.greenhouse",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        for (int i = 0; i < blockEntity.getUpgradeInventory().getSlots(); i++){
            if (this.minecraft != null && blockEntity.getUpgradeInventory().getStackInSlot(i).isEmpty()) {
                int x;
                int y;

                if (i < 4){
                    x = 22;
                    y = 34 + 20 * i;
                } else {
                    x = 178;
                    y = 34 + 20 * (i - 4);
                }

                GhostItemRenderer.renderGhostItem(blockEntity.getUpgradeSlot(i).stream().map(ItemStack::new).toList(), poseStack, this.leftPos + x, this.topPos + y);
            }
        }

        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getUpgradeInventory());

        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getEnergyStored(), blockEntity.getMaxEnergyStored(), 1);
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCountHeat(), blockEntity.getMaxCountHeat(), 2);
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCountHeat(), blockEntity.getMaxCountHeat(), 3);
    }
}
