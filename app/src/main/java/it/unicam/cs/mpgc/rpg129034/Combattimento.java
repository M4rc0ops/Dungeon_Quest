package it.unicam.cs.mpgc.rpg129034;

public class Combattimento {

    private void controllaEntita(Entita e1, Entita e2){
        if(e1 == null || e2 == null){
            throw new IllegalArgumentException("le entita non possono essere nulle");
        }
    }



    public int calcolaDanno(Entita attaccante, Entita difensore){
        controllaEntita(attaccante, difensore);

        int danno = attaccante.getAttacco() - difensore.getDifesa();

        return Math.max(1, danno);
    }



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
