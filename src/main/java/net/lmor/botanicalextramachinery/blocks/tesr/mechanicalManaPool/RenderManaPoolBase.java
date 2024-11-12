package net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import io.github.noeppi_noeppi.libx.render.block.RotatedBlockRenderer;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class RenderManaPoolBase extends RotatedBlockRenderer<BlockEntityManaPoolBase> {
    public static final double INNER_POOL_HEIGHT = 0.28125;
    public static final double POOL_BOTTOM_HEIGHT = 0.071875;

    public RenderManaPoolBase() {
    }

    protected void doRender(@Nonnull BlockEntityManaPoolBase tile, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (LibXClientConfig.RenderingVisualContent.all && LibXClientConfig.RenderingVisualContent.ManaPoolSettings.manaPoolBase) {
            ItemStack catalystStack = tile.getInventory().getStackInSlot(0);
            if (!catalystStack.isEmpty() && catalystStack.getItem() instanceof BlockItem) {
                Block block = ((BlockItem)catalystStack.getItem()).getBlock();
                if (block instanceof IPoolOverlayProvider) {
                    IPoolOverlayProvider catalyst = (IPoolOverlayProvider)block;
                    ResourceLocation spriteId = catalyst.getIcon(tile.getLevel(), tile.getBlockPos());
                    TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(spriteId);
                    poseStack.pushPose();
                    poseStack.translate(0.125, POOL_BOTTOM_HEIGHT, 0.125);
                    poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                    poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                    float alpha = (float)((Math.sin((double)((float) ClientTickHandler.ticksInGame + partialTick) / 20.0) + 1.0) * 0.3 + 0.2);
                    VertexConsumer vertex = buffer.getBuffer(RenderHelper.ICON_OVERLAY);
                    RenderHelper.renderIcon(poseStack, vertex, 0, 0, sprite, 12, 12, alpha);
                    poseStack.popPose();
                }
            }

            if (tile.getCurrentMana() > 0) {
                double amount = (double)tile.getCurrentMana() / (double)tile.getManaCap();
                poseStack.pushPose();
                poseStack.translate(0.1875, POOL_BOTTOM_HEIGHT + amount * INNER_POOL_HEIGHT, 0.1875);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                VertexConsumer vertex = buffer.getBuffer(RenderHelper.MANA_POOL_WATER);
                RenderHelper.renderIcon(poseStack, vertex, 0, 0, MiscellaneousModels.INSTANCE.manaWater.sprite(), 10, 10, 1.0F);
                poseStack.popPose();
            }


            ItemStack output_1 = tile.getInventory().getStackInSlot(4);
            ItemStack output_2 = tile.getInventory().getStackInSlot(5);

            poseStack.pushPose();
            poseStack.translate(0.3125, 0.4375, 0.5);

            if (!output_1.isEmpty() || !output_2.isEmpty()) {
                if (!output_1.isEmpty()){
                    poseStack.pushPose();
                    poseStack.translate(0.02, 0, 0.2);

                    poseStack.scale(0.4375F, 0.4375F, 0.4375F);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees((float)ClientTickHandler.ticksInGame + partialTick));
                    Minecraft.getInstance().getItemRenderer().renderStatic(output_1, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong());
                    poseStack.popPose();
                }

                if (!output_2.isEmpty()){
                    poseStack.pushPose();
                    poseStack.translate(0.02, 0, -0.2);

                    poseStack.scale(0.4375F, 0.4375F, 0.4375F);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees((float)ClientTickHandler.ticksInGame + partialTick));
                    Minecraft.getInstance().getItemRenderer().renderStatic(output_2, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong());
                    poseStack.popPose();
                }
            }

            poseStack.translate(0.375, 0.0, 0.0);


            for(int i = 1; i < 4; i++){
                ItemStack input = tile.getInventory().getStackInSlot(i);

                if (!input.isEmpty()) {
                    poseStack.pushPose();
                    poseStack.translate(0, 0.0, -0.2 + 0.2 * (i - 1));

                    poseStack.scale(0.4375F, 0.4375F, 0.4375F);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees((float)(ClientTickHandler.ticksInGame % 360)));
                    Minecraft.getInstance().getItemRenderer().renderStatic(input, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, (int)tile.getBlockPos().asLong());
                    poseStack.popPose();
                }
            }

            poseStack.popPose();
        }
    }
}