package app.controller.lists;

import app.domain.model.SNSUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SNSUserList implements Serializable {

    private static final long serialVersionUID = 1520040715670628402L;
    private List<SNSUser> list = new ArrayList<SNSUser>();

    public SNSUserList() {

    }

    public boolean add(SNSUser user) {
        list.add(user);
        return true;
    }

    public SNSUser get(int index) {
        return list.get(index);
    }

    public boolean remove(SNSUser user) {
        list.remove(user);
        return true;
    }

    public int size() {
        return list.size();
    }

    public boolean contains(SNSUser snsUser) {
        return list.contains(snsUser);
    }

}
