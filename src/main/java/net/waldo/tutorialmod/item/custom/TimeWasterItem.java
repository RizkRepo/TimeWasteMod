package net.waldo.tutorialmod.item.custom;

import com.mojang.serialization.DataResult;
import net.minecraft.client.session.report.ReporterEnvironment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import java.util.concurrent.*;

public class TimeWasterItem extends Item {
    public TimeWasterItem(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            ServerWorld server = world.getServer().getOverworld();
            if (server!=null) {
                final long[] currentTime = {world.getTimeOfDay()};
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                user.getItemCooldownManager().set(this, 500);//Item Cooldown So Evil Users Can't Crash Their Games
                Runnable task = new Runnable() {
                    int i = 1;
                    @Override
                    public void run() {
                        if (i<6){ //This allows for the time to increment instead of just immediately passing, it also adds a clock like ticking sound
                            int timeIncrementer = 750;
                            currentTime[0] = currentTime[0] +(timeIncrementer*i);
                            server.setTimeOfDay(currentTime[0]);
                            world.playSound(null,user.getBlockPos(), SoundEvents.BLOCK_COPPER_BULB_TURN_OFF, SoundCategory.NEUTRAL, 2.0f,0.5f);
                            world.playSound(null,user.getBlockPos(), SoundEvents.BLOCK_COPPER_BULB_TURN_ON, SoundCategory.NEUTRAL, 2.0f,0.5f);
                            i++;
                        } else {
                            scheduler.shutdown();
                        }
                    }

                };
                scheduler.scheduleAtFixedRate(task,0,1,TimeUnit.SECONDS);

                world.playSound(null,user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL);

            }
        }
        return TypedActionResult.success(stack);
    }
}
