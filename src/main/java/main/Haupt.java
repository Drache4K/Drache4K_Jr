package main;

//import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import main.Comands.*;
import main.DC.Anmeldung;
import main.DC.DCget;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;
import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;


public class Haupt {
    public static void main(String[] args) throws LoginException, InterruptedException, IOException {

        String status;
        status = "Ich bin klein und fein!";

        String token = "";

        BufferedReader br = new BufferedReader(new FileReader("/home/pi/DcBot/token.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            token = line.toString();
        } finally {
            br.close();
        }

        System.out.println(token);


        JDA Bot = JDABuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing(status))

                //Alles zum Aufsetzen___________
                .addEventListeners(new Reacktion())
                .addEventListeners(new BotThings())

                .addEventListeners(new ComPet())
                .addEventListeners(new ComHelp())
                .addEventListeners(new ComMySql())
                .addEventListeners(new ComUser())
                .addEventListeners(new ComSmall())
                .addEventListeners(new Anmeldung())
                .addEventListeners(new DCget())
                .addEventListeners(new ForMyServer())

                .enableIntents(GatewayIntent.GUILD_MEMBERS)

                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES)

                .build().awaitReady();

        CommandListUpdateAction commands = Bot.updateCommands();


        Bot.upsertCommand("pet", "Stroke Drache4K Jr.") //getGuildById("849582114713763840").
                .addOptions(new OptionData(OptionType.INTEGER, "time", "How long du you want to stroke Drache4K Jr.").setRequiredRange(1, 9)).queue();

        Bot.upsertCommand("help", "Gives information.").queue();

        Bot.upsertCommand("mysql_command", "Gives the mysql an command.")
                .addOptions(new OptionData(STRING, "command", "A sql Command").setRequired(true))
                .addOptions(new OptionData(OptionType.BOOLEAN, "execute", "true :Execute").setRequired(false))
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();

        Bot.upsertCommand("userinfo", "Some information above User")
                .addOptions(new OptionData(OptionType.USER, "user", "Give User"))
                .setGuildOnly(true).queue();

        Bot.upsertCommand("report", "Send a Message on the Developer")
                .addOptions(new OptionData(STRING, "message", "Your Message to Drache4K").setRequired(true)).queue();

        Bot.upsertCommand("avatar", "Get the Avatar fom a User")
                .addOptions(new OptionData(OptionType.USER, "user", "the User").setRequired(true)).queue();

        Bot.upsertCommand("server", "Get the server fom a Id")
                .addOptions(new OptionData(STRING, "id", "Server Id").setRequired(true)).queue();

        Bot.upsertCommand("get", "Get DC for a player")
                .addOptions(new OptionData(OptionType.USER, "user", "The user to give").setRequired(true))
                .addOptions(new OptionData(INTEGER, "dc", "How much DC?").setRequired(true)).queue();
        Bot.upsertCommand("del", "Remove DC from a player")
                .addOptions(new OptionData(OptionType.USER, "user", "The user to remove").setRequired(true))
                .addOptions(new OptionData(INTEGER, "dc", "How much DC?").setRequired(true))
                .queue();

        Bot.upsertCommand("give", "Give a User DC from you")
                .addOptions(new OptionData(OptionType.USER, "user", "The user to give").setRequired(true))
                .addOptions(new OptionData(INTEGER, "dc", "How much DC?").setRequired(true)).queue();

        Bot.upsertCommand("dc", "Show the Stats")
                .addOptions(new OptionData(OptionType.USER, "user", "The user to give").setRequired(false)).queue();


        //main.plugin.StringServer.Start();
        //while (1 == 1){
        //LocalTime time = LocalTime.now();
        //System.out.println(time.getHour());
        //Thread.sleep(4000);
        //}
    }

    public static Emoji DC = Emoji.fromCustom("DragonCoin", Long.parseLong("953410089639899196"), false);
    public static String DCemoji = "<:DragonCoin:953410089639899196> ";
    //public String Test = null;
    public static String Drache4K = "795949899974836245";

    public static String DatenTable = "id Text, dc Text";
}
class BotThings extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println(event.getJDA().getSelfUser() + " ist on!");
        event.getJDA().getUserById(Haupt.Drache4K).openPrivateChannel().complete().sendMessage("Bot ist on!").queue();

        /*for (Guild i :event.getJDA().getGuilds()) {
            //System.out.println(i.getName() + " ->  "+ i.getId().toString());
            //System.out.println(i.getDefaultChannel().getName().toString());
            //i.getTextChannelById(i.getDefaultChannel().getId().toString()).sendMessage("Merry Christmas!").queue();
            //i.getDefaultChannel().asTextChannel().sendMessage("Merry Christmas!").queue();


        }*/

        //System.out.println(event.getJDA().getGuildById("921446226354925568"));

        //event.getJDA().getGuildById("921446226354925568").unban(event.getJDA().getUserById(Haupt.Drache4K)).queue();


        Boolean add = true;
        List mysql;

        for (Guild i : event.getJDA().getGuilds()) {
            main.MySQL.mysql.ExecuteMySql("CREATE TABLE u" + i.getId().toString() + " (" + Haupt.DatenTable + " );");
        }


        mysql = main.MySQL.mysql.QuarryLineMySql("SELECT id FROM u0;");
        for (User i : event.getJDA().getUsers()) {
            add = true;
            for (String l : mysql.getItems()) {
                if (i.getId().toString().equals(l.toString())) {
                    add = false;
                }
            }

            if (add) {
                main.MySQL.mysql.ExecuteMySql("INSERT INTO u0 (id) VALUES (" + i.getId().toString() + ");");
            }
        }


        for (Guild i : event.getJDA().getGuilds()) {
            mysql = main.MySQL.mysql.QuarryLineMySql("SELECT id FROM u" + i.getId().toString() + ";");
            for (Member d : i.getMembers()) {
                add = true;
                for (String l : mysql.getItems()) {
                    if (d.getId().toString().equals(l.toString())) {
                        add = false;
                    }
                }

                if (add) {
                    main.MySQL.mysql.ExecuteMySql("INSERT INTO u" + i.getId().toString() + " (id) VALUES (" + d.getId().toString() + ");");
                }
            }
        }
    }

}
