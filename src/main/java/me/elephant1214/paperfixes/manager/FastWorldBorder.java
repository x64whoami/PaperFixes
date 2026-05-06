package me.elephant1214.paperfixes.manager;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.border.WorldBorder;

import javax.vecmath.Vector2d;

/**
 * The way the game handles the world border is not very good and can get expensive with
 * lots of calls to {@link WorldBorder#contains(BlockPos)} because it recomputes the
 * border every time min/max methods are called.
 * <p>
 * This just caches the corners, and mixins handle updating them when needed.
 */
public final class FastWorldBorder {
    private static final double MIN_POS = 1.0;
    private final WorldBorder border;
    /**
     * -X and -Z corner, to the north-west
     */
    private final Vector2d min = new Vector2d();
    /**
     * +X and +Z corner, to the south-east
     */
    private final Vector2d max = new Vector2d();

    public double minX() {
        return this.min.x;
    }

    public double minZ() {
        return this.min.y;
    }

    public double maxX() {
        return this.max.x;
    }

    public double maxZ() {
        return this.max.y;
    }

    /**
     * Recomputes the corners of the border.
     */
    public void recompute() {
        recompute(border.getDiameter());
    }

    /**
     * Fuu this janky ahh game bro
     */
    public void recompute(final double diameter) {
        final double radius = diameter / 2.0;
        final double centerX = border.getCenterX();
        final double centerZ = border.getCenterZ();

        this.min.set(clampPos(centerX - radius), clampPos(centerZ - radius));
        this.max.set(clampPos(centerX + radius), clampPos(centerZ + radius));
    }

    /**
     * Helper to clamp the final position between (-)1 and the world size.
     */
    private double clampPos(double limit) {
        if (limit < 0) {
            return MathHelper.clamp(limit, -border.getSize(), -MIN_POS);
        } else {
            return MathHelper.clamp(limit, MIN_POS, border.getSize());
        }
    }

    public FastWorldBorder(WorldBorder border) {
        this.border = border;
        recompute();
    }
}
