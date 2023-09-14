import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class FinishExercise {

    static String fio = "";
    static String birthDay;
    static long phoneNumber;
    static char male;

    public static void main(String[] args) {
        try {
            inputData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputData() throws IOException {
        System.out.println ("Введите Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String [] arr = userInput.split(" ");
        sc.close();
        if (arr.length != 6){
            try {
                throw new Exception(" Данных недостаточно ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }   
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() == 1){
                checkMale(arr[i].charAt(0));
                if (arr[i].equals("f") | arr[i].equals("m")){
                    male = arr[i].charAt(0);
                }
            }
            if (arr[i].matches("^[0-9]*$")){
                phoneNumber = Long.valueOf(arr[i]);
            }
            if (arr[i].matches("^[0-9.]*$") && arr[i].length() < 11){
                checkBirhday(arr[i]);
                birthDay = arr[i];
            }
            if (arr[i].matches("^[a-zA-Zа-яА-Я]*$") & arr[i].length() > 1){
                fio += arr[i] + " ";
            }
            if (arr[i].matches (".*[a-zA-Zа-яА-Я].*") && arr[i].matches(".*[0-9].*")){
                try {
                    throw new Exception("Неверный ввод ");
                } catch (Exception e) {
                    System.out.println(e.getMessage() + arr [i]);
                }
            }
        }
        
        String[] familyName = fio.split(" ");
        File file = new File(String.format("c://%s.txt", familyName[0]));
        boolean writeOverwrite = true;
        try {
            if (file.createNewFile()){
                System.out.println("File is created!");
                writeOverwrite = false;
                } else{
                System.out.println("File already exists.");
                }
                FileWriter writer = new FileWriter (file, writeOverwrite);
                writer.write("<" + fio + ">" + "<" + birthDay + ">" + "<" + phoneNumber + ">" + "<" + male + ">\n");
                writer.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
    
    public static void checkMale(char male) {
        if (male != 102 && male != 109){
            System.err.println(male);
            try {
                throw new Exception("неверный параметр пола, введите f или m. введено " + male);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void checkBirhday(String birthDay) {
        if (!birthDay.matches("\\d{2}.\\d{2}.\\d{4}")){
            try {
                throw new Exception("Неверный формат даты рождения");
            } catch (Exception e) {
                System.out.println(e.getMessage() + birthDay);
            }           
        }
    }
}
