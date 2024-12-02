package net.lmor.botanicalextramachinery.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.handler.ClientTickHandler;

import java.util.List;

public class GhostItemRenderer {

    public static void renderGhostItem(List<ItemStack> stacks, GuiGraphics guiGraphics, int x, int y) {
        if (!stacks.isEmpty()) {
            ItemStack stack = stacks.get((ClientTickHandler.ticksInGame / 20 % stacks.size() + stacks.size()) % stacks.size());
            renderGhostItem(stack, guiGraphics, x, y);
        }
    }

    public static void renderGhostItem(ItemStack stack, GuiGraphics guiGraphics, int x, int y) {
        if (!stack.isEmpty()) {
            RenderSystem.setShaderColor(0.5f, 0.5f, 0.5f, 1.0f);
            guiGraphics.renderFakeItem(stack, x, y);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

            RenderSystem.depthFunc(516);
            guiGraphics.fill(x, y, x + 16, y + 16, 822083583);
            RenderSystem.depthFunc(515);
        }
    }
}
