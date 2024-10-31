package net.lmor.botanicalextramachinery.blocks.tesr.mechanicalRunicAltar;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUltimate;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.moddingx.libx.render.block.RotatedBlockRenderer;
import vazkii.botania.client.core.handler.ClientTickHandler;

import javax.annotation.Nonnull;

public class RendererRunicAltarUltimate extends RotatedBlockRenderer<BlockEntityRunicAltarUltimate> {
    public RendererRunicAltarUltimate() {
    }

    protected void doRender(@Nonnull BlockEntityRunicAltarUltimate tile, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (LibXClientConfig.RenderingVisualContent.all && LibXClientConfig.RenderingVisualContent.RunicAltarSettings.runicAltarUltimate) {
            ItemStack livingRockStack_1 = tile.getInventory().getStackInSlot(0);
            ItemStack livingRockStack_2 = tile.getInventory().getStackInSlot(1);
            ItemStack livingRockStack_3 = tile.getInventory().getStackInSlot(2);
            if ((!livingRockStack_1.isEmpty() && livingRockStack_1.getItem() instanceof BlockItem) ||
                    (!livingRockStack_2.isEmpty() && livingRockStack_2.getItem()instanceof BlockItem) ||
                    (!livingRockStack_3.isEmpty() && livingRockStack_3.getItem() instanceof BlockItem)) {

                BlockState state = null;

                if (!livingRockStack_1.isEmpty()){
                    state = ((BlockItem)livingRockStack_1.getItem()).getBlock().defaultBlockState();
                } else if (!livingRockStack_2.isEmpty()){
                    state = ((BlockItem)livingRockStack_2.getItem()).getBlock().defaultBlockState();
                } else {
                    state = ((BlockItem)livingRockStack_3.getItem()).getBlock().defaultBlockState();
                }

                poseStack.pushPose();
                poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                poseStack.translate(6.5, 10.0, 6.5);
                poseStack.scale(3.0F, 3.0F, 3.0F);
                poseStack.translate(0.5, 0.0, 0.5);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-((float) ClientTickHandler.ticksInGame + partialTicks)));
                poseStack.translate(-0.5, 0.0, -0.5);
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, 200, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }

            double progressLeft = 1.0 - (double)tile.getProgress() / (double)tile.getMaxProgress() * 0.9;
            int items = 0;

            for(int slot = 5; slot < 21; ++slot) {
                if (!tile.getInventory().getStackInSlot(slot).isEmpty()) {
                    ++items;
                }
            }

            float[] angles = new float[items];
            float anglePer = 360.0F / (float)items;
            float totalAngle = 0.0F;

            for(int i = 0; i < angles.length; ++i) {
                angles[i] = totalAngle += anglePer;
            }

            float time = (float)ClientTickHandler.ticksInGame + partialTicks;
            int nextAngleIdx = 0;

            for(int slot = 5; slot < 21; ++slot) {
                if (!tile.getInventory().getStackInSlot(slot).isEmpty()) {
                    double travelCenter = 1.0;
                    boolean shrink = false;
                    if (tile.isSlotUsedCurrently(slot)) {
                        travelCenter = progressLeft;
                    } else if (tile.getProgress() > 0) {
                        shrink = true;
                    }

                    int angleIdx = nextAngleIdx++;
                    if (angleIdx >= angles.length) {
                        break;
                    }

                    poseStack.pushPose();
                    poseStack.translate(0.5, 0.675, 0.5);
                    poseStack.scale(0.3F, 0.3F, 0.3F);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(angles[angleIdx] + time));
                    poseStack.translate(travelCenter * 1.125, 0.0, travelCenter * 0.25);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
                    poseStack.translate(0.0, 0.075 * Math.sin((double)(time + (float)(angleIdx * 10)) / 5.0), 0.0);
                    if (shrink) {
                        poseStack.scale(0.3F, 0.3F, 0.3F);
                    }

                    ItemStack stack = tile.getInventory().getStackInSlot(slot);
                    Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong() + slot);
                    poseStack.popPose();
                }
            }

        }
    }
}