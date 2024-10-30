package net.lmor.botaniaextramachinery.blocks.tesr.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderOrechidAdvanced implements BlockEntityRenderer<BlockEntityOrechidAdvanced> {
    public RenderOrechidAdvanced() {
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
    public void render(BlockEntityOrechidAdvanced blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {}
}
