package main;
public class Loop_to_check {
public class CheckCode {
public void main (String [] args) {
int[] a = new int[100];
int[] b = new int[100];
int[] c = new int[100];
<<<<<<< HEAD
int[][] x = new int[100][100];
int[][] y = new int[100][100];
int[][] z = new int[100][100];
=======
>>>>>>> edeff119fa0155cb874184482df439cea2589858




<<<<<<< HEAD
  for (int i = 0; i < 100; i+=3){
    a[i] = 1;
  }
  for (int i = 0; i < 10; i+=3){
    b[i+1] = b[i+3];
  }
  for (int i = 0; i < 100; i+=3){
    b[i] = 2;
  }                   
  for (int i = 0; i < 10; i+=3){
    b[i] = 2;
  }
=======
for(int i = 0; i<10; i++){
a[i] = a[i] + 3;
b[i] = a[i+2] +5;
a[i] = c[i+10] + 8;

}
>>>>>>> edeff119fa0155cb874184482df439cea2589858




}
}
}
