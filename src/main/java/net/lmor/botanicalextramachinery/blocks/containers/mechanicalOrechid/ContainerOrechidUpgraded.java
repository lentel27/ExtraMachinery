package net.lmor.botanicalextramachinery.blocks.containers.mechanicalOrechid;

import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import io.github.noeppi_noeppi.libx.menu.BlockEntityMenu;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUpgraded;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class ContainerOrechidUpgraded extends BlockEntityMenu<BlockEntityOrechidUpgraded> {
    public ContainerOrechidUpgraded(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 14, 23);

        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();
        int index = this.addSlotBox(inventory, 0, 48, 19, 5, 18, 1, 18);

        int index_2 = this.addSlotBox(inventory, index, 12, 43, 9, 18, 1, 18);
        this.addSlotBox(inventory, index_2, 12, 83, 9, 18, 1, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 124);
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
