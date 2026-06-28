Knowledge Management System (KMS) — Putusan Pengadilan Narkotika

Aplikasi Java berbasis arsitektur MVC (Model-View-Controller) untuk mengelola data putusan pengadilan pidana narkotika, dengan penyimpanan data permanen di MySQL.

> Tugas Besar Mata Kuliah Pemrograman Berorientasi Object (PBO) — Semester Genap 2025/2026

---

Deskripsi Proyek

Aplikasi ini memungkinkan pengguna untuk mengelola, mencari, memfilter, dan menganalisis data putusan pengadilan narkotika dari berbagai Pengadilan Negeri di Indonesia. Dataset disimpan permanen di database MySQL (bukan sekadar di memory), sehingga data tidak hilang saat program ditutup.

Dibangun dengan menerapkan prinsip-prinsip OOP murni: Encapsulation, Inheritance, Polymorphism (Overloading & Overriding), Interface, Static Member dan Exception Handling.

---

Fitur

|  No |         Fitur          |                                        Deskripsi                                          |
|-----|------------------------|-------------------------------------------------------------------------------------------|
|  1  | Tambah Putusan         | Input data putusan baru dengan validasi lengkap, tersimpan permanen ke MySQL              |
|  2  | Tampilkan Semua        | Menampilkan seluruh data putusan dalam format tabel rapi                                  | 
|  3  | Cari by Nomor Perkara  | Pencarian exact match berdasarkan nomor perkara                                           |
|  4  | Cari by Nama Terdakwa  | Pencarian partial match (mengandung kata kunci)                                           |
|  5  | Filter Jenis Narkotika | Filter data berdasarkan jenis narkotika                                                   |
|  6  | Filter Pengadilan      | Filter data berdasarkan nama Pengadilan Negeri                                            |
|  7  | Filter Rentang Vonis   | Filter berdasarkan rentang lama hukuman (bulan)                                           |
|  8  | Statistik Ringkasan    | Total data, rata-rata vonis & denda, jenis narkotika terbanyak, distribusi peran terdakwa |
|  9  | Hapus Putusan          | Menghapus data permanen dari database (dengan konfirmasi)                                 |

---

Arsitektur MVC

kms_java/
├── schema.sql ?                        ← Script SQL: buat database & tabel + 50 data dummy
├── README.md
├── .gitignore
└── src/
    ├── app/
    │   └── Main.java                   ← Entry point, cek koneksi DB, loop menu
    ├── controller/
    │   └── KnowledgeController.java    ← Mediator antara View dan Model
    ├── model/
    │   ├── DataEntity.java             ← Abstract parent class (inheritance base)
    │   ├── Searchable.java             ← Interface kontrak pencarian/filter
    │   ├── Putusan.java                ← Entity utama (extends DataEntity)
    │   ├── KnowledgeRepository.java    ← Implements Searchable, delegasi ke DAO
    │   ├── PutusanDAO.java  ?          ← Seluruh query SQL ke MySQL
    │   └── StatistikPutusan.java       ← Kalkulasi statistik dari data
    ├── view/
    │   └── ConsoleView.java            ← Tampilan menu, tabel, form input
    └── util/
        ├── InputHandler.java           ← Validasi input + exception handling
        └── DatabaseConnection.java  ?  ← Koneksi JDBC singleton ke MySQL

---

 Alur Data MVC

[View] ConsoleView
   ↕ (input mentah / output terformat)
[Controller] KnowledgeController
   ↕ (objek Putusan / hasil query)
[Model] KnowledgeRepository → PutusanDAO
   ↕ (SQL query)
[Database] MySQL — tabel "putusan"

---

Konsep OOP yang Diimplementasikan

|       Konsep          |                             Implementasi                                       |
|-----------------------|--------------------------------------------------------------------------------|
| Encapsulation         | Semua field `Putusan` bersifat `private`, diakses lewat getter/setter          |
| Inheritance           | `Putusan extends DataEntity`                                                   |
| Interface             | `KnowledgeRepository implements Searchable`                                    |
| Method Overloading    | `tampilkan()` dan `tampilkan(boolean detail)`                                  |
| Method Overriding     | `toString()` dan `displayInfo()` pada `Putusan`                                |
| Static Field & Method | `jumlahDibuat` (field) dan `getJumlahDibuat()` (method)                        |
| ArrayList             | Digunakan untuk menampung hasil query (`ArrayList<Putusan>`)                   |
| Exception Handling    | `try-catch` di seluruh `InputHandler`, `PutusanDAO`, dan `KnowledgeController` |
| JDBC / Database       | Koneksi MySQL via `DatabaseConnection` + query lewat `PutusanDAO`              |

