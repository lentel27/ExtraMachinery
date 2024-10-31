package net.lmor.botanicalextramachinery.entities.manaSpark;

import net.lmor.botanicalextramachinery.ModEntities;
import net.lmor.botanicalextramachinery.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntitySaffronManaSpark extends EntityManaSparkPattern {

    public EntitySaffronManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 100000;
    }

    public EntitySaffronManaSpark(Level level){
        this(ModEntities.SAFFRON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.saffronSpark;
    }
}