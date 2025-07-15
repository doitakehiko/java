#include "stdio.h"
	int main() {
	double x;
	double s;
	int ch; x = 0.0001;
	s = 10000; for (int i = 0; i < 100000000; i++) {
	s = s + x; 
	}
	printf("1 に 0.0001を100000000回足すと%fになります。\n", s);
}