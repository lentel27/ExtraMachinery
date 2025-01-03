package net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaInfuser;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaInfuser.BlockEntityManaInfuserBase;
import net.minecraft.client.renderer.MultiBufferSource;
import org.moddingx.libx.render.block.RotatedBlockRenderer;

import javax.annotation.Nonnull;

public class RenderManaInfuserBase extends RotatedBlockRenderer<BlockEntityManaInfuserBase> {
    public RenderManaInfuserBase() {
    }

    protected void doRender(@Nonnull BlockEntityManaInfuserBase tile, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {

    }

}
