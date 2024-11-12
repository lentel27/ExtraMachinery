package net.lmor.botanicalextramachinery.render;

import net.lmor.botanicalextramachinery.client.ModModels;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityShadowManaSpark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.render.entity.RenderSparkBase;

public class RenderShadowManaSpark extends RenderSparkBase<EntityShadowManaSpark> {
    public RenderShadowManaSpark(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected TextureAtlasSprite getBaseIcon(EntityShadowManaSpark entity) {
        return ModModels.INSTANCE.shadowSparkWorldIcon.sprite();
    }

    @Override
    protected @Nullable TextureAtlasSprite getSpinningIcon(EntityShadowManaSpark entity) {
        int upgrade = entity.getUpgrade().ordinal() - 1;
        return upgrade >= 0 && upgrade < MiscellaneousModels.INSTANCE.sparkUpgradeIcons.length ? MiscellaneousModels.INSTANCE.sparkUpgradeIcons[upgrade].sprite() : null;
    }
}
