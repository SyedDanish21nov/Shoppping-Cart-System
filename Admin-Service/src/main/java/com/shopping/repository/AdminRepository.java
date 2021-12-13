package com.shopping.repository;

	import org.springframework.data.mongodb.repository.MongoRepository;
	import org.springframework.stereotype.Repository;

	import com.shopping.model.AdminDetails;

	@Repository
	public interface AdminRepository extends MongoRepository<AdminDetails, Integer> {

		AdminDetails findByAdmEmail(String AdmEmail);


}
