package app.controller;

import app.controller.lists.SNSUserDTOList;
import app.controller.lists.SNSUserList;
import app.controller.mappers.WaitingMapper;
import app.controller.stores.CenterStore;
import app.domain.model.Center;
import app.domain.model.Company;

public class ShowWaitingRoomListController {

    private App app;

    private Company company;

    public ShowWaitingRoomListController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
    }

    public SNSUserDTOList getWaitingList(int centerIndex) {
        CenterStore centerStore = company.getCenterStore();
        Center center = centerStore.getCenterByIndex(centerIndex);
        SNSUserList waitingList = center.getWaitingList();

        return WaitingMapper.toDTO(waitingList);
    }

}
