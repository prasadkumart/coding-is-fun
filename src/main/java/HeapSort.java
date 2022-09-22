import java.util.Arrays;

//O(NlonN) T
//O(longN) S
//https://www.youtube.com/watch?v=cuL8gXCSA58&list=PLEJXowNB4kPyP2PdMhOUlTY6GrRIITx28&index=4&ab_channel=TECHDOSE
public class HeapSort {
    int[] arr;
    int heapSize;
    HeapSort(int[] arr) {
        this.arr = arr;
        heapSize = arr.length;
        //build maxheap (heapify only internal nodes) - O(N)
        for (int i = heapSize/2 - 1; i>=0; i--) {
            heapify(arr, i, heapSize);
        }
        //extract max
        for (int i=heapSize-1; i>0; i--) { //N
            swapVals(0, i);
            //heapify root with the size of current internal nodes
            heapify(arr, 0, i); //O(lonN)
        }
        System.out.println("Ascending:");
        Arrays.stream(arr).forEach(System.out::println);

        //build minHeap (heapify only internal nodes) - O(N)
        for (int i = heapSize/2 - 1; i>=0; i--) {
            minHeapify(arr, i, heapSize);
        }
        for (int i=heapSize-1; i>0; i--) { //N
            swapVals(0, i);
            minHeapify(arr, 0, i); //O(lonN)
        }
        System.out.println("Descending:");
        Arrays.stream(arr).forEach(System.out::println);
    }

    void buildMaxHeap() {
        for (int i = heapSize /2 - 1; i>=0; i--) {
            heapify(arr, i, heapSize);
        }
    }


    void heapify(int[] arr, int index, int size) {
        int left = 2*index + 1;
        int right = 2*index + 2;
        int largest = index;
        //not root and left > parent
        if (left < size && arr[left] > arr[index]) {
            largest = left;
        }
        //not root and right > parent
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        // change in largest value
        if (largest != index) {
            swapVals(index, largest);
            heapify(arr, largest, size);
        }
    }

    void minHeapify(int[] arr, int index, int size) {
        int left = 2*index + 1;
        int right = 2*index + 2;
        int smallest = index;
        //not root and left > parent
        if (left < size && arr[left] < arr[index]) {
            smallest = left;
        }
        //not root and right > parent
        if (right < size && arr[right] < arr[smallest]) {
            smallest = right;
        }
        // change in largest value
        if (smallest != index) {
            swapVals(index, smallest);
            minHeapify(arr, smallest, size);
        }
    }

    int extractMax(int[] arr) throws Exception {
        if (heapSize < 1) {
            throw new Exception("Invalid date");
        }

        //first element is max
        int max = arr[0];
        //copy last to root
        arr[0] = arr[heapSize -1];
        //reduce heap size
        heapSize--;

        //heapify root (arr[0]) //percolate DOWN
        heapify(arr, 0, heapSize);

        return max;
    }

    void swapVals(int index, int largest) {
        int temp = arr[largest];
        arr[largest] = arr[index];
        arr[index] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {6,8,9,2,1,4,3};

        //build maxHeap using heapify algo
        new HeapSort(arr);
    }

}
