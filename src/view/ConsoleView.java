package view;

import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * View layer pada arsitektur MVC yang menangani seluruh tampilan
 * dan pengumpulan input dari pengguna melalui konsol.
 *
 * <p>Class ini TIDAK mengandung logika bisnis apapun — hanya
 * menampilkan data yang diterima dari Controller dan mengumpulkan
 * input mentah untuk diteruskan ke Controller.</p>
 *
 * @author GUI Designer / View Developer
 * @version 1.0
 */
public class ConsoleView {

    /**
     * Menampilkan dashboard ringkasan jumlah data di bagian atas menu.
     *
     * <p>Ditampilkan setiap kali pengguna kembali ke menu utama
     * agar pengguna selalu tahu kondisi terkini jumlah data.</p>
     *
     * @param totalData   jumlah total data putusan yang tersimpan di repository
     * @param totalObjek  jumlah total objek {@link Putusan} yang pernah diinstansiasi
     */
    public void tampilkanDashboard(int totalData, int totalObjek) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("      KNOWLEDGE MANAGEMENT SYSTEM PUTUSAN NARKOTIKA");
        System.out.println("==============================================================");
        System.out.println("Total Data Saat Ini     : " + totalData);
        System.out.println("Total Objek Dibuat      : " + totalObjek);
        System.out.println("==============================================================");
    }

    /**
     * Menampilkan menu utama aplikasi dan meminta pengguna memilih opsi.
     *
     * <p>Menggunakan {@link InputHandler#validasiPilihan} untuk memastikan
     * input yang diterima adalah angka dalam rentang 1–10.</p>
     *
     * @param scanner objek {@link Scanner} untuk membaca input pengguna
     * @return nomor menu yang dipilih pengguna (1–10)
     */
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

    /**
     * Menampilkan form input dan mengumpulkan data putusan baru dari pengguna.
     *
     * <p>Setiap field divalidasi menggunakan method yang sesuai dari
     * {@link InputHandler} sebelum disimpan ke array. Data mentah
     * dikembalikan ke Controller untuk diproses lebih lanjut.</p>
     *
     * @param scanner objek {@link Scanner} untuk membaca input pengguna
     * @return array {@code String[12]} berisi data putusan dengan urutan:
     *         [0] nomorPerkara, [1] pengadilan, [2] tanggal,
     *         [3] nama, [4] umur, [5] jenis, [6] berat,
     *         [7] pasal, [8] peran, [9] vonis, [10] denda, [11] hakim
     */
    public String[] inputFormPutusan(Scanner scanner) {

        String[] data = new String[12];

        System.out.println();
        System.out.println("=========== INPUT PUTUSAN ===========");

        data[0] = InputHandler.validasiString(
                "Nomor Perkara           : ", scanner);

        data[1] = InputHandler.validasiString(
                "Pengadilan              : ", scanner);

        data[2] = InputHandler.validasiString(
                "Tanggal Putusan         : ", scanner);

        data[3] = InputHandler.validasiString(
                "Nama Terdakwa           : ", scanner);

        data[4] = String.valueOf(
                InputHandler.validasiIntPositif(
                        "Umur Terdakwa          : ", scanner));

        data[5] = InputHandler.validasiString(
                "Jenis Narkotika         : ", scanner);

        data[6] = String.valueOf(
                InputHandler.validasiDoublePositif(
                        "Berat Barang Bukti     : ", scanner));

        data[7] = InputHandler.validasiString(
                "Pasal Dilanggar         : ", scanner);

        data[8] = InputHandler.validasiString(
                "Peran Terdakwa          : ", scanner);

        data[9] = String.valueOf(
                InputHandler.validasiIntPositif(
                        "Vonis (bulan)          : ", scanner));

        data[10] = String.valueOf(
                InputHandler.validasiDoublePositif(
                        "Vonis Denda            : ", scanner));

        data[11] = InputHandler.validasiString(
                "Nama Hakim             : ", scanner);

        return data;
    }

    /**
     * Menampilkan daftar putusan dalam format tabel yang rapi menggunakan {@code printf}.
     *
     * <p>Jika daftar kosong atau {@code null}, ditampilkan pesan
     * "Tidak ada data ditemukan." sebagai gantinya.</p>
     *
     * @param daftar {@link ArrayList} berisi objek {@link Putusan} yang akan ditampilkan
     */
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
                "No", "Nomor", "Terdakwa", "Narkotika", "Pengadilan", "Vonis"
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

    /**
     * Menampilkan detail lengkap satu putusan dengan memanggil
     * {@link Putusan#tampilkan(boolean)}.
     *
     * <p>Jika objek putusan {@code null}, ditampilkan pesan
     * "Data tidak ditemukan."</p>
     *
     * @param putusan objek {@link Putusan} yang akan ditampilkan detailnya,
     *                atau {@code null} jika data tidak ditemukan
     */
    public void tampilkanDetail(Putusan putusan) {

        if (putusan == null) {
            System.out.println("Data tidak ditemukan.");
            return;
        }

        putusan.tampilkan(true);
    }

    /**
     * Menampilkan laporan statistik ringkasan dari seluruh data putusan.
     *
     * <p>Menampilkan total putusan, rata-rata vonis, rata-rata denda,
     * jenis narkotika terbanyak, total objek dibuat, dan distribusi peran
     * terdakwa dalam format yang mudah dibaca.</p>
     *
     * @param statistik  objek {@link StatistikPutusan} hasil kalkulasi
     * @param totalObjek jumlah total objek {@link Putusan} yang pernah dibuat
     *                   (dari static field {@code jumlahDibuat})
     */
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

    /**
     * Menampilkan pesan singkat kepada pengguna, diawali baris kosong
     * agar tampilan lebih rapi dan mudah dibaca.
     *
     * @param pesan Teks pesan yang akan ditampilkan
     */
    public void tampilkanPesan(String pesan) {
        System.out.println();
        System.out.println(pesan);
    }
}