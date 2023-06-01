package main;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import net.dv8tion.jda.internal.entities.UserImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Reacktion extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent ereignes) {
        if (ereignes.getAuthor().isBot()) {
            return;
        }


        List<String> Hello = Arrays.asList("Hallo", "Hi", "hallo", "hi", "Moin", "moin", "Guten Morgen", "Guten Abend", "Nabend", "Morgen", "Servus");
        Random random = new Random();

        //ereignes.getChannel().sendTyping().queue();

        String Userping = ereignes.getAuthor().getAsMention();
        String Username = ereignes.getAuthor().getAsTag();

        Emoji Hi = Emoji.fromFormatted("👋");


        if (ereignes.isFromGuild()) {
            System.out.println(ereignes.getGuild() + ": " + ereignes.getAuthor() + "->" + ereignes.getMessage().getContentRaw()+" = "+ereignes.getMessage().getContentStripped());

            /*
            Role testrolle = ereignes.getGuild().getRoleById("1018962504753360990");
            if (ereignes.getMessage().getContentStripped().equals("Test")){
                ereignes.getGuild().addRoleToMember(ereignes.getMember(), testrolle).queue();
            }
            */

        } else {
            System.out.println("Privat" + ": " + ereignes.getAuthor() + "->" + ereignes.getMessage());
            //ereignes.getChannel().sendTyping().queue();
        }

        /*if (ereignes.getMessage().getContentStripped().equals("Hallo")){
            ereignes.getChannel().sendMessage("Hallo " + Username).queue();
            ereignes.getMessage().addReaction(Hi).queue();
        }*/

        Boolean hi = false;
        for (String h : Hello) {
            if (ereignes.getMessage().getContentStripped().toLowerCase().equals(h.toLowerCase())) {
                hi = true;
            }
        }
        if(hi) {
            ereignes.getChannel().sendTyping().queue();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String Begruesung = "";
            Boolean run = true;
            while (run){
                Boolean timerun = false;
                java.time.LocalTime time = java.time.LocalTime.now();
                Begruesung = Hello.get(random.nextInt(Hello.size()));
                System.out.println(Begruesung);
                if (time.getHour()<18){
                    System.out.println("<18");
                    if((Begruesung != "Guten Abend") & (Begruesung != "Nabend")){
                        //System.out.println("<18 Break");
                        run = false;
                    }else{timerun = true;}
                }else {
                    run = false;
                }
                if (time.getHour()>=10) {
                    System.out.println(">10");
                    if((Begruesung != "Guten Morgen") & (Begruesung != "Morgen")){
                        //System.out.println(">10 Break");
                        run = false;
                    }else{run = true;}

                }else {
                    if(!timerun) {
                        run = false;
                    }else{run = true;}
                }

            }
            //String Begruesung = Hello.get(random.nextInt(Hello.size()));
            ereignes.getChannel().sendMessage(Begruesung).queue();
        }

        switch (ereignes.getMessage().getContentStripped()) {
            case "Hallo":
                //ereignes.getChannel().sendTyping().queue();
                //ereignes.getChannel().sendMessage("Hallo " + Username).queue();
                //ereignes.getMessage().addReaction(Hi).queue();
                break;

            case "Hi":
                //ereignes.getChannel().sendTyping().queue();
                //ereignes.getChannel().sendMessage("Hi " + Username).queue();
                ereignes.getMessage().addReaction(Hi).queue();
                break;

            case "Trigger":
                ereignes.getChannel().sendMessage("<a:Trigger:854080186614415391>").queue();
                break;



        }
        if (ereignes.getMessage().getContentStripped().startsWith("sag ")) {
            String antwort = ereignes.getMessage().getContentRaw();
            ereignes.getChannel().sendTyping();
            StringBuilder Antwort = new StringBuilder(ereignes.getMessage().getContentRaw());
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            ereignes.getChannel().sendMessage(Antwort.toString()).queue();

        } else if (ereignes.getMessage().getContentStripped().startsWith("Sag ")) {
            ereignes.getChannel().sendTyping();
            //antwort = antwort.replace("Sag ","");
            StringBuilder Antwort = new StringBuilder(ereignes.getMessage().getContentRaw());
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            Antwort.deleteCharAt(0);
            ereignes.getChannel().sendMessage(Antwort.toString()).queue();
        }
        //if(ereignes.getMessage().getMentions().getUsers()) {
        Integer i = 0;
        if (ereignes.getMessage().getMentions().getUsers().size() != 0) {
            while (i < ereignes.getMessage().getMentions().getUsers().size()) {
                if (ereignes.getMessage().getMentions().getUsers().get(i).equals(ereignes.getJDA().getSelfUser())) {
                    ereignes.getChannel().sendMessage("Hi? \n /help").queue();
                    return;
                }
                i = i + 1;

            }
        }
    }
}

