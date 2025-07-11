#include <stdio.h>

int main()
{	
	while( 1 ) {
		printf("enter while loop.1 is true\n");
		break;
	}
	while( -1 ) {
		printf("enter while loop.-1 is true\n");
		break;
	}
	while( 0 ) {
	}
	printf("not enter while loop. 0 is false.\n");		
	return 0;
}
