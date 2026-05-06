package me.elephant1214.paperfixes.mixin.common.world.fast_border;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.elephant1214.paperfixes.manager.FastWorldBorder;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldBorder.class)
public class WorldBorderMixin {
    @Unique
    private final FastWorldBorder paperFixes$fastBorder = new FastWorldBorder((WorldBorder) (Object) this);

    /**
     * @author Elephant_1214
     * @reason Faster world border
     */
    @Overwrite
    public double minX() {
        return paperFixes$fastBorder.minX();
    }

    /**
     * @author Elephant_1214
     * @reason Faster world border
     */
    @Overwrite
    public double minZ() {
        return paperFixes$fastBorder.minZ();
    }

    /**
     * @author Elephant_1214
     * @reason Faster world border
     */
    @Overwrite
    public double maxX() {
        return paperFixes$fastBorder.maxX();
    }

    /**
     * @author Elephant_1214
     * @reason Faster world border
     */
    @Overwrite
    public double maxZ() {
        return paperFixes$fastBorder.maxZ();
    }

    @Definition(id = "startDiameter", field = "Lnet/minecraft/world/border/WorldBorder;startDiameter:D")
    @Definition(id = "endDiameter", field = "Lnet/minecraft/world/border/WorldBorder;endDiameter:D")
    @Expression("this.startDiameter + (this.endDiameter - this.startDiameter) * ?")
    @ModifyExpressionValue(
            method = "getDiameter",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private double paperFixes$fixBorderAnimation(double original) {
        paperFixes$fastBorder.recompute(original);
        return original;
    }
}
