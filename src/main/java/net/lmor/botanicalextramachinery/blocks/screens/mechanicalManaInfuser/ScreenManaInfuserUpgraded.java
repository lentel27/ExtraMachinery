package net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaInfuser;

import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaInfuser.ContainerManaInfuserUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaInfuser.BlockEntityManaInfuserUpgraded;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenManaInfuserUpgraded extends ExtraScreenBase<ContainerManaInfuserUpgraded> {

    BlockEntityManaInfuserUpgraded blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.UPGRADE);
    Bars bars;

    public ScreenManaInfuserUpgraded(ContainerManaInfuserUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);

        this.imageWidth = ContainerManaInfuserUpgraded.WIDTH_GUI;
        this.imageHeight = ContainerManaInfuserUpgraded.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(33, 109);

        blockEntity = this.menu.getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.UPGRADED_MANA_INFUSER_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, null);

        ScreenDrawLabelText.drawLabelText(guiGraphics, this.font, "block.botanicalextramachinery.upgraded_mana_infuser",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 10);

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.UPGRADED_MANA_INFUSER_GUI, this.leftPos + 78, this.topPos + 57, this.imageWidth, 0, 40, height);
        }

        this.bars.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
