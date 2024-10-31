package net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolBase;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenManaPoolBase extends ExtraScreenBase<ContainerManaPoolBase> {

    BlockEntityManaPoolBase blockEntity;
    public ScreenManaPoolBase(ContainerManaPoolBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 82);

        this.imageWidth = 176;
        this.imageHeight = 182;
        this.manaPoolSlotInfo.setCoord(
                new int[] {89, 57},
                new int[] {0, 0});

        blockEntity = (BlockEntityManaPoolBase)((ContainerManaPoolBase)this.menu).getBlockEntity();

    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_MECHANICAL_MANA_POOL_GUI);
        this.drawLabelText(poseStack);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 89, this.topPos + 57);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botaniaextramachinery.base_mana_pool");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 15) /scale, 0x00);
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
