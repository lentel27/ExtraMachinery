package net.lmor.botanicalextramachinery;

import net.lmor.botanicalextramachinery.core.LibNames;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityCrimsonManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityMalachiteManaSpark;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntitySaffronManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityShadowManaSpark;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import vazkii.botania.api.block.WandHUD;

import java.util.function.BiConsumer;
import java.util.function.Function;


public class ModEntities {
    public static final EntityType<EntityMalachiteManaSpark> MALACHITE_SPARK = EntityType.Builder.<EntityMalachiteManaSpark>of(EntityMalachiteManaSpark::new, MobCategory.MISC).sized(0.2F, 0.5F).fireImmune().clientTrackingRange(4).updateInterval(10).build(LibNames.MALACHITE_SPARK);
    public static final EntityType<EntitySaffronManaSpark> SAFFRON_SPARK = EntityType.Builder.<EntitySaffronManaSpark>of(EntitySaffronManaSpark::new, MobCategory.MISC).sized(0.2F, 0.5F).fireImmune().clientTrackingRange(4).updateInterval(10).build(LibNames.SAFFRON_SPARK);
    public static final EntityType<EntityShadowManaSpark> SHADOW_SPARK = EntityType.Builder.<EntityShadowManaSpark>of(EntityShadowManaSpark::new, MobCategory.MISC).sized(0.2F, 0.5F).fireImmune().clientTrackingRange(4).updateInterval(10).build(LibNames.SHADOW_SPARK);
    public static final EntityType<EntityCrimsonManaSpark> CRIMSON_SPARK = EntityType.Builder.<EntityCrimsonManaSpark>of(EntityCrimsonManaSpark::new, MobCategory.MISC).sized(0.2F, 0.5F).fireImmune().clientTrackingRange(4).updateInterval(10).build(LibNames.CRIMSON_SPARK);

    public static void registerWandHudCaps(ECapConsumer<WandHUD> consumer) {
        consumer.accept(e -> new EntityMalachiteManaSpark.WandHud((EntityMalachiteManaSpark) e), MALACHITE_SPARK);
        consumer.accept(e -> new EntitySaffronManaSpark.WandHud((EntitySaffronManaSpark) e), SAFFRON_SPARK);
        consumer.accept(e -> new EntityShadowManaSpark.WandHud((EntityShadowManaSpark) e), SHADOW_SPARK);
        consumer.accept(e -> new EntityCrimsonManaSpark.WandHud((EntityCrimsonManaSpark) e), CRIMSON_SPARK);
    }

    @FunctionalInterface
    public interface ECapConsumer<T> {
        void accept(Function<Entity, T> factory, EntityType<?>... types);
    }

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> r) {
        r.accept(MALACHITE_SPARK, LibResources.MALACHITE_SPARK);
        r.accept(SAFFRON_SPARK, LibResources.SAFFRON_SPARK);
        r.accept(SHADOW_SPARK, LibResources.SHADOW_SPARK);
        r.accept(CRIMSON_SPARK, LibResources.CRIMSON_SPARK);
    }
}