#include <stdio.h>

int sameFunction() {
    return 1;
}
/*
//compile error c
int sameFunction(int x, int y) {
    return x + y;
}
*/

int otherNameFunction(int x, int y) {
    return x + y;
}

int main(void) {
    int num = sameFunction();
    printf("%d\n", num);
    num = otherNameFunction(1, 2);
    printf("%d\n", num);
    return 0;
}


/*
実行方法とコンパイルエラー出力

gcc polymorphism.c -o polymorphism
$ ./polymorphism
1
3



$ gcc polymorphism.c -o polymorphism
polymorphism.c:8:5: error: conflicting types for ‘sameFunction’; have ‘int(int,  int)’
    8 | int sameFunction(int x, int y) {
      |     ^~~~~~~~~~~~
polymorphism.c:3:5: note: previous definition of ‘sameFunction’ with type ‘int(void)’
    3 | int sameFunction() {
      |     ^~~~~~~~~~~~
polymorphism.c: In function ‘main’:
polymorphism.c:17:15: error: too few arguments to function ‘sameFunction’; expected 2, have 0
   17 |     int num = sameFunction();
      |               ^~~~~~~~~~~~
polymorphism.c:8:5: note: declared here
    8 | int sameFunction(int x, int y) {
      |     ^~~~~~~~~~~~

*/
