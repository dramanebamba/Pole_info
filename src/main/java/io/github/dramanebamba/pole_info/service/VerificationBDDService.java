package main.java.io.github.dramanebamba.pole_info.service;

import javax.enterprise.context.ApplicationScoped;
import main.java.io.github.dramanebamba.pole_info.model.*;

@ApplicationScoped
public class VerificationBDDService 
{
	public boolean test(String mail)
	{
		System.out.println("TEST CDI");
		for(Personne personne:Personne.getBDD())
			if(personne.getEmail().equals(mail))	return false;
		return true;
	}

}