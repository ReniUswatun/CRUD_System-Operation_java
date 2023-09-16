/*
 * Nama : Reni Uswatun Hasanah
 * Nim : L0122136
 */

/*
 * Ini Merupakan program CRUD menggunakan Array List tentang komputer, 
 * yang lebih tepatnya sistem operasi yang ada di komputer.
 * Code ini terinspirasi dari Task Manager.
 * Yang mana hanya menggunakan penggunaan CPU dan Memory.
 * Proses sistem operasi ini tidak boleh lebih dari 100%
 * jadi jika lebih dari 100% maka hapus proses menggunakan index
 */

// untuk mengimport beberapa package yang digunakan
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//class utama dalam program ini
public class PPBO_01_L0122136 {
    // untuk ke console
    public static final int MAX = 60;
    // untuk tanda atau batas berapa space yang digunakan

    // untuk print line biasa, sebenernya nggak terlalu berguna
    // tapi bisa membuat tampilan rapih aja
    private static void printLine() {
        for (int i = 0; i < MAX; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    // untuk print menu
    // ini gunanya biar nggak numpuk di main aja
    private static void printMenu() {
        printLine();
        String[] Menu = {
                "Tambah Sistem Operasi",
                "Tambah Sistem Operasi Random",
                "Kurang Sistem Operasi",
                "Edit Sistem Operasi",
                "Keluar Program"
        };
        int index = 1;
        for (String loop : Menu) {
            System.out.println(index + ". " + loop);
            index++;
        }
        printLine();

    }

    public static void main(String[] args) {
        ArrayList<SystemOperation> computer = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        // infinity loop sampai user memencet menu keluar program
        // atau ctrl + c
        while (true) {
            displayData(computer);
            // ini buat menghitung total
            int cpu = countCPU(computer);
            int memory = countMemory(computer);
            printLine();
            System.out.printf("|  Total                                | %6d%% | %6d%% |\n", cpu, memory);
            // ini kalo misalkan sum dari memory dan sum dari cpu itu melebihi 100 %
            if (cpu < 0 || cpu > 100 || memory < 0 || memory > 100) {
                System.out.println(
                        "Data Tidak Bisa Ditambahkan Persentase CPU dan Memory harus antara 0% hingga 100% dan totalnya tidak boleh melebihi 100%. Harus ada System Operasi yang dihapus");
                removeSystemOperation(computer, keyboard);
                System.out.println("Data Berhasil di Proses");

            } else {
                printMenu();
                System.out.print("Pilih Menu => ");
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                switch (choice) {
                    case 1:
                        addSystemOperation(computer, keyboard);
                        System.out.println("Data Berhasil di Proses");
                        break;
                    case 2:
                        addRandomSystemOperation(computer, keyboard);
                        System.out.println("Data Berhasil di Proses");
                        break;
                    case 3:
                        removeSystemOperation(computer, keyboard);
                        System.out.println("Data Berhasil di Proses");
                        break;
                    case 4:
                        editSystemOperation(computer, keyboard);
                        System.out.println("Data Berhasil di Proses");
                        break;
                    case 5:
                        keyboard.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan Tidak Valid");
                        break;

                }
            }

        }

    }

    private static void displayData(ArrayList<SystemOperation> computer) {
        printLine();
        System.out.println("|                 Data Semua Sistem Operasi                 |");
        printLine();
        System.out.printf("|No. |     Nama Proses     | Id Proses  |   CPU   |  Memory |\n");
        printLine();
        int index = 1;
        if (computer.isEmpty()) {
            System.out.println("Tidak Ada Proses");
        } else {
            // looping satu satu data yang ada di dalam computer
            for (SystemOperation loops : computer) {
                loops.printData(index);
                for (int i = 0; i < MAX; i++) {
                    System.out.print("-");
                }
                System.out.println();
                index++;
            }
        }
    }

    private static void addSystemOperation(ArrayList<SystemOperation> computer, Scanner keyboard) {
        // memasukkan data secara manual
        Random random = new Random();
        System.out.print("Masukkan Nama Proses => ");
        String processName = keyboard.nextLine();
        int processId = random.nextInt(1000000);
        System.out.print("Masukkan Persentase CPU (0% - 100%) => ");
        int cpu = keyboard.nextInt();
        System.out.print("Masukkan Persentase Memory (0% - 100%) => ");
        int memory = keyboard.nextInt();
        if (cpu < 0 || cpu > 100 || memory < 0 || memory > 100 || (cpu + memory) > 100) {
            System.out.println(
                    "Data Tidak Bisa Ditambahkan Persentase CPU dan Memory harus antara 0% hingga 100% dan totalnya tidak boleh melebihi 100%.");
        } else
            computer.add(new SystemOperation(processName, processId, cpu, memory));

    }

    private static void addRandomSystemOperation(ArrayList<SystemOperation> computer, Scanner keyboard) {
        // memasukkan data secara random
        String[] proccesName = { "Windows", "Ms.Word", "Ms.Excel", "Ms. Power Point", "Chrome", "XAMPP", "Whatsapp",
                "Spotify", "Vs Code", "Oracle", "OBS", "Paint", "Discord", "DBeaver", "NotedPad", "Terminal" };
        Random random = new Random();
        String processName = proccesName[random.nextInt(15)];
        int processId = random.nextInt(1000000);
        int cpu = random.nextInt(21);
        int memory = random.nextInt(21);
        if (cpu < 0 || cpu > 100 || memory < 0 || memory > 100 || (cpu + memory) > 100) {
            System.out.println(
                    "Data Tidak Bisa Ditambahkan Persentase CPU dan Memory harus antara 0% hingga 100% dan totalnya tidak boleh melebihi 100%.");
        } else
            computer.add(new SystemOperation(processName, processId, cpu, memory));

    }

    private static void removeSystemOperation(ArrayList<SystemOperation> computer, Scanner keyboard) {
        // menghapus data yang mana berdasarkan indeks
        System.out.print("Pilih nomor sistem operasi yang akan dihapus => ");
        int indexToRemove = keyboard.nextInt() - 1;
        if (indexToRemove >= 0 && indexToRemove < computer.size()) {
            computer.remove(indexToRemove);
        } else {
            System.out.println("Nomor sistem operasi tidak valid.");
        }
    }

    private static void editSystemOperation(ArrayList<SystemOperation> computer, Scanner keyboard) {
        // edit berdasarkan indeks
        System.out.print("Pilih nomor sistem operasi yang akan diedit => ");
        int indexToEdit = keyboard.nextInt() - 1;
        if (indexToEdit >= 0 && indexToEdit < computer.size()) {
            // get disini untuk mengambil data berapa berdasarkan indeks untuk di edit
            SystemOperation systemOperation = computer.get(indexToEdit);
            // artiny udah dapet nih tempat yang mau di editnya
            System.out.println("Masukkan Proses");
            System.out.println("1. Tambah CPU");
            System.out.println("2. Kurang CPU");
            System.out.println("3. Tambah Memory");
            System.out.println("4. Kurang Memory");
            System.out.print("Masukkan Pilihan => ");
            // buat memilih ingin data yang mana di editnya
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    // jika tambah CPU maka set CPU sekarang (yang mau ditambah) dengan get CPU yang
                    // lalu di tambahkan masukkan CPU yang baru
                    System.out.print("Masukkan jumlah persentase CPU yang akan ditambahkan => ");
                    int cpuToAdd = keyboard.nextInt();
                    systemOperation.setCPU(systemOperation.getCPU() + cpuToAdd);
                    break;
                case 2:
                    // jika kurang CPU maka set CPU sekarang (yang mau dikurang) dengan get CPU yang
                    // lalu di kurangi masukkan CPU yang baru
                    System.out.print("Masukkan jumlah persentase CPU yang akan dikurangkan => ");
                    int cpuToSubtract = keyboard.nextInt();
                    systemOperation.setCPU(systemOperation.getCPU() - cpuToSubtract);
                    break;
                case 3:
                    // sama seperti yang atas
                    System.out.print("Masukkan jumlah persentase Memory yang akan ditambahkan => ");
                    int memoryToAdd = keyboard.nextInt();
                    systemOperation.setMemory(systemOperation.getMemory() + memoryToAdd);
                    break;
                case 4:
                    // sama seperti yang atas
                    System.out.print("Masukkan jumlah persentase Memory yang akan dikurangkan => ");
                    int memoryToSubtract = keyboard.nextInt();
                    systemOperation.setMemory(systemOperation.getMemory() - memoryToSubtract);
                    break;
                default:
                    System.out.println("Aksi Memory tidak valid.");
                    break;
            }
        } else
            System.out.println("Nomor sistem operasi tidak valid.");

    }

    // mengitung total CPU di komputer
    private static int countCPU(ArrayList<SystemOperation> computer) {
        int cpu = 0;
        for (SystemOperation loops : computer) {
            cpu += loops.getCPU();
        }
        return cpu;
    }

    // menghitung total Memory di komputer
    private static int countMemory(ArrayList<SystemOperation> computer) {
        int memory = 0;
        for (SystemOperation loops : computer) {
            memory += loops.getMemory();
        }
        return memory;
    }
}

class SystemOperation {
    // sebagai struct dalam menyimpan data
    private final String Processes;
    private int IdProcesses;
    private int CPU, Memory;

    // untuk memasukkan data
    public SystemOperation(String Processes, int IdProcesses, int CPU, int Memory) {
        this.Processes = Processes;
        this.IdProcesses = IdProcesses;
        this.CPU = CPU;
        this.Memory = Memory;
    }

    // digunakan ketika update
    public void setCPU(int cPU) {
        CPU = cPU;
    }

    // digunakan ketika update
    public void setMemory(int memory) {
        Memory = memory;
    }

    // digunakan untuk mengambil int sekarang sebelum di update
    public int getCPU() {
        return CPU;
    }

    // digunakan untuk mengambil int sekarang sebelum di update
    public int getMemory() {
        return Memory;
    }

    // print data berbentuk tabel
    public void printData(int index) {
        System.out.printf("| %d. | %-19s | %-10d | %6d%% | %6d%% |\n", index, this.Processes, this.IdProcesses,
                this.CPU,
                this.Memory);
    }
}
