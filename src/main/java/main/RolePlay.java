package main;

import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class RolePlay extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent ereignes) {
        if(ereignes.getAuthor().isBot())return;
        if(ereignes.getChannel().getId().equals("1122903099116368045")){
            //ereignes.getChannel().sendMessage("Get it").queue();
           String Msg = ereignes.getMessage().getContentRaw();
           String MsgID = ereignes.getMessageId();
           String UserID = ereignes.getAuthor().getId();
           main.MySQL.mysql.ExecuteMySql("INSERT INTO RP VALUES (\""+MsgID+"\", \""+Msg+"\", \""+UserID+"\");");

        }
    }

    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        if(event.getChannel().getId().equals("1122903099116368045")){
        String MsgID = event.getMessageId();
        main.MySQL.mysql.ExecuteMySql("DELETE FROM RP WHERE MsgID like \""+MsgID+"\";");
    }}

    public void onMessageUpdate(@NotNull MessageUpdateEvent event) {
        if(event.getAuthor().isBot())return;
        if(event.getChannel().getId().equals("1122903099116368045")){
        String MsgID = event.getMessageId();
        String Msg = event.getMessage().getContentRaw();
        main.MySQL.mysql.ExecuteMySql("UPDATE RP SET Msg = \""+Msg+"\" WHERE MsgID LIKE \""+MsgID+"\";");
    }}
}
