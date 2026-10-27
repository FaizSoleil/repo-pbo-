# Nama file : gaji_pegawai.py
# Nama Kelompok:
# - Muhammad Athar Alfarisi (140810250005)
# - Muhammad Faiz Hariy (140810250029)
# - Gibraldi Zilal Fachry (140810250038)
# Tanggal buat : 27 Oktober 2026
# Deskripsi : Program untuk mengolah list data pegawai dan implementasi inheritence

class Helper:
    @staticmethod
    def valid_input_int(pesan, minimum, maksimum):
        while True:
            try:
                angka = int(input(pesan))
                if minimum <= angka <= maksimum:
                    return angka
            except ValueError:
                pass
            print(f"Input harus angka {minimum} - {maksimum}!!")

    @staticmethod
    def format_ribuan(n):
        return f"{n:,}".replace(",", ".")


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.__jam = jam
        self.__menit = menit
        self.__detik = detik

    def set_jam(self, jam):
        self.__jam = jam

    def set_menit(self, menit):
        self.__menit = menit

    def set_detik(self, detik):
        self.__detik = detik

    def set_waktu(self, jam, menit, detik):
        self.set_jam(jam)
        self.set_menit(menit)
        self.set_detik(detik)

    def input_waktu(self):
        self.__jam = Helper.valid_input_int("Masukkan jam: ", 0, 23)
        self.__menit = Helper.valid_input_int("Masukkan menit: ", 0, 59)
        self.__detik = Helper.valid_input_int("Masukkan detik: ", 0, 59)

    def get_jam(self):
        return self.__jam

    def get_menit(self):
        return self.__menit

    def get_detik(self):
        return self.__detik

    def print_waktu(self, end=""):
        print(f"{self.__jam:02d}:{self.__menit:02d}:{self.__detik:02d}", end=end)

    def jarak_waktu(self, pulang):
        hasil = Waktu()

        awal = (self.__jam * 3600) + (self.__menit * 60) + self.__detik
        akhir = (pulang.get_jam() * 3600) + (pulang.get_menit() * 60) + pulang.get_detik()

        if akhir < awal:
            akhir += 24 * 3600

        selisih = abs(akhir - awal)

        hasil.set_jam(selisih // 3600)
        selisih %= 3600
        hasil.set_menit(selisih // 60)
        selisih %= 60
        hasil.set_detik(selisih)

        return hasil

class Person:
    def __init__(self, nama="", alamat=""):
        self._nama = nama
        self._alamat = alamat

    def set_nama(self, nama):
        self._nama = nama

    def set_alamat(self, alamat):
        self._alamat = alamat

    def get_nama(self):
        return self._nama

    def get_alamat(self):
        return self._alamat

    def input(self):
        self._nama = input("Masukkan nama")
        self._alamat = input("Masukkan nama")

    def output(self):
        print(f"{self._nama} {self._alamat}")

class Pegawai(Person):
    GAJI_HARIAN = {1: 150000, 2: 200000, 3: 400000, 4: 500000}
    BIAYA_LEMBUR = {1: 50000, 2: 75000, 3: 150000, 4: 200000}

    def __init__(self, nama="", alamat="" , nip="", gol=0, datang=None, pulang=None):
        super().__init__(nama, alamat)
        self.__nip = nip
        self.__gol = gol
        self.__datang = datang if datang is not None else Waktu()
        self.__pulang = pulang if pulang is not None else Waktu()

    def input_pegawai(self):
        self.__nip = input("Masukkan NIP: ")
        self._nama = input("Masukkan Nama: ")
        self._alamat = input("Masukkan Alamat: ")
        self.__gol = Helper.valid_input_int("Masukkan Golongan (1-4): ", 1, 4)

        print("\n--- Input Waktu Datang ---")
        self.__datang.input_waktu()

        print("\n--- Input Waktu Pulang ---")
        self.__pulang.input_waktu()

    def set_pegawai(self, nip, nama, gol):
        self.__nip = nip
        self._nama = nama
        self.__gol = gol

    def set_nip(self, nip):
        self.__nip = nip

    def set_nama(self, nama):
        self._nama = nama

    def set_gol(self, gol):
        self.__gol = gol

    def set_datang(self, datang):
        self.__datang = datang

    def set_pulang(self, pulang):
        self.__pulang = pulang

    def get_nip(self):
        return self.__nip

    def get_nama(self):
        return self._nama

    def get_gol(self):
        return self.__gol

    def get_datang(self):
        return self.__datang

    def get_pulang(self):
        return self.__pulang

    def get_lama_kerja(self):
        return self.__datang.jarak_waktu(self.__pulang)

    def get_waktu_lembur(self):
        lembur = Waktu()
        lembur = self.get_lama_kerja()
        if lembur.get_jam() > 8:
            lembur.set_jam((lembur.get_jam() - 8))
        else:
            lembur.set_waktu(0, 0, 0)

        return lembur

    def get_gaji_harian(self):
        return Pegawai.GAJI_HARIAN.get(self.__gol, 0)

    def get_biaya_lembur(self):
        return Pegawai.BIAYA_LEMBUR.get(self.__gol, 0)

    def get_uang_lembur_total(self):
        return self.get_waktu_lembur().get_jam() * self.get_biaya_lembur()

    def get_total_gaji(self):
        return self.get_gaji_harian() + self.get_uang_lembur_total()

    def get_status(self):
        if self.get_lama_kerja().get_jam() < 8:
            return "peringatan"
        return "ok"

    def print_pegawai(self, no=1):
        print(f"{f'{no}.':<4}{self.get_nip():<6}{self.get_nama():<16}{self.get_alamat():<20}{self.get_gol():<8}", end="")

        self.get_datang().print_waktu()
        print("   ", end="")
        self.get_pulang().print_waktu()
        print("   ", end="")
        self.get_lama_kerja().print_waktu()
        print("   ", end="")
        self.get_waktu_lembur().print_waktu()
        print("     ", end="")

        print(f"{Helper.format_ribuan(self.get_gaji_harian()):<14}"
              f"{Helper.format_ribuan(self.get_uang_lembur_total()):<13}"
              f"{Helper.format_ribuan(self.get_total_gaji()):<14}"
              f"{self.get_status():<10}")


class ArrayPegawai:
    GARIS = "-" * 152

    def __init__(self, kapasitas=100):
        self.__data = []
        self.__kapasitas = kapasitas

    def tambah_pegawai(self, pegawai):
        if len(self.__data) < self.__kapasitas:
            self.__data.append(pegawai)

    def get_data(self):
        return self.__data

    def input_data(self, n=1):
        self.__data = []
        for i in range(n):
            print(f"\n--- Data Pegawai ke-{i+1} ---")
            p = Pegawai()
            p.input_pegawai()
            self.tambah_pegawai(p)

    def print_all_pegawai(self):
        print("\n\t\t\t\tDaftar Gaji Harian PT Informatika\n")
        print(self.GARIS)
        print(f"{'No':<4}{'NIP':<6}{'Nama':<16}{'Alamat':<20}{'Gol':<8}{'Datang':<11}{'Pulang':<11}"
              f"{'Lama':<11}{'Jam Lembur':<13}{'Gaji Harian':<14}{'Lembur':<13}"
              f"{'Total':<14}{'Status':<10}")
        print(self.GARIS)

        for i in range(len(self.__data)):
            self.__data[i].print_pegawai(i + 1)

        print(self.GARIS)


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
        return Helper.valid_input_int("Menu: ", 0, 4)

    @staticmethod
    def menu():
        pilih = -1
        while pilih != 0:
            Menu.display_menu()
            pilih = Menu.input()

            if pilih == 1:
                n = Helper.valid_input_int("Masukkan jumlah pegawai: ", 1, 100)
                array_pegawai1 = ArrayPegawai()
                for i in range(n):
                    p = Pegawai(f"Pegawai {i+1}", f"jalan soetta{i+1}" , f"00{i+1}", (i % 4) + 1, Waktu(8, 0, 0), Waktu(17, 15, 10))
                    array_pegawai1.tambah_pegawai(p)
                array_pegawai1.print_all_pegawai()

            elif pilih == 2:
                n = Helper.valid_input_int("Masukkan jumlah pegawai: ", 1, 100)
                array_pegawai2 = ArrayPegawai()
                for i in range(n):
                    p = Pegawai()
                    p.set_nip(f"00{i+1}")
                    p.set_nama(f"Pegawai {i+1}")
                    p.set_alamat(f"jalan soetta{i+1}")
                    p.set_gol((i % 4) + 1)
                    p.set_datang(Waktu(8, 0, 0))
                    p.set_pulang(Waktu(17, 15, 10))
                    array_pegawai2.tambah_pegawai(p)
                array_pegawai2.print_all_pegawai()

            elif pilih == 3:
                n = Helper.valid_input_int("Masukkan jumlah pegawai: ", 1, 100)
                array_pegawai3 = ArrayPegawai()
                for i in range(n):
                    print(f"\n--- Data Pegawai ke-{i+1} ---")
                    p = Pegawai()
                    nip = input("Masukkan NIP: ")
                    nama = input("Masukkan Nama: ")
                    alamat = input("Masukkan Alamat: ")
                    gol = Helper.valid_input_int("Masukkan Golongan (1-4): ", 1, 4)

                    p.set_nip(nip)
                    p.set_nama(nama)
                    p.set_alamat(alamat)
                    p.set_gol(gol)

                    print("\n--- Input Waktu Datang ---")
                    d = Waktu()
                    d.input_waktu()
                    p.set_datang(d)

                    print("\n--- Input Waktu Pulang ---")
                    pl = Waktu()
                    pl.input_waktu()
                    p.set_pulang(pl)

                    array_pegawai3.tambah_pegawai(p)
                array_pegawai3.print_all_pegawai()

            elif pilih == 4:
                n = Helper.valid_input_int("Masukkan jumlah pegawai: ", 1, 100)
                array_pegawai4 = ArrayPegawai()
                array_pegawai4.input_data(n)
                array_pegawai4.print_all_pegawai()

            elif pilih == 0:
                print("Terima kasih...")

            else:
                pass

def main():
    Menu.menu()

if __name__ == "__main__":
    main()