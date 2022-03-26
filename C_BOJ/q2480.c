#include <stdio.h>

int main(){


    int input1 = 0;
    int input2 = 0;
    int input3 = 0;

    scanf("%d %d %d", &input1,  &input2,  &input3);

    if(input1 == input2 && input1 == input3){
        printf("%d", input1*1000 + 10000);
        return 0;
    }




    if(input1 == input2){
        printf("%d", 1000 + input1 * 100);
        return 0;
    }
    if(input1 == input3){
        printf("%d", 1000 + input1 * 100);
        return 0;
    }

    if(input2 == input3){
        printf("%d", 1000 + input2 * 100);
        return 0;
    }




    if(input1 >= input2 && input1 >= input3 ){
        printf("%d",  input1 * 100);
        return 0;
    }

    if(input2 >= input1 && input2 >= input3 ){
        printf("%d",  input2 * 100);
        return 0;
    }

    if(input3 >= input2 && input3 >= input1 ){
        printf("%d",  input3 * 100);
        return 0;
    }

    return 0;
}