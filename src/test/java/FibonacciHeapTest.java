import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FibonacciHeapTest extends AbstractTest {

  int[] input;
  int expected;
  Runnable task = new Runnable() {
    @Override
    public void run() {
      FibonacciHeap heap = new FibonacciHeap();

      for (int num : input) {
        heap.insert(num);
      }

      FibNode minNode = heap.getMinNode();
      if (minNode == null) {
        Common.fail("getMinNode() returns null.");
      }
      int actual = minNode.getKey();

      if (actual != expected) {
        String str = minNode.printNode(0);
        Common.fail(String.valueOf(actual) + str);
      }
    }
  };

  public FibonacciHeapTest(int[] input, int expected) {
    super("FibonacciHeap", "FibNode");
    this.input = input;
    this.expected = expected;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {new int[]{1, 2}, 1},
        {new int[]{5, 2, 3}, 2}, // returns minKey() after minNode was extracted
        {new int[]{100, 2, 3}, 2},
        {new int[]{10, 20, 3, 4, 0, 9}, 0},
    });
  }

  @Override
  protected Runnable getTask() {
    return task;
  }

  @Override
  protected String lastInput() {
    return new StringBuilder()
        .append("\n")
        .append("Input: insert() each ")
        .append(Common.printArray(input))
        .append("\nExpected: ")
        .append(expected)
        .toString();

  }
}
