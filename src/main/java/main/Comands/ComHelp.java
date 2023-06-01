package main.Comands;

import main.Haupt;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.component.SelectMenuImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ComHelp extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if (Comand.equals("help")) {
            event.deferReply().queue();
            EmbedBuilder embet = new EmbedBuilder();
            SelectMenu Menu = SelectMenu.create("where")
                    .setPlaceholder("Menu")
                    .addOption("/help","help")
                    .addOption("DragonCoins","DC", "The money-system of Drache4K Jr.", Haupt.DC)
                    .build();

            embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
            embet.setColor(0xd31212);
            embet.setDescription("What can I?");
            embet.setTitle("/help");
            embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
            embet.addBlankField(false);
            embet.addField("/ped","/ped [time] -> you can ped Drache4K Jr.",true);
            embet.addField("sag","He say erverthing after the sag",true);
            embet.addField("Hello","He reacts on many hellos",true);
            embet.addField("Work in progress...","Drache4K will teach me more tricks soon",false);
            embet.setFooter("Developer: Drache4K#9323");

            event.getHook().sendMessageEmbeds(embet.build()).setActionRow(Menu).queue();

            //embet.addBlankField(false);

            //event.getHook().editOriginalEmbeds(embet.build()).setActionRow(Menu).queue();

        }
    }

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        if (event.getSelectMenu().getId().equals("where")){
            EmbedBuilder embet = new EmbedBuilder();
            SelectMenu Menu = SelectMenu.create("where")
                    .setPlaceholder("Menu")
                    .addOption("/help","help")
                    .addOption("DragonCoins","DC", "The money-system of Drache4K Jr.", Haupt.DC)
                    .build();

            switch (event.getValues().get(0)){
                case "help":

                    embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
                    embet.setColor(0xd31212);
                    embet.setDescription("What can I?");
                    embet.setTitle("/help");
                    embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
                    embet.addBlankField(false);
                    embet.addField("/ped","/ped [time] -> you can ped Drache4K Jr.",true);
                    embet.addField("sag","He say erverthing after the sag",true);
                    embet.addField("Hello","He reacts on many hellos",true);
                    embet.addBlankField(false);
                    embet.addField("Work in progress...","Drache4K will teach me more tricks soon",false);
                    embet.setFooter("Developer: Drache4K#9323");

                    event.getHook().editOriginalEmbeds(embet.build()).setActionRow(Menu).queue();
                    break;

                case "DC":
                    embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
                    embet.setColor(0xd31212);
                    embet.setDescription("What is DragonCoin?");
                    embet.setTitle("<:DragonCoin:953410089639899196> ");
                    embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
                    embet.addField("Error", "Not rady yet.",false);
                    embet.addBlankField(false);
                    embet.addField("/get","get money",true);
                    embet.addField("/del","Deleat money",true);
                    embet.addField("/give","Transfere money",true);
                    embet.addField("/shop","Ther might be a Shop",true);
                    embet.addBlankField(false);
                    embet.addField("Work in progress...","Drache4K will teach me more tricks soon",false);
                    embet.setFooter("Developer: Drache4K#9323");

                    break;
            }
            event.getHook().editOriginalEmbeds(embet.build()).setActionRow(Menu).queue();
            event.deferEdit().queue();
        }

    }
}
