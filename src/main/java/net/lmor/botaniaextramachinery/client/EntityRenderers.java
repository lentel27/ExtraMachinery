package net.lmor.botaniaextramachinery.client;

import net.lmor.botaniaextramachinery.ModEntities;
import net.lmor.botaniaextramachinery.render.RenderCrimsonManaSpark;
import net.lmor.botaniaextramachinery.render.RenderMalachiteManaSpark;
import net.lmor.botaniaextramachinery.render.RenderSaffronManaSpark;
import net.lmor.botaniaextramachinery.render.RenderShadowManaSpark;
import vazkii.botania.client.render.entity.EntityRenderers.EntityRendererConsumer;

public class EntityRenderers {

    public static void registerEntityRenderers(EntityRendererConsumer consumer) {
        consumer.accept(ModEntities.MALACHITE_SPARK, RenderMalachiteManaSpark::new);
        consumer.accept(ModEntities.SAFFRON_SPARK, RenderSaffronManaSpark::new);
        consumer.accept(ModEntities.SHADOW_SPARK, RenderShadowManaSpark::new);
        consumer.accept(ModEntities.CRIMSON_SPARK, RenderCrimsonManaSpark::new);
    }

}
