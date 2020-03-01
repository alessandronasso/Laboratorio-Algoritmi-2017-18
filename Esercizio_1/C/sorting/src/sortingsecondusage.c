#include "sorting.h"
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

void load_array(long** array, int* size, const char* file_name);
int compare_long(void* ptr1, void* ptr2);
long* new_int(long value);

int main(int argc, char const *argv[]) {
  if(argc < 2) {
    printf("Usage: sortingsecondusage <file_name_array_a> <file_name_array_b>\n");
    exit(EXIT_FAILURE);
  }
  long* array_a = NULL;
  long* array_n = NULL;
  int size_a = 0, size_n = 0, status = 0;
  load_array(&array_a, &size_a, argv[1]);
  load_array(&array_n, &size_n, argv[2]);

  merge_sort((void**)array_a, 0, size_a, compare_long);

  clock_t start_time = clock();

  for(int j = 0; j < size_n+1; j++){
    printf("Number %li, has found correspondence:\n", *(long*)array_n[j]);

    status = find_sum((long**)array_a, *(long*)array_n[j], 0, size_a);
    if(status == 1) printf("true\n");
    else printf("false\n");
  }
  
  double elapsed_time = (clock() - start_time)/(double)CLOCKS_PER_SEC;
  printf("Completed in %.5f seconds\n", elapsed_time);
  free(array_a);
  free(array_n);
  return 0;
}

void load_array(long** array, int* size, const char* file_name) {
  size_t n = 0, lines = 0;
  char *buf = 0;
  int i = 0;
  FILE* fp = fopen(file_name, "r");
  if(fp == NULL){
    fprintf(stderr,"Error: unable to open the file\n");
    exit(EXIT_FAILURE);
  }

  while (getline(&buf, &n, fp) != -1) lines++;
  printf("Total numbers in the file: %zu\n", lines);
  *size = lines-1;

  *array = (long *)malloc(lines*sizeof(long));

  char* buf1 = (char *)malloc(sizeof(long));
  size_t n1 = sizeof(long);
  rewind(fp);

  while (getline(&buf1, &n1, fp) != -1) {
    (*array)[i] = (long)new_int((long)atol(buf1));
    i++;
  }
  free(buf1);
}

long* new_int(long value) {
  long* result = (long*) malloc(sizeof(long));
  *result = value;
  return result;
}

int compare_long(void* ptr1, void* ptr2) {
  long i1 = *(long*)ptr1;
  long i2 = *(long*)ptr2;
  if(i1<i2) {
    return -1;
  }
  if(i1 == i2) {
    return 0;
  }
  return 1;
} 