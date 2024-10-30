package net.lmor.botaniaextramachinery.blocks.tesr.mechanicalApothecary;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUltimate;
import net.lmor.botaniaextramachinery.config.LibXClientConfig.RenderingVisualContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.moddingx.libx.render.RenderHelper;
import org.moddingx.libx.render.block.RotatedBlockRenderer;
import vazkii.botania.client.core.handler.ClientTickHandler;

import javax.annotation.Nonnull;

public class RenderApothecaryUltimate extends RotatedBlockRenderer<BlockEntityApothecaryUltimate> {
    public RenderApothecaryUltimate() {
    }

    protected void doRender(@Nonnull BlockEntityApothecaryUltimate tile, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (RenderingVisualContent.all && RenderingVisualContent.ApothecarySettings.apothecaryUltimate) {
            int items;
            double offsetPerPetal;
            if (!tile.getInventory().getStackInSlot(0).isEmpty()) {
                float time = (float) ClientTickHandler.ticksInGame + partialTick;
                poseStack.pushPose();
                poseStack.translate(0.5, 0.89375, 0.5);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(time / 1.3F));
                ItemStack stack = tile.getInventory().getStackInSlot(0);
                if (tile.getProgress() > 0) {
                    items = tile.getProgress();
                    if (items > tile.getMaxProgress() / 2) {
                        items = tile.getMaxProgress() / 2 - Math.abs(tile.getMaxProgress() / 2 - items);
                        stack = tile.getCurrentOutput();
                    }

                    offsetPerPetal = (double)items / ((double)tile.getMaxProgress() / 2.0);
                    poseStack.translate(0.0, -offsetPerPetal, 0.0);
                }

                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong());
                poseStack.popPose();
            }

            double fluidAmount = Math.min((double) tile.getFluidInventory().getFluidAmount() / tile.FLUID_CAPACITY, 1.0F);
            if (tile.getFluidInventory().getFluidAmount() > 0) {
                poseStack.pushPose();
                poseStack.translate(0.25, (10 + fluidAmount * 3) / 16, 0.25);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                FluidStack fluidStack = tile.getFluidInventory().getFluid();
                IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
                TextureAtlasSprite sprite = (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidTypeExtensions.getStillTexture(fluidStack));
                int fluidColor = fluidTypeExtensions.getTintColor(fluidStack);
                VertexConsumer vertex = buffer.getBuffer(Sheets.translucentCullBlockSheet());
                RenderHelper.renderIconColored(poseStack, vertex, 0.0F, 0.0F, sprite, 8.0F, 8.0F, 1.0F, fluidColor, light, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }

            items = 0;

            for(int slot = 3; slot < 19; ++slot) {
                if (!tile.getInventory().getStackInSlot(slot).isEmpty()) {
                    ++items;
                }
            }

            offsetPerPetal = 360.0 / (double)items;
            double flowerTicks = (double)((float)ClientTickHandler.ticksInGame + partialTick) / 2.0;
            poseStack.pushPose();
            poseStack.translate(0.5, (10.0 + fluidAmount * 3.8 / 1.5) / 16.0, 0.5);
            poseStack.scale(0.125F, 0.125F, 0.125F);
            int nextIdx = 0;
            boolean hasFluid = tile.getFluidInventory().getFluidAmount() > 0;

            for(int slot = 3; slot < 19; ++slot) {
                if (!tile.getInventory().getStackInSlot(slot).isEmpty()) {
                    int i = nextIdx++;
                    double offset = offsetPerPetal * (double)i;
                    double deg;
                    if (hasFluid) {
                        deg = flowerTicks / 0.25 % 360.0 + offset;
                    } else {
                        deg = offset;
                    }

                    double rad = deg * Math.PI / 180.0;
                    double radiusX;
                    double radiusZ;
                    if (hasFluid) {
                        radiusX = 1.2000000476837158 + 0.10000000149011612 * Math.sin(flowerTicks / 6.0);
                        radiusZ = 1.2000000476837158 + 0.10000000149011612 * Math.cos(flowerTicks / 6.0);
                    } else {
                        radiusX = 1.300000049173832;
                        radiusZ = 1.300000049173832;
                    }

                    double x = radiusX * Math.cos(rad);
                    double z = radiusZ * Math.sin(rad);
                    double y = hasFluid ? (double)((float)Math.cos((flowerTicks + (double)(50 * i)) / 5.0) / 10.0F) : 0.0;
                    poseStack.pushPose();
                    poseStack.translate(x, y, z);
                    poseStack.translate(0.0625, 0.0625, 0.0625);
                    if (hasFluid) {
                        float xRotate = (float)Math.sin(flowerTicks * 0.25) / 2.0F;
                        float yRotate = (float)Math.max(0.6000000238418579, Math.sin(flowerTicks * 0.10000000149011612) / 2.0 + 0.5);
                        float zRotate = (float)Math.cos(flowerTicks * 0.25) / 2.0F;
                        poseStack.mulPose((new Vector3f(xRotate, yRotate, zRotate)).rotationDegrees((float)deg));
                    } else {
                        poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                    }

                    poseStack.translate(-0.0625, -0.0625, -0.0625);
                    ItemStack stack = tile.getInventory().getStackInSlot(slot);
                    Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, light, overlay, poseStack, buffer, (int)tile.getBlockPos().asLong() + slot);
                    poseStack.popPose();
                }
            }

            poseStack.popPose();
        }
    }
}

