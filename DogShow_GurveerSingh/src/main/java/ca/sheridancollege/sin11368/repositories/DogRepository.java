package ca.sheridancollege.sin11368.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sin11368.beans.Breed;
import ca.sheridancollege.sin11368.beans.Dogs;
import ca.sheridancollege.sin11368.beans.ShowList;

@Repository
public class DogRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addDog(Dogs dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO dog_show(name, o_name, breed, gender, speciality) VALUES(:name, :o_name, :breed, :gender, :speciality)";
		parameters.addValue("name", dog.getName());
		parameters.addValue("o_name", dog.getO_name());
		parameters.addValue("breed", dog.getBreed());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("speciality", dog.getSpeciality());
		jdbc.update(query, parameters);
	}
		

	
	public ArrayList<Dogs> getDog(){
		ArrayList<Dogs> dogs = new ArrayList<Dogs>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dog_show";
		List<Map<String, Object>> rows = jdbc.queryForList(query,parameters);
		for(Map<String, Object> row:rows) {
			Dogs d = new Dogs();
			d.setE_num((int)row.get("E_num"));
			d.setName((String)row.get("name"));
			d.setO_name((String)row.get("o_name"));
			d.setBreed((String)row.get("breed"));
			d.setGender((String)row.get("gender"));
			d.setSpeciality((String)row.get("speciality"));
			dogs.add(d);
		}
		return dogs;
	}
	
	public Dogs getDogByE_num(int E_num) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dog_show WHERE E_num=:E_num";
		parameters.addValue("E_num", E_num);
		ArrayList<Dogs> dogs = new ArrayList<Dogs>();
		List<Map<String, Object>> rows = jdbc.queryForList(query,parameters);
		for(Map<String, Object> row:rows) {
			Dogs d = new Dogs();
			d.setE_num((int)row.get("E_num"));
			d.setName((String)row.get("name"));
			d.setO_name((String)row.get("o_name"));
			d.setBreed((String)row.get("breed"));
			d.setGender((String)row.get("gender"));
			d.setSpeciality((String)row.get("speciality"));
			dogs.add(d);
		}
		if(dogs.isEmpty()) {
			return null;
		}else
			return dogs.get(0);
	}
	
	public void editDog(Dogs dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE dog_show SET name=:name, o_name=:o_name, breed=:breed," +
		"gender=:gender, speciality=:speciality WHERE E_num=:E_num";
		parameters.addValue("E_num", dog.getE_num());
		parameters.addValue("name", dog.getName());
		parameters.addValue("o_name", dog.getO_name());
		parameters.addValue("breed", dog.getBreed());
		parameters.addValue("gender", dog.getGender());
		parameters.addValue("speciality", dog.getSpeciality());
		jdbc.update(query, parameters);
		
	}
	
	public void deleteDog(Dogs dog) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM dog_show WHERE E_num=:E_num";
		parameters.addValue("E_num", dog.getE_num());
		jdbc.update(query, parameters);
	}
	
	
	public void addBreed(Breed breed) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO breeds (name) VALUES(:name)";
		parameters.addValue("name", breed.getName());
		jdbc.update(query,parameters);
	}
	
	public ArrayList<Breed> getBreed() {
		ArrayList<Breed> breeds = new ArrayList<Breed>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM breeds";
		List<Map<String, Object>> rows = jdbc.queryForList(query,parameters);
		for(Map<String, Object> row:rows) {
			Breed b = new Breed();
			b.setS_num((int)row.get("s_num"));
			b.setName((String)row.get("name"));
			breeds.add(b);
		}
		return breeds;
	}
	
	public Breed getBreedBys_num(int s_num) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM breeds WHERE s_num=:s_num";
		parameters.addValue("s_num", s_num);
		ArrayList<Breed> breeds = new ArrayList<Breed>();
		List<Map<String, Object>> rows = jdbc.queryForList(query,parameters);
		for(Map<String, Object> row:rows) {
			Breed b = new Breed();
			b.setS_num((int)row.get("s_num"));
			b.setName((String)row.get("name"));
			breeds.add(b);
		}
		if(breeds.isEmpty()) {
			return null;
		}else
			return breeds.get(0);
	}
	
	public void editBreed(Breed breed) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE breeds SET name=:name WHERE s_num=:s_num";
		parameters.addValue("s_num", breed.getS_num());
		parameters.addValue("name", breed.getName());
		jdbc.update(query, parameters);
	}
	
	public void deleteBreed(Breed breed) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM breeds WHERE s_num=:s_num";
		parameters.addValue("s_num", breed.getS_num());
		jdbc.update(query, parameters);
	}
	
	public ArrayList<ShowList> getShowList(){
		ArrayList<ShowList> showList = new ArrayList<ShowList>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT DISTINCT breed FROM dog_show";
		
		List<Map<String, Object>> b_rows = jdbc.queryForList(query,parameters);
		for(Map<String,Object> br:b_rows) {
			ShowList s = new ShowList();
			s.setBreed((String)br.get("breed"));
			
			String query1 = "SELECT breed, COUNT(*) FROM dog_show WHERE breed=:breed AND gender='male' AND speciality='no'";
			String query2 = "SELECT COUNT(*) FROM dog_show WHERE breed=:breed AND gender='female' AND speciality='no'";
			String query3 = "SELECT COUNT(*) FROM dog_show WHERE breed=:breed AND gender='male' AND speciality='yes'";
			String query4 = "SELECT COUNT(*) FROM dog_show WHERE breed=:breed AND gender='female' AND speciality='yes'";
			
			parameters.addValue("breed", s.getBreed());
			
			List<Map<String, Object>> rows = jdbc.queryForList(query1,parameters);
			for(Map<String, Object> row:rows) {
				s.setN_male((long)row.get("COUNT(*)"));
			}
			rows = jdbc.queryForList(query2,parameters);
			for(Map<String,Object> row:rows) {
				s.setN_female((long)row.get("COUNT(*)"));
			}
			rows = jdbc.queryForList(query3,parameters);
			for(Map<String, Object> row:rows) {
				s.setY_male((long)row.get("COUNT(*)"));
			}
			rows = jdbc.queryForList(query4,parameters);
			for(Map<String, Object> row:rows) {
				s.setY_female((long)row.get("COUNT(*)"));
				s.setTotal(s.getN_female()+s.getN_male()+s.getY_female()+s.getY_male());
			}
			showList.add(s);
		}
		return showList;
	}
	
	
}
