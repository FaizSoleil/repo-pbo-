
# Feel free to include this file if you don't feel like writing input validation for the 67th time in py.
# To include, copy the code below:
# import sys
# from pathlib import Path

# sys.path.append(str(Path(__file__).resolve().parents[1] / "common" / "py"))

# import Helpers 

# parents[0]: /home/you/repo-pbo-/*current-folder*
# parents[1]: /home/you/repo-pbo-

# note: pake packages lebih bagus tapi buat tugas kecil ini dah cukup.
# hacky tapi cukup, lmk kalo mau pake packages

class Inputs:
    @staticmethod
    def valid_input_int(pesan, minimum=float('-inf'), maksimum=float('inf')):
        while True:
            try:
                angka = int(input(pesan))
                if minimum <= angka <= maksimum:
                    return angka
            except ValueError:
                pass
            print(f"Error: valid_input_int(): Input harus angka {minimum} - {maksimum}.")

class Outputs:
    @staticmethod
    def format_ribuan(n):
        return f"{n:,}".replace(",", ".")
