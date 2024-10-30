package net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryBase;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenIndustrialAgglomerationFactoryBase extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryBase> {
    BlockEntityIndustrialAgglomerationFactoryBase blockEntity;

    public ScreenIndustrialAgglomerationFactoryBase(ContainerIndustrialAgglomerationFactoryBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 96);
        this.imageWidth = 184;
        this.imageHeight = 192;

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryBase)((ContainerIndustrialAgglomerationFactoryBase)this.menu).getBlockEntity();

    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);
        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.BASE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 49, this.imageWidth, 0, 40, height);
        }

    }
}
