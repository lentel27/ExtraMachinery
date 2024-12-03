package net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUpgraded;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

public class ScreenOrechidUpgraded extends ExtraScreenBase<ContainerOrechidUpgraded> {

    BlockEntityOrechidUpgraded blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.UPGRADE);
    Bars bars;
    SlotInfo slotInfo;

    public ScreenOrechidUpgraded(ContainerOrechidUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerOrechidUpgraded.WIDTH_GUI;
        this.imageHeight = ContainerOrechidUpgraded.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(38, 104);

        blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        String[] infoTranslate = new String[9];

        for (int i = 0; i < 5; i++){
            slots.put(i, new int[] {59 + 18 * i, 19});
            infoTranslate[i] = ("botanicalextramachinery.tooltip.screen.ore_slot");
        }

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(infoTranslate);
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_ORECHID_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.upgraded_orechid",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        slotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
