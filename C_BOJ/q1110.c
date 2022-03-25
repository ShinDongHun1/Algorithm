#include <stdio.h>




int main()
{
    int input = 0;
    scanf("%d", &input);

    int num = input;

    int count =0;
    for (count = 0; (input != num) || count == 0; count++)
    {
        int first = 0;
        int second = 0;

        if(num < 10){
            first = 0;
            second = num;
        }else{

            first = num / 10;
            second = num % 10;
        }
        num = ((first + second)%10) + (second * 10);
    }


    printf("%d\n",count);


    return 0;
}
