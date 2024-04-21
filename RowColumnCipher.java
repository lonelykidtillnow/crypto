import java.util.*;

public class Ex2 {

static char[][] matrix; static char[][] dmatrix; static int[] pos;

static int posindex;

public static int find(int[] a, int t){ for (int i = 0; i < a.length; i++){

if (a[i] == t) posindex = i;

}

return posindex;

}

public static void txtgen(String txt){ txt = txt.toUpperCase();

int i,j,count=1;

int[] ncpos = new int[txt.length()];

pos = new int[txt.length()]; for(i=0;i<ncpos.length;i++){

char c = txt.charAt(i); int cpos = (int)c; ncpos[i] = cpos%65;

}

int max = ncpos[0]; for(i=1;i<ncpos.length;i++){

if(ncpos[i] > max){ max = ncpos[i];

}

}

for(i=0;i<=max;i++){ for(j=0;j<ncpos.length;j++){

if(i == ncpos[j]){ pos[j] = count++;

}

}

}

System.out.println("Number Count array"); for(i=0;i<ncpos.length;i++){

System.out.print(" "+pos[i]);

}

}

public static void ekeygen(String txt,String key){ int i,j,index=0;

int ltxt = txt.length(); int lkey = key.length();

int row = (int)Math.ceil((ltxt/lkey)+1); matrix = new char[row][lkey]; for(i=0;i<row;i++){

for(j=0;j<lkey;j++){ if(index < ltxt){

matrix[i][j] = txt.charAt(index);

index++;

}

else{

matrix[i][j] = 'x'; index++;

}

}

}

System.out.println("\nGenerated Key Matrix"); for(i=0;i<row;i++,System.out.println("")){

for(j=0;j<lkey;j++){ System.out.print(" "+matrix[i][j]);

}

}

}

public static void encrypt(String txt, String key){ txtgen(key);

ekeygen(txt,key); int i,j,k;

String str = new String();; System.out.println("Cipher Text : "); for(i=1;i<=pos.length;i++){

int index = find(pos,i); k = index;

for(j=0;j<matrix.length;j++){ str += matrix[j][k];

}

}

System.out.print(" "+str.toUpperCase());

}

public static void decrypt(String txt, String key){ txtgen(key);

int i,j,k,x=0;

String str ="";

int ltxt = txt.length(); int lkey = key.length();

int row = (int)Math.ceil((ltxt/lkey)); dmatrix = new char[row][lkey]; for(i=1;i<=pos.length;i++){

int index = find(pos,i); k=index; for(j=0;j<row;j++){

dmatrix[j][k] = txt.charAt(x); x++;

}

}

System.out.println("\nGenerated Key Matrix : "); for(i=0;i<row;i++,System.out.println()){

for(j=0;j<pos.length;j++){ System.out.print(" "+dmatrix[i][j]); str += dmatrix[i][j];

}

}

System.out.println("Plain Text : "); System.out.print(str);

}

public static void main(String args[]){ Scanner sc = new Scanner(System.in); int ch,dch;

String key=""; String txt=""; do{

System.out.println(" 1. Encryption\n 2.Decryption\n Enter your Choice:");

ch = sc.nextInt(); switch(ch){

case 1:{

System.out.println("Enter the Plain Text : "); txt = sc.next();

System.out.println("Enter the Key"); key = sc.next();

encrypt(txt,key); break;

}

case 2:{

System.out.println("Enter the Cipher Text : "); txt = sc.next();

System.out.println("Enter the Key"); key = sc.next();

decrypt(txt,key); break;

}

default:{

System.out.println("Invalid Choice!!!"); break;

}

}

System.out.println("\nPress 1 to Continue : "); dch = sc.nextInt();

}while(dch!=0);

sc.close();

}

}