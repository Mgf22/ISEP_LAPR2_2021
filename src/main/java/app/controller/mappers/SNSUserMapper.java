package app.controller.mappers;

import app.controller.mappers.dto.SNSUserDTO;
import app.domain.model.SNSUser;

import java.util.ArrayList;

public class SNSUserMapper {
    public ArrayList<SNSUserDTO> toDTOSNSUser(ArrayList<SNSUser> listSNS){
        ArrayList<SNSUserDTO> list = new ArrayList<>();

        for(SNSUser sns : listSNS) {
            list.add(new SNSUserDTO(sns.getName(),sns.getSex(),sns.getBirthDate(),sns.getAddress(),sns.getPhoneNumber(),sns.getEmail(),sns.getSnsUserNumber(),sns.getCitizenCardNumber()));
        }

        return list;
    }
}
