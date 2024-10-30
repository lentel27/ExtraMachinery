package net.lmor.botaniaextramachinery.blocks.screens.mechanicalDaisy;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalDaisy.ContainerDaisyUltimate;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenDaisyUltimate extends ExtraScreenBase<ContainerDaisyUltimate> {
    public ScreenDaisyUltimate(ContainerDaisyUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, -999, -999);

        this.imageWidth = 184;
        this.imageHeight = 220;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_MECHANICAL_DAISY_GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
