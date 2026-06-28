package controller;

import model.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Controller yang menghubungkan View dengan Model.
 */
public class KnowledgeController {

    private KnowledgeRepository repository;

    public KnowledgeController() {
        repository = new KnowledgeRepository();
        generateDummyData();
    }

    /**
     * Tambah putusan baru.
     */
    public boolean tambahPutusan(String[] data) {

        try {

            // Validasi nomor perkara tidak boleh duplikat
            if (repository.cariByNomor(data[0]) != null) {
                return false;
            }

            Putusan putusan = new Putusan(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    data[5],
                    Double.parseDouble(data[6]),
                    data[7],
                    data[8],
                    Integer.parseInt(data[9]),
                    Double.parseDouble(data[10]),
                    data[11]
            );

            repository.simpan(putusan);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Cari berdasarkan nomor perkara.
     */
    public Putusan cariByNomor(String nomor) {
        return repository.cariByNomor(nomor);
    }

    /**
     * Cari berdasarkan nama terdakwa.
     */
    public ArrayList<Putusan> cariByNama(String nama) {
        return repository.cariByNama(nama);
    }

    /**
     * Filter berdasarkan jenis narkotika.
     */
    public ArrayList<Putusan> filterByJenis(String jenis) {
        return repository.filterByJenis(jenis);
    }

    /**
     * Filter berdasarkan pengadilan.
     */
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        return repository.filterByPengadilan(pengadilan);
    }

    /**
     * Filter berdasarkan rentang vonis.
     */
    public ArrayList<Putusan> filterByRentangVonis(
            int min,
            int max) {

        return repository.filterByRentangVonis(
                min,
                max
        );
    }

    /**
     * Hapus data berdasarkan nomor perkara.
     */
    public boolean hapusPutusan(String nomor) {
        return repository.hapus(nomor);
    }

    /**
     * Ambil seluruh data putusan.
     */
    public ArrayList<Putusan> getSemuaPutusan() {
        return repository.getDaftarSemua();
    }

    /**
     * Ambil statistik putusan.
     */
    public StatistikPutusan getStatistik() {

        return new StatistikPutusan(
                repository.getDaftarSemua()
        );
    }

    /**
     * Total data yang tersimpan.
     */
    public int getTotalData() {
        return repository.getTotalData();
    }

    /**
     * Total objek Putusan yang pernah dibuat.
     */
    public int getTotalObjekDibuat() {
        return Putusan.getJumlahDibuat();
    }

    /**
     * Generate minimal 50 data dummy.
     */
    private void generateDummyData() {

        String[] pengadilan = {
                "PN Surabaya",
                "PN Jakarta Selatan",
                "PN Bandung",
                "PN Malang",
                "PN Semarang"
        };

        String[] narkotika = {
                "Sabu",
                "Ganja",
                "Ekstasi",
                "Heroin"
        };

        String[] peran = {
                "Bandar",
                "Kurir",
                "Pengguna",
                "Penyimpan"
        };

        String[] hakim = {
                "Hakim A",
                "Hakim B",
                "Hakim C",
                "Hakim D"
        };

        String[] namaTerdakwa = {
                "Budi Santoso",
                "Andi Saputra",
                "Rizki Ramadhan",
                "Dimas Pratama",
                "Ahmad Fauzi",
                "Agus Setiawan",
                "Rahmat Hidayat",
                "Fajar Nugroho",
                "Rian Kurniawan",
                "Yusuf Maulana"
        };

        String[] pasal = {
                "Pasal 111",
                "Pasal 112",
                "Pasal 114",
                "Pasal 127"
        };

        Random random = new Random();

        for (int i = 1; i <= 50; i++) {

            Putusan putusan = new Putusan(
                    "100" + i + "/Pid.Sus/2025",
                    pengadilan[random.nextInt(pengadilan.length)],
                    "2025-06-" + ((i % 28) + 1),
                    namaTerdakwa[random.nextInt(namaTerdakwa.length)],
                    18 + random.nextInt(40),
                    narkotika[random.nextInt(narkotika.length)],
                    0.5 + random.nextDouble() * 100,
                    pasal[random.nextInt(pasal.length)],
                    peran[random.nextInt(peran.length)],
                    6 + random.nextInt(120),
                    1000000 + random.nextInt(50000000),
                    hakim[random.nextInt(hakim.length)]
            );

            repository.simpan(putusan);
        }
    }
}