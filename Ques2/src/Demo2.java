import java.util.Arrays;
import java.util.List;

public class Demo2 {

	public static void main(String[] args) {
		// create a list of integers
		List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 3);

		// demonstration of reduce method
		int even = numbers.stream().filter(x -> x % 2 == 0)
				.reduce(0, (ans, i) -> ans + i);

		System.out.println(even);
	}

}
