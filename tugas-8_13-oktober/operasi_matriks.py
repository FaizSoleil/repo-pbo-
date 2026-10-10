#    Nama File    : operasi_matriks.py
#    Nama Kelompok:
#      - Muhammad Athar Alfarisi (140810250005)
#      - Muhammad Faiz Hariy Nugroho (140810250029)
#      - Gibraldi Zilal Fachry (140810250038)
#    Tanggal Buat : 08 Oktober 202  6
#    Deskripsi    : Program operasi matriks


class Matriks:
    # REMEMBER!!:
    # Method-method disini akan throw exception (raise ValueError di python) bila data yang diberikan tidak valid
    # hal ini untuk melindungi dari adanya kerusakan yang terjadi pada objek yang sudah di instansiasi
    # karrena itu, jangan lupa untuk membungkus semua pemanggilan method yang raise ValueError dengan try-catch
    #
    # fungsi-fungsi disini meng-pass dan return copy dari matriks ([row[:] for row in data])
    # untuk menghindari akses dari luar method class, yang di assign ke self.data adalah copy dari data

    def __init__(self, baris=0, kolom=0, data=None):
        if not self._dimensi_valid(baris) or not self._dimensi_valid(kolom):
            raise ValueError("Baris dan kolom matriks tidak valid!")
        
        if data is None:
            data = self._matriks_nol(baris, kolom)

        elif not self._dimensi_cocok(data, baris, kolom):
            raise ValueError("Dimensi data tidak sesuai dengan baris dan kolom yang diberikan!")

        self.__baris = baris
        self.__kolom = kolom
        self.__data = self._salin(data)

    def set_baris(self, baris):
        if not self._dimensi_valid(baris):
            raise ValueError("Baris matriks tidak valid!")
        
        self._ubah_baris(baris)
        self.__baris = baris

    def set_kolom(self, kolom):
        if not self._dimensi_valid(kolom):
            raise ValueError("Kolom matriks tidak valid!")

        self._ubah_kolom(kolom)
        self.__kolom = kolom

    def set_data(self, data):
        if not self._dimensi_cocok(data, self.__baris, self.__kolom):
            raise ValueError("Dimensi data tidak sesuai dengan baris dan kolom matriks!")

        self.__data = self._salin(data)

    def get_baris(self): return self.__baris
    def get_kolom(self): return self.__kolom
    def get_data(self): return self._salin(self.__data)

    def input_baris(self):
        self.set_baris(Helper.input_int("Baris: ", minimum=0))
    
    def input_kolom(self):
        self.set_kolom(Helper.input_int("Kolom: ", minimum=0))

    def input_data(self):
        for i in range(self.__baris):
            for j in range(self.__kolom):
                self.__data[i][j] = Helper.input_float(
                    f"Input data [{i+1}][{j+1}]: ")


    def kali_matriks_void(self, B):
        hasil = Matriks.kali_matriks_return(self, B)
        self.set_baris(self.__baris)
        self.set_kolom(B.get_kolom())
        self.set_data(hasil.get_data())

    @staticmethod
    def kali_matriks_return(A, B):
        if A.get_kolom() != B.get_baris():
            raise ValueError("Jumlah kolom matriks pertama harus sama dengan jumlah baris matriks kedua!")

        baris = A.get_baris()
        kolom = B.get_kolom()
        n = A.get_kolom()

        data_A = A.get_data()
        data_B = B.get_data()

        hasil = Matriks._matriks_nol(baris, kolom)

        for i in range(baris):
            for j in range(kolom):
                for k in range(n):
                    hasil[i][j] += data_A[i][k] * data_B[k][j]

        return Matriks(baris, kolom, hasil)

    def tambah_matriks_void(self, B):
        hasil = Matriks.tambah_matriks_return(self, B)
        self.set_data(hasil.get_data())

    @staticmethod
    def tambah_matriks_return(A, B):
        if A.get_baris() != B.get_baris() or A.get_kolom() != B.get_kolom():
            raise ValueError("Ukuran kedua matriks harus sama!")

        baris = A.get_baris()
        kolom = A.get_kolom()

        data_A = A.get_data()
        data_B = B.get_data()

        hasil = Matriks._matriks_nol(baris, kolom)  # hasil disimpan sebagai temp list of list, biar data individunya bisa diakses di loop

        for i in range(baris):
            for j in range(kolom):
                hasil[i][j] = data_A[i][j] + data_B[i][j]

        return Matriks(baris, kolom, hasil)

    def print_matriks(self):
        for baris in self.__data:
            for nilai in baris:
                print(nilai, end=" ")
            print()

    def _ubah_baris(self, baris):
        if baris < self.__baris:
            self.__data = self.__data[:baris] # ubah jumlah baris matriks menjadi hanya sebanyak baris baru
            
        elif baris > self.__baris:
            self.__data += self._matriks_nol(baris - self.__baris, self.__kolom) # tambahkan baris [0] untuk setiap baris baru

    def _ubah_kolom(self, kolom):
        if kolom < self.__kolom:
            for baris in self.__data:
                del baris[kolom:] # hapus semua kolom dalam row yang melebihi kolom baru

        elif kolom > self.__kolom:
            for baris in self.__data:
                baris.extend([0] * (kolom - self.__kolom)) # tambahkan kolom [0] di setiap baris untuk setiap kolom baru

    @staticmethod
    def _salin(data):
        return [baris[:] for baris in data]

    # ada isinstance bool check karena nge pass True/False ke int check bakalan lewat
    @staticmethod
    def _dimensi_valid(n):
        return isinstance(n, int) and not isinstance(n, bool) and n >= 0

    @staticmethod
    def _dimensi_cocok(data, baris, kolom):
        return len(data) == baris and all(len(row) == kolom for row in data)

    # return matriks isi 0 semua
    @staticmethod
    def _matriks_nol(baris, kolom):
        return [[0] * kolom for _ in range(baris)]

