package net.lmor.botanicalextramachinery;

import net.lmor.botanicalextramachinery.data.*;
import net.lmor.botanicalextramachinery.init.InitAppbot;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.moddingx.libx.datagen.DatagenSystem;
import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod("botanicalextramachinery")
public final class ExtraMachinery extends ModXRegistration {
    private static ExtraMachinery instance;
    private static ExtraMachineryTab tab;
    public static final String MOD_ID = "botanicalextramachinery";

    public ExtraMachinery() {
        instance = this;
        tab = new ExtraMachineryTab(this);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bind(Registries.ENTITY_TYPE, ModEntities::registerEntities);
        if (ModList.get().isLoaded("appbot")) InitAppbot.initialize(bus);

        DataGen();
    }

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(registry)) {
                source.accept((t, rl) -> event.register(registry, rl, () -> (T) t));
            }
        });
    }

    private void DataGen(){
        DatagenSystem.create(this, system -> {
            system.addDataProvider(BlockStates::new);
            system.addDataProvider(CommonTags::new);
            system.addDataProvider(ItemModels::new);
            system.addDataProvider(LootTables::new);
            system.addDataProvider(Recipes::new);
        });
    }

    @Nonnull
    public static ExtraMachinery getInstance() { return instance; }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {}

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent event) {}

}
