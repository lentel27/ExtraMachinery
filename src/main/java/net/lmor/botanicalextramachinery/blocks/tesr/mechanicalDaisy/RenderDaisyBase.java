package net.lmor.botanicalextramachinery.blocks.tesr.mechanicalDaisy;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderDaisyBase implements BlockEntityRenderer<BlockEntityDaisyBase> {
    public RenderDaisyBase() {
    }

    private void renderState(@Nullable BlockState state, float translateX, float translateZ, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (state != null) {
            poseStack.pushPose();
            poseStack.translate((double)translateX, 0.0, (double)translateZ);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, light, overlay);
            poseStack.popPose();
        }

    }

    @Override
    public void render(BlockEntityDaisyBase blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {}
}