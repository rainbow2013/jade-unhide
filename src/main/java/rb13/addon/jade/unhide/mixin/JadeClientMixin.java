package rb13.addon.jade.unhide.mixin;

import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.jade.JadeClient;
import snownee.jade.api.Accessor;

@Mixin(JadeClient.class)
public abstract class JadeClientMixin {
	@Inject(at = @At("HEAD"), method = "builtInOverrides", cancellable = true)
    private static void builtInOverrides(HitResult hitResult, @Nullable Accessor<?> accessor, @Nullable Accessor<?> originalAccessor, CallbackInfoReturnable<Accessor<?>> cir) {
        cir.cancel();
        cir.setReturnValue(accessor);
    }
}