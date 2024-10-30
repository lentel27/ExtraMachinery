package net.lmor.botaniaextramachinery.blocks.containers.mechanicalDaisy;

import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUltimate;
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

public class ContainerDaisyUltimate extends BlockEntityMenu<BlockEntityDaisyUltimate> {
    private static final int SIZE_INVENTORY = 18;

    public ContainerDaisyUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, SIZE_INVENTORY, SIZE_INVENTORY);

        IItemHandlerModifiable inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 38));
        this.addSlot(new SlotItemHandler(inventory, 1, 106, 44));
        this.addSlot(new SlotItemHandler(inventory, 2, 112, 66));
        this.addSlot(new SlotItemHandler(inventory, 3, 106, 88));
        this.addSlot(new SlotItemHandler(inventory, 4, 84, 94));
        this.addSlot(new SlotItemHandler(inventory, 5, 62, 88));
        this.addSlot(new SlotItemHandler(inventory, 6, 56, 66));
        this.addSlot(new SlotItemHandler(inventory, 7, 62, 44));
        this.addSlot(new SlotItemHandler(inventory, 8, 130, 44));
        this.addSlot(new SlotItemHandler(inventory, 9, 130, 88));
        this.addSlot(new SlotItemHandler(inventory, 10, 38, 44));
        this.addSlot(new SlotItemHandler(inventory, 11, 38, 88));
        this.addSlot(new SlotItemHandler(inventory, 12, 140, 66));
        this.addSlot(new SlotItemHandler(inventory, 13, 28, 66));
        this.addSlot(new SlotItemHandler(inventory, 14, 106, 23));
        this.addSlot(new SlotItemHandler(inventory, 15, 106, 109));
        this.addSlot(new SlotItemHandler(inventory, 16, 62, 23));
        this.addSlot(new SlotItemHandler(inventory, 17, 62, 109));


        this.layoutPlayerInventorySlots(12, 138);
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
