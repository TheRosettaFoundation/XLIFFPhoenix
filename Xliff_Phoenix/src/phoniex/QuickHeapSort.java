/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import java.util.List;
import org.jdom.Element;
import lmc_builder.phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */
 public class QuickHeapSort  {
	private List strings;
	private int number;

	public void sort(List transUnitList) {
		this.strings = transUnitList;
		number = transUnitList.size();
		quicksort(0, number - 1);
	}

	private void quicksort(int low, int high) {
            if(high-low>32){
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		String pivot = ((Element)strings.get((low + high) / 2)).getChildText("source",XliffElement.xliff);

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (((Element)strings.get(i)).getChildText("source",XliffElement.xliff).compareTo(pivot)<=0) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (((Element)strings.get(j)).getChildText("source",XliffElement.xliff).compareTo(pivot)>0) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
            }
            else{
                for( int i = number / 2; i >= 0; i-- )  /* buildHeap */
                    percDown(i, number );
                for( int i = number - 1; i > 0; i-- )
                {
                    exchange(0,i);            /* deleteMax */
                    percDown(0, i );
                }
            }


            }
	

	private void exchange(int i, int j) {
		Object temp = strings.get(i);
		strings.set(i, strings.get(j));
		strings.set(j, temp);
	}
         /**
     * Internal method for heapsort.
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private int leftChild( int i )
    {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in
     * deleteMax and buildHeap.
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private void percDown( int i, int n )
    {
        int child;
        Element tmp;

        for( tmp = ((Element)strings.get(i)); leftChild( i ) < n; i = child )
        {
            child = leftChild( i );
            if( child != n - 1 && ((Element)strings.get(child)).getChildText("source",XliffElement.xliff).compareTo( ((Element)strings.get(child+1)).getChildText("source",XliffElement.xliff) ) < 0 )
                child++;
            if( tmp.getChildText("source",XliffElement.xliff).compareTo( ((Element)strings.get(child)).getChildText("source",XliffElement.xliff) ) < 0 )
                strings.set(i, ((Element)strings.get(child)));
            else
                break;
        }
        strings.set(i,tmp);
    }


    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */

    }
