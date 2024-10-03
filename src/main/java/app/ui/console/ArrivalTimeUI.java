package app.ui.console;

import app.controller.ArrivalTimeController;
import app.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class ArrivalTimeUI implements Runnable {

    private ArrivalTimeController controller;
    private int centerIndex;

    public ArrivalTimeUI(int centerIndex) {
        this.controller = new ArrivalTimeController();
        this.centerIndex = centerIndex;
    }

    public void run() {
        boolean verified;
        String snsNum;
        controller.getCenterByIndex(centerIndex);
        do {
            snsNum = Utils.readLineFromConsole("Insert the user's SNS number:\n0. Cancel\nType your option:");
            verified = controller.getSNSUserByNum(snsNum);
            if (!verified && !snsNum.equals("0")) {
                System.out.println("User not found, please re-check SNS number.");
            }
        } while (!verified && !snsNum.equals("0"));

        if (!snsNum.equals("0")) {
            LocalDate today = LocalDate.now();
            if (!controller.validateDate(today)) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                if (controller.checkSchedule(today)) {
                    LocalTime arrivalTime = LocalTime.now();
                    boolean confirm = Utils.confirm("Set SNS User nº" + snsNum + " arrival's time to " + timeFormatter.format(arrivalTime) + " (" + dateFormatter.format(today) + ")? (s/n)");
                    if (confirm) {
                        controller.saveArrival(today, arrivalTime);
                        if (!controller.containsUser()) {
                            controller.addToWaitingList();
                            Utils.printToConsole("SNS User nº" + snsNum + " arrival's time was set to " + timeFormatter.format(arrivalTime) + " (" + dateFormatter.format(today) + ") successfully.");
                        } else {
                            Utils.printToConsole("User is already on the waiting list.");
                        }
                    }
                } else {
                    Utils.printToConsole("User doesn't have a vaccine scheduled for today.");
                }
            } else {
                Utils.printToConsole("User already has an arrival time for today.");
            }
        }
    }
}
