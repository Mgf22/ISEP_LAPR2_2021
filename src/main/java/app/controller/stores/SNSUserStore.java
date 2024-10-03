package app.controller.stores;

import app.controller.App;
import app.controller.mappers.dto.SNSUserDTO;
import app.domain.model.Company;
import app.tools.PasswordGenerator;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import app.tools.Serialize;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */
public class SNSUserStore {

    private static final String SNSUSER_SAVE_FILE = "snsusers";
    private static ArrayList<SNSUser> store = new ArrayList<>();

    public SNSUserStore(){
    }

    public void createUser(String name,String sex,String birthDate,String address,String phoneNumber,String email,String SNSUserNumber,String citizenCardNumber,AuthFacade auth) {
        SNSUser user;
        if (validateUser(email,SNSUserNumber)){
            String password = PasswordGenerator.generatePassword(12);
            user = new SNSUser(name,sex,birthDate,address,phoneNumber,email,SNSUserNumber,citizenCardNumber,password);
            store.add(user);
            auth.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), Constants.ROLE_SNSUSER);
            System.out.println("Name:"+name+"\nSex:"+sex+"\nBirth Date:"+birthDate+"\nAddress:"+address+"\nPhone Number:"+phoneNumber+"\nEmail:"+email+"\nSNS User Number:"+SNSUserNumber+"\nCitizen Card Number:"+citizenCardNumber+"\nPassword:"+password+"\n");
            Serialize.serialize(store, SNSUSER_SAVE_FILE);
        }
        else {
            System.out.println("User already registered in the system");
        }

    }

    // Only use for deserialization in Sprint D
    public void deserializeSNS (AuthFacade auth){
        store = Serialize.deserialize(SNSUSER_SAVE_FILE);
        for (SNSUser sns: store){
            auth.addUserWithRole(sns.getName(),sns.getEmail(),sns.getPassword(),Constants.ROLE_SNSUSER);
            //System.out.println(sns.getEmail()+" "+sns.getPassword());
        }
    }

    public SNSUser getSNSUserByNum(String snsNum) {
        for (SNSUser user : store) {
            if (user.getSnsUserNumber().equals(snsNum)) {
                return user;
            }
        }
        return null;
    }

    public boolean validateUser(String email, String SNSUserNumber){
        for (SNSUser sns: store){
            if (sns.getEmail().equals(email) || sns.getSnsUserNumber().equals(SNSUserNumber)){
                return false;
            }
        }
        return true;
    }

    public ArrayList<SNSUser> getSNSUserStore(){
        return store;
    }
}
