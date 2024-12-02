package net.lmor.botanicalextramachinery.blocks.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityGreenhouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderGreenhouse implements BlockEntityRenderer<BlockEntityGreenhouse> {

    BakedModel pylonsModel = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/pylons"));

    BakedModel[] circleModels = new BakedModel[] {
            Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_3")),
            Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_2")),
            Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_1"))
    };

    BakedModel gardenBedModel = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/garden_bed"));

    public RenderGreenhouse() {
    }

    private void renderState(@Nullable BlockState state, float translateX, float translateZ, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (state != null) {
            poseStack.pushPose();
            poseStack.translate(translateX, 0.0, translateZ);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, light, overlay);
            poseStack.popPose();
        }

    }

    @Override
    public void render(BlockEntityGreenhouse blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);

        float angle = (blockEntity.getLevel().getGameTime() + partialTick) % 360;
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        poseStack.translate(-0.5, -0.5, -0.5);

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                buffer.getBuffer(Sheets.translucentCullBlockSheet()),
                null,
                pylonsModel,
                1, 1, 1,
                light, overlay);
        poseStack.popPose();

        float[] y_offset = new float[] {0.017f, 0.025f, 0.06f};

        for (int i = 0; i < 3; i++) {
            poseStack.pushPose();

            poseStack.translate(0.5, 0.1 - i * 0.01, 0.5);

            angle = (blockEntity.getLevel().getGameTime() + partialTick) % 360;
            poseStack.mulPose(Axis.YP.rotationDegrees(i == 1 ? -angle: angle));

            double phaseShift = i * (Math.PI / 2);
            double bounce = Math.sin((blockEntity.getLevel().getGameTime() + partialTick) * 0.05 + phaseShift) * 0.083f - y_offset[i];

            poseStack.translate(-0.5, bounce, -0.5);

            Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    buffer.getBuffer(Sheets.cutoutBlockSheet()),
                    blockEntity.getBlockState(),
                    circleModels[i],
                    1, 1, 1,
                    light, overlay);
            poseStack.popPose();
        }

        poseStack.pushPose();
        poseStack.translate(0.5, 0.05, 0.5);

        angle = (blockEntity.getLevel().getGameTime() + partialTick) % 360;
        poseStack.mulPose(Axis.YP.rotationDegrees(-angle));

        double bounce = Math.sin((blockEntity.getLevel().getGameTime() + partialTick) * 0.1) * 0.02;
        poseStack.translate(-0.5, bounce, -0.5);

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                buffer.getBuffer(Sheets.translucentCullBlockSheet()),
                blockEntity.getBlockState(),
                gardenBedModel,
                1, 1, 1,
                light, overlay);
        poseStack.popPose();


        for (int i = 0; i < blockEntity.getFlowerInventory().getSlots(); i++) {
            if (!blockEntity.getFlowerInventory().getStackInSlot(i).isEmpty()) {
                ItemStack flowerStack = blockEntity.getFlowerInventory().getStackInSlot(i);

                if (flowerStack.getItem() instanceof BlockItem blockItem) {
                    Block flowerBlock = blockItem.getBlock();

                    poseStack.pushPose();

                    poseStack.translate(0.4, 0.485 + bounce, 0.4);
                    poseStack.scale(0.2f, 0.2f, 0.2f);

                    double flowerOffsetX = Math.cos(i * Math.PI * 2 / blockEntity.getFlowerInventory().getSlots()) * 0.7;
                    double flowerOffsetZ = Math.sin(i * Math.PI * 2 / blockEntity.getFlowerInventory().getSlots()) * 0.7;

                    poseStack.translate(flowerOffsetX , 0, flowerOffsetZ);

                    // Рендерим модель блока
                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                            flowerBlock.defaultBlockState(),
                            poseStack,
                            buffer,
                            light,
                            overlay
                    );

                    poseStack.popPose();
                }
            }
        }
    }

    private float[] getCoordFlower(int slot){
        switch (slot) {
            case 0 -> { return new float[] {0.2f, 0.2f};}
            case 1 -> { return new float[] {0.0f, 0.0f};}
            case 2 -> { return new float[] {0.0f, 0.0f};}
            case 3 -> { return new float[] {0.0f, 0.0f};}
            case 4 -> { return new float[] {0.0f, 0.0f};}
            case 5 -> { return new float[] {0.0f, 0.0f};}
            case 6 -> { return new float[] {0.0f, 0.0f};}
            default -> { return new float[] {0, 0}; }
        }
    }

}
