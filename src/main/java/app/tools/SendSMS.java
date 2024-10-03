package app.tools;

import app.domain.model.SNSUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TimerTask;

public class SendSMS extends TimerTask {

    private final SNSUser snsUser;

    public SendSMS(SNSUser snsUser) {
        this.snsUser = snsUser;
    }

    @Override
    public void run() {
        FileWriter writer;
        try {
            writer = new FileWriter("sms.txt");
            PrintWriter sms = new PrintWriter(writer);
            sms.print("The SNS User nº" + snsUser.getSnsUserNumber() + " is free to leave the center. No adverse reactions were recorded.");
            sms.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
