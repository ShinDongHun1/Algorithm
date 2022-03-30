#include <stdio.h>

int main()
{
    int size = 0;
    scanf("%d", &size);


    int arr[size];


    int ele = 0;


    for(int i = 0; i< sizeof(arr)/sizeof(int); i++){
        scanf("%d", &ele);
        arr[i] = ele;

    }


    int min = 1000001;
    int max = -1000001;


    for(int i = 0; i< size; i++){
        if(min > arr[i]){
            min = arr[i];
        }
        if(max < arr[i]){
            max = arr[i];
        }

    }

    printf("%d %d\n", min, max);


    return 0;
}
