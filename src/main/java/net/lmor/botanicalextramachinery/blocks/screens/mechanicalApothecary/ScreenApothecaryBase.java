package net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryBase;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryBase;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import org.moddingx.libx.util.data.TagAccess;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryBase extends ExtraScreenBase<ContainerApothecaryBase> {
    private final BlockEntityApothecaryBase blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.BASE);
    List<ItemStack> items;
    Bars bars;
    SlotInfo slotInfo;

    public ScreenApothecaryBase(ContainerApothecaryBase screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerApothecaryBase.WIDTH_GUI;
        this.imageHeight = ContainerApothecaryBase.HEIGHT_GUI;

        bars.setBar(AllBars.WATER);
        bars.setDrawCoord(33, 100);

        blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {90, 79});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] {"botanicalextramachinery.tooltip.screen.seed_slot"});

        items = TagAccess.ROOT.get(Tags.Items.SEEDS).stream().map(Holder::value).map(ItemStack::new).toList();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_MECHANICAL_APOTHECARY_GUI, screenAddInventory,
                new int[] {blockEntity.getFluidInventory().getFluidAmount()}, new int[] {blockEntity.getFluidInventory().getCapacity()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.base_apothecary",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        if (this.blockEntity.getInventory().getStackInSlot(0).isEmpty()) {
            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 90, this.topPos + 79);
        }

        if (this.blockEntity.getProgress() > 0) {
            float pctProgress = Math.min((float)this.blockEntity.getProgress() / (float)this.blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.BASE_MECHANICAL_APOTHECARY_GUI);
            RenderHelper.drawTexturedModalRect(poseStack,this.leftPos + 92, this.topPos + 35, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }

        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, this.blockEntity.getInventory());
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getFluidInventory().getFluidAmount(), blockEntity.getFluidInventory().getCapacity(), 0);
    }
}
