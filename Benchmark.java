/**
 * relationsort benchmark
 *
 * @author legendofa
 */
public class Benchmark {
	public static void main(String[] args) {

		Sorter sorter = new Sorter();

		long[] cycles = new long[2000];

		for (int i = 0; i < cycles.length; i++) {
			int[] array = sorter.array_generation_distinct(100);
			long start = System.nanoTime();
			sorter.relation_sort(array);
			long nanoseconds = System.nanoTime() - start;
			cycles[i] = nanoseconds;
			System.out.println(i + 1 + ". array is in order");
		}

		long avg_runtime = 0;
		for (int i = 0; i < cycles.length; i++) {
			avg_runtime = avg_runtime + cycles[i];
		}
		avg_runtime = avg_runtime / cycles.length;

		System.out.println("\nSorting the array took: " + avg_runtime + " nanoseconds on average.");
		System.out.println("\nThe algorithm sorted " + cycles.length + " arrays.\n");
	}
}