package net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.ModItems;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryAdvanced;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryAdvanced;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenIndustrialAgglomerationFactoryAdvanced extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryAdvanced> {

    BlockEntityIndustrialAgglomerationFactoryAdvanced blockEntity;

    public ScreenIndustrialAgglomerationFactoryAdvanced(ContainerIndustrialAgglomerationFactoryAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 96);
        this.imageWidth = 184;
        this.imageHeight = 192;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {9, 67});
        upgrades.put(1, new int[] {158, 67});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryAdvanced)((ContainerIndustrialAgglomerationFactoryAdvanced)this.menu).getBlockEntity();

    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.catalystManaInfinity));
                items.add(new ItemStack(ModItems.catalystSpeed));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 9 + (149 * i), this.topPos + 67);
            }
        }

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 49, this.imageWidth, 0, 40, height);
        }

        this.agglomerationSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());

    }
}
