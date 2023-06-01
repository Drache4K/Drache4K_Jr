package main.Comands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComPet extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();
        Integer i = 1;

        List<String> Rar = Arrays.asList("uwu", "rrrr", "OwO", "rar","Uii");
        Random random = new Random();
        if(Comand.equals("pet")){
            event.deferReply().queue();
            if(event.getOption("time") != null){
                i = event.getOption("time").getAsInt();
            }
            event.getHook().sendMessage("https://tenor.com/view/dragon-pat-cute-pet-gif-17472506").queue();

            for (Integer f=0; f<i-1 ;f++){
                if(f == 9-2){ event.getChannel().sendMessage("https://tenor.com/view/angry-dragon-lord-of-the-rings-the-hobbit-smaug-gif-19912746").queue();

                }else {event.getChannel().sendMessage(Rar.get(random.nextInt(Rar.size()))).queue();}

            }
        }
    }
}
