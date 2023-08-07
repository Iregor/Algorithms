import java.util.Scanner;
import java.io.*;

public class PhoneSolver {
    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String n1 = scan.nextLine();
        String n2 = scan.nextLine();
        String n3 = scan.nextLine();
        String n4 = scan.nextLine();
        Phone[] numbers = new Phone[4];
        numbers[0] = getPhone(n1);
        numbers[1] = getPhone(n2);
        numbers[2] = getPhone(n3);
        numbers[3] = getPhone(n4);
        scan.close();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File("output.txt")))) {
            for (int i = 1; i <= 3; i++) {
                if (numbers[i].city == numbers[0].city && numbers[i].number == numbers[0].number) {
                    br.write("YES\n");
                } else {
                    br.write("NO\n");
                }
            }
        }
    }

    private static Phone getPhone(String number) {
        char[] arr = number.toCharArray();
        char[] charNumber = new char[7];
        char[] charCity = new char[3];
        int charRead = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (charRead == 10) {
                break;
            }
            if (arr[i] != '-' && arr[i] != '(' && arr[i] != ')') {
                if (charRead < 7) {
                    charNumber[charNumber.length - 1 - charRead] = arr[i];
                    charRead++;
                } else {
                    charCity[charCity.length - 1 +(7 - charRead)] = arr[i];
                    charRead++;
                }
            }
        }
        if (charRead == 7) {
            charCity[0] = '4';
            charCity[1] = '9';
            charCity[2] = '5';
        }
        return new Phone(Integer.parseInt(new String(charCity)), Integer.parseInt(new String(charNumber)));
    }
}

class Phone {
    int city;
    int number;

    Phone(int city, int number) {
        this.city = city;
        this.number = number;
    }
}