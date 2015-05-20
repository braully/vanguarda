package br.edu.ifgoiano.vanguarda.jpa

import javax.persistence.*

@Entity
public class Pessoa extends Entidade {
    
    String nome;
    
    String email;    
   
    String toString(){
        return "Pessoa(${nome}, ${email})"
    }
	
}

