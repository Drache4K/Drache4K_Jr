package main.DC;

import main.Haupt;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.ApplicationCommandUpdatePrivilegesEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;
import main.MySQL.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DCget extends ListenerAdapter {

    public ArrayList<List> Transfer = new ArrayList<List>();


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if (Comand.equals("get")) {
            event.deferReply().queue();
            if (event.getUser().getId().toString().equals(Haupt.Drache4K)) {
                User user = event.getOption("user").getAsUser();
                Integer DC = event.getOption("dc").getAsInt();

                Integer userdc = Integer.valueOf(mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + user.getId().toString() + ";"));
                userdc = userdc + DC;

                mysql.ExecuteMySql("UPDATE u0 SET dc = " + userdc.toString() + " WHERE id = " + user.getId().toString() + ";");

                event.getHook().sendMessage("DC = " + mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + user.getId().toString() + ";").toString()+ Haupt.DCemoji).queue();


            } else {
                event.getHook().sendMessage("You cant du this!").queue();
            }
        } else if (Comand.equals("del")) {
            event.deferReply().queue();
            if (event.getUser().getId().toString().equals(Haupt.Drache4K)) {
                User user = event.getOption("user").getAsUser();
                Integer DC = event.getOption("dc").getAsInt();

                Integer userdc = Integer.valueOf(mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + user.getId().toString() + ";"));
                userdc = userdc - DC;

                mysql.ExecuteMySql("UPDATE u0 SET dc = " + userdc.toString() + " WHERE id = " + user.getId().toString() + ";");

                event.getHook().sendMessage("DC = " + mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + user.getId().toString() + ";").toString() + Haupt.DCemoji).queue();


            } else {
                event.getHook().sendMessage("You cant do this!").queue();

            }
        } else if (Comand.equals("give")) {
            User send = event.getUser();
            User reciv = event.getOption("user").getAsUser();
            Integer DC = event.getOption("dc").getAsInt();
            event.deferReply().queue();

            Integer senddc = Integer.valueOf(mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + send.getId().toString() + ";"));

            if (senddc >= DC) {

                EmbedBuilder embet = new EmbedBuilder();
                Button butten = Button.success("accept", "Accept").withEmoji(event.getJDA().getEmojiById("953410089639899196"));

                embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
                embet.setColor(0xd31212);
                embet.setDescription("Acept transfere");
                embet.setTitle("Do you want to give "+reciv+" "+DC+ Haupt.DCemoji+ "?");
                embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
                embet.setFooter("Developer: Drache4K#9323");

                event.getHook().sendMessageEmbeds(embet.build()).addActionRow(butten).queue();

                List trasferedata = new List();


                //send.getId(), reciv.getId(), senddc.toString());
                trasferedata.add(send.getId());
                trasferedata.add(reciv.getId());
                trasferedata.add(DC.toString());



                Transfer.add(trasferedata);



                //event.getHook().sendMessage("DC = " + mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + reciv.getId().toString() + ";").toString() + Haupt.DCemoji).queue();


            } else {
                event.getHook().sendMessage("You do not have enough!").queue();
            }
        }else if (Comand.equals("dc")) {
            event.deferReply().queue();
            User user = null;

            if ( event.getOption("user") == null){
                user = event.getUser();
            }else{
                user = event.getOption("user").getAsUser();
            }

            EmbedBuilder embet = new EmbedBuilder();

            embet.setAuthor(user.getName(), "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
            embet.setColor(0xd31212);
            embet.setDescription("How much money do you have?");
            embet.setTitle("DC");
            embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
            embet.addField("Dragon Coins", mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + user.getId().toString() + ";") + Haupt.DCemoji, true);
            embet.setFooter("Developer: Drache4K#9323");

            event.getHook().sendMessageEmbeds(embet.build()).setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String Butten = event.getId();
        if (event.getButton().getId().equals("accept")) {
            System.out.println("Butten");
            User send = null;
            String reciv;
            Integer DC;
            Integer senddc;

            for (List i : Transfer) {
                if (i.getItem(0).equals(event.getUser().getId())) {
                    send = event.getUser();
                    reciv = i.getItem(1);
                    DC = Integer.valueOf(i.getItem(2));

                    System.out.println(send.getAsTag()+" " + reciv + " " + DC.toString());
                    Transfer.remove(i);





            senddc = Integer.valueOf(mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + send.getId().toString() + ";"));

            senddc = senddc - DC;

            mysql.ExecuteMySql("UPDATE u0 SET dc = " + senddc.toString() + " WHERE id = " + send.getId().toString() + ";");

            Integer recivec = Integer.valueOf(mysql.QuarryItemMySql("SELECT dc FROM u0 WHERE id =" + reciv+ ";"));

            recivec = recivec + DC;

            mysql.ExecuteMySql("UPDATE u0 SET dc = " + recivec.toString() + " WHERE id = " + reciv + ";");




                    EmbedBuilder embet = new EmbedBuilder();


                    embet.setAuthor("Drache4K Jr.", "https://discord.com/api/oauth2/authorize?client_id=928336295728660611&permissions=8&scope=bot%20applications.commands");
                    embet.setColor(0xd31212);
                    embet.setTitle("Trasfere successful "+ DC.toString() + Haupt.DCemoji);
                    embet.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl());
                    embet.setFooter("Developer: Drache4K#9323");

                    event.getHook().editOriginalEmbeds(embet.build()).queue();
                    event.deferEdit().queue();

        }
            }
        }
    }
}