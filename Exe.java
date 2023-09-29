/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author filho
 */
import java.util.Scanner;

public class Exe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc =new Scanner(System.in);
        AVL x=new AVL();
        int z=80;
        while(z!=0){
            z=sc.nextInt();
            if(z!=0)
                x.inserirAVL(z);
        }
        x.preOrdem();
        //x.inOrdem();
    }
    
}
