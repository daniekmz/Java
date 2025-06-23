//	* Nama Lengkap Anda:Muhamad Ramdani
//	* NIM: 202422027
//	* Kelompok: Kelas Sore

package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DaniOperasi {

    public static void DaniTampilkanData() throws IOException {
        FileReader DaniFileInput;
        BufferedReader DaniBufferInput;
        
        try {
            DaniFileInput = new FileReader("database.txt");
            DaniBufferInput = new BufferedReader(DaniFileInput);
        } catch (Exception e) {
            System.err.println("Database Tidak Ditemukan");
            System.err.println("Silahkan Tambah Data Terlebih Dahulu");
            return;
        }

        System.out.println("\n| No |\tTahun |\tPenulis\t\t|\tPenerbit\t|\tJudul Buku");
        System.out.println("================================================================================");

        String DaniData = DaniBufferInput.readLine();
        int DaniNomorData = 0;
        
        while (DaniData != null) {
            DaniNomorData++;
            StringTokenizer DaniToken = new StringTokenizer(DaniData, ",");
            
            DaniToken.nextToken();
            System.out.printf("| %2d", DaniNomorData);
            System.out.printf("|\t%4s\t", DaniToken.nextToken());
            System.out.printf("|\t%-20s", DaniToken.nextToken());
            System.out.printf("|\t%-20s", DaniToken.nextToken());
            System.out.printf("|\t%s", DaniToken.nextToken());
            System.out.print("\n");
            
            DaniData = DaniBufferInput.readLine();
        }

        System.out.println("================================================================================");
        DaniFileInput.close();
        DaniBufferInput.close();
    }

    public static void DaniCariData() throws IOException {
        // Mengecek database ada atau tidak
        try {
            File file = new File("database.txt");
            if (!file.exists()) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Database Tidak Ditemukan");
            System.err.println("Silahkan Tambah Data Terlebih Dahulu");
            return;
        }

        // kita ambil keyword dari user
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("Masukan kata kunci untuk mencari buku: ");
        String cariString = terminalInput.nextLine();
        String[] kataKunci = cariString.split("\\s+");

        // kita cek keyword di database
        DaniUtility.cekBukuDiDatabase(kataKunci, true);
    }

    public static void DaniTambahData() throws IOException {
        FileWriter fileOutput = new FileWriter("database.txt", true);
        
        // mengambil input dari user
        try (BufferedWriter bufferOutput = new BufferedWriter(fileOutput)) {
            // mengambil input dari user
            Scanner terminalInput = new Scanner(System.in);
            String penulis, judul, penerbit, tahun;

            System.out.print("masukan nama penulis: ");
            penulis = terminalInput.nextLine();
            System.out.print("masukan judul buku: ");
            judul = terminalInput.nextLine();
            System.out.print("masukan nama penerbit: ");
            penerbit = terminalInput.nextLine();
            System.out.print("masukan tahun terbit, format=(YYYY): ");
            tahun = DaniUtility.DaniAmbilTahun();
            
            // cek buku di database
            String[] keywords = {tahun + "," + penulis + "," + penerbit + "," + judul};
            System.out.println(Arrays.toString(keywords));
            boolean isExist = DaniUtility.cekBukuDiDatabase(keywords, false);
            
            // menulis buku di database
            if (!isExist) {
                System.out.println(DaniUtility.ambilEntryPerTahun(penulis, tahun));
                long nomorEntry = DaniUtility.ambilEntryPerTahun(penulis, tahun) + 1;

                String punulisTanpaSpasi = penulis.replaceAll("\\s+", "");
                String primaryKey = punulisTanpaSpasi + "_" + tahun + "_" + nomorEntry;

                System.out.println("\nData yang akan anda masukan adalah");
                System.out.println("=====================================");
                System.out.println("primary key\t: " + primaryKey);
                System.out.println("tahun terbit\t: " + tahun);
                System.out.println("penulis\t\t: " + penulis);
                System.out.println("judul\t\t: " + judul);
                System.out.println("penerbit\t: " + penerbit);

                boolean isTambah = DaniUtility.DaniYaAtauTidak("Apakah akan ingin menambah data tersebut?");

                if (isTambah) {
                    bufferOutput.write(primaryKey + "," + tahun + "," + penulis + "," + penerbit + "," + judul);
                    bufferOutput.newLine();
                    bufferOutput.flush();
                }
            } else {
                System.out.println("buku yang anda akan masukan sudah tersedia di database dengan data berikut:");
                DaniUtility.cekBukuDiDatabase(keywords, true);
            }
        }
    }

    public static void DaniUpdateData() throws IOException {
        // kita ambil database original
        File database = new File("database.txt");
        FileReader DaniFileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(DaniFileInput);

        // buat database sementara
        File tempDB = new File("tempDB.txt");
        FileWriter DaniFileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(DaniFileOutput);

        // tampilkan data
        System.out.println("List Buku");
        DaniTampilkanData();

        // ambil user input / pilihan data
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor buku yang akan diupdate: ");
        int updateNum = terminalInput.nextInt();

        // tampilkan data yang ingin diupdate
        String data = bufferedInput.readLine();
        int entryCounts = 0;

        while (data != null) {
            entryCounts++;

            StringTokenizer st = new StringTokenizer(data, ",");

            // tampilkan entryCounts == updateNum
            if (updateNum == entryCounts) {
                System.out.println("\nData yang ingin anda update adalah:");
                System.out.println("=====================================");
                System.out.println("Referensi\t: " + st.nextToken());
                System.out.println("Tahun\t\t: " + st.nextToken());
                System.out.println("Penulis\t\t: " + st.nextToken());
                System.out.println("Penerbit\t: " + st.nextToken());
                System.out.println("Judul\t\t: " + st.nextToken());

                // update data
                // mengambil input dari user
                String[] fieldData = {"tahun", "penulis", "penerbit", "judul"};
                String[] tempData = new String[4];

                st = new StringTokenizer(data, ",");
                String originalData = st.nextToken();

                for (int i = 0; i < fieldData.length; i++) {
                    boolean isUpdate = DaniUtility.DaniYaAtauTidak("apakah anda ingin merubah " + fieldData[i]);
                    originalData = st.nextToken();
                    if (isUpdate) {
                        // user input
                        if (fieldData[i].equalsIgnoreCase("tahun")) {
                            System.out.print("Masukan tahun terbit, format=(YYYY): ");
                            tempData[i] = DaniUtility.DaniAmbilTahun();
                        } else {
                            terminalInput = new Scanner(System.in);
                            System.out.print("\nMasukan " + fieldData[i] + " baru: ");
                            tempData[i] = terminalInput.nextLine();
                        }
                    } else {
                        tempData[i] = originalData;
                    }
                }

                // tampilkan data baru ke layar
                st = new StringTokenizer(data, ",");
                st.nextToken();

                System.out.println("\nData baru anda adalah:");
                System.out.println("=====================================");
                System.out.println("Tahun\t\t: " + st.nextToken() + " --> " + tempData[0]);
                System.out.println("Penulis\t\t: " + st.nextToken() + " --> " + tempData[1]);
                System.out.println("Penerbit\t: " + st.nextToken() + " --> " + tempData[2]);
                System.out.println("Judul\t\t: " + st.nextToken() + " --> " + tempData[3]);

                boolean isUpdate = DaniUtility.DaniYaAtauTidak("apakah anda yakin ingin mengupdate data tersebut");

                if (isUpdate) {
                    // cek data baru di database
                    boolean isExist = DaniUtility.cekBukuDiDatabase(tempData, false);

                    if (isExist) {
                        System.err.println("data buku sudah ada di database, proses update dibatalkan, \nsilahkan delete data yang bersangkutan");
                        // copy data
                        bufferedOutput.write(data);
                    } else {
                        // format data baru kedalam database
                        String tahun = tempData[0];
                        String penulis = tempData[1];
                        String penerbit = tempData[2];
                        String judul = tempData[3];

                        // kita bikin primary key
                        Long nomorEntry = DaniUtility.ambilEntryPerTahun(penulis, tahun) + 1;
                        String punulisTanpaSpasi = penulis.replaceAll("\\s+", "");
                        String primaryKey = punulisTanpaSpasi + "_" + tahun + "_" + nomorEntry;

                        // tulis data ke database
                        bufferedOutput.write(primaryKey + "," + tahun + "," + penulis + "," + penerbit + "," + judul);
                    }
                } else {
                    // copy data
                    bufferedOutput.write(data);
                }
            } else {
                // copy data
                bufferedOutput.write(data);
            }

            bufferedOutput.newLine();
            data = bufferedInput.readLine();
        }

        // tutup data ke file
        bufferedOutput.flush();
        DaniFileInput.close();
        DaniFileOutput.close();

        // delete original file
        database.delete();
        // rename file sementara ke database
        tempDB.renameTo(database);
    }

    public static void DaniHapusData() throws IOException {
        // kita ambil database original
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // kita buat database sementara
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // tampilkan data
        System.out.println("List Buku");
        DaniTampilkanData();

        // kita ambil user input untuk mendelete data
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor buku yang akan dihapus: ");
        int deleteNum = terminalInput.nextInt();

        // looping untuk membaca tiap data baris dan skip data yang akan didelete
        boolean isFound = false;
        int entryCounts = 0;

        String data = bufferedInput.readLine();

        while (data != null) {
            entryCounts++;
            boolean isDelete = false;

            StringTokenizer st = new StringTokenizer(data, ",");

            // tampilkan data yang ingin di hapus
            if (deleteNum == entryCounts) {
                System.out.println("\nData yang ingin anda hapus adalah:");
                System.out.println("=====================================");
                System.out.println("Referensi\t: " + st.nextToken());
                System.out.println("Tahun\t\t: " + st.nextToken());
                System.out.println("Penulis\t\t: " + st.nextToken());
                System.out.println("Penerbit\t: " + st.nextToken());
                System.out.println("Judul\t\t: " + st.nextToken());
                
                isDelete = DaniUtility.DaniYaAtauTidak("Apakah anda yakin akan menghapus?");
                isFound = true;
            }

            if (!isDelete) {
                // kita pindahkan data dari original ke sementara
                bufferedOutput.write(data);
                bufferedOutput.newLine();
            } else {
                // skip pindahkan data dari original ke sementara
                System.out.println("Data berhasil dihapus");
            }

            data = bufferedInput.readLine();
        }

        if (!isFound) {
            System.err.println("Buku tidak ditemukan");
        }

        // menulis data ke file
        bufferedOutput.flush();
        fileInput.close();
        fileOutput.close();

        // delete original file
        if (database.delete()) {
            System.out.println("File: " + database.getName() + " berhasil dihapus");
        } else {
            System.out.println("File: " + database.getName() + " gagal dihapus");
        }
        
        // rename file sementara ke database
        if (tempDB.renameTo(database)) {
            System.out.println("File: " + database.getName() + " berhasil di rename");
        } else {
            System.out.println("File: " + database.getName() + " gagal di rename");
        }
    }
}