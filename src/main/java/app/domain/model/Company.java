package app.domain.model;

import app.controller.mappers.dto.EmployeeDTO;
import app.controller.stores.*;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private List<UserRole> employeeRoles;

    private String designation;
    private String time;
    private String filePath;
    private String messageTime;
    private AuthFacade authFacade;
    private EmployeeStore employeeStore;
    private VaccineTypeStore vaccineTypeStore;
    private VaccineStore vaccineStore;
    private VaccinationScheduleStore vaccinationScheduleStore;
    private CenterStore centerStore;
    private SNSUserStore userStore;
    private VaccineAdministrationStore vaStore;
    private List<String> admin;
    private Timer timer;

    public Company(String designation, String time, String filePath, String messageTime)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        if (StringUtils.isBlank(time))
            throw new IllegalArgumentException("Time cannot be blank.");
        if (StringUtils.isBlank(filePath))
            throw new IllegalArgumentException("File Path cannot be blank.");
        if (StringUtils.isBlank(messageTime))
            throw new IllegalArgumentException("Time cannot be blank.");
        this.designation = designation;
        this.time = time;
        this.filePath = filePath;
        this.messageTime = messageTime;
        this.authFacade = new AuthFacade();

        this.employeeStore = new EmployeeStore();

        this.vaccineTypeStore = new VaccineTypeStore();
        this.vaccineStore = new VaccineStore();
        this.vaccinationScheduleStore = new VaccinationScheduleStore();

        this.centerStore = new CenterStore();
        this.userStore = new SNSUserStore();
        this.vaStore = new VaccineAdministrationStore();

        this.employeeRoles = new ArrayList<>();
        admin = new ArrayList<>();
        this.timer = new Timer();
    }


    public String getDesignation() {return designation;}

    public String getTime() {return time;}

    public String getFilePath() {return filePath;}

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public EmployeeStore getEmployeeStore() {
        return employeeStore;
    }

    public VaccineTypeStore getVaccineTypeStore() {
        return vaccineTypeStore;
    }

    public VaccineStore getVaccineStore() {
        return vaccineStore;
    }

    public String getMessageTime() {return messageTime;}

    public VaccineAdministrationStore getVaccineAdministrationStore() {
        return vaStore;
    }

    public SNSUserStore getSNSUserStore() {return userStore;}

    public VaccinationScheduleStore getVaccinationScheduleStore() {
        return vaccinationScheduleStore;
    }

    public Vaccine createVaccine(String typeName, int typeID, String name, int lot, int id, double dosage, int timeInterval, int ageGroupMax, int ageGroupMin, int numberOfDoses) {
        return Vaccine.create(typeName, typeID, name, lot, id, dosage, timeInterval, ageGroupMax, ageGroupMin, numberOfDoses);
    }

    public CenterStore getCenterStore() {
        return centerStore;
    }

    public List<UserRole> getEmployeeRoles() {
        return new ArrayList<>(this.employeeRoles);
    }

    public void addEmployeeRole(String id, String description) {
        this.employeeRoles.add(new UserRole(id, description));
    }

    public Employee createEmployee(EmployeeDTO empDTO) {
            return new Employee(empDTO.getRole(), empDTO.getName(), empDTO.getAddress(), empDTO.getPhoneNumber(), empDTO.getId(), empDTO.getCitizenCardNumber(),empDTO.getPassword());
    }

    public void saveEmployee(Employee employee) {
        String[] userInfo = {employee.getName(), employee.getId(), employee.getPassword(), employee.getRole()};
        for (UserRole ur : employeeRoles){
            if (ur.getId().equals(employee.getRole())){
                authFacade.addUserWithRole(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                employeeStore.addEmployee(employee);
            }
        }
    }

    public String getRoleById(String id) {
        for (UserRole role : employeeRoles) {
            if (role.getId().equals(id)) {
                return role.getId();
            }
        }
        return null;
    }

    public void startReportService(Long delay, Long interval){
        TimerTask timerReport = new TimerTask() {
            @Override
            public void run() {
                admin = vaStore.countAdministrations(centerStore);
                String data = "date;centername;countvaccinatedusers\n";
                    for (String ad: admin){

                        data = data + ad + "\n";
                    }
                    writeCSV(data);
            }
        };
        timer.scheduleAtFixedRate(timerReport,Math.abs(delay)*1000+1000,interval);
    }

    public void writeCSV(String data){
        try{
            String filePath = getFilePath();
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter csv = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(csv);
            pw.append(data);
            pw.flush();
            pw.close();
        }
        catch (Exception e){
            System.out.println("Check if the file is closed");
        }
    }

    public void shutdownTimer(){
        timer.cancel();
    }
}
