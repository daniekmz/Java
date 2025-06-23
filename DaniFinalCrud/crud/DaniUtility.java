//	* Nama Lengkap Anda:Muhamad Ramdani
//	* NIM: 202422027
//	* Kelompok: Kelas Sore

package crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DaniUtility {

    static long ambilEntryPerTahun(String penulis, String tahun) throws IOException {
        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        long entry = 0;
        String data = bufferInput.readLine();
        Scanner dataScanner;
        String primaryKey;

        while (data != null) {
            dataScanner = new Scanner(data);
            dataScanner.useDelimiter(",");

            primaryKey = dataScanner.next();
            dataScanner = new Scanner(primaryKey);
            dataScanner.useDelimiter("_");

            penulis = penulis.replaceAll("\\s+", "");

            if (penulis.equalsIgnoreCase(dataScanner.next()) && tahun.equalsIgnoreCase(dataScanner.next())) {
                entry = dataScanner.nextInt();
            }

            data = bufferInput.readLine();
        }
        fileInput.close();
        bufferInput.close();

        return entry;
    }

    protected static String DaniAmbilTahun() throws IOException {
        boolean tahunValid = false;
        Scanner terminalInput = new Scanner(System.in);
        String tahunInput = terminalInput.nextLine();
        
        while (!tahunValid) {
            try {
                Year.parse(tahunInput);
                tahunValid = true;
            } catch (Exception e) {
                System.out.println("Format tahun yang anda masukan salah, format=(YYYY)");
                System.out.print("silahkan masukan tahun terbit lagi: ");
                tahunValid = false;
                tahunInput = terminalInput.nextLine();
            }
        }

        return tahunInput;
    }

    static boolean cekBukuDiDatabase(String[] kataKunci, boolean isDisplay) throws IOException {
        FileReader DaniFileInput = new FileReader("database.txt");
        BufferedReader DaniBufferInput = new BufferedReader(DaniFileInput);

        String data = DaniBufferInput.readLine();
        boolean isExist = false;
        int nomorData = 0;

        if (isDisplay) {
            System.out.println("\n| No |\tTahun |\tPenulis\t\t|\tPenerbit\t|\tJudul Buku");
            System.out.println("================================================================================");
        }

        while (data != null) {
            // cek kataKunci didalam baris
            isExist = true;

            for (String keyword : kataKunci) {
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }

            // jika kataKuncinya cocok maka tampilkan
            if (isExist) {
                if (isDisplay) {
                    nomorData++;

                    StringTokenizer stringToken = new StringTokenizer(data, ",");
                    stringToken.nextToken();
                    System.out.printf("| %2d", nomorData);
                    System.out.printf("|\t%4s\t", stringToken.nextToken());
                    System.out.printf("|\t%-20s", stringToken.nextToken());
                    System.out.printf("|\t%-20s", stringToken.nextToken());
                    System.out.printf("|\t%s", stringToken.nextToken());
                    System.out.print("\n");
                } else {
                    break;
                }
            }

            data = DaniBufferInput.readLine();
        }

        if (isDisplay) {
            System.out.println("================================================================================");
        }

        DaniFileInput.close();
        DaniBufferInput.close();

        return isExist;
    }

    public static boolean DaniYaAtauTidak(String DaniPesan) {
        Scanner DaniMenu = new Scanner(System.in);
        System.out.print("\n" + DaniPesan + " (y/t) ? ");

        String DaniPilihan = DaniMenu.next();

        if (!DaniPilihan.equalsIgnoreCase("y") && !DaniPilihan.equalsIgnoreCase("t")) {
            while (!DaniPilihan.equalsIgnoreCase("y") && !DaniPilihan.equalsIgnoreCase("t")) {
                System.err.println("Pilihan Anda Bukan y Atau t");
                System.out.print("\n" + DaniPesan + " (y/t) ? ");
                DaniPilihan = DaniMenu.next();
            }
        } else {
            System.out.println("Dibuat oleh Muhamad Ramdani");
        }

        return DaniPilihan.equalsIgnoreCase("y");
    }

    public static void DaniHapusLayar() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("Tidak Bisa Hapus Layar");
        }
    }
}