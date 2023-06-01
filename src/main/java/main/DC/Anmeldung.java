package main.DC;

import main.Haupt;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import main.MySQL.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Anmeldung extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
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
