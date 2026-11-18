package it.unicam.cs.mpgc.rpg129034;

public abstract class Entita {

    private String nome;
    private int puntiVita;
    private int maxVita;
    private int attacco;
    private int difesa;



    public Entita(String nome, int puntiVita, int attacco, int difesa){
        if(nome==null || nome.isBlank()){
            throw new IllegalArgumentException("il nome non può essere nullo o vuoto");
        }
        if(puntiVita<=0){
            throw new IllegalArgumentException("i punti vita devono essere maggiori di zero");
        }
        if(attacco<=0){
            throw new IllegalArgumentException("l'attacco deve essere maggiore di zero");
        }
        if(difesa<0){
            throw new IllegalArgumentException("la difesa deve essere maggiore o uguale a zero");
        }
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.maxVita = puntiVita;
        this.attacco = attacco;
        this.difesa = difesa;
    }



    public String getNome() {
        return nome;
    }
    public int getPuntiVita() {
        return puntiVita;
    }
    public int getMaxVita() {
        return maxVita;
    }
    public int getAttacco() {
        return attacco;
    }
    public int getDifesa() {
        return difesa;
    }



    public void impostaVita(int puntiVita){
        if(puntiVita<0 || puntiVita>maxVita){
            throw new IllegalArgumentException("punti vita non validi");
        }
        this.puntiVita = puntiVita;
    }



    public void subisciDanno(int danno){
        if(danno<0){
            throw new IllegalArgumentException("il danno non può essere negativo");
        }

        puntiVita-=danno;
        if(puntiVita<0){
            puntiVita=0;
        }
    }



    public boolean sconfitta(){
        return puntiVita == 0;
    }



    protected void curaPuntiVita(int puntiVita){
        if(puntiVita<0){
            throw new IllegalArgumentException("i punti vita non devono essere negativi");
        }
        this.puntiVita= Math.max(0, Math.min(puntiVita, maxVita));
    }

}
