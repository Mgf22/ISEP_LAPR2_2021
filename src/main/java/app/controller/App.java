package app.controller;

import app.controller.stores.*;
import app.domain.model.*;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;
    private ArrayList<String> employeeRoles;
    private int currentCenterIndex;

    private static boolean isJavaFxLaunched;

    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION),props.getProperty(Constants.PARAMS_COMPANY_TIME),props.getProperty(Constants.PARAMS_COMPANY_FILEPATH), props.getProperty(Constants.PARAMS_COMPANY_MESSAGE_TIME));
        this.authFacade = this.company.getAuthFacade();
        this.employeeRoles = new ArrayList<>();
        bootstrap();
        calculateTime();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public int getCurrentCenterIndex() {
        return this.currentCenterIndex;
    }

    public void setCurrentCenterIndex(int centerIndex) {
        this.currentCenterIndex = centerIndex;
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap() {

        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPCIONIST, Constants.ROLE_RECEPCIONIST);
        this.authFacade.addUserRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_CENTERCOORDINATOR, Constants.ROLE_CENTERCOORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SNSUSER, Constants.ROLE_SNSUSER);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Nurse Maria", "nursemaria@lei.sem2.pt", "123456",Constants.ROLE_NURSE);
        this.authFacade.addUserWithRole("Receptionist Fernando", "receptionistfernando@lei.sem2.pt", "123456",Constants.ROLE_RECEPCIONIST);
        this.authFacade.addUserWithRole("Center Coordinator Manuel", "centercoordinatormanuel@lei.sem2.pt", "123456",Constants.ROLE_CENTERCOORDINATOR);
        this.authFacade.addUserWithRole("SNS User João", "snsuserjoao@lei.sem2.pt", "123456",Constants.ROLE_SNSUSER);

        CenterStore centerStore = this.company.getCenterStore();
        centerStore.deserializeCenter();
        SNSUserStore snsStore = this.company.getSNSUserStore();
        snsStore.deserializeSNS(authFacade);
        EmployeeStore empStore = this.company.getEmployeeStore();
        empStore.deserializeEmp(authFacade);
        VaccinationScheduleStore scheduleStore = this.company.getVaccinationScheduleStore();
        scheduleStore.deserializeSchedule();
        VaccineAdministrationStore vacadStore = this.company.getVaccineAdministrationStore();
        vacadStore.deserializeVacAd();

        //Employee bootstrap
        EmployeeStore employeeStore = this.company.getEmployeeStore();
        employeeRoles.add(Constants.ROLE_ADMIN);
        employeeRoles.add(Constants.ROLE_NURSE);
        employeeRoles.add(Constants.ROLE_RECEPCIONIST);
        employeeRoles.add(Constants.ROLE_CENTERCOORDINATOR);

        this.company.addEmployeeRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.company.addEmployeeRole(Constants.ROLE_NURSE,Constants.ROLE_NURSE);
        this.company.addEmployeeRole(Constants.ROLE_RECEPCIONIST,Constants.ROLE_RECEPCIONIST);
        this.company.addEmployeeRole(Constants.ROLE_CENTERCOORDINATOR,Constants.ROLE_CENTERCOORDINATOR);

        for (UserDTO user : authFacade.getUsers()) { //Registers user in Auth Facade into EmployeeStore
            for (UserRoleDTO role : user.getRoles()) {
                if (employeeRoles.contains(role.getId())) {
                    employeeStore.addEmployee(new Employee(user.getName(), user.getId(),"123456", company.getRoleById(role.getId())));
                }
            }
        }

        /*employeeStore.addEmployee( new Employee("0001", "employee1@lei.sem2.pt", Constants.ROLE_NURSE));
        employeeStore.addEmployee( new Employee("0002", "employee2@lei.sem2.pt", Constants.ROLE_NURSE ));
        employeeStore.addEmployee( new Employee("0003", "employee3@lei.sem2.pt", Constants.ROLE_CENTERCOORDINATOR ));
        employeeStore.addEmployee( new Employee("0004", "employee4@lei.sem2.pt", Constants.ROLE_CENTERCOORDINATOR));
        employeeStore.addEmployee( new Employee("0005", "employee5@lei.sem2.pt", Constants.ROLE_RECEPCIONIST));*/


        //Center bootstrap

        CommunityMassVaccinationCenter center1 = new CommunityMassVaccinationCenter("center1", "adress1", "999999999", "center1@centers.com", "123456789",
                "www.center1.com", "12h", "20h", 12, 5);
        CommunityMassVaccinationCenter center2 = new CommunityMassVaccinationCenter("center2", "adress2", "999999999", "center2@centers.com", "123456789",
                "www.center2.com", "12h", "20h", 12, 5);
        CommunityMassVaccinationCenter center3 = new CommunityMassVaccinationCenter("center3", "adress3", "999999999", "center3@centers.com", "123456789",
                "www.center3.com", "12h", "20h", 12, 5);

        centerStore.add(center1);
        centerStore.add(center2);
        centerStore.add(center3);

        VaccineAdministrationStore vaccineAdministrationStore = new VaccineAdministrationStore();
        vaccineAdministrationStore.add(new SNSUser("Maria", "female", "10-04-1990", "Example Street", "220193090", "maria@example.pt", "29490290", "192938989"), center1, new Vaccine("Covid-19", 1, "Pfizer", 1, 1, 20, 30, 30, 10, 1), 1, LocalDate.now());
        vaccineAdministrationStore.add(new SNSUser("Antonia", "female", "10-04-1990", "Example Street", "220193090", "maria@example.pt", "29490291", "192938989"), center1, new Vaccine("Covid-19", 1, "Pfizer", 1, 1, 20, 30, 30, 10, 1), 1, LocalDate.now());
        vaccineAdministrationStore.add(new SNSUser("Soraia", "female", "10-04-1990", "Example Street", "220193090", "maria@example.pt", "29490292", "192938989"), center2, new Vaccine("Covid-19", 1, "Pfizer", 1, 1, 20, 30, 30, 10, 1), 1, LocalDate.now().minusDays(2));


    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }

    public void calculateTime() {
        String time = company.getTime();
        LocalTime time2 = LocalTime.now();
        LocalTime time3 = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
        long initialDelay;
        // Se a task ainda vai ser executada hoje
        if (time3.isAfter(time2)){
            initialDelay = SECONDS.between(time3,time2);
        }
        // vai ser executada amanhã
        else{
            LocalDateTime time4 = LocalDateTime.now().plusDays(1).withHour(Integer.parseInt(time.substring(0,2))).withMinute(Integer.parseInt(time.substring(3,5))).withSecond(Integer.parseInt(time.substring(6,8)));
            initialDelay = SECONDS.between(LocalDateTime.now(),time4);
        }
        long interval = 24*60*60*1000;
        company.startReportService(initialDelay,interval);
    }

    public static boolean getIsJavaFxLaunched() {
        return isJavaFxLaunched;
    }

    public static void setIsJavaFxLaunched(boolean status) {
        isJavaFxLaunched = status;
    }


}
