package net.lmor.botanicalextramachinery.blocks.flowersGreenhouse;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class GenFlowers {

    private static final Map<Item, GenFlowers> allGenFlowers = new HashMap<>();

    public GenFlowers(){
    }

    public static void addAllGenFlowers(Item flower, GenFlowers genFlowers){
        allGenFlowers.put(flower, genFlowers);
    }

    public static boolean isFlower(ItemStack itemStack){
        for (Item item: allGenFlowers.keySet()){
            if (item == itemStack.getItem()){
                return true;
            }
        }
        return false;
    }

    public static GenFlowers getFlowerIsFuel(ItemStack flower, ItemStack fuel){
        if (flower.isEmpty() || fuel.isEmpty()) return null;

        for (Item item: allGenFlowers.keySet()){
            if (flower.getItem() == item && allGenFlowers.get(item).availableFuel(fuel)){
                return allGenFlowers.get(item);
            }
        }
        return null;
    }


    public static boolean availableFuelItem(ItemStack flower, ItemStack fuel){
        if (flower.isEmpty() || fuel.isEmpty()) return false;

        for (Item item: allGenFlowers.keySet()){
            if (flower.getItem() == item){
                return allGenFlowers.get(item).availableFuel(fuel);
            }
        }
        return false;
    }

    public Map<Item, GenFlowers> getAllGenFlowers(){
        return allGenFlowers;
    }

    public abstract boolean availableFuel(ItemStack fuel);

    public abstract int getPerMana(ItemStack fuel);
}
