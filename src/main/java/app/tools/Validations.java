package app.tools;

public class Validations {

    public static boolean validateHourFormat(String hour) {
        return hour.matches("^(?:[01][0-9]|2[0-3])[-:h][0-5][0-9]$");
    }

    public static boolean checkName(String name){
        if (name.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkSex(String sex){
        if (sex.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkBirthDate(String birthDate){
        if (birthDate.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkAddress(String address){
        if (address.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        if (phoneNumber.length() != 9){
            return false;
        }
        return true;
    }

    public static boolean checkEmail(String email){
        if (email.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkSNSUserNumber(String snsUserNumber){
        if (snsUserNumber.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean checkCitizenCardNumber(String citizenCardNumber){
        if (citizenCardNumber.length() != 8){
            return false;
        }
        return true;
    }

}
