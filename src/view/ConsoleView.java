package view;

import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {

    public void tampilkanDashboard(int totalData, int totalObjek) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("      KNOWLEDGE MANAGEMENT SYSTEM PUTUSAN NARKOTIKA");
        System.out.println("==============================================================");
        System.out.println("Total Data Saat Ini     : " + totalData);
        System.out.println("Total Objek Dibuat      : " + totalObjek);
        System.out.println("==============================================================");
    }

    public int tampilkanMenu(Scanner scanner) {

        System.out.println();
        System.out.println("============== MENU UTAMA ==============");
        System.out.println("1. Tambah Putusan");
        System.out.println("2. Tampilkan Semua Putusan");
        System.out.println("3. Cari Berdasarkan Nomor");
        System.out.println("4. Cari Berdasarkan Nama");
        System.out.println("5. Filter Jenis Narkotika");
        System.out.println("6. Filter Pengadilan");
        System.out.println("7. Filter Rentang Vonis");
        System.out.println("8. Statistik");
        System.out.println("9. Hapus Putusan");
        System.out.println("10. Keluar");
        System.out.println("========================================");

        return InputHandler.validasiPilihan(
                "Pilih Menu : ",
                1,
                10,
                scanner
        );
    }

    public String[] inputFormPutusan(Scanner scanner) {

        String[] data = new String[12];

        System.out.println();
        System.out.println("=========== INPUT PUTUSAN ===========");

        data[0] = InputHandler.validasiString(
                "Nomor Perkara           : ",
                scanner);

        data[1] = InputHandler.validasiString(
                "Pengadilan              : ",
                scanner);

        data[2] = InputHandler.validasiString(
                "Tanggal Putusan         : ",
                scanner);

        data[3] = InputHandler.validasiString(
                "Nama Terdakwa           : ",
                scanner);

        data[4] = String.valueOf(
                InputHandler.validasiIntPositif(
                        "Umur Terdakwa          : ",
                        scanner));

        data[5] = InputHandler.validasiString(
                "Jenis Narkotika         : ",
                scanner);

        data[6] = String.valueOf(
                InputHandler.validasiDoublePositif(
                        "Berat Barang Bukti     : ",
                        scanner));

        data[7] = InputHandler.validasiString(
                "Pasal Dilanggar         : ",
                scanner);

        data[8] = InputHandler.validasiString(
                "Peran Terdakwa          : ",
                scanner);

        data[9] = String.valueOf(
                InputHandler.validasiIntPositif(
                        "Vonis (bulan)          : ",
                        scanner));

        data[10] = String.valueOf(
                InputHandler.validasiDoublePositif(
                        "Vonis Denda            : ",
                        scanner));

        data[11] = InputHandler.validasiString(
                "Nama Hakim             : ",
                scanner);

        return data;
    }

    public void tampilkanDaftarPutusan(ArrayList<Putusan> daftar) {

        if (daftar == null || daftar.isEmpty()) {

            System.out.println();
            System.out.println("Tidak ada data ditemukan.");
            return;
        }

        System.out.println();

        System.out.println("==============================================================================================================");

        System.out.printf(
                "%-4s %-20s %-20s %-15s %-20s %-10s%n",
                "No",
                "Nomor",
                "Terdakwa",
                "Narkotika",
                "Pengadilan",
                "Vonis"
        );

        System.out.println("==============================================================================================================");

        int no = 1;

        for (Putusan p : daftar) {

            System.out.printf(
                    "%-4d %-20s %-20s %-15s %-20s %-10s%n",
                    no++,
                    p.getNomorPerkara(),
                    p.getNamaTerdakwa(),
                    p.getJenisNarkotika(),
                    p.getPengadilan(),
                    p.getVonisHukuman() + " bln"
            );
        }

        System.out.println("==============================================================================================================");

        System.out.println("Jumlah Data : " + daftar.size());
    }

    public void tampilkanDetail(Putusan putusan) {

        if (putusan == null) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        putusan.tampilkan(true);
    }

    public void tampilkanStatistik(
            StatistikPutusan statistik,
            int totalObjek) {

        System.out.println();
        System.out.println("================================================");
        System.out.println("               STATISTIK PUTUSAN");
        System.out.println("================================================");

        System.out.println("Total Putusan               : "
                + statistik.getTotalPutusan());

        System.out.printf(
                "Rata-rata Vonis            : %.2f bulan%n",
                statistik.getRataRataVonis());

        System.out.printf(
                "Rata-rata Denda            : Rp %,.2f%n",
                statistik.getRataRataDenda());

        System.out.println(
                "Jenis Narkotika Terbanyak  : "
                        + statistik.getJenisNarkotikaTerbanyak());

        System.out.println(
                "Total Objek Dibuat         : "
                        + totalObjek);

        System.out.println();
        System.out.println("Distribusi Peran:");

        for (String s : statistik.getDistribusiPeran()) {

            System.out.println("• " + s);
        }

        System.out.println("================================================");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println();
        System.out.println(pesan);
    }
}