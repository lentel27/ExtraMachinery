package net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.gui.GuiGraphics;
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

        blockEntity = this.menu.getBlockEntity();
    }

    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);
        this.drawLabelText(guiGraphics);

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI, this.leftPos + 72, this.topPos + 40, this.imageWidth, 0, 40, height);
        }
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("text.botanicalextramachinery.upgraded_industrial_agglomeration_factory_label_text_1");
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

        titleText = Component.translatable("text.botanicalextramachinery.upgraded_industrial_agglomeration_factory_label_text_2");
        scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        guiGraphics.drawString(
                this.font,
                titleText,
                (int)((leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale),
                (int)((topPos + 12) / scale),
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
