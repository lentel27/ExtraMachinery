package net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.moddingx.libx.render.block.RotatedBlockRenderer;
import vazkii.botania.api.mana.PoolOverlayProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class RenderManaPoolAdvanced extends RotatedBlockRenderer<BlockEntityManaPoolAdvanced> {
    public static final double INNER_POOL_HEIGHT = 0.28125;
    public static final double POOL_BOTTOM_HEIGHT = 0.071875;
    private static final TextureAtlasSprite MANA_WATER_SPRITE;

    public RenderManaPoolAdvanced() {
    }

    protected void doRender(@Nonnull BlockEntityManaPoolAdvanced tile, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (LibXClientConfig.RenderingVisualContent.all && LibXClientConfig.RenderingVisualContent.ManaPoolSettings.manaPoolAdvanced) {
            ItemStack catalystStack = tile.getInventory().getStackInSlot(0);
            if (!catalystStack.isEmpty() && catalystStack.getItem() instanceof BlockItem) {
                Block var9 = ((BlockItem)catalystStack.getItem()).getBlock();
                if (var9 instanceof PoolOverlayProvider) {
                    PoolOverlayProvider catalyst = (PoolOverlayProvider)var9;
                    ResourceLocation spriteId = catalyst.getIcon(tile.getLevel(), tile.getBlockPos());
                    TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(spriteId);
                    poseStack.pushPose();
                    poseStack.translate(0.125, POOL_BOTTOM_HEIGHT, 0.125);
                    poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                    poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                    float alpha = (float)((Math.sin((double)((float) ClientTickHandler.ticksInGame + partialTick) / 20.0) + 1.0) * 0.3 + 0.2);
                    VertexConsumer vertex = buffer.getBuffer(RenderHelper.ICON_OVERLAY);
                    RenderHelper.renderIconFullBright(poseStack, vertex, sprite, alpha);
                    poseStack.popPose();
                }
            }

            if (tile.getCurrentMana() > 0) {
                double amount = (double)tile.getCurrentMana() / (double)tile.getMaxMana();
                poseStack.pushPose();
                poseStack.translate(0.1875, POOL_BOTTOM_HEIGHT + amount * INNER_POOL_HEIGHT, 0.1875);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.scale(0.625F, 0.625F, 0.625F);
                VertexConsumer vertex = buffer.getBuffer(RenderHelper.MANA_POOL_WATER);
                RenderHelper.renderIconFullBright(poseStack, vertex, MANA_WATER_SPRITE, 1.0F);
                poseStack.popPose();
            }

            poseStack.pushPose();
            poseStack.translate(0.3125, 0.4375, 0.5);

            int slot_output = 11;
            for(int i = 1; i < 4; i++){
                for (int j = 1; j < 3; j++){
                    ItemStack output = tile.getInventory().getStackInSlot(slot_output);

                    if (!output.isEmpty()) {
                        poseStack.pushPose();
                        poseStack.translate(-0.05 + 0.1 * (j - 1), 0.0, -0.2 + 0.2 * (i - 1));

                        poseStack.scale(0.4375F, 0.4375F, 0.4375F);
                        poseStack.mulPose(Axis.YP.rotationDegrees((float)ClientTickHandler.ticksInGame + partialTick));
                        Minecraft.getInstance().getItemRenderer().renderStatic(output, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, tile.getLevel(), (int)tile.getBlockPos().asLong());
                        poseStack.popPose();
                    }
                    slot_output++;
                }
            }

            poseStack.translate(0.375, 0.0, 0.0);

            int slot_input = 2;
            for(int i = 1; i <= 3; i++){
                for (int j = 1; j <= 3; j++){
                    ItemStack input = tile.getInventory().getStackInSlot(slot_input);

                    if (!input.isEmpty()) {
                        poseStack.pushPose();
                        poseStack.translate(-0.05 + 0.1 * (i - 1), 0.0, -0.2 + 0.2 * (j - 1));

                        poseStack.scale(0.4375F, 0.4375F, 0.4375F);
                        poseStack.mulPose(Axis.YP.rotationDegrees((float)(ClientTickHandler.ticksInGame % 360)));
                        Minecraft.getInstance().getItemRenderer().renderStatic(input, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, tile.getLevel(), (int)tile.getBlockPos().asLong());
                        poseStack.popPose();
                    }
                    slot_input++;
                }
            }

            poseStack.popPose();
        }
    }

    static {
        MANA_WATER_SPRITE = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation("botania", "block/mana_water"));
    }
}