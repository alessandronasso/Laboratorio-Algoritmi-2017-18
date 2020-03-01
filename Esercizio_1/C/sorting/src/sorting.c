#include "sorting.h"

void swap(void** a, void** b){
  void* tmp;
  tmp = *a;
  *a = *b;
  *b = tmp;
}

void insertion_sort(void** array, int size, CompFunction compare){
	int i, j;
	void* key;
	for(i = 1; i<size; i++){
		key = array[i];
		for(j = i-1; j>=0 && compare(array[j],key)>=0;j--){
			swap(&array[j+1],&array[j]);
		}
		array[j+1] = key;
	}
}

void merge(void** array, int low, int centre, int sup, CompFunction compare, void** tmp_array){
	int index_1 = low;
	int index_2 = centre + 1;
	int index_tmp = 0;
	int size = sup - low +1;
	memset(tmp_array, 0, size);
	while((index_1 <= centre) && (index_2 <= sup)){
		if(compare(array[index_1], array[index_2]) <= 0){
			tmp_array[index_tmp] = array[index_1];
			index_1++;
			index_tmp++;
		}else{
			tmp_array[index_tmp] = array[index_2];
			index_2++;
			index_tmp++;
		}
	}
	while(index_1 <= centre){
		tmp_array[index_tmp] = array[index_1];
		index_1++;
		index_tmp++;
	}
	while(index_2 <= sup){
		tmp_array[index_tmp] = array[index_2];
		index_2++;
		index_tmp++;
	}
	index_tmp = 0;
	int i;
	for(i=low; i<=sup; i++){
		array[i] = tmp_array[index_tmp];
		index_tmp++;
	}
	free(tmp_array);
}

void merge_sort(void** array, int low, int sup, CompFunction compare){
	int centre = low + (sup-low)/2;
	if(low < sup){
		void** tmp_array = (void**) malloc(sizeof(void*) * (sup-low+1));
		merge_sort(array, low, centre, compare);
		merge_sort(array, centre+1, sup, compare);
		merge(array, low, centre, sup, compare, tmp_array);
	}	
}

int find_sum(long** array, long n, int i, int j){
  int status = -1, left = i, right = j;
  while (left < right) {
    long n1 = (long)*(long*)array[left];
    long n2 = (long)*(long*)array[right];

    if ((n1+n2) == n)
      return 1;
    else if ((n1+n2) < n) left++;
    else right--;
  }
  return status;
}
