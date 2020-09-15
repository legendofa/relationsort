/**
 * Class for sorting shuffled arrays
 *
 * @author legendofa
 */
public class Sorter {

	/**
	 * Generates random array with a certain length
	 */
	public int[] array_generation(int length) {
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 2 * (array.length - 1) - (array.length));
		}
		return array;
	}

	/**
	 * Generates ascending but shuffled array with a certain length
	 */
	public int[] array_generation_distinct(int length) {
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		for (int a = 0; a < array.length; a++) {
			swap(array, a, (int) (Math.random() * (array.length - 1)));
		}
		return array;
	}

	/**
	 * Swaps two indices in an array
	 */
	public int[] swap(int[] array, int first_index, int second_index) {
		int temp = array[first_index];
		array[first_index] = array[second_index];
		array[second_index] = temp;
		return array;
	}

	/**
	 * Sorting algorithm that is effective for ascending numbers that are out of
	 * order
	 */
	public int[] relation_sort(int[] array) {
		int max_index = max_index(array);
		int min_index = min_index(array);
		shift_indices(array, min_index);
		max_index = max_index - min_index + 1;
		calculate_new_positions(array, max_index);
		insertion_sort(array);
		return array;
	}

	/**
	 * O(n) best case sorting algorithm
	 */
	public int[] insertion_sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int j = i - 1;
			int key = array[i];
			while (j >= 0 && key < array[j]) {
				swap(array, j, j + 1);
				j--;
			}
		}
		return array;
	}

	/**
	 * Searches for the minimum value
	 */
	private int min_index(int[] array) {
		int min_index = array[0];
		for (int j = 1; j < array.length; j++) {
			if (array[j] < min_index) {
				min_index = array[j];
			}
		}
		return min_index;
	}

	/**
	 * Searches for the maximum value
	 */
	private int max_index(int[] array) {
		int max_index = array[0];
		for (int j = 1; j < array.length; j++) {
			if (array[j] > max_index) {
				max_index = array[j];
			}
		}
		return max_index;
	}

	/**
	 * Shifts all values by the minimum index
	 */
	private int[] shift_indices(int[] array, int min_index) {
		for (int t = 0; t < array.length; t++) {
			array[t] = array[t] - min_index + 1;
		}
		return array;
	}

	/**
	 * Calculates new relational positions in array. Calculates new index
	 * proportional to the value. Increments or decrements index to find a spot for
	 * the value
	 */
	private int[] calculate_new_positions(int[] array, int max_index) {
		int[] sorting_array = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			float calculated_position = (array.length - 1) * (float) array[i] / max_index;
			int position = (int) calculated_position;
			int position_key = position;
			while (sorting_array[position] != 0) {
				position++;
				if (position >= array.length) {
					position = position_key;
					while (sorting_array[position] != 0) {
						position--;
					}
					break;
				}
			}
			sorting_array[position] = array[i];
		}
		return sorting_array;
	}
}
