package net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarUpgraded;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUpgraded;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarUpgraded extends ExtraScreenBase<ContainerRunicAltarUpgraded> {

    BlockEntityRunicAltarUpgraded blockEntity;
    public ScreenRunicAltarUpgraded(ContainerRunicAltarUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 120);

        this.imageWidth = 184;
        this.imageHeight = 216;
        Map<Integer, int[]> livingrock = new HashMap<>();

        livingrock.put(0, new int[] {66, 93});
        livingrock.put(1, new int[] {84, 93});
        livingrock.put(2, new int[] {102, 93});

        this.runicAltarSlotInfo.setCoord(livingrock, null);

        blockEntity = (BlockEntityRunicAltarUpgraded)((ContainerRunicAltarUpgraded)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_MECHANICAL_RUNIC_ALTAR_GUI);
        this.drawLabelText(poseStack);

        for (int i = 0; i < 3; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), poseStack, this.leftPos + 66 + i * 18, this.topPos + 94);
            }
        }

        this.runicAltarSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_MECHANICAL_RUNIC_ALTAR_GUI);
            this.blit(poseStack, this.leftPos + 87, this.topPos + 30, this.imageWidth, 0, Math.round(11.0F * pct), 37);
        }
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.upgraded_runic_altar");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 5) /scale, 0x00);
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
