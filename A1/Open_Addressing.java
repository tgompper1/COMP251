import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }

     /**Implements the hash function g(k)*/
     public int probe(int key, int i) {
            return (int)((((int)((A*key)% Math.pow(2,w)) >> (w-r))+i) % Math.pow(2,r));
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            int collision = 0;
            int i = 0;
            while(Table[probe(key,i)] >= 0){
                if((i > 0 && probe(key,i) == probe(key,0)) || i == m){
                    return collision;
                }
                collision++;
                i++;
            }
            Table[probe(key,i)] = key;
            return collision;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            int collision = 0;
            int i = 0;
            while((Table[probe(key,i)] != key && i <= m) ){
                collision++;
                if(Table[probe(key,i)] == -1){
                    return collision;
                }
                i++;
                if(i==m) {
                    return i;
                }
            }
            Table[probe(key, i)] = -2;
            return collision;
        }
}
