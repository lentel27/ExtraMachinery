package net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenIndustrialAgglomerationFactoryBase extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryBase> {

    BlockEntityIndustrialAgglomerationFactoryBase blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.BASE);
    Bars bars;

    public ScreenIndustrialAgglomerationFactoryBase(ContainerIndustrialAgglomerationFactoryBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);

        this.imageWidth = ContainerIndustrialAgglomerationFactoryBase.WIDTH_GUI;
        this.imageHeight = ContainerIndustrialAgglomerationFactoryBase.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(33, 109);

        blockEntity = this.menu.getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, null);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "text.botanicalextramachinery.base_industrial_agglomeration_factory_label_text_1",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 5);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "text.botanicalextramachinery.base_industrial_agglomeration_factory_label_text_2",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 13);

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);
            RenderSystem.setShaderTexture(0, LibResources.BASE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 78, this.topPos + 57, this.imageWidth, 0, 40, height);
        }

        this.bars.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
