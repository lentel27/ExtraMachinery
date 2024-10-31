package net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ScreenManaPoolUltimate extends ExtraScreenBase<ContainerManaPoolUltimate> {

    BlockEntityManaPoolUltimate blockEntity;
    public ScreenManaPoolUltimate(ContainerManaPoolUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 89);

        this.imageWidth = 176;
        this.imageHeight = 189;
        this.manaPoolSlotInfo.setCoord(
                new int[] {80, 63},
                new int[] {80, 16});

        blockEntity = (BlockEntityManaPoolUltimate)((ContainerManaPoolUltimate)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_MECHANICAL_MANA_POOL_GUI);
        this.drawLabelText(poseStack);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 80, this.topPos + 63);
        }
        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 80, this.topPos + 16);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.ultimate_mana_pool");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 4) /scale, 0x00);
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
