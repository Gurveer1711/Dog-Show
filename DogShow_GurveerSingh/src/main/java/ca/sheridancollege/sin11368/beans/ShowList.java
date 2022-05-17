package ca.sheridancollege.sin11368.beans;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014640679435199286L;
	
	private long total;
	private String breed;
	private long n_male;
	private long n_female;
	private long y_male;
	private long y_female;
}
