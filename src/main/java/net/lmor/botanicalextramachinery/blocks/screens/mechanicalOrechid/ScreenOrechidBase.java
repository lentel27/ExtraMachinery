package net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidBase;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ScreenOrechidBase extends ExtraScreenBase<ContainerOrechidBase> {

    BlockEntityOrechidBase blockEntity;

    public ScreenOrechidBase(ContainerOrechidBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 110);

        this.imageWidth = 184;
        this.imageHeight = 206;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> ores = new HashMap<>();

        ores.put(0, new int[] {66, 19});
        ores.put(1, new int[] {84, 19});
        ores.put(2, new int[] {102, 19});

        this.orechidSlotInfo.setCoord(ores, null);

        blockEntity = (BlockEntityOrechidBase)((ContainerOrechidBase)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_ORECHID_GUI);
        this.drawLabelText(poseStack);

        this.orechidSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});

    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.base_orechid");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 6) /scale, 0x00);
        poseStack.popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
