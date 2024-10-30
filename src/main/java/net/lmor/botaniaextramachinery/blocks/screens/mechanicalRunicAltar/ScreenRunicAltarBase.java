package net.lmor.botaniaextramachinery.blocks.screens.mechanicalRunicAltar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarBase;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarBase;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarBase extends ExtraScreenBase<ContainerRunicAltarBase> {
    BlockEntityRunicAltarBase blockEntity;
    public ScreenRunicAltarBase(ContainerRunicAltarBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 123);

        this.imageWidth = 184;
        this.imageHeight = 220;

        Map<Integer, int[]> livingrock = new HashMap<>();

        livingrock.put(0, new int[] {84, 97});

        this.runicAltarSlotInfo.setCoord(livingrock, null);

        blockEntity = (BlockEntityRunicAltarBase)((ContainerRunicAltarBase)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_MECHANICAL_RUNIC_ALTAR_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), poseStack, this.leftPos + 84, this.topPos + 97);
        }

        this.runicAltarSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.BASE_MECHANICAL_RUNIC_ALTAR_GUI);
            this.blit(poseStack, this.leftPos + 87, this.topPos + 34, this.imageWidth, 0, Math.round(11.0F * pct), 37);
        }
    }
}
