package net.lmor.botanicalextramachinery.events;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExtraMachinery.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional event) {
        event.register(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/pylons"));
        event.register(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_1"));
        event.register(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_2"));
        event.register(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/circle_3"));
        event.register(new ResourceLocation(ExtraMachinery.MOD_ID, "block/greenhouse/garden_bed"));
    }
}