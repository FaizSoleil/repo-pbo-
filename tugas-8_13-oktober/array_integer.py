import sys
from pathlib import Path

sys.path.append(str(Path(__file__).resolve().parents[1]/"common"/"py"))

from helpers import Inputs

def main():
    Menu.runMenu()

class ArrayInteger1D:
    def __init__(self, data = 0, kapasitas=100):
        self.__data = []
        self.__kapasitas = kapasitas
        for i in range(data):
            self.tambah_integer(i)

    def tambah_integer(self, integer):
        if len(self.__data) < self.__kapasitas:
            self.__data.append(integer)

    def get_data(self):
        return self.__data

    def input_data(self, jumlah = 1):
        self.__data = []
        for i in range(jumlah):
            print(f"\n --- Data Integer ke-{i+1} ---")
            integer_input = Inputs.valid_input_int("Masukkan integer: ")
            self.tambah_integer(integer_input)

    def get_rata_rata(self):
        TOTAL_DATA = len(self.__data)
        jumlah = 0

        for i in range(len(self.__data)):
            jumlah += self.__data[i]

        return jumlah/TOTAL_DATA

    def get_tertinggi(self):
        highest = float('-inf')

        for i in range(len(self.__data)):
            if self.__data[i] > highest:
                highest = self.__data[i]

        return highest

    def get_terendah(self):
            lowest = float('inf')
    
            for i in range(len(self.__data)):
                if self.__data[i] < lowest:
                    lowest = self.__data[i]
    
            return lowest

    def bubble_sort_asc(self):
        for i in range(len(self.__data) - 1):
            swapped = False
            for j in range(len(self.__data)-i-1):
                if self.__data[j] > self.__data[j+1]:
                    self.__data[j], self.__data[j+1] = self.__data[j+1], self.__data[j]
                    swapped = True
            if not swapped:
                break

    def bubble_sort_desc(self):
        for i in range(len(self.__data) - 1):
            swapped = False
            for j in range(len(self.__data)-i-1):
                if self.__data[j] < self.__data[j+1]:
                    self.__data[j], self.__data[j+1] = self.__data[j+1], self.__data[j]
                    swapped = True
            if not swapped:
                break

    def printData(self):
        for i in range(len(self.__data)):
            print(self.__data[i], end=' ')

class Menu:
    @staticmethod
    def display_menu():
        print("\n============= MENU ===============")
        print("1. CONSTRUCTOR KONSTANTA")
        print("2. SETTER KONSTANTA")
        print("3. SETTER INPUT LUAR")
        print("4. INPUT DALAM")
        print("0. KELUAR")
        print("==================================")

    @staticmethod
    def input():
        return Inputs.valid_input_int("Menu: ", 0, 4)

    @staticmethod
    def print_test(objek_nomor, array_integer):
        print(f"Objek ke-{objek_nomor}")
        print(f"Rata-rata: {array_integer.get_rata_rata():.2f}")
        print(f"Tertinggi: {array_integer.get_tertinggi()}")
        print(f"Terendah: {array_integer.get_terendah()}")
        print("Data awal:")
        array_integer.printData()

        array_integer.bubble_sort_asc()
        print("\nData sorted asc:")
        array_integer.printData()

        array_integer.bubble_sort_desc()
        print("\nData sorted desc:")
        array_integer.printData()

    @staticmethod
    def runMenu():
        pilihan = -1

        while pilihan != 0:
            Menu.display_menu()
            pilihan = Menu.input()
            match pilihan:
                case 1:
                    n = Inputs.valid_input_int("Masukkan jumlah integer: ", 1, 100)
                    array_integer1 = ArrayInteger1D()

                    for i in range(n):
                        array_integer1.tambah_integer(i)

                    Menu.print_test(1, array_integer1)

                case 2:
                    n = Inputs.valid_input_int("Masukkan jumlah integer: ", 1, 100)
                    array_integer2 = ArrayInteger1D(n)

                    Menu.print_test(2, array_integer2)

                case 3:
                    n = Inputs.valid_input_int("Masukkan jumlah integer: ", 1, 100)
                    array_integer3 = ArrayInteger1D()

                    for i in range(n):
                        print(f"\n --- Data Integer ke-{i+1} ---")
                        Inputs.valid_input_int("Masukkan integer: ")
                        array_integer3.tambah_integer(i)

                    Menu.print_test(3, array_integer3)

                case 4:
                    n = Inputs.valid_input_int("Masukkan jumlah integer: ", 1, 100)
                    array_integer4 = ArrayInteger1D()
                    array_integer4.input_data(n)

                    Menu.print_test(4, array_integer4)

                case 0:
                    print("Terima kasih sudah menggunakan program ini.")

                case _:
                    print("Pilihan tidak valid.")

if __name__ == "__main__":
    main()