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
public class AVL {
    private No raiz;
    private int cont=0;
    private boolean h=false;
    private No iniciarNo(int valor,No novo){
        novo=new No();
        novo.setBal(0);
        novo.setValor(valor);
        cont++;
        novo.setDir(null);
        novo.setPai(null);
        novo.setEsq(null);
        return novo;
    }
    public void inserirAVL(int valor){
        inserirAVL(raiz,raiz,valor);
    }
    private void inserirAVL(No pt,No ptu,int valor){
        if(ptu==null){
            if(cont==0){
                raiz=iniciarNo(valor,raiz);
            }else if(pt.getValor()<valor){
                pt.setDir(iniciarNo(valor,ptu));
                pt.getDir().setPai(pt);
                h=true;
            }else{
                pt.setEsq(iniciarNo(valor,ptu));
                pt.getEsq().setPai(pt);
                h=true;
            }
        }else if(valor==ptu.getValor()){
            System.out.println("valor ja existente");
        }else if(valor<ptu.getValor()){
            inserirAVL(ptu,ptu.getEsq(), valor);
            if(h){
                if(ptu.getBal()==1){
                    ptu.setBal(0);
                    h=false;
                }else if(ptu.getBal()==0){
                    ptu.setBal(-1);
                    
                }else if(ptu.getBal()==-1){
                    No aux=ptu.getEsq();
                    if(aux.getBal()==1){
                        System.out.println("RDD");
                        RDD(ptu);
                        calBal();
                    }else{
                        System.out.println("RD");
                        RD(ptu);
                        ptu.setBal(0);
                        aux.setBal(0);
                    }
                    h=false;
                }
                
            }
        }else{
            inserirAVL(ptu,ptu.getDir(), valor);
            if(h){
                if(ptu.getBal()==-1){
                    ptu.setBal(0);
                    h=false;
                }else if(ptu.getBal()==0){
                    ptu.setBal(1);
                }else if(ptu.getBal()==1){
                    No aux=ptu.getDir();
                    if(aux.getBal()==1){
                        RE(ptu);
                        ptu.setBal(0);
                        aux.setBal(0);
          //              System.out.println("RE");
                    }else{
                        System.out.println("RDE");
                        RDE(ptu);
                        calBal();
                    }
                    h=false;
                }
            }
        }
    }
    
    public void RD(No ptu){
        No aux=ptu.getEsq();
        ptu.setEsq(aux.getDir());
        aux.setDir(ptu);
        if(ptu.getPai()==null){
            raiz=aux;
            aux.setPai(null);
        }else{
            if(ptu==ptu.getPai().getEsq()){
                ptu.getPai().setEsq(aux);
                aux.setPai(ptu.getPai());
            }else{
                ptu.getPai().setDir(aux);
                aux.setPai(ptu.getPai());
            }
        }
        if(ptu.getEsq()!=null){
            ptu.getEsq().setPai(ptu);
        }
        ptu.setPai(aux);
    }
    
    public void RE(No ptu){
        No aux=ptu.getDir();
        ptu.setDir(aux.getEsq());
        aux.setEsq(ptu);
        if(ptu.getPai()==null){
            raiz=aux;
            aux.setPai(null);
        }else{
            if(ptu==ptu.getPai().getEsq()){
                ptu.getPai().setEsq(aux);
                aux.setPai(ptu.getPai());
            }else{
                ptu.getPai().setDir(aux);
                aux.setPai(ptu.getPai());
            }
        }
        if(ptu.getDir()!=null){
            ptu.getDir().setPai(ptu);
        }
        ptu.setPai(aux);
    }
    
    public void RDD(No ptu){
        No aux=ptu.getEsq();
        RE(aux);
        RD(ptu);
    }
    
    
    public void RDE(No ptu){
        No aux=ptu.getDir();
        RD(aux);
        RE(ptu);
        
    }
    public int buscar(int valor){
        if(busca(valor)!=null){
            return valor;
        }else{
            return -1;
        }
    }
    private No busca(int valor){
        No aux=raiz;
        for (int i = 0; i < cont; i++) {
            if(aux==null){
                aux=null;
                break;
            }else if(aux.getValor()==valor){
                break;
            }else{
                if(valor<aux.getValor()){
                    aux=aux.getEsq();
                }else{
                    aux=aux.getDir();
                }
            }
        }
        return aux;
    }
    private void inOrdem(No n){
        if(n!=null){
            if(n.getEsq()!=null){
                inOrdem(n.getEsq());
            }
            System.out.println(n.getValor());
            if(n.getDir()!=null){
                inOrdem(n.getDir());
            }
        }
    }
    public void inOrdem(){
        inOrdem(raiz);
    }
    public void preOrdem(){
        preOrdem(raiz);
    }
    private void preOrdem(No n){
        if(n!=null){
            if(n.getPai()==null){
            //    System.out.print("null <-pai ");
            }else{
              //  System.out.print(n.getPai().getValor()+" <-pai ");
            }
            //System.out.print(n.getBal()+" <-bal ");
            System.out.println(n.getValor());
            if(n.getEsq()!=null){
                preOrdem(n.getEsq());
            }
            
            if(n.getDir()!=null){
                preOrdem(n.getDir());
            }
        }
    }
    private void calBal(){
        calBal(raiz);
    }
    private void calBal(No n){
        int x;
        if(n!=null){
            int z=0;
            if(n.getEsq()!=null){
                z++;
                x=altura(n.getDir())-altura(n.getEsq());
                n.setBal(x);
                calBal(n.getEsq());
            }
            if(n.getDir()!=null){
                z++;
                x=altura(n.getDir())-altura(n.getEsq());
                n.setBal(x);
                calBal(n.getDir());
            }if(z==0){
                n.setBal(0);
            }
        }
    }
    private int altura(No n){
        int x=0;
        if(n!=null){
            int e=0,d=1;
            if(n.getEsq()!=null){
               e=1+altura(n.getEsq());
            }
            if(n.getDir()!=null){
                d=1+altura(n.getDir());
            }
            if(e<=d){
                x=d;
            }else{
                x=e;
            }
        }
        return x;
    }
    public int remover(int valor){
        int x=0,n=0;
        No pt=busca(valor);
        No p=null;
        char nore='a';
        if(pt==null){
            return x;
        }else{
            p=pt.getPai();
            x=pt.getValor();
            if(p!=null){
                if(p.getEsq()==pt){
                    nore='e';
                }else{
                    nore='d';
                }
            }
            if(pt.getEsq()==null){
                moverPai(pt,pt.getDir());
            }else{ 
                if(pt.getDir()==null){
                    moverPai(pt,pt.getEsq());
                }else{
                    No s=sucessor(pt);
                    //System.out.println(s.getValor());
                    s.setBal(pt.getBal());
                    if(pt.getEsq()!=null){
                        pt.getEsq().setPai(s);
                    }
                    if(s!=pt.getDir()){
                        p=s.getPai();
                        nore='e';
                        moverPai(s,s.getDir());
                        if(s.getDir()!=null){
                            s.getDir().setPai(s);
                        }
                    }
                    moverPai(pt,s);
                    s.setEsq(pt.getEsq());
                    s.getEsq().setPai(s);
                    if(s!=pt.getDir()){
                        //System.out.println("aqui");
                        s.setDir(pt.getDir());
                        pt.getDir().setPai(s);
                    }
                }
            }
        }
        if(p!=null){
            balancearAVL(p,nore);
        }
        calBal();
        cont--;
        return x;
    }
    private No sucessor(No pt){
        if(pt.getDir()!=null){
            pt=pt.getDir();
            while(pt.getEsq()!=null){
                pt=pt.getEsq();
            }
        }
        return pt;
    }
    
