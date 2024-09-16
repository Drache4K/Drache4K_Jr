package main;

//import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import main.Comands.*;
import main.DC.*;
import main.SendMessage;
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
import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;
import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;


public class Haupt {
    public static String url = "";
    public static String password = "";
    public static String log ="";
    public static JDA bot;
    public static void main(String[] args) throws LoginException, InterruptedException, IOException {

        String status;
        status = "Ich bin klein und fein!";

        String token = "";

        BufferedReader br = new BufferedReader(new FileReader("/home/pi/DcBot/token.txt"));
        try {
            StringBuilder sb = new StringBuilder();

            token = br.readLine();
            password = br.readLine();
            url = br.readLine();


            //token = line.toString();
        } finally {
            br.close();
        }

        System.out.println(token);
        System.out.println(password);
        System.out.println(url);


        JDA Bot = JDABuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing(status))

                //Alles zum Aufsetzen___________
                .addEventListeners(new Reacktion())
                .addEventListeners(new RolePlay())
                .addEventListeners(new BotThings())

                .addEventListeners(new ReaktionRoll())
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
                .addOptions(new OptionData(OptionType.USER, "user", "User or UserID")) .queue();

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

        Bot.upsertCommand("role", "Get a Message With Rekation for roll") //getGuildById("849582114713763840").
                .addOptions(new OptionData(OptionType.ROLE, "role", "The ID of the Roll").setRequired(true))
                .addOptions(new OptionData(STRING, "emoji", "The Emojie you whant to be reaktet").setRequired(true))
                .addOptions(new OptionData(STRING, "message", "What stands in the Message to Reakt to").setRequired(false))
                .addOptions(new OptionData(STRING, "msg_id", "If you want to hang to a message").setRequired(false)).queue();

        System.out.println("Bot: "+Haupt.bot +" !!!!!!!!!!!!!!!");
        //SendMessage.SendToAllUser( "Happy New Year ");
        while (1 == 1){
            LocalDateTime time = LocalDateTime.now();
            //LocalTime time = LocalTime.now();
            //ime.toString()
            Integer s = time.getSecond();
            Integer M = time.getMinute();
            Integer h = time.getHour();
            Integer d = time.getDayOfMonth();
            Integer mo = time.getMonthValue();
            Integer Y = time.getYear();


            //System.out.println(time.toString());
            if(s==0 & M==0 & h==0 & d == 1 & mo == 1){
                SendMessage.SendToAllUser( "Happy New Year " + Y.toString());
            }

            Thread.sleep(1000);
        }
        //main.plugin.StringServer.Start();

    }

    public static Emoji DC = Emoji.fromCustom("DragonCoin", Long.parseLong("953410089639899196"), false);
    public static String DCemoji = "<:DragonCoin:953410089639899196> ";
    //public String Test = null;
    public static String Drache4K = "889171999698608199";

    public static String[] Programmer = {"889171999698608199","1232416030866018314"};
    public static boolean isMe(String ID){
        for(String i : Programmer){
            if(i.equals(ID)){
                return true;
            }
        }
        return false;
    }

    public static String DatenTable = "id Text, dc Text";
}
class BotThings extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        ByteArrayOutputStream error = new ByteArrayOutputStream();
        PrintStream er = new PrintStream(error);
        PrintStream old = System.err;

        System.setErr(er);
        System.out.println(event.getJDA().getSelfUser() + " ist on!");

        main.MySQL.mysql.ExecuteMySql("CREATE TABLE IF NOT EXISTS RP (MsgID TEXT, Msg Text, UserID TEXT);");
        main.MySQL.mysql.ExecuteMySql("CREATE TABLE IF NOT EXISTS Roles (MsgID TEXT, Emoji Text, RoleID TEXT);");

        //event.getJDA().getUserById(Haupt.Drache4K).openPrivateChannel().complete().sendMessage("Bot ist on!").queue();

        /*
        for (Guild i :event.getJDA().getGuilds()) {
            //System.out.println(i.getName() + " ->  "+ i.getId().toString());
            //System.out.println(i.getDefaultChannel().getName().toString());
            //i.getTextChannelById(i.getDefaultChannel().getId().toString()).sendMessage("Merry Christmas!").queue();
            //i.getDefaultChannel().asTextChannel().sendMessage("Merry Christmas!").queue();


        }*/

        //System.out.println(event.getJDA().getGuildById("921446226354925568"));

        //event.getJDA().getGuildById("921446226354925568").unban(event.getJDA().getUserById(Haupt.Drache4K)).queue();


        Boolean add = true;
        List mysql;

        mysql = main.MySQL.mysql.QuarryLineMySql("SHOW tables;");
        //System.out.println(mysql.getItems());
        for (Guild i : event.getJDA().getGuilds()) {
            add = true;
            for (String l : mysql.getItems()) {
                //System.out.println("Name: " + l);
                if (("u"+i.getId().toString()).equals(l.toString())) {
                    add = false;
                }
            }

            if (add) {
                main.MySQL.mysql.ExecuteMySql("CREATE TABLE u" + i.getId().toString() + " (" + Haupt.DatenTable + " );");
            }
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

        System.err.flush();
        System.setErr(old);
        System.out.println(error.toString());

        Haupt.bot = event.getJDA();
        SendMessage.SendMessageToUser(Haupt.Drache4K, event.getJDA().getSelfUser()  + " ist on!\n \n" + error.toString());

        //event.getJDA().getUserById(Haupt.Drache4K).openPrivateChannel().complete().sendMessage(event.getJDA().getSelfUser()  + " ist on!\n \n" + error.toString()).queue();

    }


}
