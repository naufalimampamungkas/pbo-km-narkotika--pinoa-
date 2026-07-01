package util;

import java.util.Scanner;

/**
 * Utility class untuk menangani input dan validasi data dari pengguna.
 *
 * <p>Semua method bersifat {@code static} sehingga dapat dipanggil
 * tanpa membuat objek {@code InputHandler} terlebih dahulu.</p>
 *
 * <p>Setiap method menggunakan loop {@code while(true)} dengan
 * {@code try-catch} agar program tidak crash ketika pengguna
 * memasukkan data yang tidak valid — program akan terus meminta
 * input ulang sampai data yang benar dimasukkan.</p>
 *
 * @author Backend Developer / Controller Engineer
 * @version 2.0
 */
public class InputHandler {

    /**
     * Meminta input String dari pengguna dan memastikan tidak kosong.
     *
     * <p>Jika pengguna menekan Enter tanpa mengetik apapun,
     * program akan menampilkan pesan error dan meminta input ulang.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return String yang tidak kosong hasil input pengguna
     */
    public static String validasiString(
            String prompt,
            Scanner scanner) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input tidak boleh kosong!"
            );
        }
    }

    /**
     * Meminta input bilangan bulat ({@code int}) dari pengguna.
     *
     * <p>Menggunakan {@code try-catch NumberFormatException} untuk
     * menangani input yang bukan angka bulat.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code int} hasil input pengguna
     */
    public static int validasiInt(
            String prompt,
            Scanner scanner) {

        while (true) {

            try {

                System.out.print(prompt);

                int angka =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                return angka;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka bulat!"
                );
            }
        }
    }

    /**
     * meminta input bilangan bulat positif (lebih dari 0) dari pengguna.
     *
     * <p>Memanggil {@link #validasiInt(String, Scanner)} terlebih dahulu,
     * kemudian memeriksa apakah nilainya lebih besar dari 0.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code int} positif (lebih dari 0) hasil input pengguna
     */
    public static int validasiIntPositif(
            String prompt,
            Scanner scanner) {

        while (true) {

            int nilai =
                    validasiInt(
                            prompt,
                            scanner
                    );

            if (nilai > 0) {
                return nilai;
            }

            System.out.println(
                    "Nilai harus lebih besar dari 0!"
            );
        }
    }

    /**
     * Meminta input bilangan desimal ({@code double}) dari pengguna.
     *
     * <p>Menggunakan {@code try-catch NumberFormatException} untuk
     * menangani input yang bukan angka desimal.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code double} hasil input pengguna
     */
    public static double validasiDouble(
            String prompt,
            Scanner scanner) {

        while (true) {

            try {

                System.out.print(prompt);

                double nilai =
                        Double.parseDouble(
                                scanner.nextLine()
                        );

                return nilai;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka!"
                );
            }
        }
    }

    /**
     * Meminta input bilangan desimal positif (lebih dari 0) dari pengguna.
     *
     * <p>Memanggil {@link #validasiDouble(String, Scanner)} terlebih dahulu,
     * kemudian memeriksa apakah nilainya lebih besar dari 0.
     * Digunakan untuk input seperti berat barang bukti.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code double} positif (lebih dari 0) hasil input pengguna
     */
    public static double validasiDoublePositif(
            String prompt,
            Scanner scanner) {

        while (true) {

            double nilai =
                    validasiDouble(
                            prompt,
                            scanner
                    );

            if (nilai > 0) {
                return nilai;
            }

            System.out.println(
                    "Nilai harus lebih besar dari 0!"
            );
        }
    }

    /**
     * Meminta input pilihan menu dari pengguna dalam rentang tertentu.
     *
     * <p>Memanggil {@link #validasiInt(String, Scanner)} terlebih dahulu,
     * kemudian memastikan nilainya berada di antara {@code min} dan {@code max}
     * (inklusif). Digunakan untuk validasi pilihan menu utama.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param min     batas bawah pilihan yang valid (inklusif)
     * @param max     batas atas pilihan yang valid (inklusif)
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code int} dalam rentang [{@code min}, {@code max}]
     */
    public static int validasiPilihan(
            String prompt,
            int min,
            int max,
            Scanner scanner) {

        while (true) {

            int pilihan =
                    validasiInt(
                            prompt,
                            scanner
                    );

            if (pilihan >= min &&
                    pilihan <= max) {

                return pilihan;
            }

            System.out.println(
                    "Pilihan harus antara "
                            + min +
                            " sampai "
                            + max
            );
        }
    }

    /**
     * Meminta input bilangan bulat non-negatif (lebih dari atau sama dengan 0).
     *
     * <p>Berbeda dengan {@link #validasiIntPositif(String, Scanner)},
     * method ini mengizinkan nilai 0. Digunakan untuk input seperti
     * vonis hukuman yang bisa bernilai 0.</p>
     *
     * @param prompt  teks yang ditampilkan sebagai petunjuk input
     * @param scanner objek {@link Scanner} yang digunakan untuk membaca input
     * @return nilai {@code int} non-negatif (lebih dari atau sama dengan 0)
     */
    public static int validasiIntNonNegatif(
            String prompt,
            Scanner scanner) {

        while (true) {

            int nilai =
                    validasiInt(
                            prompt,
                            scanner
                    );

            if (nilai >= 0) {
                return nilai;
            }

            System.out.println(
                    "Nilai tidak boleh negatif!"
            );
        }
    }
}