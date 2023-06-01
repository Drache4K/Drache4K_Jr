package main.Comands;

import main.MySQL.mysql;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ComMySql extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();
        String replay = "Done";

        if(Comand.equals("mysql_command")){
            event.deferReply().queue();
            if(event.getMember().getUser().getId().equals("795949899974836245")) {

                String mysql = event.getOption("command").getAsString();
                if (null != event.getOption("execute")) {
                    main.MySQL.mysql.ExecuteMySql(mysql);
                } else {
                    replay = main.MySQL.mysql.QuarryListMySql(mysql);
                }

                event.getHook().sendMessage(replay).queue();

            }else {event.getHook().sendMessage("Only Drache4K can this!").queue();}
        }

    }

}
