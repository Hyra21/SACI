/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateTieneSelloPK;
import alucintech.entidad.TieneselloPK;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeTieneSelloPK {
    
    private final DelegateTieneSelloPK delegateTieneSelloPK;

    public FacadeTieneSelloPK() {
        this.delegateTieneSelloPK = new DelegateTieneSelloPK();
    }
}