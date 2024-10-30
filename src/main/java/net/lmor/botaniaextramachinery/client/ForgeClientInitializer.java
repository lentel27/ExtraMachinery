package net.lmor.botaniaextramachinery.client;

import com.google.common.base.Suppliers;
import net.lmor.botaniaextramachinery.ExtraMachinery;
import net.lmor.botaniaextramachinery.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.WandHUD;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.integration.ears.EarsIntegration;
import vazkii.botania.forge.CapabilityUtil;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

@Mod.EventBusSubscriber(modid = ExtraMachinery.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientInitializer {

    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent evt) {

        var bus = MinecraftForge.EVENT_BUS;

        ClientProxy.initSeasonal();
        bus.addGenericListener(Entity.class, ForgeClientInitializer::attachEntityCapabilities);

        if (XplatAbstractions.INSTANCE.isModLoaded("ears")) {
            EarsIntegration.register();
        }
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers evt) {
        EntityRenderers.registerEntityRenderers(evt::registerEntityRenderer);
    }

    private static final Supplier<Map<EntityType<?>, Function<Entity, WandHUD>>> ENTITY_WAND_HUD = Suppliers.memoize(() -> {
        var ret = new IdentityHashMap<EntityType<?>, Function<Entity, WandHUD>>();
        ModEntities.registerWandHudCaps((factory, types) -> {
            for (var type : types) {
                ret.put(type, factory);
            }
        });
        return Collections.unmodifiableMap(ret);
    });

    private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> e) {
        var entity = e.getObject();

        var makeWandHud = ENTITY_WAND_HUD.get().get(entity.getType());
        if (makeWandHud != null) {
            e.addCapability(prefix("wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, makeWandHud.apply(entity)));
        }
    }

}
