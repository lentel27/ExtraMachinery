package net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenIndustrialAgglomerationFactoryUpgraded extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryUpgraded> {

    BlockEntityIndustrialAgglomerationFactoryUpgraded blockEntity;
    public ScreenIndustrialAgglomerationFactoryUpgraded(ContainerIndustrialAgglomerationFactoryUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 87);
        this.imageWidth = 184;
        this.imageHeight = 183;

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryUpgraded)((ContainerIndustrialAgglomerationFactoryUpgraded)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);
        this.drawLabelText(poseStack);

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 40, this.imageWidth, 0, 40, height);
        }
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("text.botaniaextramachinery.upgraded_industrial_agglomeration_factory_label_text_1");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 4) /scale, 0x00);
        poseStack.popPose();

        titleText = Component.translatable("text.botaniaextramachinery.upgraded_industrial_agglomeration_factory_label_text_2");
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
