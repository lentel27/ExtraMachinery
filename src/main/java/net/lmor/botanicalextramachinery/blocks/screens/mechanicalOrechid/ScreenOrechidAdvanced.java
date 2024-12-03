package net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenOrechidAdvanced extends ExtraScreenBase<ContainerOrechidAdvanced> {

    BlockEntityOrechidAdvanced blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ADVANCED);
    List<ItemStack> items = new ArrayList<>();
    Bars bars;
    SlotInfo slotInfo;

    public ScreenOrechidAdvanced(ContainerOrechidAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerOrechidAdvanced.WIDTH_GUI;
        this.imageHeight = ContainerOrechidAdvanced.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(38, 144);

        blockEntity = this.menu.getBlockEntity();

        items.add(new ItemStack(ModItems.catalystManaInfinity));
        items.add(new ItemStack(ModItems.catalystStoneInfinity));

        Map<Integer, int[]> slots = new HashMap<>();
        String[] infoTranslate = new String[9];

        slots.put(0, new int[] {17, 74});
        slots.put(1, new int[] {173, 74});
        infoTranslate[0] = ("botanicalextramachinery.tooltip.screen.upgrade_slot");
        infoTranslate[1] = ("botanicalextramachinery.tooltip.screen.upgrade_slot");

        for (int i = 0; i < 7; i++){
            slots.put(i + 2, new int[] {41 + 18 * i, 19});
            infoTranslate[i + 2] = ("botanicalextramachinery.tooltip.screen.ore_slot");
        }

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(infoTranslate);
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_ORECHID_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.advanced_orechid",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 17 + 156 * i, this.topPos + 74);
            }
        }
        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);


    }
}
