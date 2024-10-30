package net.lmor.botaniaextramachinery.blocks.tesr.mechanicalAlfheimMarket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUpgraded;
import net.lmor.botaniaextramachinery.config.LibXClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemStack;
import org.moddingx.libx.render.block.RotatedBlockRenderer;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;

public class RenderAlpheimMarketUpgraded extends RotatedBlockRenderer<BlockEntityAlfheimMarketUpgraded> {
    public RenderAlpheimMarketUpgraded() {
    }

    public void doRender(@Nonnull BlockEntityAlfheimMarketUpgraded tile, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (LibXClientConfig.RenderingVisualContent.all && LibXClientConfig.RenderingVisualContent.AlfheimMarketSettings.alfheimMarketUpgraded) {
            poseStack.pushPose();
            poseStack.scale(0.0625F, 0.0625F, 0.0625F);
            poseStack.translate(3.2, 2.0, 3.6);
            poseStack.scale(3.6F, 3.6F, 3.6F);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(BotaniaBlocks.naturaPylon.defaultBlockState(), poseStack, buffer, light, overlay);
            poseStack.translate(1.5555555555555556, 0.0, 0.0);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(BotaniaBlocks.naturaPylon.defaultBlockState(), poseStack, buffer, light, overlay);
            poseStack.popPose();
            if (tile.getCurrentMana() > 0) {
                poseStack.pushPose();
                poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                poseStack.translate(6.8, 1.0, 8.8);
                poseStack.scale(2.4F, 2.4F, 2.4F);
                poseStack.translate(-1.0, 1.0, 0.25);
                float alpha = (float)Math.min(1.0, (Math.sin((double)((float) ClientTickHandler.ticksInGame + partialTick) / 8.0) + 1.0) / 7.0 + 0.7);
                this.renderPortal(poseStack, buffer, MiscellaneousModels.INSTANCE.alfPortalTex.sprite(), 0, 0, 3, 3, alpha, overlay);
                poseStack.translate(3.0, 0.0, 0.5);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                this.renderPortal(poseStack, buffer, MiscellaneousModels.INSTANCE.alfPortalTex.sprite(), 0, 0, 3, 3, alpha, overlay);
                poseStack.popPose();
            }

            if (tile.getProgress() > 0) {
                double progress = ((double)tile.getProgress() - (double)tile.getMaxProgress() / 2.0) / ((double)tile.getMaxProgress() / 2.0);
                ItemStack stack = progress < 0.0 ? tile.getCurrentInput() : tile.getCurrentOutput();
                if (!stack.isEmpty()) {
                    double yPos = Math.pow(progress, 2.0);
                    double zPos = -(progress * 0.75);
                    poseStack.pushPose();
                    poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                    poseStack.translate(8.0, 4.6, 8.8);
                    poseStack.scale(5.4F, 5.4F, 5.4F);
                    poseStack.translate(0.0, yPos, zPos);
                    poseStack.scale(-1.0F, 1.0F, -1.0F);
                    poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
                    Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong());
                    poseStack.popPose();
                }
            }

        }
    }

    private void renderPortal(PoseStack poseStack, MultiBufferSource buffer, TextureAtlasSprite sprite, int x, int y, int width, int height, float alpha, int overlay) {
        VertexConsumer vertex = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        Matrix4f model = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal();
        vertex.vertex(model, (float)x, (float)(y + height), 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(sprite.getU0(), sprite.getV1()).overlayCoords(overlay).uv2(LightTexture.pack(15, 15)).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
        vertex.vertex(model, (float)(x + width), (float)(y + height), 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(sprite.getU1(), sprite.getV1()).overlayCoords(overlay).uv2(LightTexture.pack(15, 15)).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
        vertex.vertex(model, (float)(x + width), (float)y, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(sprite.getU1(), sprite.getV0()).overlayCoords(overlay).uv2(LightTexture.pack(15, 15)).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
        vertex.vertex(model, (float)x, (float)y, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(sprite.getU0(), sprite.getV0()).overlayCoords(overlay).uv2(LightTexture.pack(15, 15)).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
    }
}
