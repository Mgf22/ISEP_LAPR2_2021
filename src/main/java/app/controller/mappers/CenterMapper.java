package app.controller.mappers;


import app.controller.mappers.dto.CenterDTO;
import app.domain.model.Center;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class CenterMapper {

    public CenterDTO toDTO(Center center){
        return new CenterDTO(center.getName(), center.getAddress(), center.getPhoneNumber(), center.getEmailAddress(), center.getFaxNumber(), center.getWebsiteAddress(),
                            center.getOpeningHours(), center.getClosingHours(), center.getSlotDuration(), center.getMaxNumberOfVaccinesPerSlot());
    }

    public ArrayList<CenterDTO> toDTO(Set<Center> listCenter){

        ArrayList<CenterDTO> list = new ArrayList<>();

        for(Center center : listCenter) {
            list.add( new CenterDTO(center.getName(), center.getAddress(), center.getPhoneNumber(), center.getEmailAddress(), center.getFaxNumber(), center.getWebsiteAddress(),
                    center.getOpeningHours(), center.getClosingHours(), center.getSlotDuration(), center.getMaxNumberOfVaccinesPerSlot()) );
        }

        return list;
    }

    public ArrayList<CenterDTO> toDTO(ArrayList<Center> listCenter){

        ArrayList<CenterDTO> list = new ArrayList<>();

        for(Center center : listCenter) {
            list.add( new CenterDTO(center.getName(), center.getAddress(), center.getPhoneNumber(), center.getEmailAddress(), center.getFaxNumber(), center.getWebsiteAddress(),
                    center.getOpeningHours(), center.getClosingHours(), center.getSlotDuration(), center.getMaxNumberOfVaccinesPerSlot()) );
        }

        return list;
    }

}
