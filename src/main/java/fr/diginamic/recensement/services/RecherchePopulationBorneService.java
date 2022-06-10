package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exception.RecherchePopulationBusinessException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws RecherchePopulationBusinessException {

		int min;
		int max;

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		try {
			min = Integer.parseInt(saisieMin);
		} catch (NumberFormatException numberFormatException) {
			throw new RecherchePopulationBusinessException("la population minimum ne doit contenir uniquement des chiffres");
		}
		if (min < 0 ) {
			throw new RecherchePopulationBusinessException("le min doit être supérieur à 0");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		try {
			max = Integer.parseInt(saisieMax);
		} catch (NumberFormatException numberFormatException) {
			throw new RecherchePopulationBusinessException("la population maximum ne doit contenir uniquement des chiffres");
		}
		if (max < 0) {
			throw new RecherchePopulationBusinessException("le max doit être supérieur à 0");
		}
		if(min > max) {
			throw new RecherchePopulationBusinessException("le max doit être supérieur au min");
		}

		min = Integer.parseInt(saisieMin) * 1000;
		max = Integer.parseInt(saisieMax) * 1000;

		boolean isDepartementExistant = false;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				isDepartementExistant = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		if(!isDepartementExistant) {
			throw new RecherchePopulationBusinessException(String.format("Le département %s n'existe pas !", choix));
		}
	}

}
