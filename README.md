# IQ Puzzle Pro Solver 🧩

Welcome!

Program ini merupakan IQ Puzzle Pro solver yang **mencari satu solusi dari permainan** ini menggunakan algoritma **Brute Force**. IQ Puzzler Pro adalah permainan papan dengan tujuan pemain harus dapat mengisi seluruh papan dengan piece (blok/puzzle) yang telah tersedia.

Komponen penting dari permainan IQ Puzzler Pro terdiri dari: <br>
1. Board (Papan) – Board merupakan komponen utama yang menjadi tujuan permainan
dimana pemain harus mampu mengisi seluruh area papan menggunakan blok-blok yang
telah disediakan. <br>
2. Blok/Piece – Blok adalah komponen yang digunakan pemain untuk mengisi papan
kosong hingga terisi penuh. Setiap blok memiliki bentuk yang unik dan semua blok harus
digunakan untuk menyelesaikan puzzle. <br>

Permainan dimulai dengan papan yang kosong. Pemain dapat meletakkan blok puzzle
sedemikian sehingga tidak ada blok yang bertumpang tindih. Setiap
blok puzzle dapat dirotasikan maupun dicerminkan. Puzzle dinyatakan selesai jika dan hanya
jika papan terisi penuh dan seluruh blok puzzle berhasil diletakkan.

![Game IQ Puzzle Pro](https://d32bxxnq6qs937.cloudfront.net/sites/default/files/smartgames-product-banner_IQ-Puzzler-Pro.jpg)

---

## Penjelasan Program 💻
Program akan menerima input dalam file .txt. Jika terdapat solusi, maka solusi akan ditampilkan dan dapat disimpan pada suatu file .txt. Format input pada file adalah sebagai berikut: <br>
```
N M P
S
puzzle_1_shape
puzzle_2_shape
…
puzzle_P_shape
```
dengan N baris papan, M kolom papan, P jumlah blok, S konfigurasi. Konfigurasi yang tersedia pada program ini adalah konfigurasi DEFAULT dan CUSTOM.

### Catatan
1. File input disimpan pada `test/input`
2. File output disimpan pada `test/output`

## Instalasi dan Penggunaan 👩🏻‍💻
1. Install [Java](https://www.oracle.com/java/technologies/downloads/?er=221886) pada komputer Anda.
2. Pastikan Java sudah terinstall. <br>
   ```
   java -version
    ```
3. Jalankan perintah di bawah ini. <br>
   ```
   java -cp bin Main
   ```
4. Jika dilakukan perubahan pada kode program, compile ulang dan jalankan run.bat. <br>
   ```
   .\run.bat
   ```
---
Program ini dibuat oleh **Najwa Kahani Fatima - 13523043** untuk memenuhi penugasan Tugas Kecil 1 mata kuliah IF2211 Strategi Algoritma.
