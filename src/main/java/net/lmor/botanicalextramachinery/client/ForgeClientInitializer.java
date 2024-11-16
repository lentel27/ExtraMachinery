package net.lmor.botanicalextramachinery.client;

import com.google.common.base.Suppliers;
import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ModEntities;
import net.lmor.botanicalextramachinery.client.render.ColorHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.WandHUD;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.integration.ears.EarsIntegration;
import vazkii.botania.common.lib.ResourceLocationHelper;
import vazkii.botania.forge.CapabilityUtil;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;


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

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item evt) {
        ColorHandler.submitItems(evt::register);
    }

    private static final Supplier<Map<EntityType<?>, Function<Entity, WandHUD>>> ENTITY_WAND_HUD = Suppliers.memoize(() -> {
        IdentityHashMap<EntityType<?>, Function<Entity, WandHUD>> ret = new IdentityHashMap<>();
        ModEntities.registerWandHudCaps((factory, types) -> {
            EntityType[] entityTypes = types;
            int typesLength = types.length;

            for(int i = 0; i < typesLength; ++i) {
                EntityType<?> type = entityTypes[i];
                ret.put(type, factory);
            }

        });
        return Collections.unmodifiableMap(ret);
    });

    private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> e) {
        Entity entity = e.getObject();
        Function<Entity, WandHUD> makeWandHud = (Function)((Map)ENTITY_WAND_HUD.get()).get(entity.getType());
        if (makeWandHud != null) {
            e.addCapability(ResourceLocationHelper.prefix("wand_hud"), CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, makeWandHud.apply(entity)));
        }

    }

}
