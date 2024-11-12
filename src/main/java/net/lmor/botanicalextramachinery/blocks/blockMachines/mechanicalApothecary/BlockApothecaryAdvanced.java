package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalApothecary;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import io.github.noeppi_noeppi.libx.base.tile.BlockEntityBase;
import io.github.noeppi_noeppi.libx.base.tile.MenuBlockBE;
import io.github.noeppi_noeppi.libx.block.RotationShape;
import io.github.noeppi_noeppi.libx.mod.ModX;
import io.github.noeppi_noeppi.libx.render.ItemStackRenderer;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryAdvanced;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalApothecary.RenderApothecaryAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryAdvanced;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BlockApothecaryAdvanced extends MenuBlockBE<BlockEntityApothecaryAdvanced, ContainerApothecaryAdvanced> {
    public static final RotationShape SHAPE;

    public BlockApothecaryAdvanced(ModX mod, Class<BlockEntityApothecaryAdvanced> teClass, MenuType<ContainerApothecaryAdvanced> menu) {
        super(mod, teClass, menu, Properties.of(Material.STONE).strength(2.0F, 10.0F).dynamicShape(), new Item.Properties());
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(ResourceLocation id, Consumer<Runnable> defer) {
        ItemStackRenderer.addRenderBlock(this.getBlockEntityType(), true);
        MenuScreens.register(ModBlocks.advancedApothecary.menu, ScreenApothecaryAdvanced::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderApothecaryAdvanced();
        });
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeItemClient(@Nonnull Consumer<IItemRenderProperties> consumer) {
        consumer.accept(ItemStackRenderer.createProperties());
    }


    @Nonnull
    public InteractionResult use(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity tile = level.getBlockEntity(pos);
            ItemStack held = player.getMainHandItem();
            FluidActionResult fluidActionResult = FluidUtil.tryEmptyContainer(held, tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).orElse( null), 1000, player, true);
            if (fluidActionResult.isSuccess()) {
                if (tile instanceof BlockEntityBase) {
                    ((BlockEntityBase)tile).setDispatchable();
                }

                if (!player.isCreative()) {
                    player.setItemInHand(hand, fluidActionResult.getResult());
                }

                return InteractionResult.SUCCESS;
            }

            super.use(state, level, pos, player, hand, hit);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    public int getLightBlock(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return 0;
    }

    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityApothecaryAdvanced){
                ((BlockEntityApothecaryAdvanced) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    protected boolean shouldDropInventory(Level level, BlockPos pos, BlockState state) {
        return false;
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0), box(0.0, 1.0, 0.0, 1.0, 15.0, 1.0), box(15.0, 1.0, 0.0, 16.0, 15.0, 1.0), box(0.0, 1.0, 15.0, 1.0, 15.0, 16.0), box(15.0, 1.0, 15.0, 16.0, 15.0, 16.0), box(0.0, 15.0, 1.0, 1.0, 16.0, 15.0), box(15.0, 15.0, 1.0, 16.0, 16.0, 15.0), box(0.0, 15.0, 0.0, 16.0, 16.0, 1.0), box(0.0, 15.0, 15.0, 16.0, 16.0, 16.0), box(12.0, 10.0, 4.0, 13.0, 13.0, 12.0), box(3.0, 1.0, 3.0, 13.0, 2.0, 13.0), box(5.0, 2.0, 5.0, 11.0, 9.0, 11.0), box(3.0, 9.0, 3.0, 13.0, 10.0, 13.0), box(3.0, 10.0, 12.0, 13.0, 13.0, 13.0), box(3.0, 10.0, 3.0, 13.0, 13.0, 4.0), box(3.0, 10.0, 4.0, 4.0, 13.0, 12.0)));
    }
}
