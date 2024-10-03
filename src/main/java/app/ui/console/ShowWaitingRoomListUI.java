package app.ui.console;

import app.controller.ShowWaitingRoomListController;
import app.controller.lists.SNSUserDTOList;
import app.controller.mappers.dto.SNSUserDTO;
import app.ui.console.utils.Utils;

import java.time.format.DateTimeFormatter;

public class ShowWaitingRoomListUI implements Runnable{

    private ShowWaitingRoomListController controller;

    private int centerIndex;

    public ShowWaitingRoomListUI(int centerIndex) {
        this.controller = new ShowWaitingRoomListController();
        this.centerIndex = centerIndex;
    }

    public void run() {
        SNSUserDTOList waitingList = controller.getWaitingList(centerIndex);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        if(waitingList.size() > 0) {
            Utils.printToConsole("Users in the waiting room:\n");
            for(int i = 0; i < waitingList.size(); i++) {
                SNSUserDTO user = waitingList.get(i);
                Utils.printToConsole("Name: " + user.getName() + ", Sex: " + user.getSex() + ", Birth Date: " + user.getBirthDate() + ", Sns Number: " + user.getSnsUserNumber() + ", Phone Number: " + user.getPhoneNumber() + ", Arrival Time: " + timeFormatter.format(user.getArrivalTime()) + "\n");
            }
        } else {
            Utils.printToConsole("The waiting room is empty.");
        }
    }
}
