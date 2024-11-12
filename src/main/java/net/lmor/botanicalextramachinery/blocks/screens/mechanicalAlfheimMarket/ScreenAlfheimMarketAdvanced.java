package net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenAlfheimMarketAdvanced extends ExtraScreenBase<ContainerAlfheimMarketAdvanced> {

    BlockEntityAlfheimMarketAdvanced blockEntity;

    public ScreenAlfheimMarketAdvanced(ContainerAlfheimMarketAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 110);

        this.imageWidth = 184;
        this.imageHeight = 206;

        this.inventoryLabelY = -999;
        this.titleLabelY = -999;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {84, 84});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = this.getMenu().getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_ALFHEIM_MARKET_GUI);
        this.drawLabelText(poseStack);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84 , this.topPos + 84);
        }

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_ALFHEIM_MARKET_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 84, this.topPos + 42, this.imageWidth, 0, Math.round(16.0F * pct), 16);
        }

        this.agglomerationSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = new TranslatableComponent("block.botanicalextramachinery.advanced_alfheim_market");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 10) /scale, 0x00);
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
