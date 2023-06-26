package main.Comands;

import main.Haupt;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;

public class ComSmall extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if (Comand.equals("report")) {
            event.getChannel().sendTyping();
            event.deferReply().queue();
            EmbedBuilder embet = new EmbedBuilder();

            embet.setAuthor(event.getUser().getName());
            embet.setColor(0xd31212);
            if(event.isFromGuild()) {embet.setDescription(event.getGuild().toString());
            }else{embet.setDescription("Privat message");}
            embet.setTitle("Report");
            embet.setThumbnail(event.getUser().getAvatarUrl());
            embet.addBlankField(false);
            embet.addField("Messag", event.getOption("message").getAsString(), false);
            embet.addBlankField(false);
            embet.addField("User", event.getUser().getAsTag(), false);
            embet.setFooter("Developer: Drache4K#9323");

            event.getHook().sendMessage("Message sent, please be patient!").queue();
            event.getJDA().getUserById(Haupt.Drache4K).openPrivateChannel().complete().sendMessageEmbeds(embet.build()).queue();

        } else if (Comand.equals("avatar")) {
            event.deferReply().queue();
            event.getHook().sendMessage(event.getOption("user").getAsUser().getAvatarUrl()).queue();

        } else if (Comand.equals("name...")) {
            event.getJDA().getGuildById("849582114713763840").deleteCommandById(event.getCommandId()).queue();

        } else if (Comand.equals("server")) {
            event.deferReply().queue();
            event.getHook().sendMessage(event.getJDA().getGuildById(event.getOption("id").getAsString().toString()).getName()).queue();

        }
    }
}
