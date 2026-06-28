package app;

import controller.KnowledgeController;
import model.Putusan;
import view.ConsoleView;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        KnowledgeController controller =
                new KnowledgeController();

        ConsoleView view =
                new ConsoleView();

        boolean running = true;

        while (running) {

            view.tampilkanDashboard(
                    controller.getTotalData(),
                    controller.getTotalObjekDibuat()
            );

            int pilihan =
                    view.tampilkanMenu(scanner);

            switch (pilihan) {

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

                case 2:

                    view.tampilkanDaftarPutusan(
                            controller.getSemuaPutusan()
                    );

                    break;

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

                case 8:

                    StatistikPutusan statistik =
                            controller.getStatistik();

                    view.tampilkanStatistik(
                            statistik,
                            controller.getTotalObjekDibuat()
                    );

                    break;

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

                case 10:

                    running = false;

                    view.tampilkanPesan(
                            "Terima kasih telah menggunakan sistem."
                    );

                    break;
            }
        }

        scanner.close();
    }
}