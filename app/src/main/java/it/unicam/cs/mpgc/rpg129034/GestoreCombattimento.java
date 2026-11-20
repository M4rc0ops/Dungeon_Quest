package it.unicam.cs.mpgc.rpg129034;

import java.util.List;

public class GestoreCombattimento {

    private final Combattimento combattimento;
    private final Eroe eroe;
    private Stanza stanzaCorrente;
    private Nemico nemicoCorrente;
    private boolean combattimentoAttivo;



    public GestoreCombattimento(Eroe eroe){
        if(eroe == null){
            throw new IllegalArgumentException("l'eroe non può essere nullo");
        }
        this.eroe = eroe;
        this.combattimento = new Combattimento();
        this.combattimentoAttivo = false;
    }



    public Nemico avviaCombattimento(Stanza stanza){
        if(stanza == null || stanza.isCompletata() ){
            return null;
        }

        List<Nemico> nemici = stanza.getNemici();

        if(nemici.isEmpty()){
            return null;
        }


        for(Nemico nemico : nemici){
            if(!nemico.sconfitta()){
                this.stanzaCorrente = stanza;
                this.nemicoCorrente = nemico;
                this.combattimentoAttivo = true;

                return nemico;
            }
        }
        return null;
    }



    public EsitoCombattimento eseguiTurnoCombattimento(){
        if(!combattimentoAttivo || nemicoCorrente == null){
            return EsitoCombattimento.IN_CORSO;
        }

        combattimento.eseguiTurno(eroe,nemicoCorrente);

        if(eroe.sconfitta()){
            combattimentoAttivo = false;
            return EsitoCombattimento.EROE_SCONFITTO;
        }

        if(nemicoCorrente.sconfitta()){
            nemicoCorrente = null;


            List<Nemico> nemici = stanzaCorrente.getNemici();

            for(Nemico nemico : nemici){
                if(!nemico.sconfitta()){
                    nemicoCorrente = nemico;
                    return EsitoCombattimento.NEMICO_SCONFITTO;
                }
            }

            boolean stanzaCompletata = !stanzaCorrente.isCompletata() && stanzaCorrente.tuttiNemiciSconfitti();

            if(stanzaCompletata){
                stanzaCorrente.completa();
                eroe.recuperaVitaStanza();
            }
            combattimentoAttivo = false;
            return EsitoCombattimento.STANZA_COMPLETATA;
        }

        return EsitoCombattimento.IN_CORSO;

    }



    public Nemico getNemicoCorrente(){
        return nemicoCorrente;
    }



    public boolean isCombattimentoAttivo(){
        return combattimentoAttivo;
    }



}
