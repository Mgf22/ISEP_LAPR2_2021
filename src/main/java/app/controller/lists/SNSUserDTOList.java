package app.controller.lists;

import app.controller.mappers.dto.SNSUserDTO;

import java.util.ArrayList;
import java.util.List;

public class SNSUserDTOList {

    private List<SNSUserDTO> list = new ArrayList<SNSUserDTO>();

    public SNSUserDTOList() {

    }

    public boolean add(SNSUserDTO user) {
        list.add(user);
        return true;
    }

    public SNSUserDTO get(int index) {
        return list.get(index);
    }

    public boolean remove(SNSUserDTO user) {
        list.remove(user);
        return true;
    }

    public int size() {
        return list.size();
    }

}
