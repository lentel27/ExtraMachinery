package net.lmor.botanicalextramachinery.render;

import net.lmor.botanicalextramachinery.client.ModModels;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntitySaffronManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityShadowManaSpark;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import vazkii.botania.client.render.entity.BaseSparkRenderer;
import vazkii.botania.common.lib.ResourceLocationHelper;

import java.util.Objects;
import java.util.function.Function;

public class RenderShadowManaSpark extends BaseSparkRenderer<EntityShadowManaSpark> {
    private final TextureAtlasSprite dispersiveIcon;
    private final TextureAtlasSprite dominantIcon;
    private final TextureAtlasSprite recessiveIcon;
    private final TextureAtlasSprite isolatedIcon;

    @Override
    protected TextureAtlasSprite getBaseIcon(EntityShadowManaSpark entity) {
        return ModModels.INSTANCE.shadowSparkWorldIcon.sprite();
    }

    public RenderShadowManaSpark(EntityRendererProvider.Context ctx) {
        super(ctx);
        Function<ResourceLocation, TextureAtlasSprite> atlas = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS);
        this.dispersiveIcon = Objects.requireNonNull(atlas.apply(ResourceLocationHelper.prefix("item/spark_upgrade_rune_dispersive")));
        this.dominantIcon = Objects.requireNonNull(atlas.apply(ResourceLocationHelper.prefix("item/spark_upgrade_rune_dominant")));
        this.recessiveIcon = Objects.requireNonNull(atlas.apply(ResourceLocationHelper.prefix("item/spark_upgrade_rune_recessive")));
        this.isolatedIcon = Objects.requireNonNull(atlas.apply(ResourceLocationHelper.prefix("item/spark_upgrade_rune_isolated")));
    }

    public TextureAtlasSprite getSpinningIcon(EntityShadowManaSpark entity) {
        TextureAtlasSprite textureAtlasSprite;
        switch (entity.getUpgrade()) {
            case NONE:
                textureAtlasSprite = null;
                break;
            case DISPERSIVE:
                textureAtlasSprite = this.dispersiveIcon;
                break;
            case DOMINANT:
                textureAtlasSprite = this.dominantIcon;
                break;
            case RECESSIVE:
                textureAtlasSprite = this.recessiveIcon;
                break;
            case ISOLATED:
                textureAtlasSprite = this.isolatedIcon;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return textureAtlasSprite;
    }
}
