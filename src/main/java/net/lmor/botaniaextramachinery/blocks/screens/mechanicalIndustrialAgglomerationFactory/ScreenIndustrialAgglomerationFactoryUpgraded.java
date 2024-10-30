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
        super(menu, inventory, title, 27, 96);
        this.imageWidth = 184;
        this.imageHeight = 192;

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryUpgraded)((ContainerIndustrialAgglomerationFactoryUpgraded)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 49, this.imageWidth, 0, 40, height);
        }

    }
}
