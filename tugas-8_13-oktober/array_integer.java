import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Helper{
    static Scanner sc = new Scanner(System.in);
    public static int validInputInt(String pesan, int min, int maks){
        int angka = 0;
        boolean valid = false;
        do{
            System.out.printf(pesan);
            try{
                angka = sc.nextInt();
                if (angka < min || angka > maks){
                    System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e){
                System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                sc.next();
            }
        } while (!valid);

        return angka;
    }
}

class ArrayInteger1D{
    private ArrayList<Float> data = new ArrayList<>();
    private int kapasitas;

    public ArrayInteger1D(){
        this.kapasitas = 100;
    }

    public ArrayInteger1D(int kapasitas){
        this.kapasitas = kapasitas;
        for (float i = 0; i < kapasitas; i += 1){
            data.add(i);
        }
    }

    public void tambah_integer(float integer){
        data.add(integer);
    }

    public ArrayList<Float> get_data(){
        return this.data;
    }

    public void input_data(int jumlah){
        for (int i = 0; i < jumlah; i++){
            System.out.printf("\n --- Data Integer ke-%d ---\n", i + 1);
            float temp = Helper.validInputInt("Masukkan integer: ", -9999, 9999);
            data.add(temp);
        }
    }

    public float get_rata_rata(){
        int TOTAL_DATA = data.size();
        float jumlah = 0;

        for (float i : data){
            jumlah += i;
        }

        return jumlah/TOTAL_DATA;
    }

    public float get_tertinggi(){
        float highest = -Float.MAX_VALUE;
        for (float i : data){
            if (highest < i) highest = i;
        }

        return highest;
    }

    public float get_terendah(){
        float lowest = Float.MAX_VALUE;
        for (float i : data){
            if (lowest > i) lowest = i;
        }

        return lowest;
    }

    public void bubble_sort_asc() {
        for (int i = 0; i < this.data.size() - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < this.data.size() - i - 1; j++) {
                if (this.data.get(j) > this.data.get(j+1)) {
                    float temp = this.data.get(j);
                    this.data.set(j, this.data.get(j + 1));
                    this.data.set(j + 1, temp);
                    
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void bubble_sort_desc() {
        for (int i = 0; i < this.data.size() - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < this.data.size() - i - 1; j++) {
                if (this.data.get(j) < this.data.get(j+1)) {
                    float temp = this.data.get(j);
                    this.data.set(j, this.data.get(j + 1));
                    this.data.set(j + 1, temp);
                    
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void printData(){
        for (int i = 0; i < this.data.size(); i++){
            System.out.printf("%.0f ", this.data.get(i));
        }
    }
}

class Menu{
    public static void display_menu(){
        System.out.println("\n============= MENU ===============");
        System.out.println("1. CONSTRUCTOR KONSTANTA");
        System.out.println("2. SETTER KONSTANTA");
        System.out.println("3. SETTER INPUT LUAR");
        System.out.println("4. INPUT DALAM");
        System.out.println("0. KELUAR");
        System.out.println("==================================");
    }

    public static int input(){
        return Helper.validInputInt("Menu: ", 0, 4);
    }

    public static void print_test(int objek_nomor, ArrayInteger1D array){
        System.out.printf("Objek ke-%d\n", objek_nomor);
        System.out.printf("Rata-rata: %.2f\n", array.get_rata_rata());
        System.out.printf("Tertinggi: %.0f\n", array.get_tertinggi());
        System.out.printf("Terendah: %.0f\n", array.get_terendah());
        System.out.println("Data awal:");
        array.printData();

        array.bubble_sort_asc();
        System.out.println("\nData sorted asc:");
        array.printData();

        array.bubble_sort_desc();
        System.out.println("\nData sorted desc:");
        array.printData();

        System.out.println();
    }

    public void runMenu(){
        int pilihan = -1;

        while (pilihan != 0){
            Menu.display_menu();
            pilihan = Menu.input();
            switch (pilihan) {
                case 1:{
                    int n = Helper.validInputInt("Masukkan jumlah integer: ", 1, 100);
                    ArrayInteger1D array_integer1 = new ArrayInteger1D();

                    for (int i = 0; i < n; i++){
                        array_integer1.tambah_integer(i);
                    }

                    Menu.print_test(1, array_integer1);
                    break;
                }
                case 2:{
                    int n = Helper.validInputInt("Masukkan jumlah integer: ", 1, 100);
                    ArrayInteger1D array_integer2 = new ArrayInteger1D(n);

                    Menu.print_test(2, array_integer2);
                    break;
                }
                case 3:{
                    int n = Helper.validInputInt("Masukkan jumlah integer: ", 1, 100);
                    ArrayInteger1D array_integer3 = new ArrayInteger1D();

                    for (float i = 0; i < n; i++){
                        System.out.printf("\n --- Data Integer ke-%.0f ---\n", i + 1);
                        float temp = Helper.validInputInt("Masukkan integer: ", -9999, 9999);
                        array_integer3.tambah_integer(temp);
                    }

                    Menu.print_test(3, array_integer3);
                    break;
                }
                case 4:{
                    int n = Helper.validInputInt("Masukkan jumlah integer: ", 1, 100);
                    ArrayInteger1D array_integer4 = new ArrayInteger1D();

                    array_integer4.input_data(n);

                    Menu.print_test(4, array_integer4);
                    break;
                }
                case 0:{
                    System.out.println("Terima kasih sudah menggunakan program ini.");
                }
                default:{
                    System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }
}

public class array_integer{
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.runMenu();
    }
}