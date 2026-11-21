package it.unicam.cs.mpgc.rpg129034;

public class Combattimento {

    //Metodo esclusivo per questa classe. Controlla negli altri metodi se i valori passati sono nulli o meno
    private void controllaEntita(Entita e1, Entita e2){
        if(e1 == null || e2 == null){
            throw new IllegalArgumentException("le entita non possono essere nulle");
        }
    }



    //Calcola il danno inflitto in base all'attacco dell'entità attaccante e alla difesa dell'entità difensore, con un minimo di 1 punto vita
    public int calcolaDanno(Entita attaccante, Entita difensore){
        controllaEntita(attaccante, difensore);

        int danno = attaccante.getAttacco() - difensore.getDifesa();

        return Math.max(1, danno);
    }



    //Esegue un turno di combattimento, facendo prima attaccare l'eroe, poi il nemico
    public void eseguiTurno(Eroe eroe, Nemico nemico){
        controllaEntita(eroe, nemico);

        if(eroe.sconfitta() || nemico.sconfitta()){
            return;
        }

        int dannoEroe = calcolaDanno(eroe, nemico);
        nemico.subisciDanno(dannoEroe);

        if(!nemico.sconfitta()){
            int dannoNemico = calcolaDanno(nemico, eroe);
            eroe.subisciDanno(dannoNemico);
        }


    }

}
