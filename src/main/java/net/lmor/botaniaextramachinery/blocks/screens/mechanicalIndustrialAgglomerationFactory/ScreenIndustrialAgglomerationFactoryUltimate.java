package net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.ModItems;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenIndustrialAgglomerationFactoryUltimate extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryUltimate> {

    BlockEntityIndustrialAgglomerationFactoryUltimate blockEntity;

    public ScreenIndustrialAgglomerationFactoryUltimate(ContainerIndustrialAgglomerationFactoryUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 119);
        this.imageWidth = 184;
        this.imageHeight = 215;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {9, 84});
        upgrades.put(1, new int[] {158, 84});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryUltimate)((ContainerIndustrialAgglomerationFactoryUltimate)this.menu).getBlockEntity();

    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);
        this.drawLabelText(poseStack);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.catalystManaInfinity));
                items.add(new ItemStack(ModItems.catalystSpeed));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 9 + (149 * i), this.topPos + 84);
            }
        }

        this.agglomerationSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 58, this.imageWidth, 0, 40, height);
        }
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("text.botaniaextramachinery.ultimate_industrial_agglomeration_factory_label_text_1");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 4) /scale, 0x00);
        poseStack.popPose();

        titleText = Component.translatable("text.botaniaextramachinery.ultimate_industrial_agglomeration_factory_label_text_2");
        scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 12) /scale, 0x00);
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
