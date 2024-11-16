package net.lmor.botanicalextramachinery.client.render;

import net.lmor.botanicalextramachinery.ModItems;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ItemLike;
import vazkii.botania.client.core.handler.ClientTickHandler;

public class ColorHandler {

    public interface ItemHandlerConsumer {
        void register(ItemColor handler, ItemLike... items);
    }

    public static void submitItems(ItemHandlerConsumer items) {
        items.register((s, t) -> t == 1 ? Mth.hsvToRgb(ClientTickHandler.ticksInGame * 2 % 360 / 360F, 0.25F, 1F) : -1,
                ModItems.catalystPetal, ModItems.catalystPetalBlock);
    }
}
