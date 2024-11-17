import java.io.*;
import java.util.*;

public class BubbleSortvsMerge {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // random number between 0 and 100
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int number : array) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        // Convert list to array
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void mergeSort( int[] arr){
        mergeSort(arr, 0, arr.length);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (end - start <= 1)
            return;
        int middle = (start + end) / 2;
        mergeSort(arr, start, middle);
        mergeSort(arr, middle, end);
        merge(arr, start, middle, end);
    }

    public static void merge(int[] arr, int start, int middle, int end){
        int i=start, j=middle, k=0;
        int[] tempArr = new int[end-start];
        while(i < middle && j < end) {
            if (arr[i] <= arr[j]){
                tempArr[k] = arr[i];
                i++;
            } else {
                tempArr[k] = arr[j];
                j++;
            }
            k++;
        }

        while(i < middle){
            tempArr[k] = arr[i];
            i++;
            k++;
        }

        while(j < end) {
            tempArr[k] = arr[j];
            j++;
            k++;
        }

        for (i=start; i<end; i++){
            arr[i]= tempArr[i-start];
        }







    }


    // Main method 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                System.out.print("Enter the length of the array: ");
                int length = scanner.nextInt();
                System.out.print("Enter the filename to store the array: ");
                String outputFilename = scanner.next();
                int[] randomArray = createRandomArray(length);
                writeArrayToFile(randomArray, outputFilename);
                System.out.println("Random array has been written to " + outputFilename);
                

                System.out.print("Enter the filename to read the array from: ");
                String inputFilename = scanner.next();
                System.out.print("Enter the filename to store the Bubble sorted array: ");
                String sortedFilename1 = scanner.next();
                System.out.print("Enter the filename to store the Merge sorted array: ");
                String sortedFilename2 = scanner.next();

                int[] array1 = readFileToArray(inputFilename);
                long start1 = System.currentTimeMillis();
                bubbleSort(array1);
                long end1 = System.currentTimeMillis();
                writeArrayToFile(array1, sortedFilename1);

                int[] array2 = readFileToArray(inputFilename);
                long start2 = System.currentTimeMillis();
                mergeSort(array2);
                long end2 = System.currentTimeMillis();
                writeArrayToFile(array2, sortedFilename2);

                System.out.println("The sorted arrays have been written");
                System.out.println("It took Bubble sort: " + (end1 - start1) + "ms");
                System.out.println("It took Merge sort: " + (end2 - start2) + "ms");

        scanner.close();
    }
}

