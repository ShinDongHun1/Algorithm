#include <stdio.h>

int multifly(int a, int b);

int main()
{
    int a,b;

    scanf("%d", &a);
    scanf("%d", &b);

    int c = b%10;
    int d = b/10 % 10;
    int e = b/100;


    printf("%d\n", multifly(c,a));
    printf("%d\n", multifly(d,a));
    printf("%d\n", multifly(e,a));
    printf("%d\n", multifly(b,a));
    return 0;
}
int multifly(int a, int b){
    return a * b;
}
