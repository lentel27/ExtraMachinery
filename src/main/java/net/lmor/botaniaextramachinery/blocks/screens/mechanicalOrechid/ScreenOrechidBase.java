package net.lmor.botaniaextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botaniaextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidBase;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidBase;
import net.lmor.botaniaextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ScreenOrechidBase extends ExtraScreenBase<ContainerOrechidBase> {

    BlockEntityOrechidBase blockEntity;

    public ScreenOrechidBase(ContainerOrechidBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 113);

        this.imageWidth = 184;
        this.imageHeight = 209;

        Map<Integer, int[]> ores = new HashMap<>();

        ores.put(0, new int[] {66, 22});
        ores.put(1, new int[] {84, 22});
        ores.put(2, new int[] {102, 22});

        this.orechidSlotInfo.setCoord(ores, null);

        blockEntity = (BlockEntityOrechidBase)((ContainerOrechidBase)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_ORECHID_GUI);

        this.orechidSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});

    }
}
