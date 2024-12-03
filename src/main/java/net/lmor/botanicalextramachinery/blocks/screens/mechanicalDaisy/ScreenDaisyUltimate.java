package net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalDaisy.ContainerDaisyUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenDaisyUltimate extends ExtraScreenBase<ContainerDaisyUltimate> {
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ULTIMATE);
    BlockEntityDaisyUltimate blockEntity;
    SlotInfo slotInfo;

    public ScreenDaisyUltimate(ContainerDaisyUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerDaisyUltimate.WIDTH_GUI;
        this.imageHeight = ContainerDaisyUltimate.HEIGHT_GUI;

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {166, 105});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] { "botanicalextramachinery.tooltip.screen.upgrade_slot"});

        blockEntity = this.menu.getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_MECHANICAL_DAISY_GUI, screenAddInventory,
                new int[] {}, new int[] {}, null, slotInfo);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.ultimate_daisy",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 3);

        if (blockEntity.getInventoryUpgrade() != null && blockEntity.getInventoryUpgrade().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), poseStack, this.leftPos + 166, this.topPos + 105);
        }

        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventoryUpgrade());
    }
}
