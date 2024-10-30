package net.lmor.botaniaextramachinery;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;


import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

@Mod("botaniaextramachinery")
public final class ExtraMachinery extends ModXRegistration {
    private static ExtraMachinery instance;
    public static final String MOD_ID = "botaniaextramachinery";

    public ExtraMachinery() {
        super(new CreativeModeTab(MOD_ID) {
            @Nonnull
            public ItemStack makeIcon() {
                return new ItemStack(ModBlocks.shadowDragonstoneBlock);
            }
        });

        instance = this;
        ModEntities.registerEntities(bind(ForgeRegistries.ENTITY_TYPES));

    }

    private static <T> BiConsumer<T, ResourceLocation> bind(IForgeRegistry<? super T> registry) {
        return (t, id) -> registry.register(id, t);
    }

    @Nonnull
    public static ExtraMachinery getInstance() { return instance; }

    @Override
    protected void initRegistration(RegistrationBuilder registrationBuilder) {registrationBuilder.enableRegistryTracking();}

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {}

}
