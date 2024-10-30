package net.lmor.botaniaextramachinery.blocks.containers.mechanicalApothecary;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUltimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerApothecaryUltimate extends BlockEntityMenu<BlockEntityApothecaryUltimate> {
    public ContainerApothecaryUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 19, 35);
        IItemHandlerModifiable inventory = ((BlockEntityApothecaryUltimate)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 97));
        this.addSlot(new SlotItemHandler(inventory, 1, 47, 97));
        this.addSlot(new SlotItemHandler(inventory, 2, 121, 97));

        int index = this.addSlotBox(inventory, 3, 11, 18, 4, 18, 4, 18);
        this.addSlotBox(inventory, index, 103, 18, 4, 18, 4, 18, UnrestrictedOutputSlot::new);
        this.layoutPlayerInventorySlots(12, 138);
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
