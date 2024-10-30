package net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.ModItems;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolAdvanced;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ScreenManaPoolAdvanced extends ExtraScreenBase<ContainerManaPoolAdvanced> {

    BlockEntityManaPoolAdvanced blockEntity;
    public ScreenManaPoolAdvanced(ContainerManaPoolAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 85);

        this.imageWidth = 176;
        this.imageHeight = 182;
        this.manaPoolSlotInfo.setCoord(
                new int[] {88, 59},
                new int[] {88, 15});

        blockEntity = (BlockEntityManaPoolAdvanced)((ContainerManaPoolAdvanced)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_MECHANICAL_MANA_POOL_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 89, this.topPos + 60);
        }

        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 89, this.topPos + 16);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});

//        if (blockEntity.getCooldown() > 0) {
//            float pct = Math.min((float)blockEntity.getCooldown() / (float)blockEntity.getMaxCooldown(), 1.0F);
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_MECHANICAL_MANA_POOL_GUI);
//            this.blit(poseStack, this.leftPos + 79, this.topPos + 41, this.imageWidth, 0, Math.round(35.0F - 35.0F * pct), 11);
//        }

    }
}
