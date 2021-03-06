package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a player interacts with a Bucket
 */
public abstract class PlayerBucketEvent extends PlayerEvent implements Cancellable {
    private ItemStack itemStack;
    private boolean cancelled = false;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final Material bucket;
    private final EquipmentSlot hand; // Paper - add EquipmentSlot

    public PlayerBucketEvent(@NotNull final Player who, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        // Paper start - add EquipmentSlot
        this(who, blockClicked, blockFace, bucket, itemInHand, null);
    }

    public PlayerBucketEvent(@NotNull final Player who, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand, @Nullable  final EquipmentSlot hand) {
        // Paper end
        super(who);
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.itemStack = itemInHand;
        this.bucket = bucket;
        this.hand = hand == null ? player.getInventory().getItemInMainHand().equals(itemInHand) ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND : hand; // Paper - add EquipmentSlot
    }

    /**
     * Returns the bucket used in this event
     *
     * @return the used bucket
     */
    @NotNull
    public Material getBucket() {
        return bucket;
    }

    /**
     * Get the resulting item in hand after the bucket event
     *
     * @return ItemStack hold in hand after the event.
     */
    @Nullable
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Set the item in hand after the event
     *
     * @param itemStack the new held ItemStack after the bucket event.
     */
    public void setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Return the block clicked
     *
     * @return the clicked block
     */
    @NotNull
    public Block getBlockClicked() {
        return blockClicked;
    }

    /**
     * Get the face on the clicked block
     *
     * @return the clicked face
     */
    @NotNull
    public BlockFace getBlockFace() {
        return blockFace;
    }

    // Paper start
    /**
     * The hand used to perform this action.
     *
     * @return the hand used
     */
    @NotNull
    public EquipmentSlot getHand() {
        return hand;
    }
    // Paper end

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
