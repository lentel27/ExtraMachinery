package net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenOrechidAdvanced extends ExtraScreenBase<ContainerOrechidAdvanced> {

    BlockEntityOrechidAdvanced blockEntity;

    public ScreenOrechidAdvanced(ContainerOrechidAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 144);

        this.imageWidth = 184;
        this.imageHeight = 240;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> ores = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        upgrades.put(0, new int[] {14, 80});
        upgrades.put(1, new int[] {154, 80});


        ores.put(2, new int[] {30, 19});
        ores.put(3, new int[] {48, 19});
        ores.put(4, new int[] {66, 19});
        ores.put(5, new int[] {84, 19});
        ores.put(6, new int[] {102, 19});
        ores.put(7, new int[] {120, 19});
        ores.put(7, new int[] {138, 19});


        this.orechidSlotInfo.setCoord(ores, upgrades);

        blockEntity = (BlockEntityOrechidAdvanced)((ContainerOrechidAdvanced)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_ORECHID_GUI);
        this.drawLabelText(poseStack);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.catalystManaInfinity));
                items.add(new ItemStack(ModItems.catalystStoneInfinity));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 14 + 140 * i, this.topPos + 80);
            }
        }
        this.orechidSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});


    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.advanced_orechid");
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
