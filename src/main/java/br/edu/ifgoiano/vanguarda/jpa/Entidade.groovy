/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifgoiano.vanguarda.jpa

import javax.persistence.*
import javax.persistence.MappedSuperclass

/**
 *
 * @author strike
 */
@MappedSuperclass
class Entidade implements Serializable {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id
    
    boolean isPersistido(){
        return id
    }
}

