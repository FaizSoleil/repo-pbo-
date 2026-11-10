#    Nama File    : parkir.py
#    Nama Kelompok:
#      - Muhammad Athar Alfarisi (140810250005)
#      - Muhammad Faiz Hariy Nugroho (140810250029)
#      - Gibraldi Zilal Fachry (140810250038)
#    Tanggal Buat : 21 November 2026
#    Deskripsi    : Parkir

from abc import ABC, abstractmethod


# ---------- Helper ----------
def valid_input_int(pesan, minimum, maksimum):
    while True:
        temp = input(pesan).strip()
        if temp == "":
            continue
        try:
            angka = int(temp)
        except ValueError:
            print("Input harus sebuah integer!!")
            continue
        if angka < minimum or angka > maksimum:
            print(f"Input harus angka {minimum} - {maksimum}!!")
            continue
        return angka


def format_ribuan(n):
    return f"{n:,}".replace(",", ".")


def input_string(pesan=""):
    while True:
        hasil = input(pesan)
        if hasil != "":
            return hasil
        print("Input tidak boleh kosong!!")


# ---------- Waktu ----------
class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def input_waktu(self):
        self.jam = valid_input_int("Masukkan jam: ", 0, 23)
        self.menit = valid_input_int("Masukkan menit: ", 0, 59)
        self.detik = valid_input_int("Masukkan detik: ", 0, 59)

    def __str__(self):
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"

    def total_detik(self):
        return self.jam * 3600 + self.menit * 60 + self.detik

    def jarak_waktu(self, pulang):
        awal = self.total_detik()
        akhir = pulang.total_detik()
        if akhir < awal:  # melewati tengah malam
            akhir += 24 * 3600
        selisih = akhir - awal
        return Waktu(selisih // 3600, (selisih % 3600) // 60, selisih % 60)


# ---------- Person & Pegawai ----------
class Person:
    def __init__(self, nama="", alamat=""):
        self.nama = nama
        self.alamat = alamat

    def input(self):
        self.nama = input_string("Masukkan Nama: ")
        self.alamat = input_string("Masukkan Alamat: ")

    def output(self):
        print(self.nama, self.alamat)


class Pegawai(Person):
    GAJI_HARIAN = {1: 150000, 2: 200000, 3: 400000, 4: 500000}
    BIAYA_LEMBUR = {1: 50000, 2: 75000, 3: 150000, 4: 200000}

    def __init__(self, nama="", alamat="", nip="", gol=0, datang=None, pulang=None):
        super().__init__(nama, alamat)
        self.nip = nip
        self.gol = gol
        self.datang = datang or Waktu()
        self.pulang = pulang or Waktu()

    def input_pegawai(self):
        self.input()
        self.nip = input_string("Masukkan NIP: ")
        self.gol = valid_input_int("Masukkan Golongan (1-4): ", 1, 4)

        print("\n--- Input Waktu Datang ---")
        self.datang.input_waktu()
        print("\n--- Input Waktu Pulang ---")
        self.pulang.input_waktu()

    def get_lama_kerja(self):
        return self.datang.jarak_waktu(self.pulang)

    def get_waktu_lembur(self):
        lembur = self.get_lama_kerja()
        if lembur.jam > 8:
            lembur.jam -= 8
            return lembur
        return Waktu()

    def get_gaji_harian(self):
        return self.GAJI_HARIAN.get(self.gol, 0)

    def get_biaya_lembur(self):
        return self.BIAYA_LEMBUR.get(self.gol, 0)

    def get_uang_lembur_total(self):
        return self.get_waktu_lembur().jam * self.get_biaya_lembur()

    def get_total_gaji(self):
        return self.get_gaji_harian() + self.get_uang_lembur_total()

    def get_status(self):
        return "peringatan" if self.get_lama_kerja().jam < 8 else "ok"

    def print_pegawai(self, no):
        print(
            f"{str(no) + '.':<4}{self.nip:<6}{self.nama:<16}{self.alamat:<20}{self.gol:<8}"
            f"{self.datang}   {self.pulang}   {self.get_lama_kerja()}     {self.get_waktu_lembur()}     "
            f"{format_ribuan(self.get_gaji_harian()):<14}"
            f"{format_ribuan(self.get_uang_lembur_total()):<13}"
            f"{format_ribuan(self.get_total_gaji()):<14}"
            f"{self.get_status():<10}"
        )


class ArrayPegawai:
    GARIS = "-" * 152
    MAX = 100

    def __init__(self):
        self.data = []

    def tambah_pegawai(self, pegawai):
        if len(self.data) < self.MAX:
            self.data.append(pegawai)

    def get_jumlah(self):
        return len(self.data)

    def input_data(self, n):
        self.data = []
        for i in range(n):
            print(f"\n--- Data Pegawai ke-{i + 1} ---")
            p = Pegawai()
            p.input_pegawai()
            self.tambah_pegawai(p)

    def print_all_pegawai(self):
        print("\n\t\t\t\tDaftar Gaji Harian PT Informatika")
        print(self.GARIS)
        print(
            f"{'No':<4}{'NIP':<6}{'Nama':<16}{'Alamat':<20}{'Gol':<8}"
            f"{'Datang':<11}{'Pulang':<11}{'Lama':<13}{'Jam Lembur':<13}"
            f"{'Gaji Harian':<14}{'Lembur':<13}{'Total':<14}{'Status':<10}"
        )
        print(self.GARIS)
        for i, p in enumerate(self.data):
            p.print_pegawai(i + 1)
        print(self.GARIS)


# ---------- Kendaraan ----------
class Kendaraan(ABC):
    def __init__(self, nomor_polisi, pemilik, datang, pulang):
        self.nomor_polisi = nomor_polisi
        self.pemilik = pemilik  # None = anonim
        self.datang = datang
        self.pulang = pulang

    def adalah_pegawai(self):
        return isinstance(self.pemilik, Pegawai)

    # minimum 1 jam, kalau kurang bulatkan ke atas
    def get_jam_tagihan(self):
        detik = self.datang.jarak_waktu(self.pulang).total_detik()
        return max(1, (detik + 3599) // 3600)

    def biaya_per_jam(self, tarif):
        return self.get_jam_tagihan() * tarif

    def get_nama_pemilik(self):
        return "null" if self.pemilik is None else self.pemilik.nama

    def get_status(self):
        if self.adalah_pegawai():
            return "pegawai"
        if self.pemilik is None:
            return "umum (anonim)"
        return "umum"

    @abstractmethod
    def hitung_biaya(self):
        pass

    @abstractmethod
    def get_jenis(self):
        pass

    @abstractmethod
    def get_detail(self):
        pass

    def print_kendaraan(self, no):
        lama = self.datang.jarak_waktu(self.pulang)
        print(
            f"{str(no) + '.':<4}{self.get_jenis():<8}{self.nomor_polisi:<9}"
            f"{self.get_nama_pemilik():<16}{self.get_status():<16}{self.get_detail():<12}"
            f"{self.datang}   {self.pulang}   {lama}   {format_ribuan(self.hitung_biaya()):<12}"
        )


class Mobil(Kendaraan):
    def __init__(self, nomor_polisi, pemilik, datang, pulang, jumlah_pintu):
        super().__init__(nomor_polisi, pemilik, datang, pulang)
        self.jumlah_pintu = jumlah_pintu

    def hitung_biaya(self):
        if self.adalah_pegawai():
            return 6000  # per hari
        return self.biaya_per_jam(3000)

    def get_jenis(self):
        return "Mobil"

    def get_detail(self):
        return f"Pintu: {self.jumlah_pintu}"


class Truk(Kendaraan):
    def __init__(self, nomor_polisi, pemilik, datang, pulang, jumlah_roda):
        super().__init__(nomor_polisi, pemilik, datang, pulang)
        self.jumlah_roda = jumlah_roda

    def hitung_biaya(self):
        return self.biaya_per_jam(5000)  # pegawai juga bayar per jam

    def get_jenis(self):
        return "Truk"

    def get_detail(self):
        return f"Roda: {self.jumlah_roda}"


class Motor(Kendaraan):
    def __init__(self, nomor_polisi, pemilik, datang, pulang, jumlah_tak):
        super().__init__(nomor_polisi, pemilik, datang, pulang)
        self.jumlah_tak = jumlah_tak

    def hitung_biaya(self):
        if self.adalah_pegawai():
            return 3000  # per hari
        return self.biaya_per_jam(2000)

    def get_jenis(self):
        return "Motor"

    def get_detail(self):
        return f"Tak: {self.jumlah_tak}"


class ArrayParkir:
    GARIS = "-" * 110

    def __init__(self):
        self.data = []

    def get_kendaraan(self, index):
        if index < 0 or index >= len(self.data):
            return None
        return self.data[index]

    def tambah_kendaraan(self, k):
        self.data.append(k)

    def get_jumlah(self):
        return len(self.data)

    def print_all_parkir(self):
        print("\n\t\t\t\tLaporan Parkir Gedung")
        print(self.GARIS)
        print(
            f"{'No':<4}{'Jenis':<8}{'No.Pol':<9}{'Pemilik':<16}{'Status':<16}{'Detail':<12}"
            f"{'Datang':<11}{'Pulang':<11}{'Lama':<11}{'Biaya':<12}"
        )
        print(self.GARIS)
        for i, k in enumerate(self.data):
            k.print_kendaraan(i + 1)  # polimorfisme otomatis
        print(self.GARIS)


# ---------- Menu ----------
def display_menu():
    print("\n========== MENU PARKIR ==========")
    print("1. Tambah kendaraan (Input dalam)")
    print("2. Tambah kendaraan (Input luar)")
    print("3. Tampilkan laporan parkir")
    print("4. Ganti pemilik kendaraan")
    print("5. Isi data contoh")
    print("0. Keluar")
    print("=================================")


def pilih_pemilik(list_orang):
    print("\n--- Pilih Pemilik ---")
    print("0. Anonim (tanpa identitas)")
    for i, orang in enumerate(list_orang):
        status = "pegawai" if isinstance(orang, Pegawai) else "umum"
        print(f"{i + 1}. {orang.nama} ({status})")
    p = valid_input_int("Pemilik: ", 0, len(list_orang))
    return None if p == 0 else list_orang[p - 1]


def buat_kendaraan(list_orang):
    print("\n1. Mobil\n2. Truk\n3. Motor")
    jenis = valid_input_int("Jenis kendaraan: ", 1, 3)
    plat = valid_input_int("Nomor polisi (angka): ", 1, 999999)
    pemilik = pilih_pemilik(list_orang)

    print("\n--- Waktu Datang ---")
    datang = Waktu()
    datang.input_waktu()
    print("\n--- Waktu Pulang ---")
    pulang = Waktu()
    pulang.input_waktu()

    if jenis == 1:
        pintu = valid_input_int("Jumlah pintu: ", 2, 6)
        return Mobil(plat, pemilik, datang, pulang, pintu)
    if jenis == 2:
        roda = valid_input_int("Jumlah roda: ", 4, 18)
        return Truk(plat, pemilik, datang, pulang, roda)
    tak = valid_input_int("Jumlah tak (2/4): ", 2, 4)
    return Motor(plat, pemilik, datang, pulang, tak)


def isi_contoh(parkir, list_orang):
    # list_orang: 0 Budi (umum), 1 Ani (umum), 2 Siti (pegawai), 3 Joko (pegawai)
    parkir.tambah_kendaraan(Mobil(1234, list_orang[2], Waktu(8, 0, 0), Waktu(17, 30, 0), 4))
    parkir.tambah_kendaraan(Motor(5678, list_orang[3], Waktu(8, 0, 0), Waktu(16, 0, 0), 2))
    parkir.tambah_kendaraan(Truk(9012, list_orang[2], Waktu(7, 0, 0), Waktu(10, 10, 0), 6))
    parkir.tambah_kendaraan(Mobil(3456, list_orang[0], Waktu(9, 0, 0), Waktu(11, 0, 0), 4))
    parkir.tambah_kendaraan(Motor(7890, None, Waktu(10, 0, 0), Waktu(10, 20, 0), 2))
    parkir.tambah_kendaraan(Mobil(1111, None, Waktu(22, 0, 0), Waktu(2, 30, 0), 4))
    print("6 data contoh ditambahkan.")


def menu():
    # Pemilik hard-code
    list_orang = [
        Person("Budi", "Jl. Merdeka"),
        Person("Ani", "Jl. Sudirman"),
        Pegawai("Siti", "Jl. Soetta", "001", 2, Waktu(8, 0, 0), Waktu(17, 0, 0)),
        Pegawai("Joko", "Jl. Diponegoro", "002", 3, Waktu(8, 0, 0), Waktu(17, 0, 0)),
    ]
    parkir = ArrayParkir()

    while True:
        display_menu()
        pilih = valid_input_int("Menu: ", 0, 5)

        if pilih == 1 or pilih == 2:  # dua menu ini identik di kode asli
            parkir.tambah_kendaraan(buat_kendaraan(list_orang))
            print("Kendaraan ditambahkan.")
        elif pilih == 3:
            if parkir.get_jumlah() == 0:
                print("Belum ada data.")
            else:
                parkir.print_all_parkir()
        elif pilih == 4:
            if parkir.get_jumlah() == 0:
                print("Belum ada data.")
                continue
            parkir.print_all_parkir()
            no = valid_input_int("Nomor kendaraan: ", 1, parkir.get_jumlah())
            parkir.get_kendaraan(no - 1).pemilik = pilih_pemilik(list_orang)
            print("Pemilik diganti.")
        elif pilih == 5:
            isi_contoh(parkir, list_orang)
        elif pilih == 0:
            print("Terima kasih...")
            break


if __name__ == "__main__":
    menu()