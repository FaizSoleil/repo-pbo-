import java.util.Scanner;
import java.util.InputMismatchException;

class Helper{
    static Scanner sc = new Scanner(System.in);
    public static int validInputInt(String pesan, int min, int maks){
        int angka = 0;
        boolean valid = false;
        do{
            System.out.print(pesan);
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
    private Integer[] data;
    private int ukuran;
    private int kapasitas;

    public ArrayInteger1D(){
        this.kapasitas = 100;
        this.data = new Integer[this.kapasitas];
        this.ukuran = 0;
    }

    public ArrayInteger1D(int jumlah){
        this.kapasitas = 100;
        this.data = new Integer[this.kapasitas];
        this.ukuran = 0;
        for (int i = 0; i < jumlah; i++){
            tambah_integer(i);
        }
    }

    public void tambah_integer(int integer){
        if (this.ukuran >= this.kapasitas){
            System.out.println("Kapasitas array sudah full.");
        } else {
            this.data[this.ukuran] = integer;
            this.ukuran++;
        }
    }

    public Integer[] get_data(){
        return this.data;
    }

    public void input_data(int jumlah){
        this.ukuran = 0;
        for (int i = 0; i < jumlah; i++){
            System.out.printf("\n --- Data Integer ke-%d ---\n", i + 1);
            int temp = Helper.validInputInt("Masukkan integer: ", -9999, 9999);
            tambah_integer(temp);
        }
    }

    public float get_rata_rata(){
        if (this.ukuran == 0) return 0;
        float jumlah = 0;

        for (int i = 0; i < this.ukuran; i++){
            jumlah += data[i];
        }

        return (jumlah / this.ukuran);
    }

    public int get_tertinggi(){
        if (this.ukuran == 0) return 0;
        int highest = data[0];
        for (int i = 1; i < this.ukuran; i++){
            if (highest < data[i]) highest = data[i];
        }

        return highest;
    }

    public int get_terendah(){
        if (this.ukuran == 0) return 0;
        int lowest = data[0];
        for (int i = 1; i < this.ukuran; i++){
            if (lowest > data[i]) lowest = data[i];
        }

        return lowest;
    }

    public void bubble_sort_asc() {
        for (int i = 0; i < this.ukuran - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < this.ukuran - i - 1; j++) {
                if (this.data[j] > this.data[j + 1]) {
                    int temp = this.data[j];
                    this.data[j] = this.data[j + 1];
                    this.data[j + 1] = temp;
                    
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void bubble_sort_desc() {
        for (int i = 0; i < this.ukuran - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < this.ukuran - i - 1; j++) {
                if (this.data[j] < this.data[j + 1]) {
                    int temp = this.data[j];
                    this.data[j] = this.data[j + 1];
                    this.data[j + 1] = temp;
                    
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void printData(){
        for (int i = 0; i < this.ukuran; i++){
            System.out.printf("%d ", this.data[i]);
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
        System.out.printf("Tertinggi: %d\n", array.get_tertinggi());
        System.out.printf("Terendah: %d\n", array.get_terendah());
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

                    for (int i = 0; i < n; i++){
                        System.out.printf("\n --- Data Integer ke-%d ---\n", i + 1);
                        int temp = Helper.validInputInt("Masukkan integer: ", -9999, 9999);
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
                    break;
                }
                default:{
                    System.out.println("Pilihan tidak valid.");
                    break;
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