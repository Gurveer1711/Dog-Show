package ca.sheridancollege.sin11368.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.sheridancollege.sin11368.repositories.DogRepository;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dogs implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 903734912276508776L;

	DogRepository d = new DogRepository();
	
	private int E_num;
	private String name;
	private String o_name;
	private String gender;
	private String speciality;
	private String breed;
	
}
