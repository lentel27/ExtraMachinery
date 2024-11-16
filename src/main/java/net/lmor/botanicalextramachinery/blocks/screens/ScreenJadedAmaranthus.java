package net.lmor.botanicalextramachinery.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.ContainerJadedAmaranthus;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityJadedAmaranthus;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenJadedAmaranthus extends ExtraScreenBase<ContainerJadedAmaranthus> {

    BlockEntityJadedAmaranthus blockEntity;

    public ScreenJadedAmaranthus(ContainerJadedAmaranthus menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 81);

        this.imageWidth = 184;
        this.imageHeight = 183;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> upgrades = new HashMap<>();

        upgrades.put(0, new int[]{66, 20});
        upgrades.put(1, new int[]{102, 20});

        this.jadedAmaranthusSlotInfo.setCoord(upgrades);

        blockEntity = this.menu.getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.JADED_AMARANTHUS_GUI);
        this.drawLabelText(poseStack);

        for (int i = 0; i < 2; i++) {
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.catalystManaInfinity));
                items.add(new ItemStack(ModItems.catalystPetal));
                items.add(new ItemStack(ModItems.catalystPetalBlock));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 66 + 36 * i, this.topPos + 20);
            }
        }

        this.jadedAmaranthusSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
    }

    private void drawLabelText(PoseStack poseStack) {
        Component titleText = Component.translatable("block.botanicalextramachinery.jaded_amaranthus");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 6) /scale, 0x00);
        poseStack.popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}