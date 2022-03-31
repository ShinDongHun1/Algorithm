#include <stdio.h>
#include <string.h>

int main()
{
    int count = 0;

    char str[101];


    scanf("%s", str);

    int i =0;
    for ( i = 0; i < strlen(str); i++)
    {
        char temp;
        switch (str[i])
        {
        case 'c':
            temp = str[i+1];
            if(temp == '=' || temp == '-'){
                i++;
            }
            break;

        case 'd':
            temp = str[i+1];
            if(temp == 'z' && str[i+2] == '='){
                i++;
                i++;
            }
            else if (temp == '-')
            {
                i++;
            }
            
            break;

        case 'l':
            temp = str[i+1];
            if(temp == 'j'){
                i++;
            }
            break;
        case 'n':
            temp = str[i+1];
            if(temp == 'j'){
                i++;
            }
            break;
        case 's':
            temp = str[i+1];
            if(temp == '='){
                i++;
            }
            break;      
        case 'z':
            temp = str[i+1];
            if(temp == '='){
                i++;
            }
            break;      

        default:
            break;
        }
        count++;
    }
    printf("%d\n",count);
    
    
    return 0;
}
