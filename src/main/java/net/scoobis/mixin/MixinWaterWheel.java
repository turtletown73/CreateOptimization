package net.scoobis.mixin;

import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlock;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WaterWheelBlock.class, remap = false)
public class MixinWaterWheel {
	@Unique
    private int ticksPerTick = 60;
    
    @Unique
    private int tickCounter = 0;

    @Inject( method = "tick", at = @At(value = "HEAD"), cancellable = true )
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci) {
        tickCounter++;
        if (tickCounter % ticksPerTick != 0) {
            ci.cancel();
        }
    }
}