package app.controller;

import app.controller.mappers.SNSUserMapper;
import app.controller.mappers.dto.SNSUserDTO;
import app.controller.stores.SNSUserStore;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.tools.Validations;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */
public class LoadCSVController {
    private App app;
    private Company company;
    private SNSUserStore store;
    private AuthFacade auth;
    //private SNSUserMapper mapper;

    public LoadCSVController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.store = company.getSNSUserStore();
        this.auth = company.getAuthFacade();
        //this.mapper = new SNSUserMapper();
    }

    public boolean loadCSV(String path) throws IOException {
        Path pathToFile = Paths.get(path);
        String line;
        String[] atributes;
        BufferedReader br = Files.newBufferedReader(pathToFile);
        if (identifyFile(path)) {
            if (validateFile(path)) {
                line = br.readLine();
                while (line != null) {
                    atributes = line.split(",");
                    store.createUser(atributes[0], atributes[1], atributes[2], atributes[3], atributes[4], atributes[5], atributes[6], atributes[7], auth);
                    line = br.readLine();
                }
            }
            else {
                return false;
            }
        }
        else {
            if (validateFile(path)) {
                line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    atributes = line.split(";");
                    store.createUser(atributes[0], atributes[1], atributes[2], atributes[3], atributes[4], atributes[5], atributes[6], atributes[7], auth);
                    line = br.readLine();
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean identifyFile(String path) throws IOException {
        Path pathToFile = Paths.get(path);
        String line1 = "";
        BufferedReader br = Files.newBufferedReader(pathToFile);
        line1 = br.readLine();
        if (line1.contains(",")) {
            return true;
        } else {
            return false;
        }
    }

    /*public List<SNSUserDTO> getSNSUser() {
        ArrayList<SNSUser> users = store.getSNSUserStore();
        return mapper.toDTOSNSUser(users);

    }*/

    public boolean validateFile(String path) throws IOException {
        Path pathToFile = Paths.get(path);
        String line;
        String[] atributes;
        BufferedReader br = Files.newBufferedReader(pathToFile);
        if (identifyFile(path)) {
            line = br.readLine();
            while (line != null) {
                atributes = line.split(",");
                if (Validations.checkName(atributes[0]) == false || Validations.checkSex(atributes[1]) == false || Validations.checkBirthDate(atributes[2]) == false || Validations.checkAddress(atributes[3]) == false || Validations.checkPhoneNumber(atributes[4]) == false || Validations.checkEmail(atributes[5]) == false || Validations.checkSNSUserNumber(atributes[6]) == false || Validations.checkCitizenCardNumber(atributes[7]) == false) {
                    return false;
                }
                line = br.readLine();
            }
        } else {

            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                atributes = line.split(";");
                if (Validations.checkName(atributes[0]) == false || Validations.checkSex(atributes[1]) == false || Validations.checkBirthDate(atributes[2]) == false || Validations.checkAddress(atributes[3]) == false || Validations.checkPhoneNumber(atributes[4]) == false || Validations.checkEmail(atributes[5]) == false || Validations.checkSNSUserNumber(atributes[6]) == false || Validations.checkCitizenCardNumber(atributes[7]) == false) {
                    return false;
                }
                line = br.readLine();
            }
        }
        return true;
    }
}