---

Cara Menjalankan

Prasyarat
- Java JDK 11 atau lebih baru
- IntelliJ IDEA (atau Eclipse/NetBeans)
- MySQL Server (XAMPP/MySQL Installer/Laragon)
- Driver MySQL Connector/J (`.jar`)

Langkah Setup

1. Clone repository
bash
git clone https://github.com/naufalimampamungkas/pbo-km-narkotika--pinoa-.git
cd pbo-km-narkotika--pinoa-


2. Setup Database MySQL
- Jalankan MySQL Server
- Jalankan file `schema.sql` (lewat phpMyAdmin / MySQL Workbench / terminal):
bash
mysql -u root -p < schema.sql

Ini akan otomatis membuat database `kms_narkotika`, tabel `putusan`, dan mengisi 50 data dummy. 

3. Buka project di IntelliJ IDEA
- `File → Open` → pilih folder hasil clone
- Klik kanan folder `src` → Mark Directory as → Sources Root
- `File → Project Structure` → pilih **JDK 11+

4. Tambahkan Driver MySQL Connector/J
- Download dari https://dev.mysql.com/downloads/connector/j/
- `File → Project Structure → Modules → Dependencies → + → JARs or Directories` → pilih file `.jar`

5. Sesuaikan Konfigurasi Database
Buka `src/util/DatabaseConnection.java`, sesuaikan dengan instalasi MySQL Anda:
java
private static final String USER = "root";
private static final String PASSWORD = "";

6. Jalankan
Buka `src/app/Main.java` → klik Run ▶

---

Tampilan Aplikasi

Menu Utama
============================================================
        KNOWLEDGE MANAGEMENT SYSTEM PUTUSAN NARKOTIKA
============================================================
Total Data Saat Ini   : 50
Total Objek Dibuat     : 50
============================================================

============== MENU UTAMA ==============
1. Tambah Putusan
2. Tampilkan Semua
3. Cari Berdasarkan Nomor
4. Cari Berdasarkan Nama
5. Filter Jenis Narkotika
6. Filter Pengadilan
7. Filter Rentang Vonis
8. Statistik
9. Hapus Putusan
0. Keluar
=========================================
Pilih Menu :

? > Screenshot lengkap (tabel data, detail putusan, statistik) — lihat folder `/screenshots` (tambahkan sesuai hasil running) ?

---

? Struktur Branch Git ?

main                → branch produksi (kode final)
develop             → branch integrasi sebelum ke main
feature/model       → pengembangan layer Model
feature/view        → pengembangan layer View
feature/controller  → pengembangan layer Controller

---

Anggota Kelompok

| No | 		Nama 	       | 	NIM	   | Kelas | 		   Peran 		     |       Branch         |
|----|-------------------------|-------------------|-------|-----------------------------------------|----------------------|
| 1  | [Naufal Imam Pamungkas] | [202510370110028] |  [B]  | Backend Developer / Controller Engineer | `feature/controller` |
| 2  | [Dewangga]	       | [202510370110031] |  [B]  | Knowledge/DB Engineer                   | `feature/model`      |
| 3  | [Rafi Nasywa Pratama]   | [202510370110024] |  [B]  | GUI Designer / View Developer           | `feature/view`       |


? Video Demo ?

? > Link video demo (YouTube) : [ISI LINK VIDEO] ?

Video mencakup:
- Penjelasan arsitektur MVC dan pembagian tugas tim
- Demo alur Knowledge Management (tambah, validasi, exception handling)
- Demo pencarian & filter data
- Code walkthrough layer Model (encapsulation, constructor, overloading, static)
- Demo statistik
- Demo tampilan & CRUD akhir
- Bukti aktivitas Git (commit history, branch)

---

Lisensi

Proyek akademik — dibuat untuk keperluan Tugas Besar mata kuliah PBO, tidak untuk distribusi komersial.
