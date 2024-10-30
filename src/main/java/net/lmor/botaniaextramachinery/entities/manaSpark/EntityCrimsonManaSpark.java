package net.lmor.botaniaextramachinery.entities.manaSpark;

import net.lmor.botaniaextramachinery.ModEntities;
import net.lmor.botaniaextramachinery.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityCrimsonManaSpark extends EntityManaSparkPattern {

    public EntityCrimsonManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 1000000;
    }

    public EntityCrimsonManaSpark(Level level){
        this(ModEntities.CRIMSON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.crimsonSpark;
    }
}