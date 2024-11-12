package net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import io.github.noeppi_noeppi.libx.menu.BlockEntityMenu;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class ContainerIndustrialAgglomerationFactoryBase extends BlockEntityMenu<BlockEntityIndustrialAgglomerationFactoryBase> {
    public ContainerIndustrialAgglomerationFactoryBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 3, 7);
        IItemHandlerModifiable inventory = (this.blockEntity).getInventory();


        int index = this.addSlotBox(inventory, 0, 66, 22, 3, 18, 1, 18);
        this.addSlotBox(inventory, index, 57, 58, 4, 18, 1, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 101);
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
