public class Arraysort{

	int[] array = new int[1000];

	public int[] ArrayGeneration(){
		for(int i=0; i<array.length; i++){
			array[i] = (int)(Math.random()*2*(array.length-1)-(array.length));
		}
		return array;
	}

	public int[] ArrayGenerationDistinct(){
		for(int i=0; i<array.length; i++){
			array[i] = i+1;
		}
		for(int a=0; a<array.length; a++){
			Swap(a, (int)(Math.random()*(array.length-1)));
		}
		return array;
	}

	public void Swap(int first_index, int second_index){
		int tmp = array[first_index];
		array[first_index] = array[second_index];
		array[second_index] = tmp;
	}

	public int[] Algorithm(){ //Sorting algorithm that is effective for ascending numbers that are out of order
		int max_index = array[0];
		int min_index = array[0];
		for(int j=1; j<array.length; j++){ //Search for min and max values
			if(array[j]<min_index){
				min_index = array[j];
			}
			if(array[j]>max_index){
				max_index = array[j];
			}
		}
		for(int t=0; t<array.length; t++){ //Change min value to zero and all other values accordingly
			array[t] = array[t] - min_index + 1;
		}
		max_index = max_index - min_index + 1;
		int[] sorting_array = new int[array.length];
		for(int i=0; i<array.length; i++){ //Calculate new relational positions in array
			float calculate_position = (array.length-1)*(float)array[i]/max_index; //Calculate new index proportional to the value
			int position = (int)calculate_position;
			int position_key = position;
			while(sorting_array[position]!=0){ //Increment or decrement index to find a spot for the value
				position++;
				if(position>=array.length){
					position = position_key;
					while(sorting_array[position]!=0){
						position--;
					}
					break;
				}
			}
			sorting_array[position] = array[i];
		}
		array = sorting_array;
		for(int t=0; t<array.length; t++){ //Run Insertion Sort
			array[t] = array[t] + min_index - 1;
		}
		return array;
	}

	public static void main(String[] args){
		Arraysort C = new Arraysort();

		long[] cycles = new long[1000];

		for(int i=0; i<cycles.length; i++){
			C.ArrayGenerationDistinct();
			long start = System.nanoTime();
			C.Algorithm();
			long nanoseconds = System.nanoTime() - start;
			cycles[i] = nanoseconds;
			System.out.println(i+1+". array is in order");
		}

		long avg_runtime = 0;
		//System.out.print("\nTime in nanoseconds for each run: \n");
		for(int i=0; i<cycles.length; i++){
			avg_runtime = avg_runtime + cycles[i];
			//System.out.print(cycles[i]+";");
		}
		avg_runtime = avg_runtime / cycles.length;

		System.out.println("\nSorting the array took: "+avg_runtime+" nanoseconds on average.");
		System.out.println("\nThe algorithm sorted "+cycles.length+" arrays.\n");

	}
}
