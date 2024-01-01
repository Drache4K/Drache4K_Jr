package main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDA.Status;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.internal.JDAImpl;
import org.jetbrains.annotations.NotNull;

public class SendMessage {
    public static void SendMessageToUser(String user, String Msg){
        try {
            Haupt.bot.getUserById(user).openPrivateChannel().complete().sendMessage(Msg).queue();
        }catch (Exception e){
            System.out.println(e);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Haupt.bot.getUserById(user).openPrivateChannel().complete().sendMessage(Msg).queue();
            }catch (Exception d){
                System.out.println(d);
            }
        }
    }

    public static void SendMessageToGuild(String guild, String Msg){
        try{
        Haupt.bot.getGuildById(guild).getDefaultChannel().asTextChannel().sendMessage(Msg).queue();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void SendToAllUser(String Msg){
        for (User i : Haupt.bot.getUsers()) {
            System.out.println(i.getName() +"\t"+ i.getId());
            SendMessageToUser(i.getId().toString(), Msg);
        }
    }
    public static void SendToAllGuilds(String Msg){
        for (Guild i : Haupt.bot.getGuilds()) {
            SendMessageToGuild(i.getId(), Msg);
        }
    }
}
