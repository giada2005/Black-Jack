package main.model;

import java.util.List;
public interface PlayerStrategy {

	    /**
	     * L'interfaccia determina se il giocatore vuole chiedere
	     * un'altra carta basandosi sulla sua mano attuale.
	     * 
	     * <p>
	     * Questo metodo implementa la logica decisionale della strategia di gioco.
	     * Diverse implementazioni di questa interfaccia possono fornire diverse
	     * strategie
	     * per decidere quando chiedere un'altra carta.
	     * </p>
	     *
	     * @param hand Le carte attualmente nella mano del giocatore.
	     * @return true se il giocatore vuole un'altra carta, false altrimenti.
	     */
	     static boolean wantsToHit(List<Card> hand) {
			
			return false;
		}
			
		}
	

