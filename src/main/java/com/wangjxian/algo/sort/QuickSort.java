package com.wangjxian.algo.sort;

/**
 * <p>
 *
 * </p>
 *
 * @author wangjiaxian
 * @since 2021/5/8
 */
public class QuickSort extends AbstractSort{

  public static void main(String[] args) {
    int[] numbers = {11,8,3,9,7,1,2,5};
    new QuickSort().quickSort(numbers);
    show(numbers);
  }


  public void quickSort(int[] numbers){
    int length = numbers.length ;
    if (length == 1){
      return ;
    }
    quickSort(numbers,0,length-1);
  }


  private void quickSort(int[] numbers,int p ,int q){
      if (p >= q){
        return;
      }
      //寻找中间点
      int pivot = partition(numbers, p, q);
      //分成两个区
      //排序比pivot小的左边
      quickSort(numbers,0,pivot-1);
      //排序比pivot大的右边
      quickSort(numbers,pivot+1,q);
  }

  //将比pivot小的放到左边,将比pivot大的放到右边
  private int partition(int[] numbers, int p, int q) {
    int pivot = numbers[q];
    int i = p;
    for (int j = i; j < q; j++) {
      if (numbers[j] < pivot){
        swap(numbers,i,j);
        i++;
      }
    }
    swap(numbers,i,q);
    return i;
  }

  //交换位置
  private void swap(int[] numbers, int i, int j) {
    int tmp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = tmp;
  }


}
