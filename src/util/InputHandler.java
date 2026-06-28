package util;

import java.util.Scanner;

/**
 * Utility class untuk menangani input dan validasi.
 * Semua input angka menggunakan try-catch agar
 * program tidak crash ketika user salah memasukkan data.
 */
public class InputHandler {

    /**
     * Validasi String tidak boleh kosong.
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
     * Validasi Integer.
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
     * Validasi Integer positif.
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
     * Validasi Double.
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
     * Validasi Double Positif.
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
     * Validasi pilihan menu.
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
     * Validasi Integer tidak boleh negatif.
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
