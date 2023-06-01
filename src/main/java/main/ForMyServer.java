package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

//Hir werde ich Ein Join / Berüsung und eine Rollenverteiler für mein eigenen Dc Server machen
public class ForMyServer extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        System.out.println("Join! " + event.getGuild().getId().toString());
        if(event.getGuild().getId().equals("849582114713763840")){
            User user;
            Member member;

            user = event.getUser();
            member = event.getMember();

            EmbedBuilder embet = new EmbedBuilder();

            embet.setAuthor("Hello from Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
            embet.setColor(0xd31212);
            embet.setDescription("Hello <@" + user.getId() + ">");
            embet.setTitle("Welcome <@" + user.getId() + "> to "+ event.getGuild().getName());
            embet.setThumbnail(user.getAvatarUrl());
            embet.addField("Name", user.getName()+ "/" + user.getAsTag() ,true);
            embet.addField("Created","" + user.getTimeCreated().getDayOfMonth() + "." + event.getUser().getTimeCreated().getMonthValue() + "." + event.getUser().getTimeCreated().getYear() ,true);
            

            embet.setFooter("Developer: Drache4K#9323");

            TextChannel welcome;
            welcome = event.getJDA().getTextChannelById("854379620974592071");
            welcome.sendMessageEmbeds(embet.build()).queue();
        }
    }
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if(Comand.equals("Roles")){
            event.deferReply().queue();
            if(event.getMember().getUser().getId().equals("795949899974836245")) {

                //event.getHook().sendMessage(replay).queue();

            }else {event.getHook().sendMessage("Only Drache4K can this!").queue();}
        }

    }

}

