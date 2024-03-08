package satisfy.dragonflame.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import satisfy.dragonflame.Dragonflame;

import java.util.stream.Stream;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Dragonflame.MOD_ID, Registries.CREATIVE_MODE_TAB);

    /**
     *
     * // TODO: I've outcommited Bows and Crossbows since they can't easily be modified in terms of damage and other benefits - Should we keep or remove them?
     *
     *
     */

    public static final RegistrySupplier<CreativeModeTab> DRAGONFLAME_TAB = CREATIVE_MODE_TABS.register("dragonflame", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .icon(() -> new ItemStack(ObjectRegistry.HEARTHSTONE.get()))
            .title(Component.translatable("itemGroup.dragonflame.dragonflame_tab"))
            .displayItems((parameters, out) -> Stream.of(
                    ObjectRegistry.DRAGON_LOG,
                    ObjectRegistry.DRAGON_WOOD,
                    ObjectRegistry.STRIPPED_DRAGON_WOOD,
                    ObjectRegistry.STRIPPED_DRAGON_LOG,
                    ObjectRegistry.DRAGON_PLANKS,
                    ObjectRegistry.DRAGON_STAIRS,
                    ObjectRegistry.DRAGON_SLAB,
                    ObjectRegistry.DRAGON_PRESSURE_PLATE,
                    ObjectRegistry.DRAGON_BUTTON,
                    ObjectRegistry.DRAGON_FENCE,
                    ObjectRegistry.DRAGON_FENCE_GATE,
                    ObjectRegistry.DRAGON_WINDOW,
                    ObjectRegistry.DRAGON_TRAPDOOR,
                    ObjectRegistry.DRAGON_DOOR,
                    BoatAndSignRegistry.DRAGON_BOAT,
                    BoatAndSignRegistry.DRAGON_CHEST_BOAT,
                    BoatAndSignRegistry.DRAGON_SIGN_ITEM,
                    BoatAndSignRegistry.DRAGON_HANGING_SIGN_ITEM,
                    ObjectRegistry.BURNT_LOG,
                    ObjectRegistry.BURNT_WOOD,
                    ObjectRegistry.BURNT_PLANKS,
                    ObjectRegistry.BURNT_STAIRS,
                    ObjectRegistry.BURNT_SLAB,
                    ObjectRegistry.BURNT_PRESSURE_PLATE,
                    ObjectRegistry.BURNT_BUTTON,
                    ObjectRegistry.BURNT_TRAPDOOR,
                    ObjectRegistry.BURNT_DOOR,
                    ObjectRegistry.BURNT_FENCE,
                    ObjectRegistry.BURNT_FENCE_GATE,
                    ObjectRegistry.WHITE_SAND,
                    ObjectRegistry.BLACK_SAND,
                    ObjectRegistry.DRAGON_LEAVES,
                    ObjectRegistry.DRAGONSTONE,
                    ObjectRegistry.DRAGONSTONE_STAIRS,
                    ObjectRegistry.DRAGONSTONE_SLAB,
                    ObjectRegistry.COBBLED_DRAGONSTONE,
                    ObjectRegistry.COBBLED_DRAGONSTONE_STAIRS,
                    ObjectRegistry.COBBLED_DRAGONSTONE_SLAB,
                    ObjectRegistry.DRAGONSTONE_BRICKS,
                    ObjectRegistry.DRAGONSTONE_BRICK_STAIRS,
                    ObjectRegistry.DRAGONSTONE_BRICK_SLAB,
                    ObjectRegistry.MOSSY_COBBLED_DRAGONSTONE,
                    ObjectRegistry.MOSSY_COBBLED_DRAGONSTONE_STAIRS,
                    ObjectRegistry.MOSSY_COBBLED_DRAGONSTONE_SLAB,
                    ObjectRegistry.MOSSY_DRAGONSTONE_BRICKS,
                    ObjectRegistry.MOSSY_DRAGONSTONE_BRICK_STAIRS,
                    ObjectRegistry.MOSSY_DRAGONSTONE_BRICK_SLAB,
                    ObjectRegistry.CRACKED_DRAGONSTONE_BRICKS,
                    ObjectRegistry.DRAGONSTONE_WALL,
                    ObjectRegistry.COBBLED_DRAGONSTONE_WALL,
                    ObjectRegistry.DRAGONSTONE_BRICK_WALL,
                    ObjectRegistry.MOSSY_COBBLED_DRAGONSTONE_WALL,
                    ObjectRegistry.MOSSY_DRAGONSTONE_BRICK_WALL,
                    ObjectRegistry.CHISELED_DRAGONSTONE,
                    ObjectRegistry.POLISHED_DRAGONSTONE,
                    ObjectRegistry.TITAN_ORE,
                    ObjectRegistry.TITAN_BLOCK,
                    ObjectRegistry.TITAN_BLOCK_STAIRS,
                    ObjectRegistry.TITAN_BLOCK_SLAB,
                    ObjectRegistry.GRIM_ANVIL,
                    ObjectRegistry.LOOTCHEST,
                    ObjectRegistry.STATUE_LILITU,
                    ObjectRegistry.STATUE_ADOREDU,
                    ObjectRegistry.ARMORED_PILLAGER_SPAWN_EGG,
                    ObjectRegistry.ARMORED_VINDICATOR_SPAWN_EGG,
                    ObjectRegistry.PILLAGER_DOG_SPAWN_EGG,
                    ObjectRegistry.DRAGON_WHELPLING_SPAWN_EGG,
                    ObjectRegistry.FIREDRAGON_SPAWN_EGG,
                    ObjectRegistry.REINFORCED_LEATHER_HELMET,
                    ObjectRegistry.REINFORCED_LEATHER_CHESTPLATE,
                    ObjectRegistry.REINFORCED_LEATHER_LEGGINGS,
                    ObjectRegistry.REINFORCED_LEATHER_BOOTS,
                    ObjectRegistry.DRAGON_HELMET,
                    ObjectRegistry.DRAGON_CHESTPLATE,
                    ObjectRegistry.DRAGON_LEGGINGS,
                    ObjectRegistry.DRAGON_BOOTS,
                    ObjectRegistry.DRAGON_SWORD,
                    ObjectRegistry.DRAGON_AXE,
                    ObjectRegistry.DRAGON_PICKAXE,
                    ObjectRegistry.DRAGON_SHOVEL,
                    ObjectRegistry.DRAGON_HOE,
                    //ObjectRegistry.DRAGON_BOW,
                    //ObjectRegistry.DRAGON_CROSSBOW,
                    ObjectRegistry.TITAN_HELMET,
                    ObjectRegistry.TITAN_CHESTPLATE,
                    ObjectRegistry.TITAN_LEGGINGS,
                    ObjectRegistry.TITAN_BOOTS,
                    ObjectRegistry.TITAN_SWORD,
                    ObjectRegistry.TITAN_AXE,
                    ObjectRegistry.TITAN_PICKAXE,
                    ObjectRegistry.TITAN_SHOVEL,
                    ObjectRegistry.TITAN_HOE,
                    //ObjectRegistry.TITAN_BOW,
                    //ObjectRegistry.TITAN_CROSSBOW,
                    ObjectRegistry.HARDENED_TITAN_HELMET,
                    ObjectRegistry.HARDENED_TITAN_CHESTPLATE,
                    ObjectRegistry.HARDENED_TITAN_LEGGINGS,
                    ObjectRegistry.HARDENED_TITAN_BOOTS,
                    ObjectRegistry.HARDENED_TITAN_SWORD,
                    ObjectRegistry.HARDENED_TITAN_AXE,
                    ObjectRegistry.HARDENED_TITAN_PICKAXE,
                    ObjectRegistry.HARDENED_TITAN_SHOVEL,
                    ObjectRegistry.HARDENED_TITAN_SCYTHE,
                    //ObjectRegistry.HARDENED_TITAN_BOW,
                    //ObjectRegistry.HARDENED_TITAN_CROSSBOW,
                    ObjectRegistry.QUALAMRAR,
                    ObjectRegistry.RAUBBAU,
                    ObjectRegistry.FLAMETHROWER,
                    ObjectRegistry.EMBERGRASP,
                    ObjectRegistry.FIERY_WARAXE,
                    ObjectRegistry.SHATTERBRAND,
                    ObjectRegistry.ARMORED_VINDICATOR_AXE,
                    ObjectRegistry.THOAREL_BOW,
                    ObjectRegistry.DRAGON_SAPLING,
                    ObjectRegistry.DRAGON_EGG,
                    ObjectRegistry.DRAGON_BONES,
                    ObjectRegistry.DRAGON_EYE,
                    ObjectRegistry.DRAGON_HEARTH,
                    ObjectRegistry.DRAGON_HEAD_HELMET,
                    ObjectRegistry.DRAGON_EMBLEM,
                    ObjectRegistry.DRAGONSCALE,
                    ObjectRegistry.DRAGONFODDER,
                    ObjectRegistry.DRAGON_SADDLE,
                    ObjectRegistry.DRACONIC_FOR_DUMMIES,
                    ObjectRegistry.HEARTHSTONE,
                    ObjectRegistry.DRAGON_TEARS,
                    ObjectRegistry.FIERY_WARHORSE_SPAWN_EGG,
                    ObjectRegistry.ESSENCE_OF_FIRE,
                    ObjectRegistry.HEART_OF_FLAME,
                    ObjectRegistry.TITAN_DUST,
                    ObjectRegistry.TITAN_INGOT,
                    ObjectRegistry.TITAN_PLATES,
                    ObjectRegistry.HARDENED_TITAN_PLATES

            ).forEach(registrySupplier -> out.accept(registrySupplier.get().asItem().getDefaultInstance())))
            .build());

    public static void init() {
        CREATIVE_MODE_TABS.register();
    }
}
