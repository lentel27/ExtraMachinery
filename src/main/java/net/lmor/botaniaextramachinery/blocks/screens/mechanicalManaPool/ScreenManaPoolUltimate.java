package net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.ModItems;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolUltimate;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ScreenManaPoolUltimate extends ExtraScreenBase<ContainerManaPoolUltimate> {

    BlockEntityManaPoolUltimate blockEntity;
    public ScreenManaPoolUltimate(ContainerManaPoolUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 92);

        this.imageWidth = 176;
        this.imageHeight = 189;
        this.manaPoolSlotInfo.setCoord(
                new int[] {79, 64},
                new int[] {79, 18});

        blockEntity = (BlockEntityManaPoolUltimate)((ContainerManaPoolUltimate)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_MECHANICAL_MANA_POOL_GUI);
        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 80, this.topPos + 66);
        }
        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 80, this.topPos + 19);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});
    }
}
