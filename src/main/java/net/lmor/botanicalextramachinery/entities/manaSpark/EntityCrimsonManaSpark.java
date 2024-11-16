package net.lmor.botanicalextramachinery.entities.manaSpark;

import net.lmor.botanicalextramachinery.ModEntities;
import net.lmor.botanicalextramachinery.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityCrimsonManaSpark extends EntityManaSparkPattern {

    private static int RATE = 1000000;

    public EntityCrimsonManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = RATE;
    }

    public static int geRate(){
        return RATE;
    }

    public EntityCrimsonManaSpark(Level level){
        this(ModEntities.CRIMSON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.crimsonSpark;
    }
}