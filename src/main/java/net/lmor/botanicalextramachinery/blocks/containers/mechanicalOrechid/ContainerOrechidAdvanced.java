package net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid;

import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import io.github.noeppi_noeppi.libx.menu.BlockEntityMenu;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ContainerOrechidAdvanced extends BlockEntityMenu<BlockEntityOrechidAdvanced> {
    public ContainerOrechidAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 19, 29);

        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 14, 80));
        this.addSlot(new SlotItemHandler(inventory, 1, 154, 80));


        int index = this.addSlotBox(inventory, 2, 30, 19, 7, 18, 1, 18);

        int index_2 = this.addSlotBox(inventory, index, 48, 43, 5, 18, 2, 18);
        this.addSlotBox(inventory, index_2, 48, 99, 5, 18, 2, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 158);
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
