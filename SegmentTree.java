package MyDoing;
/**
 * This Class represents SegmentTree Data Structure which is used to deter minte
 * the range sum in logarithmic time bound
 * 
 * @author subhojit.mukherjee
 * 
 */
public class SegmentTree {

	int getMid(int start, int end) {
		return start + (end - start) / 2;
	}

	/**
	 * Thsi method creates the segment tree from the source array
	 * 
	 * @param arr
	 *            source array
	 * @param ss
	 *            current start Index of actual array
	 * @param se
	 *            current end Index of actual array
	 * @param segArr
	 *            Segment Tree Array
	 * @param si
	 *            segment tree index
	 * @return
	 */
	int constructSegTreeUtil(int arr[], int ss, int se, int segArr[], int si) {
		// if there is only one element in source array
		if (ss == se) {
			segArr[si] = arr[ss];
			// System.out.println("ss:" + ss + " se:" + se + " si:" + si + " "
			// + segArr[si]);
			return arr[ss];
		}
		int mid = getMid(ss, se);

		segArr[si] = constructSegTreeUtil(arr, ss, mid, segArr, si * 2 + 1)
				+ constructSegTreeUtil(arr, mid + 1, se, segArr, si * 2 + 2);
		return segArr[si];
	}

	/**
	 * This method creates the memory for Segment array with 2*2^(height of
	 * tree)-1 height of tree is (base2)log n where n is number of elements
	 * 
	 * @param arr
	 *            Source Array
	 * @return
	 */
	int[] constructSegmentTree(int arr[]) {
		int length = arr.length;
		int x = (int) Math.ceil((Math.log(length) / Math.log(2)));
		int max_size = 2 * (int) Math.pow(2, x) - 1;// Height of segment tree
		// System.out.println("Tree:" + length + " Max_siz:" + max_size);
		int[] newArr = new int[max_size];
		constructSegTreeUtil(arr, 0, length - 1, newArr, 0);
		return newArr;

	}

	/**
	 * 
	 * @param segArr
	 *            Segment array
	 * @param ss
	 *            current start Index of actual array
	 * @param se
	 *            current end Index of actual array
	 * @param qs
	 *            Start index of query
	 * @param qe
	 *            End Index of Query
	 * @param index
	 *            current segment Array index
	 * @return
	 */
	int getSum(int segArr[], int ss, int se, int qs, int qe, int index) {
		// System.out.println("ss:" + ss + " se:" + se + " " + index);
		if (qs <= ss && qe >= se) {
			/*
			 * System.out .println("HIss:" + ss + " se:" + se + " " +
			 * segArr[index]);
			 */
			return segArr[index];
		}
		if (se < qs || ss > qe)
			return 0;
		int mid = getMid(ss, se);
		return getSum(segArr, ss, mid, qs, qe, 2 * index + 1)
				+ getSum(segArr, mid + 1, se, qs, qe, 2 * index + 2);
	}

	int getSumValidator(int segArr[], int n, int startRange, int endRange) {
		int length = segArr.length;
		if (startRange < 0 || endRange > length - 1 || startRange > endRange) {
			System.out.println("Invalid Input");
			return -1;
		}
		return getSum(segArr, 0, n, startRange, endRange, 0);

	}

	void updateValueUtil(int segArr[], int ss, int se, int i, int diff,
			int index) {
		// Base Case: If the input index lies outside the range of this segment
		if (i < ss || i > se)
			return;
		segArr[index] = segArr[index] + diff;
		if (se != ss) {
			int mid = getMid(ss, se);
			updateValueUtil(segArr, ss, mid, i, diff, 2 * index + 1);
			updateValueUtil(segArr, mid + 1, se, i, diff, 2 * index + 2);
		}
	}

	void updateValue(int arr[], int segArr[], int i, int new_val) {
		int length = arr.length;
		if (i < 0 || i > length - 1) {
			System.out.println("Invalid Input");
			return;
		}
		// Get the difference between new value and old value
		int diff = new_val - arr[i];
		// Update the value in array
		arr[i] = new_val;
		updateValueUtil(segArr, 0, length - 1, i, diff, 0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 1, 3, 5, 7, 9, 11 };
		SegmentTree st = new SegmentTree();
		int segArr[] = st.constructSegmentTree(arr);
		// System.out.println(Arrays.toString(segArr));
		System.out.println(st.getSumValidator(segArr, arr.length - 1, 1, 3));
		st.updateValue(arr, segArr, 1, 10);
		System.out.println(st.getSumValidator(segArr, arr.length - 1, 1, 3));

	}

}
