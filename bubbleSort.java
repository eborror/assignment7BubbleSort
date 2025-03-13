import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class bubbleSort {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean repeat = true;
            int arrayLength, userInput;
            String filename;
            int[] arrayGiven;
            while (repeat) { 
                System.out.println("Enter one of the following");
                System.out.println("1 : generate a file of random integers");
                System.out.println("2 : read and sort an existing file");
                System.out.println("3 : Exit program");
                userInput = sc.nextInt();
                switch (userInput) {
                    case 1 -> {
                        System.out.println("What should the file be named?");
                        filename = sc.next() + ".txt";
                        System.out.println("How long do you want your array?");
                        arrayLength = sc.nextInt();
                        int[] array = createRandomArray(arrayLength);
                        writeArrayToFile(array, filename);
                    }
                    case 2 -> {
                        System.out.println("What is the name of your file?");
                        filename = sc.next() + ".txt";
                        arrayGiven = readFileToArray(filename);
                        bubbleSortFunc(arrayGiven);
                    }
                    case 3 -> {
                        System.out.println("Exiting...");
                        repeat = false;
                    }
                    default -> System.out.println("Error: invalid input. Please try again.");
                }
            }
        }
    }

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int array1[] = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array1[i] = random.nextInt(101);
        }
        return array1;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try {
            File randomArray = new File(filename);
            if (randomArray.createNewFile()) {
                System.out.println("File created: " + filename);
            }
            try (FileWriter writer = new FileWriter(filename)) {
                for (int i = 0; i < array.length; i++) {
                    writer.write(array[i] + "");
                    if (i+1 < array.length) {
                        writer.write("\n");
                    }
                }
                writer.close();
            }
            System.out.println("Wrote to file");
        } catch (IOException e) {
            System.err.println("Error writing to the file");
        }
    }

    public static int[] readFileToArray(String filename) {
        File givenFile = new File(filename);
        int[] givenArray = {0};
        try (Scanner fileReader = new Scanner(givenFile)) {
            int fileLength = 0;
            while (fileReader.hasNextLine()) {
                fileReader.nextLine();
                fileLength++;
            }
            System.out.println("File has " + fileLength + " Lines");
            givenArray = new int[fileLength];
        } catch (Exception e) {
            System.err.println("Error reading the file");
        }
        try (Scanner fileReader = new Scanner(givenFile)) {
            int i = 0;
            while (fileReader.hasNextLine()) {
                givenArray[i] = fileReader.nextInt();
                i++;
            }
            fileReader.close();
        } catch (Exception e) {
            System.err.println("Error reading the file");
        }
        return givenArray;
    }

    public static void bubbleSortFunc(int[] array) {
        int i, j, temp;
        boolean swapped;
        String filename = "SortedArray.txt";
        for (i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
        try {
            File sortedFile = new File(filename);
            if (sortedFile.createNewFile()) {
                System.out.println("File created: " + filename);
            }
            try (FileWriter writer = new FileWriter(filename)) {
                for (int k = 0; k < array.length; k++) {
                    writer.write(array[k] + "");
                    if (k+1 < array.length) {
                        writer.write("\n");
                    }
                }
                writer.close();
            }
            System.out.println("Wrote to file");
        } catch (IOException e) {
            System.err.println("Error writing to the file");
        }
    }
}
