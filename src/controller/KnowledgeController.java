package controller;

import model.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Controller utama yang mengorkestrasi interaksi antara View dan Model
 * dalam arsitektur MVC (Model-View-Controller).
 *
 * <p>Menerima input dari View, memvalidasi data,
 * lalu mendelegasikan operasi ke {@link KnowledgeRepository} (Model).</p>
 *
 * <p>Tidak ada logika bisnis di View — semua diproses di sini.</p>
 *
 * @author Backend Developer / Controller Engineer
 * @version 2.0
 */
public class KnowledgeController {

    /** Repository sebagai sumber data utama (Model layer) */
    private KnowledgeRepository repository;

    /**
     * Konstruktor default yang menginisialisasi repository
     * dan mengisi data dummy secara otomatis.
     */
    public KnowledgeController() {
        repository = new KnowledgeRepository();
        generateDummyData();
    }

    /**
     * Menambahkan putusan baru ke dalam repository.
     *
     * <p>Melakukan validasi duplikasi nomor perkara sebelum menyimpan.
     * Jika nomor perkara sudah ada, data tidak akan disimpan.</p>
     *
     * @param data array String berisi data putusan dengan urutan:
     *             [0] nomorPerkara, [1] pengadilan, [2] tanggal,
     *             [3] nama, [4] umur, [5] jenis, [6] berat,
     *             [7] pasal, [8] peran, [9] vonis, [10] denda, [11] hakim
     * @return {@code true} jika berhasil disimpan, {@code false} jika duplikat atau error
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
     * Mencari satu putusan berdasarkan nomor perkara (exact match).
     *
     * @param nomor nomor perkara yang dicari
     * @return objek {@link Putusan} jika ditemukan, atau {@code null} jika tidak ada
     */
    public Putusan cariByNomor(String nomor) {
        return repository.cariByNomor(nomor);
    }

    /**
     * Mencari daftar putusan berdasarkan nama terdakwa (partial match).
     *
     * @param nama keyword nama terdakwa yang dicari
     * @return {@link ArrayList} berisi putusan yang cocok
     */
    public ArrayList<Putusan> cariByNama(String nama) {
        return repository.cariByNama(nama);
    }

    /**
     * Memfilter putusan berdasarkan jenis narkotika.
     *
     * @param jenis jenis narkotika yang difilter (contoh: "Sabu", "Ganja")
     * @return {@link ArrayList} berisi putusan yang sesuai jenis narkotika
     */
    public ArrayList<Putusan> filterByJenis(String jenis) {
        return repository.filterByJenis(jenis);
    }

    /**
     * Memfilter putusan berdasarkan nama pengadilan.
     *
     * @param pengadilan nama pengadilan yang difilter
     * @return {@link ArrayList} berisi putusan dari pengadilan tersebut
     */
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        return repository.filterByPengadilan(pengadilan);
    }

    /**
     * Memfilter putusan berdasarkan rentang lama vonis hukuman.
     *
     * @param min batas bawah vonis dalam bulan (inklusif)
     * @param max batas atas vonis dalam bulan (inklusif)
     * @return {@link ArrayList} berisi putusan dalam rentang vonis tersebut
     */
    public ArrayList<Putusan> filterByRentangVonis(int min, int max) {
        return repository.filterByRentangVonis(min, max);
    }

    /**
     * Menghapus putusan dari repository berdasarkan nomor perkara.
     *
     * @param nomor nomor perkara yang akan dihapus
     * @return {@code true} jika berhasil dihapus, {@code false} jika tidak ditemukan
     */
    public boolean hapusPutusan(String nomor) {
        return repository.hapus(nomor);
    }

    /**
     * Mengambil seluruh data putusan yang tersimpan di repository.
     *
     * @return {@link ArrayList} berisi semua objek {@link Putusan}
     */
    public ArrayList<Putusan> getSemuaPutusan() {
        return repository.getDaftarSemua();
    }

    /**
     * Membuat dan mengembalikan objek statistik dari seluruh data putusan.
     *
     * @return objek {@link StatistikPutusan} hasil kalkulasi data terkini
     */
    public StatistikPutusan getStatistik() {
        return new StatistikPutusan(repository.getDaftarSemua());
    }

    /**
     * Mengembalikan jumlah total data putusan yang tersimpan.
     *
     * @return jumlah total data di repository
     */
    public int getTotalData() {
        return repository.getTotalData();
    }

    /**
     * Mengembalikan total objek Putusan yang pernah diinstansiasi
     * selama program berjalan (menggunakan static field {@code jumlahDibuat}).
     *
     * @return total objek Putusan yang pernah dibuat
     */
    public int getTotalObjekDibuat() {
        return Putusan.getJumlahDibuat();
    }

    /**
     * Mengisi repository dengan 50 data dummy secara otomatis saat program pertama kali dijalankan.
     *
     * <p>Data di-generate secara acak menggunakan {@link Random} dengan kombinasi
     * nama terdakwa, jenis narkotika, pengadilan, dan peran yang sudah ditentukan.</p>
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