    private void moverPai(No pt,No ptu){
        No pai=pt.getPai();
        int num=0;
        if(ptu!=null){
            ptu.setPai(pai);
            num++;
        }
        if(pai!=null){
            if(pai.getEsq()==pt){
                pai.setEsq(ptu);
            }else{
                pai.setDir(ptu);
            }
        }else if(num==1){
            ptu.setPai(pai);
            raiz=ptu;
        }
    }
    
    private void balancearAVL(No pt,char nore){
        if(nore=='d'){
            /*switch(pt.getBal()){
                case 1:
                    pt.setBal(0);
                    h=true;
                    break;
                case 0:
                    pt.setBal(-1);
                    h=false;
                    break;
                case -1:
                    remoCaso1(pt);
                    break;
                
            }*/
            if(pt.getBal()==-1){
                remoCaso1(pt);
            }else{
                calBal();
            }
        }else{
            /*switch(pt.getBal()){
                case -1:
                    pt.setBal(0);
                    h=true;
                    break;
                case 0:
                    pt.setBal(1);
                    h=false;
                    break;
                case 1:
                    remoCaso2(pt);
                    break;
            }*/
            if(pt.getBal()==1){
                System.out.println(" aqui "+pt.getValor());
                remoCaso2(pt);
            }else{
                System.out.println("calbal");
                calBal();
            }
        }
        if(h){
            if(pt.getPai()!=null){
                if(pt.getPai().getEsq()==pt){
                    balancearAVL(pt.getPai(),'e');
                }else{
                    balancearAVL(pt.getPai(),'d');
                }
            }
        }
    }
    
    private void remoCaso1(No pt){
        System.out.println("caso 1");
        No ptu=pt.getEsq();
        if(ptu.getBal()<=0){
            RD(pt);
            if(ptu.getBal()==0){
                ptu.setBal(1);
                pt.setBal(-1);
                h=false;
            }else{
                ptu.setBal(0);
                pt.setBal(0);
            }
        }else{
            No ptz=ptu.getDir();
            RDD(pt);
            switch(ptz.getBal()){
                case 1:
                    ptu.setBal(-1);
                    pt.setBal(0);
                    break;
                case 0:
                    ptu.setBal(0);
                    pt.setBal(0);
                    break;
                case -1:
                    ptu.setBal(0);
                    pt.setBal(1);
                    break;
            }
            ptz.setBal(0);
            h=true;
        }
    }
    private void remoCaso2(No pt){
        System.out.println("caso 2");
        No ptu=pt.getDir();
        System.out.println(ptu.getValor());
        System.out.println(ptu.getBal());
        if(ptu.getBal()>0){
            RE(pt);
            //System.out.println(pt.getPai().getPai().getValor());
            System.out.println("re");
            //System.out.println(pt.getPai().getValor());
            if(ptu.getBal()==0){
                ptu.setBal(1);
                pt.setBal(-1);
                h=false;
            }else{
                ptu.setBal(0);
                pt.setBal(0);
                //h=false;
                //calBal();
            }
        }else if(ptu.getBal()==1){
            No ptz=ptu.getEsq();
            RDE(pt);
            switch(ptz.getBal()){
                case 1:
                    ptu.setBal(-1);
                    pt.setBal(0);
                    break;
                case 0:
                    ptu.setBal(0);
                    pt.setBal(0);
                    break;
                case -1:
                    ptu.setBal(0);
                    pt.setBal(1);
                    break;
            }
            ptz.setBal(0);
            h=true;
        }else if(ptu.getBal()==0){
            calBal();
            if(pt.getBal()==2){
                RE(pt);
            }
        }
    }
    public void printCont(){
        System.out.println(cont);
    }
}
