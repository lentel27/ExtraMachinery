package net.lmor.botanicalextramachinery.client;

import net.lmor.botanicalextramachinery.ModEntities;
import net.lmor.botanicalextramachinery.render.RenderCrimsonManaSpark;
import net.lmor.botanicalextramachinery.render.RenderMalachiteManaSpark;
import net.lmor.botanicalextramachinery.render.RenderSaffronManaSpark;
import net.lmor.botanicalextramachinery.render.RenderShadowManaSpark;
import vazkii.botania.client.render.entity.EntityRenderers.EntityRendererConsumer;

public class EntityRenderers {

    public static void registerEntityRenderers(EntityRendererConsumer consumer) {
        consumer.accept(ModEntities.MALACHITE_SPARK, RenderMalachiteManaSpark::new);
        consumer.accept(ModEntities.SAFFRON_SPARK, RenderSaffronManaSpark::new);
        consumer.accept(ModEntities.SHADOW_SPARK, RenderShadowManaSpark::new);
        consumer.accept(ModEntities.CRIMSON_SPARK, RenderCrimsonManaSpark::new);
    }

}
