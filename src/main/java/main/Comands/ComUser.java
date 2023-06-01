package main.Comands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ComUser extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if (Comand.equals("userinfo")) {
            event.deferReply().queue();

            //String Name = event.getOption("User").getAsString();

            User user;
            Member member;
            if (event.getOption("user") == null) {
                user = event.getUser();
                member = event.getMember();

            } else {
                user = event.getOption("user").getAsUser();
                member = event.getOption("user").getAsMember();
                }

            EmbedBuilder embet = new EmbedBuilder();

            embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
            embet.setColor(0xd31212);
            embet.setDescription("UserInvos");
            embet.setTitle(user.getAsTag());
            embet.setThumbnail(user.getAvatarUrl());
            embet.addField("Name",user.getName(),true);
            embet.addField("Status", String.valueOf(member.getOnlineStatus()), false);
            embet.addField("Created","" + user.getTimeCreated().getDayOfMonth() + "." + event.getUser().getTimeCreated().getMonthValue() + "." + event.getUser().getTimeCreated().getYear() ,true);
            embet.addField("Joined","" + member.getTimeJoined().getDayOfMonth() + "." + event.getMember().getTimeJoined().getMonthValue() + "." + event.getMember().getTimeJoined().getYear(),true);
            //DragonCoins

            String rollen = "";
            for(Role i: member.getRoles()){
                rollen = rollen + i.getName() + " \n";
            }
            embet.addField("Roles",rollen,false);
            embet.addBlankField(false);
            embet.setFooter("Developer: Drache4K#9323");

            event.getHook().sendMessageEmbeds(embet.build()).queue();

        }
    }
}
