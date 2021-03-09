package xyz.mcboyz.suicidecommand.suicidecommand;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.util.Objects;

@Mod("suicidecommand")
public class SuicideCommand {

    public SuicideCommand() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getServer().getCommandManager().getDispatcher();
        commandDispatcher.register(
                Commands.literal("suicide")
                .executes(c -> {
                    Objects.requireNonNull(c.getSource().getEntity()).onKillCommand();
                    return 1;
                })
        );
    }
}
