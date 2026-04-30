# Jade: unhide
### This Mod based on [Jade](//github.com/Snownee/Jade) by [Snownee](//github.com/Snownee)

Jade: Unhide is a Mod that let Jade don't hide blocks like: 
- Powder Snow
- Trapped Chest
- Suspicious Sand/Gravel
- Infested Blocks

Make it doesn't hide blocks is what some players using Jade want, [Jade Issue #465](//github.com/Snownee/Jade/issues/465) and [Jade Issue #341](//github.com/Snownee/Jade/issues/341) could prove that.

This Mod lets you don't need to worry about is this 
- stone infested
- chest lead to a redstone trap (ignore comparator)
- snow will let you fall when you can't distinguish it's powder one or not
- when you excavating in a trail ruin (don't use xray pack to avoid this)

## How did I do this

Jade only hide blocks when the player is in survival/adventure mode, here's how Jade do it:

```java
// In snownee.jade.JadeClient
public static @Nullable Accessor<?> builtInOverrides(HitResult hitResult, @Nullable Accessor<?> accessor, @Nullable Accessor<?> originalAccessor) {
    if (!WailaClientRegistration.instance().maybeLowVisionUser() && IWailaConfig.get().getGeneral().getBuiltinCamouflage()) {
        if (accessor instanceof BlockAccessor) {
            BlockAccessor target = (BlockAccessor)accessor;
            PlayerEntity player = accessor.getPlayer();
            if (player.isCreative() || player.isSpectator()) {
                return accessor;
            }
            IWailaClientRegistration client = VanillaPlugin.CLIENT_REGISTRATION;
            if (target.getBlock() instanceof ChestBlock) {
                BlockState state = VanillaPlugin.getCorrespondingNormalChest(target.getBlockState());
                if (state != target.getBlockState()) {
                    return client.blockAccessor().from(target).blockState(state).build();
                }
            }
            BlockAccessor.Builder builder = client.blockAccessor().from(target).blockEntity(() -> null);
            if (target.getBlock() instanceof InfestedBlock) {
                Block block = ((InfestedBlock)target.getBlock()).getRegularBlock();
                return builder.blockState(block.getDefaultState()).build();
            }
            if (target.getBlock() == Blocks.POWDER_SNOW) {
                Block block = Blocks.SNOW_BLOCK;
                return builder.blockState(block.getDefaultState()).build();
            }
            Block block = target.getBlock();
            if (block instanceof BrushableBlock) {
                BrushableBlock brushable = (BrushableBlock)block;
                block = brushable.getBaseBlock();
                return builder.blockState(block.getDefaultState()).build();
            }
        }
        return accessor;
    } else {
        return accessor;
    }
}
```

'Cause it's client side, so I just created a client only mod, and wrote a Mixin to let the entire function cancel & return accessor.

Then turn it to jar, and writing README here!
