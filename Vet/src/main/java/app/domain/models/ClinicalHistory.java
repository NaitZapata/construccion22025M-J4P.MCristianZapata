/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor

public class ClinicalHistory {
	private Timestamp dateCreated;
    private char veterinarian;
    private char reason;
    private char diagnosis;
    private char procedure;
    private char medicine;
    private char dose;
    private long orderId;
    private char vaccination;
    private char alergies;
    private char producerDetail;
    private char cancellation;
    
	public ClinicalHistory(Timestamp dateCreated, char veterinarian, char reason, char diagnosis, char procedure, char medicine,
						   char dose, long orderId, char vaccination, char alergies, char producerDetail, char cancellation) {
		super();
		this.dateCreated = dateCreated;
		this.veterinarian = veterinarian;
		this.reason = reason;
		this.diagnosis = diagnosis;
		this.procedure = procedure;
		this.medicine = medicine;
		this.dose = dose;
		this.orderId = orderId;
		this.vaccination = vaccination;
		this.alergies = alergies;
		this.producerDetail = producerDetail;
		this.cancellation = cancellation;
	}
}
