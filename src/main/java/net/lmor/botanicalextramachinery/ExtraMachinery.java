package net.lmor.botanicalextramachinery;

import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import io.github.noeppi_noeppi.libx.mod.registration.RegistrationBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;


import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod("botanicalextramachinery")
public final class ExtraMachinery extends ModXRegistration {
    private static ExtraMachinery instance;
    public static final String MOD_ID = "botanicalextramachinery";

    public ExtraMachinery() {
        super(new CreativeModeTab(MOD_ID) {
            @Nonnull
            public ItemStack makeIcon() {
                return new ItemStack(ModBlocks.shadowDragonstoneBlock);
            }
        });

        instance = this;

        bind(ForgeRegistries.ENTITIES, ModEntities::registerEntities);
    }

    private static <T extends IForgeRegistryEntry<T>> void bind(IForgeRegistry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(registry.getRegistrySuperType(), (RegistryEvent.Register<T> event) -> {
            source.accept((t, rl) -> {
                t.setRegistryName(rl);
                event.getRegistry().register(t);
            });
        });
    }


    @Nonnull
    public static ExtraMachinery getInstance() { return instance; }

    @Override
    protected void initRegistration(RegistrationBuilder registrationBuilder) {
        registrationBuilder.setVersion(1);
    }

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {}

}
