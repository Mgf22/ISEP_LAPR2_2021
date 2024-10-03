package app.controller.mappers;

import app.controller.lists.SNSUserDTOList;
import app.controller.lists.SNSUserList;
import app.controller.mappers.dto.SNSUserDTO;
import app.domain.model.SNSUser;

import java.time.LocalTime;

public class WaitingMapper {

    public static SNSUserDTOList toDTO(SNSUserList waitingList) {
        SNSUserDTOList waitingListDTO = new SNSUserDTOList();
        for(int i = 0; i < waitingList.size(); i++) {
            SNSUser user = waitingList.get(i);
            String name = user.getName();
            String sex = user.getSex();
            String phoneNumber = user.getPhoneNumber();
            String birthDate = user.getBirthDate();
            String snsNumber = user.getSnsUserNumber();
            LocalTime arrivalTime = user.getArrivalTime();
            SNSUserDTO userDTO = new SNSUserDTO(name, sex, phoneNumber, birthDate, snsNumber, arrivalTime);
            waitingListDTO.add(userDTO);
        }
        return waitingListDTO;
    }

}
