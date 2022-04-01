#include <stdio.h>

int pivot(int n);

int main(int argc, char const *argv[])
{
    
    int n;

    scanf("%d", &n);

    printf("%d\n", pivot(n));


    return 0;
}


int pivot(int n){
    if(n == 0 || n == 1) return n;
    if(n==2) return 1;

    return pivot(n-2) + pivot(n-1);
}