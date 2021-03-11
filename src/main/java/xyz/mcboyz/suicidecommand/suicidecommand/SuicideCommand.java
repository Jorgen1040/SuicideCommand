package xyz.mcboyz.suicidecommand.suicidecommand;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
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
                    ServerPlayerEntity player = c.getSource().asPlayer();
                    // Multiple different suicide messages? Choose random message from list/array
                    // "Player could no longer handle the pressure"
                    // etc...
                    ITextComponent message = new StringTextComponent(player.getDisplayName().getString() + " gave up on life");
                    player.setHealth(0);
                    c.getSource().getServer().getPlayerList().sendMessageToTeamOrAllPlayers(player, message);
                    return 1;
                })
        );
    }
}
