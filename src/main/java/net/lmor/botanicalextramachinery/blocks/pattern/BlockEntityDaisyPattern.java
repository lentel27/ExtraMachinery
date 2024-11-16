package net.lmor.botanicalextramachinery.blocks.pattern;


import appeng.api.config.Actionable;
import appeng.api.networking.GridHelper;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.networking.IManagedGridNode;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.util.AECableType;
import appeng.hooks.ticking.TickHandler;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.google.common.collect.Range;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUpgraded;
import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.DaisySettings;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.moddingx.libx.base.tile.BlockEntityBase;
import org.moddingx.libx.base.tile.TickingBlock;
import org.moddingx.libx.capability.ItemCapabilities;
import org.moddingx.libx.crafting.RecipeHelper;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.recipe.PureDaisyRecipe;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

import javax.annotation.Nonnull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

public class BlockEntityDaisyPattern extends BlockEntityBase implements TickingBlock,
        IInWorldGridNodeHost, IGridConnectedBlockEntity {
    private int ticksToNextUpdate = DaisySettings.ticksToNextUpdate;
    private int[] workingTicks;
    private final InventoryHandler inventory;

    private int SLOT_UPGRADE = -1;
    private BaseItemStackHandler inventoryUpgrade;

    private final int sizeItemSlots;
    private final LazyOptional<IItemHandlerModifiable> lazyInventory;
    private final LazyOptional<IItemHandlerModifiable> hopperInventory;
    private final int countSlotInventory;
    private final SettingPattern setting;
    private boolean recipeOutputItem = false;

    private int timeCheckOutputSlot = LibXServerConfig.tickOutputSlots;

    public BlockEntityDaisyPattern(BlockEntityType<?> type, BlockPos pos, BlockState state, int countSlotInventory,
                                   SettingPattern settingPattern, int... slotUpgrade) {
        super(type, pos, state);

        if (slotUpgrade.length != 0){
            SLOT_UPGRADE = slotUpgrade[0];
            inventoryUpgrade = BaseItemStackHandler.builder(SLOT_UPGRADE + 1)
                    .validator((stack) -> { return stack.getItem() == ModItems.catalystStoneInfinity.asItem() || stack.getItem() == ModItems.catalystWoodInfinity.asItem();}, SLOT_UPGRADE)
                    .slotLimit(1, SLOT_UPGRADE).output().contentsChanged(() -> {this.setChanged();this.setDispatchable();this.activeUpgradeSlot();})
                    .build();
        }


        this.countSlotInventory = countSlotInventory;
        this.setting = settingPattern;

        this.sizeItemSlots = settingPattern.getConfigInt("sizeSlots");


        inventory = new InventoryHandler();

        workingTicks = new int[this.countSlotInventory];


        this.lazyInventory = ItemCapabilities.create(this.inventory).cast();
        this.hopperInventory = ItemCapabilities.create(this.inventory, (slot) -> { return this.workingTicks[slot] < 0;}, null).cast();

        this.setChangedQueued = false;

    }

    //region Base
    public void tick() {
        boolean hasSpawnedParticles = false;

        for(int i = 0; i < this.countSlotInventory; ++i) {
            PureDaisyRecipe recipe = this.getRecipe(i);

            if (recipe != null && this.workingTicks[i] >= 0) {
                if (!this.level.isClientSide) {

                    if (this.workingTicks[i] >= recipe.getTime() * this.setting.getConfigInt("durationTime")) {
                        BlockState state = recipe.getOutputState();

                        this.recipeOutputItem = true;
                        this.inventory.setStackInSlot(i, state.getBlock().getCloneItemStack(this.level, this.worldPosition, state));

                        this.workingTicks[i] = -1;
                        this.setDispatchable();
                    } else {
                        this.workingTicks[i]++;
                    }
                } else if (!hasSpawnedParticles && LibXClientConfig.RenderingVisualContent.all && this.setting.getConfigBoolean("renderingDaisy")) {
                    hasSpawnedParticles = true;
                    double x = (double)this.worldPosition.getX() + Math.random();
                    double y = (double)this.worldPosition.getY() + Math.random() + 0.25;
                    double z = (double)this.worldPosition.getZ() + Math.random();
                    WispParticleData data = WispParticleData.wisp((float)Math.random() / 2.0F, 1.0F, 1.0F, 1.0F);
                    this.level.addParticle(data, x, y, z, 0.0, 0.0, 0.0);
                }
            } else if (this.workingTicks[i] < 0 && !this.inventory.getStackInSlot(i).isEmpty()) {
                this.workingTicks[i] = -1;
            } else {
                this.workingTicks[i] = 0;
            }
        }

        if (this.level != null && !this.level.isClientSide) {

            if (!this.getMainNode().isReady()){
                this.getMainNode().create(this.level, this.getBlockPos());
            }

            if (this.getMainNode().isReady() && this.recipeOutputItem) {
                exportResultsItemsME();
                this.recipeOutputItem = false;

                this.activeUpgradeSlot();
            }

            if (this.ticksToNextUpdate <= 0) {
                this.ticksToNextUpdate = DaisySettings.ticksToNextUpdate;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            } else {
                --this.ticksToNextUpdate;
            }
        }

    }

    private void activeUpgradeSlot(){
        if (this.inventoryUpgrade != null && !this.inventoryUpgrade.getStackInSlot(0).isEmpty()){
            ItemStack upgradeItem = this.inventoryUpgrade.getStackInSlot(SLOT_UPGRADE);
            ItemStack setSlotItem = ItemStack.EMPTY;

            if (upgradeItem.getItem() == ModItems.catalystStoneInfinity.asItem()){
                setSlotItem = new ItemStack(Blocks.STONE);
            } else if (upgradeItem.getItem() == ModItems.catalystWoodInfinity.asItem()){
                setSlotItem = new ItemStack(Blocks.OAK_LOG);
            }

            if (!setSlotItem.isEmpty()){
                setSlotItem.setCount(setting.getConfigInt("sizeSlots"));

                for(int i = 0; i < this.countSlotInventory; ++i) {
                    if (!this.inventory.getStackInSlot(i).isEmpty()) continue;

                    this.inventory.setStackInSlot(i, setSlotItem.copy());
                }
            }
        }
    }

    @Nullable
    private PureDaisyRecipe getRecipe(int slot) {
        BlockState state = this.getState(slot);
        return state == null ? null : this.getRecipe(state);
    }

    @Nullable
    public BlockState getState(int slot) {
        BlockState state = null;
        ItemStack stack = this.inventory.getStackInSlot(slot);

        if (stack.getItem() instanceof BlockItem) {
            state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
        }
        return state;
    }

    @Nullable
    public PureDaisyRecipe getRecipe(BlockState state) {
        if (this.level == null) {
            return null;
        } else {
            Iterator iterator = this.level.getRecipeManager().getAllRecipesFor(BotaniaRecipeTypes.PURE_DAISY_TYPE).iterator();

            while(iterator.hasNext()) {
                Recipe<?> genericRecipe = (Recipe)iterator.next();
                if (genericRecipe instanceof PureDaisyRecipe) {
                    PureDaisyRecipe recipe = (PureDaisyRecipe)genericRecipe;
                    if (recipe.matches(this.level, this.worldPosition, null, state)) {
                        return recipe;
                    }
                }
            }

            return null;
        }
    }

    public InventoryHandler getInventory() {
        return this.inventory;
    }

    public BaseItemStackHandler getInventoryUpgrade() {
        return this.inventoryUpgrade;
    }

    public List<ItemStack> getUpgrades(){
        List<ItemStack> upgrade = new ArrayList<>();

        upgrade.add(new ItemStack(ModItems.catalystWoodInfinity));
        upgrade.add(new ItemStack(ModItems.catalystStoneInfinity));

        return upgrade;
    }

    @Nonnull
    public <X> LazyOptional<X> getCapability(@Nonnull Capability<X> cap, @Nullable Direction side) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            return (LazyOptional<X>) (side == null ? this.lazyInventory : this.hopperInventory);
        }
        return super.getCapability(cap, side);

    }

    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains("workingTicks")) {
            this.workingTicks = nbt.getIntArray("workingTicks");
        }

        if (nbt.contains("inv")){
            this.getInventoryUpgrade().deserializeNBT(nbt.getCompound("inv"));
        }
    }

    public void saveAdditional(@Nonnull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putIntArray("workingTicks", this.workingTicks);

        if (this.inventoryUpgrade != null){
            nbt.put("inv", this.getInventoryUpgrade().serializeNBT());
        }
    }

    public void handleUpdateTag(CompoundTag nbt) {
        if (this.level == null || this.level.isClientSide) {
            super.handleUpdateTag(nbt);

            if (nbt.contains("workingTicks")) {
                this.workingTicks = nbt.getIntArray("workingTicks");
            }

            if (nbt.contains("inv")){
                this.getInventoryUpgrade().deserializeNBT(nbt.getCompound("inv"));
            }

        }
    }

    @Nonnull
    public CompoundTag getUpdateTag() {
        if (this.level != null && this.level.isClientSide) {
            return super.getUpdateTag();
        } else {
            CompoundTag nbt = super.getUpdateTag();
            nbt.putIntArray("workingTicks", this.workingTicks);

            if (this.inventoryUpgrade != null){
                nbt.put("inv", this.getInventoryUpgrade().serializeNBT());
            }

            return nbt;
        }
    }

    public BlockEntity getBlockEntity(){
        return this;
    }

    public class InventoryHandler extends ItemStackHandler {
        public InventoryHandler() {
            super(countSlotInventory);
        }

        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            if (recipeOutputItem){
                stack.setCount(inventory.getStackInSlot(slot).getCount());
            }
            this.stacks.set(slot, stack);
        }

        public int getSlots() {
            return countSlotInventory;
        }

        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return super.insertItem(slot, stack, simulate);
        }

        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (getMainNode() != null && getMainNode().getNode() != null && getMainNode().isOnline()){
                ItemStack stackInSlot = getInventory().getStackInSlot(slot);

                if (!stackInSlot.isEmpty()) {
                    int getCountExport = Math.toIntExact(getMainNode().getGrid().getStorageService().getInventory().insert(AEItemKey.of(stackInSlot), stackInSlot.getCount(), Actionable.MODULATE, IActionSource.empty()));

                    if (getCountExport > 0) {
                        stackInSlot.shrink(getCountExport);
                        inventory.setStackInSlot(slot, stackInSlot);
                    }
                }
            }

            return super.extractItem(slot, amount, simulate);
        }

        public int getSlotLimit(int slot) {
            return sizeItemSlots;
        }

        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return !stack.isEmpty() && stack.getItem() instanceof BlockItem && BlockEntityDaisyPattern.this.getRecipe(((BlockItem)stack.getItem()).getBlock().defaultBlockState()) != null;
        }

        protected void onContentsChanged(int slot) {
            if (slot >= 0 && slot < countSlotInventory && this.getStackInSlot(slot).isEmpty()) {
                BlockEntityDaisyPattern.this.workingTicks[slot] = 0;
            }

            BlockEntityDaisyPattern.this.setChanged();
            BlockEntityDaisyPattern.this.setDispatchable();
        }
    }

    public void drops(){
        InventoryHandler inventory = this.getInventory();
        for (int i = 0; i < inventory.getSlots(); i++){
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isEmpty()) continue;
            ItemEntity ie = new ItemEntity(this.level, (double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.7, (double)this.worldPosition.getZ() + 0.5, itemStack.copy());
            this.level.addFreshEntity(ie);
        }

        if (this.inventoryUpgrade != null && !this.inventoryUpgrade.getStackInSlot(0).isEmpty()){
            ItemEntity ie = new ItemEntity(this.level, (double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.7, (double)this.worldPosition.getZ() + 0.5, this.inventoryUpgrade.getStackInSlot(0).copy());
            this.level.addFreshEntity(ie);
        }
    }

    //endregion

    //region AE INTEGRATION
    private boolean setChangedQueued;

    private final IManagedGridNode mainNode = this.createMainNode().setVisualRepresentation(this.getItemFromBlockEntity()).setInWorldNode(true).setTagName("proxy");


    protected IManagedGridNode createMainNode() {
        return GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE);
    }

    protected Item getItemFromBlockEntity() {
        BlockEntity blockEntity = this.getBlockEntity();
        if (blockEntity instanceof BlockEntityDaisyBase){
            return ModBlocks.baseDaisy.asItem();
        }
        else if (blockEntity instanceof BlockEntityDaisyUpgraded){
            return ModBlocks.upgradedDaisy.asItem();
        }
        else if (blockEntity instanceof BlockEntityDaisyAdvanced){
            return ModBlocks.advancedDaisy.asItem();
        }
        else {
            return ModBlocks.ultimateDaisy.asItem();
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (this.getMainNode() != null) {
            this.getMainNode().destroy();
        }
    }

    private Object setChangedAtEndOfTick(Level level) {
        this.setChanged();
        this.setChangedQueued = false;
        return null;
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction direction) {
        return this.getMainNode().getNode();
    }

    @Override
    public IManagedGridNode getMainNode() {
        return this.mainNode;
    }

    @Override
    public void saveChanges() {
        if (level != null) {
            if (level.isClientSide) {
                this.setChanged();
            } else {
                this.level.blockEntityChanged(this.worldPosition);
                if (!this.setChangedQueued) {
                    TickHandler.instance().addCallable(null, this::setChangedAtEndOfTick);
                    this.setChangedQueued = true;
                }
            }
        }
    }

    private void exportResultsItemsME(){
        for (int slot = 0; slot < this.countSlotInventory; slot++) {

            ItemStack stackInSlot = this.inventory.getStackInSlot(slot);
            if (!stackInSlot.isEmpty()) {
                int getCountExport = Math.toIntExact(
                        getMainNode().getGrid().getStorageService().getInventory()
                                .insert(AEItemKey.of(stackInSlot), stackInSlot.getCount(), Actionable.MODULATE, IActionSource.empty())
                );

                if (getCountExport > 0) {
                    stackInSlot.shrink(getCountExport);
                    this.inventory.setStackInSlot(slot, stackInSlot);
                    this.inventory.onContentsChanged(slot);
                }
            }
        }
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.SMART;
    }

    //endregion
}

