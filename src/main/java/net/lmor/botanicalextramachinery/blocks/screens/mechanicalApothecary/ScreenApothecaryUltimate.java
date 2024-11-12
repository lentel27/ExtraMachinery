package net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import io.github.noeppi_noeppi.libx.util.TagAccess;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryUltimate extends ExtraScreenBase<ContainerApothecaryUltimate> {
    private final BlockEntityApothecaryUltimate tile;

    public ScreenApothecaryUltimate(ContainerApothecaryUltimate screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title, 27, 120);
        this.imageWidth = 184;
        this.imageHeight = 217;

        this.titleLabelY = -99999;
        this.inventoryLabelY = -99999;

        Map<Integer, int[]> seed = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        seed.put(0, new int[] {84, 94});
        upgrades.put(1, new int[] {47, 94});
        upgrades.put(2, new int[] {121, 94});

        this.apothecarySlotInfo.setCoord(seed, upgrades);

        this.tile = (BlockEntityApothecaryUltimate) this.menu.getLevel().getBlockEntity(this.menu.getPos());
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_MECHANICAL_APOTHECARY_GUI);
        this.drawLabelText(poseStack);

        if (this.tile.getInventory().getStackInSlot(0).isEmpty()) {
            List<ItemStack> items = TagAccess.ROOT.get(Tags.Items.SEEDS).stream().map(Holder::value).map(ItemStack::new).toList();
            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84, this.topPos + 94);
        }

        if (this.tile.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(this.tile.getUpgrades(), poseStack, this.leftPos + 47, this.topPos + 94);
        }

        if (this.tile.getInventory().getStackInSlot(2).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(this.tile.getUpgrades(), poseStack, this.leftPos + 121, this.topPos + 94);
        }

        this.apothecarySlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, this.tile.getInventory(), new boolean[]{true, true});


        if (this.tile.getProgress() > 0) {
            float pctProgress = Math.min((float)this.tile.getProgress() / (float)this.tile.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_MECHANICAL_APOTHECARY_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 87, this.topPos + 31, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = new TranslatableComponent("block.botanicalextramachinery.ultimate_apothecary");
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
