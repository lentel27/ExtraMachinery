package net.lmor.botaniaextramachinery.render;

import net.lmor.botaniaextramachinery.client.ModModels;
import net.lmor.botaniaextramachinery.entities.manaSpark.EntityMalachiteManaSpark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.render.entity.BaseSparkRenderer;

public class RenderMalachiteManaSpark extends BaseSparkRenderer<EntityMalachiteManaSpark> {
    public RenderMalachiteManaSpark(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected TextureAtlasSprite getBaseIcon(EntityMalachiteManaSpark entity) {
        return ModModels.INSTANCE.malachiteSparkWorldIcon.sprite();
    }

    @Override
    protected @Nullable TextureAtlasSprite getSpinningIcon(EntityMalachiteManaSpark entity) {
        int upgrade = entity.getUpgrade().ordinal() - 1;
        return upgrade >= 0 && upgrade < MiscellaneousModels.INSTANCE.sparkUpgradeIcons.length ? MiscellaneousModels.INSTANCE.sparkUpgradeIcons[upgrade].sprite() : null;
    }
}
