package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalIndustrialAgglomerationFactory.RenderIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;

public class BlockIndustrialAgglomerationFactoryBase extends BotanicalBlock<BlockEntityIndustrialAgglomerationFactoryBase, ContainerIndustrialAgglomerationFactoryBase> {
    public static final RotationShape SHAPE;

    public BlockIndustrialAgglomerationFactoryBase(ModX mod, Class<BlockEntityIndustrialAgglomerationFactoryBase> teClass, MenuType<ContainerIndustrialAgglomerationFactoryBase> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.baseIndustrialAgglomerationFactory.menu, ScreenIndustrialAgglomerationFactoryBase::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderIndustrialAgglomerationFactoryBase();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityIndustrialAgglomerationFactoryBase){
                ((BlockEntityIndustrialAgglomerationFactoryBase) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(2.6, 0.0, 2.6, 13.4, 4.6, 13.4), box(6.2, 0.0, 6.2, 9.8, 5.3, 9.8)}));
    }
}
