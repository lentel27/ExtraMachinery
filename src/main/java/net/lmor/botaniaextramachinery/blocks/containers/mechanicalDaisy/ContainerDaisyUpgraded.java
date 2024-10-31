package net.lmor.botaniaextramachinery.blocks.containers.mechanicalDaisy;

import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUpgraded;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

public class ContainerDaisyUpgraded extends BlockEntityMenu<BlockEntityDaisyUpgraded> {
    private static final int SIZE_INVENTORY = 12;

    public ContainerDaisyUpgraded(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, SIZE_INVENTORY, SIZE_INVENTORY);

        IItemHandlerModifiable inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 35));
        this.addSlot(new SlotItemHandler(inventory, 1, 106, 41));
        this.addSlot(new SlotItemHandler(inventory, 2, 112, 63));
        this.addSlot(new SlotItemHandler(inventory, 3, 106, 85));
        this.addSlot(new SlotItemHandler(inventory, 4, 84, 91));
        this.addSlot(new SlotItemHandler(inventory, 5, 62, 85));
        this.addSlot(new SlotItemHandler(inventory, 6, 56, 63));
        this.addSlot(new SlotItemHandler(inventory, 7, 62, 41));
        this.addSlot(new SlotItemHandler(inventory, 8, 130, 41));
        this.addSlot(new SlotItemHandler(inventory, 9, 130, 85));
        this.addSlot(new SlotItemHandler(inventory, 10, 38, 41));
        this.addSlot(new SlotItemHandler(inventory, 11, 38, 85));


        this.layoutPlayerInventorySlots(12, 135);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < SIZE_INVENTORY) {
                if (!this.moveItemStackTo(stack, SIZE_INVENTORY, 36 + SIZE_INVENTORY, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (!this.moveItemStackTo(stack, 0, SIZE_INVENTORY, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }

}
