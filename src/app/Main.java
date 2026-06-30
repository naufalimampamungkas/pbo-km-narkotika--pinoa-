package app;

import controller.KnowledgeController;
import model.Putusan;
import view.ConsoleView;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point aplikasi Knowledge Management System (KMS) Putusan
 * Pengadilan Narkotika.
 *
 * <p>Class ini bertugas sebagai titik masuk (App layer) pada arsitektur
 * MVC. Tanggung jawabnya seminimal mungkin: menginisialisasi komponen
 * {@link KnowledgeController} (Controller) dan {@link ConsoleView} (View),
 * lalu menjalankan loop menu utama yang menerjemahkan pilihan pengguna
 * menjadi pemanggilan method pada Controller.</p>
 *
 * <p><b>Catatan arsitektur:</b> Main TIDAK boleh berisi logika bisnis
 * (validasi data, perhitungan statistik, query data, dll). Seluruh
 * logika tersebut didelegasikan ke {@link KnowledgeController} dan
 * layer Model di baliknya, sesuai aturan pemisahan tanggung jawab MVC.</p>
 *
 * @author Backend Developer / Controller Engineer
 * @version 1.0
 */
public class Main {

    /**
     * Method utama yang dijalankan saat aplikasi dimulai.
     *
     * <p>Alur kerja:</p>
     * <ol>
     *   <li>Inisialisasi {@link Scanner} untuk membaca input pengguna.</li>
     *   <li>Inisialisasi {@link KnowledgeController} dan {@link ConsoleView}.</li>
     *   <li>Menjalankan loop menu hingga pengguna memilih keluar (menu 10).</li>
     *   <li>Setiap pilihan menu diteruskan ke method Controller yang sesuai,
     *       lalu hasilnya ditampilkan melalui View.</li>
     * </ol>
     *
     * @param args argumen command-line (tidak digunakan pada aplikasi ini)
     */
    public static void main(String[] args) {

        // Scanner tunggal yang dipakai bersama untuk seluruh input pengguna
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi Controller: jembatan antara Model dan View
        KnowledgeController controller =
                new KnowledgeController();

        // Inisialisasi View: menampilkan menu dan menerima input mentah
        ConsoleView view =
                new ConsoleView();

        boolean running = true;

        // ====== Loop menu utama aplikasi ======
        while (running) {

            // Tampilkan ringkasan jumlah data setiap kali kembali ke menu
            view.tampilkanDashboard(
                    controller.getTotalData(),
                    controller.getTotalObjekDibuat()
            );

            int pilihan =
                    view.tampilkanMenu(scanner);

            switch (pilihan) {

                // --- Menu 1: Tambah data putusan baru ---
                case 1:

                    String[] data =
                            view.inputFormPutusan(scanner);

                    if (controller.tambahPutusan(data)) {

                        view.tampilkanPesan(
                                "Data berhasil ditambahkan."
                        );

                    } else {

                        view.tampilkanPesan(
                                "Gagal menambahkan data."
                        );
                    }

                    break;

                // --- Menu 2: Tampilkan seluruh data putusan ---
                case 2:

                    view.tampilkanDaftarPutusan(
                            controller.getSemuaPutusan()
                    );

                    break;

                // --- Menu 3: Cari putusan berdasarkan nomor perkara ---
                case 3:

                    System.out.print(
                            "Masukkan Nomor Perkara : "
                    );

                    String nomor =
                            scanner.nextLine();

                    view.tampilkanDetail(
                            controller.cariByNomor(nomor)
                    );

                    break;

                // --- Menu 4: Cari putusan berdasarkan nama terdakwa ---
                case 4:

                    System.out.print(
                            "Masukkan Nama Terdakwa : "
                    );

                    String nama =
                            scanner.nextLine();

                    view.tampilkanDaftarPutusan(
                            controller.cariByNama(nama)
                    );

                    break;

                // --- Menu 5: Filter berdasarkan jenis narkotika ---
                case 5:

                    System.out.print(
                            "Jenis Narkotika : "
                    );

                    String jenis =
                            scanner.nextLine();

                    view.tampilkanDaftarPutusan(
                            controller.filterByJenis(jenis)
                    );

                    break;

                // --- Menu 6: Filter berdasarkan nama pengadilan ---
                case 6:

                    System.out.print(
                            "Nama Pengadilan : "
                    );

                    String pengadilan =
                            scanner.nextLine();

                    view.tampilkanDaftarPutusan(
                            controller.filterByPengadilan(
                                    pengadilan
                            )
                    );

                    break;

                // --- Menu 7: Filter berdasarkan rentang vonis ---
                case 7:

                    int min =
                            InputHandler.validasiIntPositif(
                                    "Vonis Minimum (bulan) : ",
                                    scanner
                            );

                    int max =
                            InputHandler.validasiIntPositif(
                                    "Vonis Maksimum (bulan) : ",
                                    scanner
                            );

                    if (min > max) {

                        view.tampilkanPesan(
                                "Vonis minimum tidak boleh lebih besar dari maksimum."
                        );

                    } else {

                        view.tampilkanDaftarPutusan(
                                controller.filterByRentangVonis(
                                        min,
                                        max
                                )
                        );
                    }

                    break;

                // --- Menu 8: Tampilkan statistik ringkasan ---
                case 8:

                    StatistikPutusan statistik =
                            controller.getStatistik();

                    view.tampilkanStatistik(
                            statistik,
                            controller.getTotalObjekDibuat()
                    );

                    break;

                // --- Menu 9: Hapus data putusan berdasarkan nomor perkara ---
                case 9:

                    System.out.print(
                            "Nomor Perkara yang akan dihapus : "
                    );

                    String nomorHapus =
                            scanner.nextLine();

                    if (controller.hapusPutusan(
                            nomorHapus)) {

                        view.tampilkanPesan(
                                "Data berhasil dihapus."
                        );

                    } else {

                        view.tampilkanPesan(
                                "Data tidak ditemukan."
                        );
                    }

                    break;

                // --- Menu 10: Keluar dari aplikasi ---
                case 10:

                    running = false;

                    view.tampilkanPesan(
                            "Terima kasih telah menggunakan sistem."
                    );

                    break;
            }
        }

        // Tutup Scanner agar resource input tidak bocor
        scanner.close();
    }
}