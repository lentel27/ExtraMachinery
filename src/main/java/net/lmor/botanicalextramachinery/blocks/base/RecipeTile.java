package net.lmor.botanicalextramachinery.blocks.base;

import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.moddingx.libx.crafting.recipe.RecipeHelper;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public abstract class RecipeTile<T extends Recipe<Container>> extends BotanicalTile {
    private final RecipeType<T> recipeType;
    private final int firstInputSlot;
    private final int firstOutputSlot;
    protected T recipe;
    private boolean needsRecipeUpdate;
    private final int countCraft;
    private int countCraftPerRecipe;


    public RecipeTile(BlockEntityType<?> blockEntityType, RecipeType<T> recipeType, BlockPos pos, BlockState state, int manaCap, int firstInputSlot, int firstOutputSlot, int countCraft) {
        super(blockEntityType, pos, state, manaCap);
        this.recipeType = recipeType;
        this.firstInputSlot = firstInputSlot;
        this.firstOutputSlot = firstOutputSlot;
        this.needsRecipeUpdate = true;
        this.countCraft = countCraft;
        this.countCraftPerRecipe = countCraft;
    }

    public int getCountCraft() {
        return countCraftPerRecipe;
    }

    protected void updateRecipeIfNeeded() {
        this.updateRecipeIfNeeded(() -> {
        }, (stack, slot) -> {
        });
    }

    protected void updateRecipeIfNeeded(Runnable doUpdate, BiConsumer<ItemStack, Integer> usedStacks) {
        if (this.level != null && !this.level.isClientSide) {
            if (this.needsRecipeUpdate) {
                this.needsRecipeUpdate = false;
                doUpdate.run();
                this.updateRecipe(usedStacks);
            }

        }
    }

    protected void updateRecipe() {
        this.updateRecipe((stack, slot) -> {
        });
    }

    public int getCountCraftPerRecipe() {
        return countCraftPerRecipe;
    }

    protected void updateRecipe(BiConsumer<ItemStack, Integer> usedStacks) {
        if (this.level != null && !this.level.isClientSide) {
            if (!this.canMatchRecipes()) {
                this.recipe = null;
            } else {
                IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
                IntStream range = IntStream.range(this.firstInputSlot, this.firstOutputSlot);
                Objects.requireNonNull(inventory);
                List<ItemStack> stacks = range.mapToObj(inventory::getStackInSlot).toList();

                Iterator iterator = this.level.getRecipeManager().getAllRecipesFor(this.recipeType).iterator();

                Recipe recipe;
                do {
                    if (!iterator.hasNext()) {
                        this.recipe = null;
                        return;
                    }

                    recipe = (Recipe)iterator.next();
                } while(!this.matchRecipe((T) recipe, stacks));

                List<ItemStack> consumedStacks = new ArrayList();
                Iterator iteratorRecipe = recipe.getIngredients().iterator();

                this.countCraftPerRecipe = maxCountCraft(iteratorRecipe, stacks);

                if (recipe.getResultItem().getCount() != 0){
                    int remainingItemsToPlace;
                    if (recipe.getResultItem().getCount() != 0){
                        remainingItemsToPlace = countCraftPerRecipe * recipe.getResultItem().getCount();
                    } else {
                        remainingItemsToPlace = countCraftPerRecipe;
                    }

                    for (int slot = this.firstOutputSlot; slot < inventory.getSlots(); ++slot) {
                        ItemStack slotStack = inventory.getStackInSlot(slot);

                        if (slotStack.isEmpty() || slotStack.sameItem(recipe.getResultItem())) {
                            int maxStackSize = recipe.getResultItem().getMaxStackSize();
                            int currentStackSize = slotStack.getCount();
                            int availableSpace = maxStackSize - currentStackSize;

                            int itemsToPlaceInSlot = Math.min(remainingItemsToPlace, availableSpace);

                            remainingItemsToPlace -= itemsToPlaceInSlot;

                            if (remainingItemsToPlace <= 0) {
                                break;
                            }
                        }
                    }

                    if (remainingItemsToPlace < countCraftPerRecipe * recipe.getResultItem().getCount()) {
                        // Рассчитываем, сколько полных рецептов удалось поместить
                        this.countCraftPerRecipe -= remainingItemsToPlace / recipe.getResultItem().getCount();

                    } else if (remainingItemsToPlace >= countCraftPerRecipe * recipe.getResultItem().getCount()) {
                        this.recipe = null; // Ничего не помещается, отменяем рецепт
                        return;
                    }
                }

                while(true) {
                    while(iteratorRecipe.hasNext()) {
                        Ingredient ingredient = (Ingredient)iteratorRecipe.next();

                        for(int stackIdx = 0; stackIdx < stacks.size(); ++stackIdx) {
                            if (ingredient.test((ItemStack)stacks.get(stackIdx))) {
                                ItemStack theStack = ((ItemStack)stacks.get(stackIdx)).copy();


                                theStack.setCount(this.countCraftPerRecipe);
                                consumedStacks.add(theStack.copy());
                                usedStacks.accept(theStack, this.firstInputSlot + stackIdx);
                                break;
                            }
                        }
                    }

                    List<ItemStack> resultItems = this.resultItems((T) recipe, consumedStacks);

                    if (!resultItems.isEmpty() && !inventory.hasSpaceFor(resultItems, this.firstOutputSlot, inventory.getSlots())) {
                        this.recipe = null;
                    } else {
                        this.recipe = (T) recipe;
                    }

                    return;
                }
            }
        }
    }

    public int maxCountCraft(Iterator iteratorRecipe, List<ItemStack> stacks){
        Map<Item, Integer> iteratorMap = new HashMap<>();
        Map<Item, Integer> allIngredients = new HashMap<>();

        int count = 9999999;
        while (iteratorRecipe.hasNext()){
            Ingredient ingredient = (Ingredient)iteratorRecipe.next();

            for (ItemStack itemStack: Arrays.stream(ingredient.getItems()).toList()){
                iteratorMap.merge(itemStack.getItem().asItem(), itemStack.getCount(), Integer::sum);
            }
        }

        for(int stackIdx = this.firstInputSlot; stackIdx < this.firstOutputSlot; stackIdx++) {
            ItemStack theStack = this.getInventory().getStackInSlot(stackIdx);

            if (theStack.isEmpty()) continue;
            for (Item item: iteratorMap.keySet()){

                if (item == theStack.getItem().asItem()){
                    allIngredients.merge(theStack.getItem().asItem(), theStack.getCount(), Integer::sum);
                }
            }
        }

        for (Item itemIngredient: allIngredients.keySet()){
            int itemIngredientCount = allIngredients.get(itemIngredient);

            for (Item itemDefaultIngredient: iteratorMap.keySet()){
                if (itemDefaultIngredient != itemIngredient) continue;

                int count_item = iteratorMap.get(itemDefaultIngredient);
                int min_craft = Math.min(itemIngredientCount, itemIngredientCount / count_item);

                if (min_craft < count){
                    count = min_craft;
                }
                break;
            }
        }

        count = Math.min(this.countCraft, count);
        return count;
    }

    protected void craftRecipe() {
        this.craftRecipe((stack, slot) -> {
        });
    }

    protected void craftRecipe(BiConsumer<ItemStack, Integer> usedStacks) {
        if (this.level != null && !this.level.isClientSide) {
            if (this.recipe != null) {
                IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
                List<ItemStack> consumedStacks = new ArrayList<>();
                Iterator iterator = this.recipe.getIngredients().iterator();
                Iterator iteratorTest = this.recipe.getIngredients().iterator();

                while(true) {
                    int countItemCraft = 0;

                    while(iteratorTest.hasNext()) {
                        Ingredient ingredient = (Ingredient)iteratorTest.next();
                        for(int slot = this.firstInputSlot; slot < this.firstOutputSlot; ++slot) {
                            if (ingredient.test(inventory.getStackInSlot(slot))) {
                                int cc = inventory.getStackInSlot(slot).getCount();
                                if (countItemCraft == 0){
                                    countItemCraft = Math.min(this.countCraftPerRecipe, cc);
                                } else {
                                    countItemCraft = Math.min(countItemCraft, cc);
                                }
                                break;
                            }
                        }
                    }

                    while(iterator.hasNext()) {
                        Ingredient ingredient = (Ingredient)iterator.next();
                        for(int slot = this.firstInputSlot; slot < this.firstOutputSlot; ++slot) {
                            if (ingredient.test(inventory.getStackInSlot(slot))) {
                                ItemStack extracted = inventory.extractItem(slot, countItemCraft, false);
                                if (!extracted.isEmpty()) {
                                    consumedStacks.add(extracted);
                                    usedStacks.accept(extracted, slot);
                                }
                                break;
                            }
                        }
                    }

                    iterator = this.resultItems(this.recipe, consumedStacks).iterator();

                    while(iterator.hasNext()) {
                        ItemStack result = (ItemStack)iterator.next();

                        result.setCount(result.getCount() * countItemCraft);

                        this.putIntoOutputOrDrop(result.copy());
                    }

                    this.onCrafted(this.recipe, countItemCraft);

                    this.recipe = null;
                    this.needsRecipeUpdate();
                    this.countCraftPerRecipe = this.countCraft;

                    break;
                }
            }

        }
    }

    protected boolean canMatchRecipes() {
        return true;
    }

    protected boolean matchRecipe(T recipe, List<ItemStack> stacks) {
        return RecipeHelper.matches(recipe, stacks, false);
    }

    protected void onCrafted(T recipe, int countItemCraft) {
    }

    protected List<ItemStack> resultItems(T recipe, List<ItemStack> stacks) {
        return recipe.getResultItem().isEmpty() ? List.of() : List.of(recipe.getResultItem().copy());
    }

    protected void putIntoOutputOrDrop(ItemStack stack) {
        if (this.level != null && !this.level.isClientSide) {
            IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
            ItemStack left = stack.copy();

            for(int slot = this.firstOutputSlot; slot < inventory.getSlots(); ++slot) {
                left = inventory.insertItem(slot, left, false);
                if (left.isEmpty()) {
                    return;
                }
            }

            if (!left.isEmpty()) {


                ItemEntity ie = new ItemEntity(this.level, (double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.7, (double)this.worldPosition.getZ() + 0.5, left.copy());
                this.level.addFreshEntity(ie);
            }

        }
    }

    public void needsRecipeUpdate() {
        this.needsRecipeUpdate = true;
    }

    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        this.needsRecipeUpdate = true;
    }
}