# -------------------------------------------------------------------------------------------------------- #

class Main:
    @staticmethod
    def print_menu():
        print("\n============= MENU ===============")
        print("1. CONSTRUCTOR KONSTANTA")
        print("2. SETTER KONSTANTA")
        print("3. SETTER INPUT LUAR")
        print("4. INPUT DALAM")
        print("0. KELUAR")
        print("==================================")

    @staticmethod
    def run_menu():
        choice = -1

        # emulasi do-while, dengan kondisi berhenti nya di akhir
        while True:
            Main.print_menu()
            choice = Helper.input_int(": ")
            match choice:
                case 1:
                    # definisi konstanta -> masukkan ke kedua matriks lewat parameterized constructor
                    baris = 3
                    kolom = 3
                    data_A = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
                    data_B = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]

                    try:
                        A = Matriks(baris, kolom, data_A)
                        B = Matriks(baris, kolom, data_B)
                    except ValueError as e:
                        print(f"Error: {e}")
                    else:
                        Main.demo_matriks(A, B)

                case 2:
                    # definisi konstanta -> masukkan ke kedua matriks lewat setter
                    A = Matriks()
                    B = Matriks()
                    
                    baris = 3
                    kolom = 3
                    data_A = [[3, 8, 11], [0, 9, 2], [4, 5, 14]]
                    data_B = [[8, 9, 1], [0, 3, 5], [0, 0, 20]]

                    try:
                        A.set_baris(baris)
                        A.set_kolom(kolom)
                        A.set_data(data_A)

                        B.set_baris(baris)
                        B.set_kolom(kolom)
                        B.set_data(data_B)
                    except ValueError as e:
                        print(f"Error: {e}")
                    else:
                        Main.demo_matriks(A, B)

                case 3:
                    # input m, n -> input matriks A_m x n -> input o, p -> input matriks B_o x p -> masukkan semua variabel yang sudah diinput lewat setter
                    try:
                        A = Helper.input_matriks("A")
                        B = Helper.input_matriks("B")
                    except ValueError as e:
                        print(f"Error: {e}")
                    else:
                        Main.demo_matriks(A, B)

                case 4:
                    # definisi matriks A, B -> input matriks A, B lewat input dalam class
                    try:
                        A = Matriks()
                        A.input_baris()
                        A.input_kolom()
                        A.input_data()

                        B = Matriks()
                        B.input_baris()
                        B.input_kolom()
                        B.input_data()
                    except ValueError as e:
                        print(f"Error: {e}")
                    else:
                        Main.demo_matriks(A, B)

                case 0:
                    print("Terimakasih")

                case _:
                    pass

            if choice == 0:
                break

    @staticmethod
    def demo_matriks(A, B):
        print("Matriks A:")
        A.print_matriks()
        print()
        print("Matriks B:")
        B.print_matriks()
        print()
        A2 = Matriks(A.get_baris(), A.get_kolom(), A.get_data()) # A dipake dua kali, jadi buat copy untuk tes

        # PENJUMLAHAN TES

        try:
            C = Matriks.tambah_matriks_return(A, B)
            print("Matriks C = A + B:")
            C.print_matriks()
            print()
        except ValueError as e:
            print(f"Error: {e}")

        try:
            A.tambah_matriks_void(B)
            print("Matriks A = A + B:")
            A.print_matriks()
            print()
        except ValueError as e:
            print(f"Error: {e}")
        print("=================")
        # PERKALIAN TES

        try:
            D = Matriks.kali_matriks_return(A2, B)
            print("Matriks D = A * B:")
            D.print_matriks()
            print()
        except ValueError as e:
            print(f"Error: {e}")

        try:
            A2.kali_matriks_void(B)
            print("Matriks A = A * B")
            A2.print_matriks()
            print()
        except ValueError as e:
            print(f"Error: {e}")


# -------------------------------------------------------------------------------------------------------- #

class Helper:
    @staticmethod
    def _input_angka(pesan, cast, minimum=None, maksimum=None):
        while True:
            try:
                angka = cast(input(pesan))
            except ValueError:
                pass
            else:
                if (minimum is None or angka >= minimum) and (maksimum is None or angka <= maksimum):
                    return angka

            if minimum is not None and maksimum is not None:
                print(f"Input harus angka {minimum} - {maksimum}!")
            elif minimum is not None:
                print(f"Input harus angka diatas {minimum}!")
            elif maksimum is not None:
                print(f"Input harus angka dibawah {maksimum}!")
            else:
                print("Input harus angka!")

    @staticmethod
    def input_int(pesan, minimum=None, maksimum=None):
        return Helper._input_angka(pesan, int, minimum, maksimum)

    @staticmethod
    def input_float(pesan, minimum=None, maksimum=None):
        return Helper._input_angka(pesan, float, minimum, maksimum)

    # helper khusus case 3
    @staticmethod
    def input_matriks(nama):
        baris = Helper.input_int(f"Input baris {nama}: ", minimum=0)
        kolom = Helper.input_int(f"Input kolom {nama}: ", minimum=0)
        data = []

        for i in range(baris):
            temp_baris = []
            for j in range(kolom):
                temp_baris.append(Helper.input_float(
                    f"Input data [{i+1}][{j+1}]: "
                ))
            data.append(temp_baris)

        m = Matriks(baris, kolom, data)
        return m

if __name__=="__main__":
    # test_main()
    Main.run_menu()