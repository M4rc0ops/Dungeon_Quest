package it.unicam.cs.mpgc.rpg129034;

public abstract class Ambiente {

    private String nome;
    private int coordinataX;
    private int coordinataY;
    private int larghezza;
    private int altezza;


    public Ambiente(String nome, int coordinataX, int coordinataY, int larghezza, int altezza) {
        if(nome==null || nome.isBlank()){
            throw new IllegalArgumentException("il nome dell'ambiente non può essere vuoto");
        }

        if(coordinataX < 0 || coordinataY < 0){
            throw new IllegalArgumentException("le coordinate non possono essere negative");
        }

        if(larghezza<=0 || altezza<=0){
            throw new IllegalArgumentException("le dimensioni devono essere positive");
        }
        this.nome = nome;
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
        this.larghezza = larghezza;
        this.altezza = altezza;
    }



    public String getNome() {
        return nome;
    }
    public int getCoordinataX() {
        return coordinataX;
    }
    public int getCoordinataY() {
        return coordinataY;
    }
    public int getLarghezza() {
        return larghezza;
    }
    public int getAltezza() {
        return altezza;
    }


}
