package net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalDaisy.ContainerDaisyAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenDaisyAdvanced extends ExtraScreenBase<ContainerDaisyAdvanced> {
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ADVANCED);
    public ScreenDaisyAdvanced(ContainerDaisyAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = ContainerDaisyAdvanced.WIDTH_GUI;
        this.imageHeight = ContainerDaisyAdvanced.HEIGHT_GUI;
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_MECHANICAL_DAISY_GUI, screenAddInventory,
                new int[] {}, new int[] {}, null, null);

        ScreenDrawLabelText.drawLabelText(poseStack, this.font, "block.botanicalextramachinery.advanced_daisy",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 5);
    }
}
