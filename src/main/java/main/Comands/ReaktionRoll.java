package main.Comands;

import main.Haupt;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;

public class ReaktionRoll extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String Comand = event.getName();

        if (Comand.equals("role")) {
            //event.deferReply().queue();
            if(Haupt.isMe(event.getMember().getUser().getId().toString())) {

                Role rolle = event.getOption("role").getAsRole();
                String emoji = String.valueOf(event.getOption("emoji").getAsString());
                Emoji Emoji = net.dv8tion.jda.api.entities.emoji.Emoji.fromFormatted(emoji);
                String Msg = "Reakt with " + Emoji + " to get " + rolle.getAsMention();
                final String[] MsgID = {null};


                if (event.getOption("msg_id") != null) {
                    MsgID[0] = String.valueOf(event.getOption("msg_id").getAsString());

                } else if (event.getOption("message") != null) {
                    Msg = String.valueOf(event.getOption("message").getAsString());
                }


                //event.getHook().sendMessage("Yee").queue();
                if (MsgID[0] == null) {
                    System.out.println("Crerat Meesage for Role");
                    event.deferReply().queue();
                    event.getHook().deleteOriginal().queue();
                    event.getMessageChannel().sendMessage(Msg).queue((message) -> {
                        MsgID[0] = message.getId();
                        main.MySQL.mysql.ExecuteMySql("INSERT INTO Roles VALUES (\"" + MsgID[0] + "\", \"" + Emoji + "\", \"" + rolle.getId() + "\");");
                        message.addReaction(Emoji).queue();
                    });

                } else {
                    System.out.println("Atach Role");
                    main.MySQL.mysql.ExecuteMySql("INSERT INTO Roles VALUES (\"" + MsgID[0] + "\", \"" + Emoji + "\", \"" + rolle.getId().toString() + "\");");
                    event.getMessageChannel().addReactionById(MsgID[0].toString(), Emoji).queue();
                    event.deferReply().queue();
                    event.getHook().deleteOriginal().queue();
                }

            }else{
                System.out.println("Good Try:"+event.getUser().getId() +"your not Allowed to do that");
            }

        }
    }
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        /*
        String MsgID = event.getMessageId();
        main.MySQL.mysql.ExecuteMySql("DELETE FROM Roles WHERE MsgID like \"" + MsgID + "\";");
        System.out.println("Deletet all Roles reaktion from "+ MsgID);
         */
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(event.getUser().isBot()) return;
        User user = event.getUser();
        String MsgID = event.getMessageId();
        Emoji Emoji = event.getEmoji();
        String RoleID = main.MySQL.mysql.QuarryItemMySql("SELECT RoleID FROM Roles WHERE MsgID like \""+MsgID+"\" AND Emoji like \""+Emoji+"\";");
        if(RoleID.equals("0")) return;
        Role role =  event.getGuild().getRoleById(RoleID);
        System.out.println(Emoji + ">>"+ RoleID);
        event.getGuild().addRoleToMember(user, role).queue();
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        if(event.getUser().isBot()) return;
        User user = event.getUser();
        String MsgID = event.getMessageId();
        Emoji Emoji = event.getEmoji();
        String RoleID = main.MySQL.mysql.QuarryItemMySql("SELECT RoleID FROM Roles WHERE MsgID like \""+MsgID+"\" AND Emoji like \""+Emoji+"\";");
        if(RoleID.equals("0")) return;
        Role role =  event.getGuild().getRoleById(RoleID);
        event.getGuild().removeRoleFromMember(user, role).queue();
    }
}
