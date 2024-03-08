package satisfy.dragonflame.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfy.dragonflame.block.GrimAnvilBlock;
import satisfy.dragonflame.registry.BlockEntityRegistry;
import satisfy.dragonflame.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrimAnvilBlockEntity extends BlockEntity implements BlockEntityTicker<GrimAnvilBlockEntity> {
    private ItemStack ore;
    private int boss;
    private static final Random random = new Random();

    public GrimAnvilBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.GRIM_ANVIL_BLOCK_ENTITY.get(), blockPos, blockState);
        this.ore = ItemStack.EMPTY;
        this.boss = -1;
    }

    public ItemStack getOre() {
        return this.ore;
    }

    public InteractionResult addOre(ItemStack itemStack) {
        if (this.ore.isEmpty()) {
            this.ore = itemStack.split(1);
            return InteractionResult.SUCCESS;
        }
        this.setChanged();
        return InteractionResult.PASS;
    }

    public ItemStack removeOre() {
        ItemStack returnStack = this.boss < 0 ? this.ore.split(this.ore.getCount()) : ItemStack.EMPTY;
        this.setChanged();
        return returnStack;
    }

    @Override
    public void tick(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull GrimAnvilBlockEntity blockEntity) {
        if (level.isClientSide || this.level == null) return;

        // Existing Titan Ingot functionality
        if (!this.ore.isEmpty() && this.ore.getItem() == ObjectRegistry.TITAN_INGOT.get()) {
            BlockPos lightPos = blockPos.above();
            if (level.isEmptyBlock(lightPos)) {
                level.setBlockAndUpdate(lightPos, Blocks.LIGHT.defaultBlockState());
            }
        } else {
            BlockPos lightPos = blockPos.above();
            if (level.getBlockState(lightPos).is(Blocks.LIGHT)) {
                level.setBlockAndUpdate(lightPos, Blocks.AIR.defaultBlockState());
            }
        }

        // Existing boss functionality
        if (this.boss > 0) {
            Entity entity = this.level.getEntity(this.boss);
            if (entity instanceof Mob mob && mob.isAlive()) {
                displayparticlewhenblazeactive(level, blockPos, blockState);
                return;
            }
            defeat();
            return;
        }
        if (canSpawn()) {
            spawn();
            this.setChanged();
        }

        // New DRAGON_EMBLEM functionality
        if (!this.ore.isEmpty() && this.ore.getItem() == ObjectRegistry.DRAGON_EMBLEM.get()) {
            if (!(level instanceof ServerLevel)) return;
            ServerLevel serverLevel = (ServerLevel) level;
            displayDragonEmblemParticles(serverLevel, blockPos);
            transformItem(serverLevel);
            this.ore = ItemStack.EMPTY;
            this.setChanged();
        }
    }

    private void displayDragonEmblemParticles(ServerLevel serverLevel, BlockPos blockPos) {
        serverLevel.sendParticles(ParticleTypes.LAVA, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 100, 0.5, 0.5, 0.5, 0.05);
        serverLevel.sendParticles(ParticleTypes.FLAME, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 100, 0.5, 0.5, 0.5, 0.05);
    }

    private void transformItem(ServerLevel serverLevel) {
        ItemStack loot = ItemStack.EMPTY;
        if (random.nextDouble() < 0.00008) loot = new ItemStack(ObjectRegistry.SHATTERBRAND.get());
        else if (random.nextDouble() < 0.00005) loot = new ItemStack(ObjectRegistry.FIERY_WARHORSE_SPAWN_EGG.get());
        else if (random.nextDouble() < 0.25) loot = new ItemStack(ObjectRegistry.FIERY_WARAXE.get());
        else if (random.nextDouble() < 0.40) loot = new ItemStack(Blocks.AIR);
        else if (random.nextDouble() < 0.10) loot = new ItemStack(ObjectRegistry.DRAGON_HEAD_HELMET.get());
        else if (random.nextDouble() < 0.05) loot = new ItemStack(ObjectRegistry.QUALAMRAR.get());
        else if (random.nextDouble() < 0.20) loot = new ItemStack(ObjectRegistry.RAUBBAU.get());
        else if (random.nextDouble() < 0.05) loot = new ItemStack(ObjectRegistry.THOAREL_BOW.get());
        else if (random.nextDouble() < 0.05) loot = new ItemStack(ObjectRegistry.FLAMETHROWER.get());
        else if (random.nextDouble() < 0.05) loot = new ItemStack(ObjectRegistry.EMBERGRASP.get());
        else if (random.nextDouble() < 0.50) loot = new ItemStack(ObjectRegistry.DRAGONSCALE.get(), 32);

        if (!loot.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(serverLevel, this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 1, this.worldPosition.getZ() + 0.5, loot);
            serverLevel.addFreshEntity(itemEntity);
            this.ore = ItemStack.EMPTY;
            this.setChanged();
            serverLevel.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }




    private boolean canSpawn() {
        return this.ore.getItem() == ObjectRegistry.TITAN_INGOT.get() && this.level != null &&
                GrimAnvilBlock.getGrimAnvilComponents(this.worldPosition, this.getBlockState()).stream().allMatch(pos -> this.level.getBlockState(pos).getValue(GrimAnvilBlock.ESSENCE));
    }

    private void spawn() {
        if (!(level instanceof ServerLevel)) return;

        int[][] cornerOffsets = {{-3, 0, -3}, {-3, 0, 2}, {2, 0, -3}, {2, 0, 2}};

        for (int[] offset : cornerOffsets) {
            Mob mob = EntityType.BLAZE.create(this.level);
            if (mob == null) continue;

            BlockPos spawnPos = this.worldPosition.offset(offset[0], offset[1], offset[2]);
            mob.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
            this.level.addFreshEntity(mob);
            mob.spawnAnim();
            this.boss = mob.getId();
            displayparticlewhenblazespawn(this.level, spawnPos, this.getBlockState()); // Oder displayparticlewhenblazespawn

        }

        this.setChanged();
    }


    private void displayparticlewhenblazespawn(Level world, BlockPos pos, BlockState blockState) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, 4, 0.5, 0.5, 0.5, 0.05);
            displayLavaParticles(world, pos);
        }
    }

    private void displayLavaParticles(Level world, BlockPos pos) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.LAVA, pos.getX(), pos.getY() + 1, pos.getZ(), 50, 0.5, 0.5, 0.5, 0.05);
        }
    }

    private void displayparticlewhenblazeactive(Level world, BlockPos pos, BlockState blockState) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, 4, 0.5, 0.5, 0.5, 0.05);
            displayActiveParticles(world, pos);
        }
    }

    private void displayActiveParticles(Level world, BlockPos pos) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ASH, pos.getX(), pos.getY() + 1, pos.getZ(), 2, 0.5, 0.5, 0.5, 0.05);
            serverLevel.sendParticles(ParticleTypes.WHITE_ASH, pos.getX(), pos.getY() + 1, pos.getZ(), 2, 0.5, 0.5, 0.5, 0.05);
        }
    }

    private void defeat() {
        assert this.level instanceof ServerLevel;
        this.boss = -1;
        this.ore = ObjectRegistry.TITAN_PLATES.get().getDefaultInstance();
        System.out.println(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, this.ore));
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, this.ore) > 0)
            this.ore.setCount(3);
        GrimAnvilBlock.getGrimAnvilComponents(this.worldPosition, this.getBlockState()).forEach(pos -> this.level.setBlockAndUpdate(pos, this.level.getBlockState(pos).setValue(GrimAnvilBlock.ESSENCE, false)));
        this.setChanged();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compoundTag) {
        compoundTag.put("ore", this.ore.save(new CompoundTag()));
        compoundTag.putInt("boss", this.boss);
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        this.ore = compoundTag.contains("ore") ? ItemStack.of(compoundTag.getCompound("ore")) : ItemStack.EMPTY;
        this.boss = compoundTag.contains("boss") ? compoundTag.getInt("boss") : -1;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        this.saveAdditional(compoundTag);
        return compoundTag;
    }
}
