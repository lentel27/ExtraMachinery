package net.lmor.botanicalextramachinery;

import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUpgraded;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExtraMachinery.MOD_ID)
public class EventListener {
    public EventListener() {
    }

    @SubscribeEvent
    public static void resourcesReload(OnDatapackSyncEvent event) {
        BlockEntityManaPoolBase.invalidateCatalysts();
        BlockEntityManaPoolUpgraded.invalidateCatalysts();
        BlockEntityManaPoolAdvanced.invalidateCatalysts();
        BlockEntityManaPoolUltimate.invalidateCatalysts();
    }
}