package net.lmor.botanicalextramachinery.render;

import net.lmor.botanicalextramachinery.client.ModModels;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityMalachiteManaSpark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.render.entity.RenderSparkBase;

public class RenderMalachiteManaSpark extends RenderSparkBase<EntityMalachiteManaSpark> {
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
